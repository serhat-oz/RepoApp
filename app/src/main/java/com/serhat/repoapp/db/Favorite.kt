package com.serhat.repoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class Favorite(

    var nodeId: String

) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}