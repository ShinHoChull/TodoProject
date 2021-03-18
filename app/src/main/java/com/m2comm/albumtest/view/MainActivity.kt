package com.m2comm.albumtest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.m2comm.albumtest.R
import com.m2comm.albumtest.extensions.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBottomNavigationView: BottomNavigationView
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            initNavigation()
        }
    }


    private fun initNavigation() {

        mBottomNavigationView = findViewById(R.id.nav_bottom)
        val navGraphIds = listOf(R.navigation.meber_nav_graph, R.navigation.nav_lotto_camera_graph, R.navigation.nav_lotto_list_graph)

        val controller = mBottomNavigationView.setupWithNavController(
            navGraphIds, supportFragmentManager, R.id.nav_host_container, intent
        )

        controller.observe(this, Observer {navController->
            setupActionBarWithNavController(navController)
        })

        currentNavController = controller

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


//    private fun initNavigation() {
//        nav_bottom.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.menuJoin -> {
//                    replaceFragment(MemberProfile())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.menuLottoCamera -> {
//                    replaceFragment(LottoCamera())
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.menuLottoList -> {
//                    replaceFragment(LottoSaveList())
//                    return@setOnNavigationItemSelectedListener true
//                }
//
//                else -> return@setOnNavigationItemSelectedListener true
//            }
//        }
//    }



}