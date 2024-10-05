package com.example.screenchangekotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screenchangekotlin.ui.theme.ScreenChangeKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenChangeKotlinTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){

    // initialize
    val navController = rememberNavController()

    //control the screen
    NavHost(navController = navController, startDestination = "firstscreen"){

        // this will show what will happen on navigate
        composable("firstscreen"){   //own key
            FirstScreen {name, age ->
                navController.navigate("secondscreen/$name/$age")   // route to second screen
            }
        }

        // this will show what will happen on navigate
        composable(route = "secondscreen/{name}/{age}"){   //own key
            val name = it.arguments?.getString("name") ?: "no name"
            val ageString = it.arguments?.getString("age") ?: "0"
            val age = ageString.toIntOrNull() ?: 0 // Convert age from string to integer

            SecondScreen(name = name, age = age) {
                navController.navigate("firstscreen")   // route back to first screen
            }

        }
    }
}
