package com.example.architecturebase.domain.repositories

import retrofit2.Call
import com.example.architecturebase.domain.entities.Post

interface IRepository {

    fun loadPosts(): Call<List<Post>>

}