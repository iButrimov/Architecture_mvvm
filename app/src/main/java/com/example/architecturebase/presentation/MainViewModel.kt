package com.example.architecturebase.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.domain.entities.Post
import com.example.architecturebase.data.RemoteRepository
import com.example.architecturebase.domain.usecases.NewsUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(), Contract.IViewModel {

    private val getPostsUseCase = NewsUseCase(RemoteRepository())

    override val post: MutableLiveData<List<Post>?> by lazy { MutableLiveData() }

    override fun getPost() {
        post.postValue(emptyList())

        getPostsUseCase.execute().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        //logic starts
                        val processedPosts =  posts.filter {
                            !it.title.startsWith("H")
                        }.map {
                            it.copy(title = it.title + "appendix")
                        }.sortedBy {
                            it.title
                        }.subList(0, posts.size - 3)
                        //logic ends
                        post.postValue(processedPosts)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                post.postValue(null)
                t.printStackTrace()
            }
        })
    }
}