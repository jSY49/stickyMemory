package com.example.stickymemory


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs_principle() {
    val tabs = listOf(
        tabs_items.item_all,
        tabs_items.item_todo,
        tabs_items.item_dday,
        tabs_items.item_memo
    )
    val pagerState = rememberPagerState()
    Column {
        Tabs(tabs, pagerState)
        Tabs_content(tabs, pagerState)
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<tabs_items>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        modifier = Modifier
            .height(60.dp),
        selectedTabIndex = pagerState.currentPage,
        contentColor = colorResource(id = R.color.moreOrange)
    ) {
        tabs.forEachIndexed { index, tabsItems ->
            LeadingIconTab(
                selectedContentColor = colorResource(id = R.color.moreOrange),
                unselectedContentColor = colorResource(id = R.color.littleOrange),
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {},
                text = { Text(tabsItems.title, fontWeight = FontWeight.Bold) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs_content(tabs: List<tabs_items>, pagerState: PagerState) {
    HorizontalPager(
        state = pagerState,
        count = tabs.size
    ) { page ->
        tabs[page].screen()
    }
}