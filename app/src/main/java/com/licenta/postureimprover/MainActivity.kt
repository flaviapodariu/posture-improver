package com.licenta.postureimprover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.licenta.postureimprover.navigation.Navigation
import com.licenta.postureimprover.navigation.Routes
import com.licenta.postureimprover.screens.components.StandardScaffold
import com.licenta.postureimprover.ui.theme.PostureImproverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PostureImproverTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    StandardScaffold(
                        navController = navController,
                        showBottomBar = navBackStackEntry?.destination?.route !in listOf(
                            Routes.Camera.route,
                            Routes.Login.route,
                            Routes.SignUp.route
                        )

                    ){
                        Navigation(navController = navController)
                    }



                }
            }

        }
    }

}
