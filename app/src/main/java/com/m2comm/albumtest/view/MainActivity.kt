package com.m2comm.albumtest.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.bumptech.glide.Glide
import com.m2comm.albumtest.modules.Custom_SharedPreferences
import com.m2comm.albumtest.R
import com.m2comm.albumtest.database.TestDAO
import com.m2comm.albumtest.database.TestDatabase
import com.m2comm.albumtest.database.TodoDAO
import com.m2comm.albumtest.database.TodoDatabase
import com.m2comm.albumtest.model.Test
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.lang.Runnable


class MainActivity : AppCompatActivity() {

    private lateinit var mTestDAO : TestDAO
    private lateinit var mTestDatabase : TestDatabase

    var mData : Test? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTestDatabase = TestDatabase.getInstance(application)
        mTestDAO = mTestDatabase.TestDAO()


        GlobalScope.launch(Dispatchers.IO) {
            Log.d("MainActivity=","123")
            mData = mTestDAO.getData()
            if (mData != null) {
                Log.d("MainActivity=",mData.toString())
                editTextTextPersonName.setText(mData!!.title)
            }
        }


        this.initButton()

    }




    private fun initButton() {

        saveButton.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                mTestDAO.insertTest(Test(null , editTextTextPersonName.text.toString()))
                Log.d("TEST", "TEsT!!!!")
            }


        }

        updateButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                if ( mData != null ) {
                    mData!!.title = editTextTextPersonName.text.toString()
                    mTestDAO.updateTest(mData!!)
                    //userMessageToRead("수정완료!")
                } else {
                    Log.d("TEST", "Null!!!!")
                }
            }
        }

        deleteButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                mTestDAO.deleteTest(mData!!)
                editTextTextPersonName.setText("")
                //userMessageToRead("삭제")
            }
        }

    }

    private fun userMessageToRead(message : String) {
        Toast.makeText(applicationContext , message , Toast.LENGTH_SHORT).show()
    }


}