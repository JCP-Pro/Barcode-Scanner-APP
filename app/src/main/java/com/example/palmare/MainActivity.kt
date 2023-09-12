package com.example.palmare

import android.os.Bundle
import androidx.activity.compose.setContent

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.palmare.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.palmare.ui.theme.PalmareTheme


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        //Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController

        //Make sure the actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)

        /*setContent {
            PalmareTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
                }
            }
        }*/
    }
    //Enable back button support. Navigates one element up on the stack
    override fun onSupportNavigateUp(): Boolean {
    return super.onSupportNavigateUp() || navController.navigateUp()
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PalmareTheme {
        Greeting("Android")
    }
}*/
