@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.stickymemory

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.stickymemory.ui.theme.StickyMemoryTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StickyMemoryTheme {
                Surface {
                    Column() {
                        TopBar(title = R.string.app_name, actions = actions())
                        Tabs_principle()
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
        Tabs_principle()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StickyMemoryTheme {
        Greeting()

    }
}


