package com.example.fakeapp.data.repositories.faker_characters.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FakeUserResponseList(
    val id: Int,
    val email: String,
    val firstname: String,
    val image: String,
    val ip: String,
    val lastname: String,
    val macAddress: String,
    val password: String,
    val username: String,
    val uuid: String,
    val website: String
) : Parcelable