package com.example.moviesdbapp.data.repositories.movie

import com.example.moviesdbapp.data.network.ResponseModel
import com.google.gson.annotations.SerializedName

class MovieBasicResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("video")
    val video: Boolean
): ResponseModel<MovieBasic> {

    override fun toModel() = MovieBasic(
        id = id,
        title = title,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        backdropPath = backdropPath,
        releaseDate = /*DateParser.parse(releaseDate)*/ releaseDate,
        posterPath = posterPath,
        popularity = popularity,
        voteAverage = voteAverage,
        voteCount = voteCount,
        video = video,
        genreIds = genreIds,
        adult = adult,
    )
}