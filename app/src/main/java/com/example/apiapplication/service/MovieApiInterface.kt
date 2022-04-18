package com.example.apiapplication.service



import com.example.apiapplication.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=43d99e9835a01d2ab7a821edc63c0ec1")
    fun getMovieList(): Call<MovieResponse>
}