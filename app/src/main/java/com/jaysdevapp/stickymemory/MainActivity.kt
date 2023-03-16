package com.jaysdevapp.stickymemory

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jaysdevapp.stickymemory.ui.theme.StickyMemoryTheme
import com.google.android.gms.ads.MobileAds


@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {

    @SuppressLint("ResourceAsColor", "UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)
        setContent {
            StickyMemoryTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                       home(navController)
                    }
                    composable("info") {
                        info(navController)
                    }
                }


            }
        }
    }

    @Composable
    private fun home(navController: NavHostController) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Scaffold(
                //topBar = { TopBar(title = R.string.app_name, actions = actions(application)) },
                topBar = {ToolbarWithMenu(navController,application)},
                bottomBar = {ad()}
            ){
                Column(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                    Tabs_principle(application)
                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onRestart() {
        super.onRestart()

        MobileAds.initialize(this)
        setContent {
            StickyMemoryTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        home(navController)
                    }
                    composable("info") {
                        info(navController)
                    }
                }


            }
        }
    }

}


