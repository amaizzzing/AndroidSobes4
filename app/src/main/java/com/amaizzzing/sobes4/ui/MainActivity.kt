package com.amaizzzing.sobes4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.amaizzzing.sobes4.R
import com.amaizzzing.sobes4.data.services.TimerService
import com.amaizzzing.sobes4.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class MainActivity : AppCompatActivity() {
    private var activityMainBinding: ActivityMainBinding? = null
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding?.root)

        initNavigation()
    }

    private fun initNavigation() {
        val navView: BottomNavigationView? = activityMainBinding?.bottomNavView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.mobile_navigation)
        navController.graph = navGraph

        activityMainBinding?.let { binding ->
            navView?.setupWithNavController(navController)

            binding.bottomNavView.setOnItemSelectedListener { menuItem ->
                if (menuItem.itemId != navController.currentDestination?.id) {
                    navController.navigate(
                        menuItem.itemId,
                        null,
                        NavOptions.Builder().setPopUpTo(R.id.mobile_navigation, false).build()
                    )
                }
                true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityMainBinding = null
    }
}