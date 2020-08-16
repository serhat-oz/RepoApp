package com.serhat.repoapp.ui.listofrepos

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.serhat.repoapp.db.Favorite
import com.serhat.repoapp.db.FavoriteRepository
import com.serhat.repoapp.model.User
import com.serhat.repoapp.remote.repository.RepoRepository
import com.serhat.repoapp.ui.base.BaseViewModel

class ListOfRepoViewModel(application: Application) : BaseViewModel(application) {

    val liveRepoList = MutableLiveData<List<User>>()
    val obsUserId = ObservableField<String>()

    private var repository: FavoriteRepository =
        FavoriteRepository(application)
    private var allFavorites: LiveData<List<Favorite>> = repository.getAllFavorites()

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
                    toastMessage.value = "User not found"
                }
            }
        }else{
            toastMessage.value = "Please fill the user name area"
        }

    }

    fun getAllFavorites(): LiveData<List<Favorite>> {
        return allFavorites
    }
}