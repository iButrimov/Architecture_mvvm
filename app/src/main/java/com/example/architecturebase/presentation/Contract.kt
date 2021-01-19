package com.example.architecturebase.presentation

import androidx.lifecycle.LiveData
import com.example.architecturebase.domain.entities.Post

interface Contract {

    interface IViewModel {
        val post: LiveData<List<Post>?>
        fun getPost()
    }

}