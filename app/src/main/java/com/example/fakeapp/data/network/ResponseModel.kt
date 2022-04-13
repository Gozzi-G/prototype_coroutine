package com.example.fakeapp.data.network

interface ResponseModel<Model> {
    fun toModel(): Model
}