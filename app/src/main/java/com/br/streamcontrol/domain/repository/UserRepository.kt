package com.br.streamcontrol.domain.repository

import com.br.streamcontrol.data.model.User

interface UserRepository {
    suspend fun insertUser(user: User)

    suspend fun getUser(): List<User>
}