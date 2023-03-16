package com.jaysdevapp.stickymemory.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = lightOrange,
    primaryVariant = littleOrange,
    secondary = moreOrange
)

private val LightColorPalette = lightColors(
    primary = lightOrange,
    primaryVariant = littleOrange,
    secondary = moreOrange

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@SuppressLint("ResourceAsColor")
@Composable
fun StickyMemoryTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    //status bar
    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = colors.primary
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = colors.primary
        )
    }
}