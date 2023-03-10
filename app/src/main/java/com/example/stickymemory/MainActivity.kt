@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.stickymemory

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.stickymemory.ui.theme.StickyMemoryTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StickyMemoryTheme {
                Surface {
                    Column {
                        TopBar(title = R.string.app_name, actions = actions(application))
                        Tabs_principle(application)
                    }
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        setContent {
            StickyMemoryTheme {
                Surface {
                    Column {
                        Tabs_principle(application)
                    }
                }
            }
        }
    }

}

@Composable
fun Greeting() {
//    Text(text = "Hello $name!")
    Surface {
        //Tabs_principle(application)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StickyMemoryTheme {
        Greeting()

    }
}


