package com.serhat.repoapp.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class FavoriteRepository(application: Application) {

    private var favoriteDao: FavoriteDao

    private var allFavorites: LiveData<List<Favorite>>

    private lateinit var favoriteByNodeId: LiveData<Favorite>

    init {
        val database: FavoriteDatabase = FavoriteDatabase.getInstance(
            application.applicationContext
        )!!
        favoriteDao = database.favoriteDao()
        allFavorites = favoriteDao.getAllFavorites()
    }

    fun insert(favorite: Favorite) {
        val insertFavoriteAsyncTask = InsertFavoriteAsyncTask(favoriteDao).execute(favorite)
    }

    fun getFavoriteByNodeId(nodeId: String):LiveData<Favorite>{
        favoriteByNodeId = favoriteDao.getFavoriteByNodeId(nodeId)
        return favoriteByNodeId
    }


    fun getAllFavorites(): LiveData<List<Favorite>> {
        return allFavorites
    }

    private class InsertFavoriteAsyncTask(favoriteDao: FavoriteDao) : AsyncTask<Favorite, Unit, Unit>() {
        val favoriteDao = favoriteDao

        override fun doInBackground(vararg p0: Favorite?) {
            favoriteDao.insert(p0[0]!!)
        }
    }



}