package com.jaysdevapp.stickymemory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jaysdevapp.stickymemory.ui.theme.StickyMemoryTheme

@Composable
fun memoview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMemoview(){
    StickyMemoryTheme {
        memoview()
    }
}