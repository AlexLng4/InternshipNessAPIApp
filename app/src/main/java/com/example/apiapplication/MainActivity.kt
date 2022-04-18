package com.example.apiapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiapplication.models.Movie
import com.example.apiapplication.models.MovieResponse
import com.example.apiapplication.service.MovieApiInterface
import com.example.apiapplication.service.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var TAG="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movies_list.layoutManager = LinearLayoutManager(this)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }
}