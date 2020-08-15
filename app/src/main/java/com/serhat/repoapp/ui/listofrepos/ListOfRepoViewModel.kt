package com.serhat.repoapp.ui.listofrepos

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.serhat.repoapp.model.User
import com.serhat.repoapp.remote.repository.RepoRepository
import com.serhat.repoapp.ui.base.BaseViewModel

class ListOfRepoViewModel : BaseViewModel() {

    val liveRepoList = MutableLiveData<List<User>>()
    val obsUserId = ObservableField<String>()

    fun fetchSpecificUserRepoList() {
        if (!obsUserId.get().isNullOrEmpty()){
            dataLoading.value = true
            RepoRepository.getInstance().getUserRepoList(obsUserId.get()!!) { isSuccess, response ->
                dataLoading.value = false
                if (isSuccess) {
                    liveRepoList.value = response
                    empty.value = false
                } else {
                    empty.value = true
                }
            }
        }else{
            toastMessage.value = "Please fill the user name area"
        }

    }
}