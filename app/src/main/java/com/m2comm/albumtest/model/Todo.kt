package com.m2comm.albumtest.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Todo")
data class Todo (
    @PrimaryKey(autoGenerate = true) var id :Long?,
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "description") var description : String,
    @ColumnInfo(name = "createdDate") var createdDate: Long
        ) {
    constructor() : this(null , "" , "" , -1)

}