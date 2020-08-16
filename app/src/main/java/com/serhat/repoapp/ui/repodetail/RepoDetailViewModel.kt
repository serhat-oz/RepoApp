package com.serhat.repoapp.ui.repodetail

import androidx.lifecycle.MutableLiveData
import com.serhat.repoapp.model.User
import com.serhat.repoapp.ui.base.BaseViewModel

class RepoDetailViewModel : BaseViewModel(){

    val liveRepoList = MutableLiveData<List<User>>()

    fun setList(liveRepoList: MutableLiveData<List<User>> ){

    }
}