package com.m2comm.albumtest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Test")
data class Test (@PrimaryKey(autoGenerate = true) var id : Long? ,
            @ColumnInfo(name = "title") var title : String){

    constructor() : this(null , "")

}