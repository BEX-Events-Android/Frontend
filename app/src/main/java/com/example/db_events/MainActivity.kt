package com.example.db_events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.db_events.databinding.ActivityMainBinding
import com.example.db_events.databinding.FragmentLoginBinding

//val register_button = findViewById<Button>(R.id.register_button)
//register_button.setOnClickListener{v ->
////            v.findNavController().navigate()
//}

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavGraph()

        // test
        //
    }

    private fun initNavGraph() {
        val navHost = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment

        val navController = navHost.navController
        val navInflater = navController.navInflater

        val graph = navInflater.inflate(R.navigation.navigation)
        navController.graph = graph
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
//        return navController.navigateUp()
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}