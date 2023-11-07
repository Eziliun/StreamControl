package com.br.streamcontrol.domain.rules

import com.br.streamcontrol.domain.rules.enums.EnumAuthErrors

fun handleFirebaseAuthErrorMessage(firebaseErrorMessage: String): String {
    return when (firebaseErrorMessage) {
        "An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]" -> EnumAuthErrors.ERROR_INVALID_CREDENTIAL.message
        "An internal error has occurred. [ CUSTOM_TOKEN_MISMATCH ]" -> EnumAuthErrors.ERROR_CUSTOM_TOKEN_MISMATCH.message
        "An internal error has occurred. [ INVALID_CREDENTIAL ]" -> EnumAuthErrors.ERROR_INVALID_CREDENTIAL.message
        "An internal error has occurred. [ INVALID_EMAIL ]" -> EnumAuthErrors.ERROR_INVALID_EMAIL.message
        "An internal error has occurred. [ USER_MISMATCH ]" -> EnumAuthErrors.ERROR_USER_MISMATCH.message
        "An internal error has occurred. [ REQUIRES_RECENT_LOGIN ]" -> EnumAuthErrors.ERROR_REQUIRES_RECENT_LOGIN.message
        "An internal error has occurred. [ ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL ]" -> EnumAuthErrors.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL.message
        "An internal error has occurred. [ EMAIL_ALREADY_IN_USE ]" -> EnumAuthErrors.ERROR_EMAIL_ALREADY_IN_USE.message
        "An internal error has occurred. [ CREDENTIAL_ALREADY_IN_USE ]" -> EnumAuthErrors.CREDENTIAL_ALREADY_IN_USE.message
        "An internal error has occurred. [ USER_DISABLED ]" -> EnumAuthErrors.ERROR_USER_DISABLED.message
        "An internal error has occurred. [ USER_TOKEN_EXPIRED ]" -> EnumAuthErrors.ERROR_USER_TOKEN_EXPIRED.message
        "An internal error has occurred. [ USER_NOT_FOUND ]" -> EnumAuthErrors.ERROR_USER_NOT_FOUND.message
        "An internal error has occurred. [ INVALID_USER_TOKEN ]" -> EnumAuthErrors.ERROR_INVALID_USER_TOKEN.message
        "An internal error has occurred. [ OPERATION_NOT_ALLOWED ]" -> EnumAuthErrors.ERROR_OPERATION_NOT_ALLOWED.message
        "An internal error has occurred. [ WEAK_PASSWORD ]" -> EnumAuthErrors.ERROR_WEAK_PASSWORD.message

        else -> EnumAuthErrors.UNKNOWN_ERROR.message
    }
}
