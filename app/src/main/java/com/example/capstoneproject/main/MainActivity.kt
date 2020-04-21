package com.example.capstoneproject.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.capstoneproject.R
import com.google.android.material.navigation.NavigationView

class MainActivity: AppCompatActivity() {

    private lateinit var mainActivityPresenter: MainActivityPresenter
    private lateinit var drawerLayout: DrawerLayout



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_main)

        // Set up the toolbar.
        setSupportActionBar(findViewById(R.id.toolbar))

        // Set up the navigation drawer.
        val navController = findNavController(R.id.nav_view)
        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)

        drawerLayout = (findViewById<DrawerLayout>(R.id.drawer_layout)).apply {
            setStatusBarBackground(R.color.colorPrimaryDark)
        }
        setupDrawerContent(findViewById(R.id.nav_view))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            /*if (menuItem.itemId == R.id.statistics_navigation_menu_item) {
                val intent = Intent(this@TasksActivity, StatisticsActivity::class.java)
                startActivity(intent)
            }*/
            // Close the navigation drawer when an item is selected.
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
    }
}