package com.fitgoapps.utils

import android.content.Context
import android.content.SharedPreferences
import com.fitgoapps.R
import com.fitgoapps.model.User
import com.google.gson.Gson

class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
        const val USER = "user"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String?) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun saveUser(user: User){
        val gson = Gson()
        val editor = prefs.edit()
        editor.putString( USER, gson.toJson(user) )
        editor.apply()
    }

    fun getUser(): User {
        val gson = Gson()
        val user = prefs.getString(USER, "")

        if (user == ""){
            return User(
                id = null,
                email = null,
                password = null,
                noHandphone = null,
                user_sso = null,
                id_type_user = null,
                createdAt = null,
                updatedAt = null,
                userDetail = null,
                typeUser = null,
            )
        }

        return gson.fromJson(user, User::class.java)
    }

}