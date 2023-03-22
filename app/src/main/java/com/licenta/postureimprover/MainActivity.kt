package com.licenta.postureimprover

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.licenta.postureimprover.screens.components.StandardScaffold
import com.licenta.postureimprover.screens.navigation.Navigation
import com.licenta.postureimprover.screens.navigation.Routes
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel
import com.licenta.postureimprover.screens.viewmodels.MainViewModel
import com.licenta.postureimprover.screens.viewmodels.TimerDialogViewModel
import com.licenta.postureimprover.theme.PostureImproverTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply{
            mainViewModel.whatUser()
            this.setKeepOnScreenCondition {
                mainViewModel.isLoading
            }
        }
        setContent {
            PostureImproverTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    navController.backQueue.forEach{
                        println(it.destination)
                    }
                    StandardScaffold(
                        navController = navController,
                        showBottomBar = navBackStackEntry?.destination?.route !in listOf(
                            Routes.Start.route,
                            Routes.Camera.route,
                            Routes.Login.route,
                            Routes.SignUp.route
                        ),
                        prefs = this.getSharedPreferences("preferences", MODE_PRIVATE)

                    ){
                        // navigation is called before nickname update so wrong screen is rendered
                        // this if causes a laggy screen render (dashboard)
                        if(!mainViewModel.isLoading) {
                            Navigation(navController = navController, nickname = mainViewModel.nickname)
                        }
                    }

                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
    }

}
