package com.jaysdevapp.stickymemory.tabs

import androidx.compose.runtime.Composable
import com.jaysdevapp.stickymemory.home.ddayview
import com.jaysdevapp.stickymemory.home.memoview
import com.jaysdevapp.stickymemory.home.todoview

typealias MyFunc= @Composable ()-> Unit
sealed class tabs_items(
    var title : String,
    var screen : MyFunc
){
    object item_todo : tabs_items("Todo",{ todoview() })
    object item_dday : tabs_items("D-day",{ ddayview() })
    object item_memo : tabs_items("Memo",{ memoview() })
}
