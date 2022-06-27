package com.fitgoapps.model

data class User(
    var id: Int,
    var email: String,
    var password: String,
    var noHandphone: String,
    var user_sso: String,
    var id_type_user: Int,
    var createdAt: String,
    var updatedAt: String,

    var userDetail: UserDetail,
    var typeUser: TypeUser,
)
