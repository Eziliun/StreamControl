package com.br.streamcontrol.domain.viewmodel

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.streamcontrol.data.model.User
import com.br.streamcontrol.domain.repository.UserRepositoryImpl
import com.br.streamcontrol.domain.routes.Router
import com.br.streamcontrol.domain.routes.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepositoryImpl
) : ViewModel() {

    private val TAG = HomeViewModel::class.simpleName

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    val emailId: MutableLiveData<String> = MutableLiveData()

    val username: MutableLiveData<String> = MutableLiveData()

    val userPhoto: MutableLiveData<Uri> = MutableLiveData()

    var photo: Uri? = null

    fun saveUser() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e("ERRO Location ", "$throwable")
        }) {
            val user = userPhoto.value?.let { photo ->
                User(
                    email = emailId.value ?: "",
                    name = username.value ?: "",
                    photo = photo.toString(),
                )
            }
            if (user != null) {
                repository.insertUser(user)
            }
        }
    }

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            Log.e("ERRO Location ", "$throwable")
        }) {
            val user = repository.getUser()
            photo = user.last().photo.toUri()
        }
    }

    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                Router.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }

    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
            it.displayName?.also { name ->
                username.value = name
                println(username.value)
                println(name)
            }
            it.photoUrl?.also { photo ->
                userPhoto.value = photo
            }
        }
    }

    fun updateUser() {
        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
            .setPhotoUri(userPhoto.value)
            .setDisplayName(username.value)
            .build()

        FirebaseAuth.getInstance().currentUser?.updateProfile(userProfileChangeRequest)
            ?.addOnSuccessListener {
                if (emailId.value != null) {
                    FirebaseAuth.getInstance().currentUser?.updateEmail(emailId.value ?: "")
                        ?.addOnSuccessListener {
                            // Email update successful
                            // You can perform further actions if needed
                        }
                        ?.addOnFailureListener { e ->
                            // Handle email update failure
                            Log.e(TAG, "Error updating email: ${e.message}")
                        }
                }
            }
            ?.addOnFailureListener { e ->
                // Handle profile update failure
                Log.e(TAG, "Error updating profile: ${e.message}")
            }
        saveUser()
    }

}