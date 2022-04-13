package com.example.fakeapp.data.repositories.faker_characters.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FakeResponseModel(
    val code: Int,
    val data: List<FakeUserResponseList>,
    val status: String,
    val total: Int
): Parcelable