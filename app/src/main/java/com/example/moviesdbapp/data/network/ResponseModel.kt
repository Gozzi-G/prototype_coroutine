package com.example.moviesdbapp.data.network

interface ResponseModel<Model> {
    fun toModel(): Model
}