package com.example.appartementcomplex.db

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


private class IntPreference(
    private val sharedPreferences: SharedPreferences,
    private val defaultValue: Int
) : ReadWriteProperty<Any, Int> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Int =
        sharedPreferences.getInt(property.name, defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        sharedPreferences.edit()
            .putInt(property.name, value)
            .commit()
    }
}

private class BooleanPreference(
    private val sharedPreferences: SharedPreferences,
    private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {

    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return sharedPreferences.getBoolean(property.name, defaultValue)
    }


    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(property.name, value)
            .commit()
    }
}

private class StringPreference(
    private val sharedPreferences: SharedPreferences,
    private val defaultValue: String
) : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        val all = sharedPreferences.all
        return if (all[property.name] is String) {
            sharedPreferences.getString(property.name, defaultValue)!!
        } else {
            defaultValue
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        sharedPreferences.edit().putString(property.name, value).commit()
    }
}

fun SharedPreferences.asInt(
    defaultValue: Int = 0
): ReadWriteProperty<Any, Int> =
    IntPreference(this, defaultValue)

fun SharedPreferences.asBoolean(
    defaultValue: Boolean = false
): ReadWriteProperty<Any, Boolean> =
    BooleanPreference(this, defaultValue)

fun SharedPreferences.asString(
    defaultValue: String = ""
): ReadWriteProperty<Any, String> =
    StringPreference(this, defaultValue)