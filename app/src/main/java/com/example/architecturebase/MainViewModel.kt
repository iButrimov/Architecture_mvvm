package com.example.architecturebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturebase.network.model.Post
import com.example.architecturebase.network.usecases.NewsUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(), Contract.IViewModel {

    private val repository: RemoteRepository = RemoteRepository()
    private val newsUseCase = NewsUseCase()
    override val post: MutableLiveData<List<Post>?> by lazy { MutableLiveData() }

    override fun getPost() {

        repository.postApi.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        newsUseCase.loadPostsUseCase(posts)
                        post.postValue(posts)
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