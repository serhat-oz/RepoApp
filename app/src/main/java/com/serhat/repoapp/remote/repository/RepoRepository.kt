package com.serhat.repoapp.remote.repository

import com.serhat.repoapp.model.User
import com.serhat.repoapp.remote.RequestFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    fun getUserRepoList(
        username: String,
        onResult: (isSuccess: Boolean, response: List<User>?) -> Unit
    ) {
        RequestFactory.instance.getSpesificUsersRepo(username)
            .enqueue(object : Callback<List<User>> {

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.code() == 200) {
                        onResult(true, response.body())
                    } else {
                        onResult(false, response.body())
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    onResult(false, null)
                }
            })
    }

    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE
            ?: RepoRepository().also {
                INSTANCE = it
            }
    }
}