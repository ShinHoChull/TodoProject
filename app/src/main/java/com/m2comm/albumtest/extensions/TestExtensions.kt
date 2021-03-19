package com.m2comm.albumtest.extensions

import android.util.Log
import com.m2comm.albumtest.common.Defines


public fun Log.y(
    log : String ,
    logName : String = "YONG_CHEOL"
) {
    Log.d(logName , "=============")
    Log.d(logName , log)
    Log.d(logName , "=============")
}

