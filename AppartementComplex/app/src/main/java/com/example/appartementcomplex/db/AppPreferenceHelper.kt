package com.example.appartementcomplex.db

import android.content.Context

class AppPreferenceHelper(context: Context) {

    private var preferenceName = context.applicationInfo.packageName  + "apartment"

    private val sharedPreferences =
        context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    var isLoggedIn by sharedPreferences.asBoolean(false)


    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}