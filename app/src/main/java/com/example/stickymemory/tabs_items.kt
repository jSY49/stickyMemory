package com.example.stickymemory

import androidx.compose.runtime.Composable

typealias MyFunc= @Composable ()-> Unit
sealed class tabs_items(
    var title : String,
    var screen : MyFunc
){
    object item_all : tabs_items("ALL",{ allview()})
    object item_todo : tabs_items("Todo",{ todoview() })
    object item_dday : tabs_items("D-day",{ ddayview() })
    object item_memo : tabs_items("Memo",{ memoview() })
}
