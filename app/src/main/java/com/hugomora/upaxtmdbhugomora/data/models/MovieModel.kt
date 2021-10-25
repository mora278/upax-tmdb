package com.hugomora.upaxtmdbhugomora.data.models

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("popularity") val popularity: String,
    @SerializedName("poster_path") val imageUrl: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("id") val id: String,
    )