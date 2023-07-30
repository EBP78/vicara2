package com.vicara.vicara2.data.preference

import android.content.Context

class UserPreference (context: Context) {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun login(){
        val editor = preferences.edit()
        editor.putBoolean(ISLOGIN, true)
        editor.apply()
    }

    fun logout(){
        val editor = preferences.edit()
        editor.putBoolean(ISLOGIN, false)
        editor.apply()
    }


    fun checkIsLogin(): Boolean{
        return preferences.getBoolean(ISLOGIN, false)
    }

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val ISLOGIN = "islogin"
    }
}