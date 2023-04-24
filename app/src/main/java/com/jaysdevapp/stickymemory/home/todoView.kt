package com.jaysdevapp.stickymemory.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jaysdevapp.stickymemory.ui.theme.StickyMemoryTheme

@Composable
fun todoview(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTodoview() {
    StickyMemoryTheme {
        todoview()
    }
}