package com.example.simple_socialmedia.data.model


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("company")
    val company: Company?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("website")
    val website: String?
)
data class UserDTO(
    val username: String?,
    val email: String?,
    val id: Int?,
    val name: String?,
)