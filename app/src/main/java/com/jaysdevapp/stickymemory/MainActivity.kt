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
import androidx.compose.ui.tooling.preview.Preview
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
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBar(title = R.string.app_name, actions = actions(application)) },
                        bottomBar = {ad()}
                    ){
                        Column(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                            Tabs_principle(application)
                        }
                    }


                }
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onRestart() {
        super.onRestart()
        setContent {
            StickyMemoryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopBar(title = R.string.app_name, actions = actions(application)) },
                        bottomBar = {ad(modifier = Modifier.fillMaxWidth())}
                    ){
                        Column(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
                            Tabs_principle(application)
                        }
                    }


                }
            }
        }
    }

}

@Composable
fun Greeting() {
    Surface {
        Column {
            //TopBar(title = R.string.app_name, actions = actions(application))
            //Tabs_principle(application)
            //ad()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StickyMemoryTheme {
        Greeting()

    }
}


