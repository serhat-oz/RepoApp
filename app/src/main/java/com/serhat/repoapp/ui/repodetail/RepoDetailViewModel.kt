package com.serhat.repoapp.ui.repodetail

import android.app.Application
import androidx.lifecycle.LiveData
import com.serhat.repoapp.db.Favorite
import com.serhat.repoapp.db.FavoriteRepository
import com.serhat.repoapp.ui.base.BaseViewModel

class RepoDetailViewModel(application: Application) : BaseViewModel(application) {


    private var repository: FavoriteRepository =
        FavoriteRepository(application)

    private var allFavorites: LiveData<List<Favorite>> = repository.getAllFavorites()
    private lateinit var favoriteByNodeId:LiveData<Favorite>

    fun insert(favorite: Favorite) {
        repository.insert(favorite)
    }

    fun remove(favorite: Favorite) {
        repository.remove(favorite.nodeId)
    }

    fun getAllFavorites(): LiveData<List<Favorite>> {
        return allFavorites
    }

    fun getFavoriteByNodeId(nodeId:String):LiveData<Favorite>{
        favoriteByNodeId = repository.getFavoriteByNodeId(nodeId)
        dataLoading.value = true
        return favoriteByNodeId
    }
}