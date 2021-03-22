package com.m2comm.albumtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var mIsChangeScroll : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var mSaveClickRow : MutableLiveData<String> = MutableLiveData()

    fun setChangeScroll (b : Boolean) : MutableLiveData<Boolean> {
        mIsChangeScroll.value = b
        return mIsChangeScroll
    }

    fun getChangeScroll () : MutableLiveData<Boolean> {
        return mIsChangeScroll
    }
}