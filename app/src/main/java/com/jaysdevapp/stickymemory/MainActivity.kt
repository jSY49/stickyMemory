package com.jaysdevapp.stickymemory

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import com.jaysdevapp.stickymemory.ui.theme.StickyMemoryTheme


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
    fun home(navController: NavHostController) {
       /* Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = { ToolbarWithMenu(navController, application) },
                content = {
                    //광고가 화면 가리는 문제 있음. 이전에는 됐었는데 왜 갑자기 안되는거임 짜증나게
                    Column(modifier = Modifier.padding(it)){
                        Tabs_principle(application)
                    }
                },
                bottomBar = { ad() }
            )
        }*/
        Scaffold(
            topBar = { ToolbarWithMenu(navController, application) },
            content = {
                //광고가 화면 가리는 문제 있음. 이전에는 됐었는데 왜 갑자기 안되는거임 짜증나게
                Column(modifier = Modifier.padding(it.calculateBottomPadding())){
                    Tabs_principle(application)
                }
            },
            bottomBar = {
                Column(modifier = Modifier.heightIn(30.dp)){
                    ad()
                }
            }
        )
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


