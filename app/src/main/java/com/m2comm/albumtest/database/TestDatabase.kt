package com.m2comm.albumtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.m2comm.albumtest.model.Test

@Database(entities = [Test::class] , version = 1)
abstract class TestDatabase : RoomDatabase() {
    abstract fun TestDAO() : TestDAO

    //싱글톤 방식으로 DB 인스턴스 생성
    companion object {
        @Volatile private var INSTANCE : TestDatabase? = null

        fun getInstance(context : Context) : TestDatabase = INSTANCE ?:
        synchronized(this) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context : Context) =
            Room.databaseBuilder(context.applicationContext ,
                TestDatabase::class.java , "Test.db")
                .build()

    }
}