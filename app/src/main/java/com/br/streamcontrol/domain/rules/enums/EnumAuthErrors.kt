package com.br.streamcontrol.domain.rules.enums

enum class EnumAuthErrors(val message: String) {
    ERROR_INVALID_CUSTOM_TOKEN("Token personalizado inválido."),
    ERROR_CUSTOM_TOKEN_MISMATCH("Token personalizado não corresponde à ID do aplicativo."),
    ERROR_INVALID_CREDENTIAL("Credencial fornecida inválida."),
    ERROR_INVALID_EMAIL("E-mail inválido."),
    ERROR_WRONG_PASSWORD("Senha incorreta."),
    ERROR_USER_MISMATCH("Credencial do usuário não corresponde."),
    ERROR_REQUIRES_RECENT_LOGIN("Ação requer autenticação recente."),
    ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL("Já existe uma conta com a mesma credencial, mas com diferentes detalhes."),
    ERROR_EMAIL_ALREADY_IN_USE("Já existe uma conta com este e-mail."),
    CREDENTIAL_ALREADY_IN_USE("Já existe uma conta com a mesma credencial."),
    ERROR_USER_DISABLED("Usuário desativado."),
    ERROR_USER_TOKEN_EXPIRED("Token de autenticação do usuário expirado."),
    ERROR_USER_NOT_FOUND("Usuário não encontrado. Por favor, verifique suas credenciais"),
    ERROR_INVALID_USER_TOKEN("Token de autenticação de usuário inválido."),
    ERROR_OPERATION_NOT_ALLOWED("Operação não permitida."),
    ERROR_WEAK_PASSWORD("A senha deve ter no mínimo 6 caracteres."),
    UNKNOWN_ERROR("Erro na autenticação.")
}

