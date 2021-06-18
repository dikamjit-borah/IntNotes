package com.hobarb.internshalanotes.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {
    var sharedpreferences: SharedPreferences? = null
    init{
        sharedpreferences = context.getSharedPreferences(
            Constants.SHARED_PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )
    }

    fun writePrefs(key: String?, value: String?) {
        val editor = sharedpreferences!!.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun readPrefs(key: String?): String? {
        return if (sharedpreferences!!.contains(key)) sharedpreferences!!.getString(
            key,
            ""
        ) else ""
    }
}