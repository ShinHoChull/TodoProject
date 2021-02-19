package com.m2comm.albumtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.m2comm.albumtest.model.Todo

@Database(entities = [Todo::class] , version = 1 )
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDAO

    //싱글톤 방식으로 DB 인스턴스 생성
    companion object {
        @Volatile private var INSTANCE : TodoDatabase? = null

        fun getInstance(context : Context) : TodoDatabase = INSTANCE ?:
        synchronized(this) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context : Context) =
            Room.databaseBuilder(context.applicationContext ,
            TodoDatabase::class.java , "Todo.db").build()

    }
}