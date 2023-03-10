package com.example.stickymemory.tabs

import androidx.compose.runtime.Composable
import com.example.stickymemory.ddayview
import com.example.stickymemory.memoview
import com.example.stickymemory.todoview

typealias MyFunc= @Composable ()-> Unit
sealed class tabs_items(
    var title : String,
    var screen : MyFunc
){
    object item_todo : tabs_items("Todo",{ todoview() })
    object item_dday : tabs_items("D-day",{ ddayview() })
    object item_memo : tabs_items("Memo",{ memoview() })
}
