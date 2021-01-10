package com.example.architecturebase

import androidx.lifecycle.LiveData
import com.example.architecturebase.network.model.Post

interface Contract {

    interface IViewModel {
        val post: LiveData<List<Post>?>
        fun getPost()
    }

}