package com.personal.common

import android.content.Context

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // Save a String value
    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    // Retrieve a String value
    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    // Save an Integer value
    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    // Retrieve an Integer value
    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    // Save a Boolean value
    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    // Retrieve a Boolean value
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // Save a Long value
    fun saveLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }

    // Retrieve a Long value
    fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    // Save a Set of Strings (for storing lists, sets, or arrays)
    fun saveStringSet(key: String, value: Set<String>) {
        editor.putStringSet(key, value)
        editor.apply()
    }

    // Retrieve a Set of Strings
    fun getStringSet(key: String, defaultValue: Set<String>): Set<String>? {
        return sharedPreferences.getStringSet(key, defaultValue)
    }

}