package com.m2comm.albumtest.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.m2comm.albumtest.R
import com.m2comm.albumtest.common.Defines
import com.m2comm.albumtest.common.Defines.Companion.y
import com.m2comm.albumtest.extensions.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() ,NavController.OnDestinationChangedListener {

    private lateinit var mBottomNavigationView: BottomNavigationView
    private var currentNavController: LiveData<NavController>? = null
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if(savedInstanceState == null) {
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
            //내부적으로 하고싶으면 하단 주석을 풀면되고 .
            //따로 재정의 하겠다 싶으면 addOnDestinationChangedListener 태우면 됨.
            //일단 Toolbar를 사용한것 자체가 내부적으로 이용 할 수 는 없다.
            //setupActionBarWithNavController(navController)

            navController.addOnDestinationChangedListener(this)
        })

        currentNavController = controller
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        if ( destination.label.toString() != "1" ) {
            Defines.y("depth Number 2")
            toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            toolbar.mainLog.visibility = View.GONE
            toolbar.setNavigationOnClickListener {
                controller.navigateUp()
            }
        } else {
            toolbar.setNavigationIcon(null)
            toolbar.mainLog.visibility = View.VISIBLE
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        initNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }



}
