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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.bumptech.glide.Glide
import com.m2comm.albumtest.modules.Custom_SharedPreferences
import com.m2comm.albumtest.R
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    val REQ_GALLERY = 999
    var img: ImageView? = null
    var imgPath: String? = ""
    var customSharedpreferences =
        Custom_SharedPreferences(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img = findViewById(R.id.imageView)
        val bt: Button = findViewById(R.id.button)
        val imgPath = customSharedpreferences.getValue("imgPath", "")

        if (!imgPath.equals("")) {
            Log.d("img", imgPath)
            Glide.with(this).load(imgPath).into(img!!)
        }


        bt.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            startActivityForResult(intent, REQ_GALLERY)
        }

    }

    fun getBase64String(bitmap: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(imageBytes, Base64.NO_WRAP)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {
                REQ_GALLERY -> {
                    data?.data?.let { it ->
                        //it ->  image Uri 임 이거를 가지고 파일저장하거나 하면됩니당.
                        Log.d("img", it.toString())
                        customSharedpreferences.put("imgPath", it.toString())
                        val subImg: ImageView = ImageView(this)
                        subImg.setImageURI(it)
                        img!!.setImageURI(it)

                        val d: Drawable = subImg.drawable
                        val bt: Bitmap = d.toBitmap()
                        val base64enCode = getBase64String(bt)

                        Log.d("base64Code=", base64enCode!!)

                        AndroidNetworking.post("https://형서버")
                            .addBodyParameter("imgPath",base64enCode)
                            .addBodyParameter("회원ID","회원ID")
                            .build().getAsString(object : StringRequestListener {
                                override fun onResponse(response: String) {

                                    Log.d("response=", response)


                                }

                                override fun onError(anError: ANError) {}
                            })


                    }
                }
            }
        }
    }
}