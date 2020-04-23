package com.example.capstoneproject.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.capstoneproject.R
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class RecordsActivity: AppCompatActivity() {

    private lateinit var recordsPresenter: RecordsPresenter
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Set up the toolbar.
        //setSupportActionBar(findViewById(R.id.toolbar))

        Log.d(TAG, "Hello")


        val recordsFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as RecordsFragment? ?: RecordsFragment.newInstance().also {
            Log.d(TAG, "Loading fragment")
            replaceFragmentInActivity(it, R.id.contentFrame)
            Log.d(TAG, "Finished loading fragment")
        }

        // Create the presenter
        recordsPresenter = RecordsPresenter(recordsFragment).also { Log.d(TAG, "recordsPresenter") }

        Toast.makeText(this, "This is a damn activity", Toast.LENGTH_LONG).show()
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

    fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, @IdRes frameId: Int) {
        supportFragmentManager.transact {
            replace(frameId, fragment)
            Log.d(TAG, "Replace fragment")
        }
    }

    private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
        beginTransaction().apply {
            action()
        }.commit()
        Log.d(TAG, "transact")
    }

    companion object {
        private const val TAG = "RecordsActivity"
    }
}