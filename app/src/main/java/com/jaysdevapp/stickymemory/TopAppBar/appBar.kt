package com.jaysdevapp.stickymemory

import android.app.Application
import android.graphics.Paint.Align
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToolbarWithMenu(navController: NavHostController, application: Application) {
    var expanded by remember { mutableStateOf(false) }
    val listItems = arrayOf("To-do", "D-day", "Memo")
    var selected by remember { mutableStateOf(0) }

    var showTodoDialog by remember { mutableStateOf(false) }
    var showDdayDialog by remember { mutableStateOf(false) }
    var showMemoDialog by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(R.string.app_name), textAlign = TextAlign.Center
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigate("Info") }) {
                Icon(
                    imageVector = Icons.Filled.Info, contentDescription = "Info"
                )
            }
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    // 3 vertical dots icon
                    IconButton(onClick = {
                        expanded = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Open Options"
                        )
                    }

                    if (showTodoDialog) {
                        todo_ui(setShowDialog = { showTodoDialog = it }, application)
                    }
                    if (showDdayDialog) {
                        dday_ui(
                            value = "", setShowDialog = { showDdayDialog = it }, application
                        )
                    }
                    if (showMemoDialog) {
                        memo_ui(
                            value = "", setShowDialog = { showMemoDialog = it }, application
                        )
                    }

                    // drop down menu
                    DropdownMenu(
                        expanded = expanded, onDismissRequest = {
                            expanded = false
                        }, offset = DpOffset(10.dp, 0.dp)
                    ) {
                        listItems.forEachIndexed { itemIndex, itemValue ->
                            DropdownMenuItem(onClick = {
                                selected = itemIndex
                                when (selected) {
                                    0 -> {
                                        showTodoDialog = true
                                    }
                                    1 -> {
                                        showDdayDialog = true
                                    }
                                    2 -> {
                                        showMemoDialog = true
                                    }
                                    else -> {}
                                }
                                expanded = false

                            }) {
                                Text(text = itemValue)
                            }
                        }
                    }
                }
            }
        })
    }) {
        Column(modifier = Modifier.padding(bottom = it.calculateBottomPadding())) {
            Tabs_principle(application)
        }
    }
}


@Composable
fun info(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.How), textAlign = TextAlign.Center, fontSize = 30.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text =stringResource(R.string.How_info),fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))
            Text(text =stringResource(R.string.How_info1) )
            Image(painter = painterResource(R.drawable.info1), contentDescription ="info1" , modifier = Modifier.fillMaxWidth().padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text =stringResource(R.string.How_info2) )
            Image(painter = painterResource(R.drawable.info2), contentDescription ="info2"  , modifier = Modifier.fillMaxWidth().padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text =stringResource(R.string.How_info3) )
            Image(painter = painterResource(R.drawable.info3), contentDescription ="info3"  , modifier = Modifier.fillMaxWidth().padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text =stringResource(R.string.How_info4) )
            Image(painter = painterResource(R.drawable.info4), contentDescription ="info4"  , modifier = Modifier.fillMaxWidth().padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun pre() {
    val navController = rememberNavController()
    info(navController)
}