package com.example.moviesdbapp.data.repositories.movie


import android.os.Parcelable
import com.example.moviesdbapp.data.network.ResponseModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


class MoviesListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<MovieBasicResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
): ResponseModel<MoviesList>{

    override fun toModel() = MoviesList(
        page = page,
        movies = movies?.map { it.toModel() } ?: emptyList(),
        totalPages = totalPages,
        totalResults = totalResults,
    )
}


@Parcelize
class MoviesList(
    val page: Int,
    val movies: List<MovieBasic>,
    val totalPages: Int,
    val totalResults: Int,
) : Parcelable

@Parcelize
open class MovieBasic(
    open val id: Int,
    open val title: String?,
    open val originalTitle: String?,
    open val overview: String?,
    open val backdropPath: String?,
    open val releaseDate: String?,
    open val posterPath: String?,
    open val voteAverage: Double?,
    open val voteCount: Int?,
    open val popularity: Double?,
    open val genreIds: List<Int>,
    open val originalLanguage: String?,
    open val adult: Boolean,
    open val video: Boolean,
) : Parcelable
