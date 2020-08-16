package com.serhat.repoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorites_table ")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("DELETE FROM favorites_table where nodeId = :nodeId")
    fun deleteFavorite(nodeId: String)

    @Query("select * FROM favorites_table where nodeId = :nodeId")
    fun getFavoriteByNodeId(nodeId:String):LiveData<Favorite>

}