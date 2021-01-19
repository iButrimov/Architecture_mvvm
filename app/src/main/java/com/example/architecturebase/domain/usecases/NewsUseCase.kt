package com.example.architecturebase.domain.usecases

import retrofit2.Call
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.domain.repositories.IRepository

class NewsUseCase (private val newRepository: IRepository) {

    fun execute(): Call<List<Post>> {
        return newRepository.loadPosts()
    }

}