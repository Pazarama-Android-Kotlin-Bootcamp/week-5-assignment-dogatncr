package com.example.simple_socialmedia.data.local.database.converter

import androidx.room.TypeConverter
import com.example.simple_socialmedia.data.model.Address

/**
 ** */
class DaoConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.split(",").map { it }
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString?.joinToString(separator = ",") ?: ""
    }
    @TypeConverter
    fun fromAddress(value: Address): List<String>? {
        var list= ArrayList<String>();
        value.city?.let { list.add(it) }
        value.street?.let { list.add(it) }
        value.suite?.let { list.add(it) }
        value.zipcode?.let { list.add(it) }
        value.geo?.let { it.lat?.let { it -> list.add(it) } }
        value.geo?.let { it.lng?.let { it -> list.add(it) } }
        return list
    }
}