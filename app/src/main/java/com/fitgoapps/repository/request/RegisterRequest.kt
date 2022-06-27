package com.fitgoapps.repository.request

data class RegisterRequest (
    var message: String,
    var name: String,
    var password: String,
    var confirmPassword: String,
    var email: String,
    var noHandphone: String,
    var gender: String,
    var namaTim: String,
)