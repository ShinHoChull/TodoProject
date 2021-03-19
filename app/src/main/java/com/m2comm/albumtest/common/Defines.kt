package com.m2comm.albumtest.common

import android.util.Log

class Defines {



    companion object {
        public fun y(
            log : String ,
            logName : String = "YONG_CHEOL"
        ) {
            Log.d(logName , "==================")
            Log.d(logName , log)
            Log.d(logName , "=====================")
        }
    }


}