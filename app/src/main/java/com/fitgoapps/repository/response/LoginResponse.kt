package com.fitgoapps.repository.response

import com.fitgoapps.model.User

data class LoginResponse(
    var message: String,
    var token: String,
    var data: User
)
