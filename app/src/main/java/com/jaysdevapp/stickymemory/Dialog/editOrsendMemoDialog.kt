package com.jaysdevapp.stickymemory

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaysdevapp.stickymemory.dataclasses.Memo
import com.jaysdevapp.stickymemory.ui.theme.lightOrange
import com.jaysdevapp.stickymemory.ui.theme.light_blue_200
import com.jaysdevapp.stickymemory.ui.theme.purple_100
import com.jaysdevapp.stickymemory.ui.theme.yellow_100
import com.jaysdevapp.stickymemory.viewModel.MemoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun editOrsendDialog_memo(
    application: Application,
    item: Memo,
    showEditDialog: Boolean,
    setShowEditDialog: (Boolean) -> Unit,
    vm: MemoViewModel = viewModel(factory = MemoViewModel.Factory(application)),
    onConfirm: () -> Unit
) {
    val TAG = object {}.javaClass.enclosingClass.name

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(item.memoThing) }
    val txtTitleField = remember { mutableStateOf(item.memoTitle) }

    val color_chart = listOf(light_blue_200, yellow_100, purple_100)
    var selectedIndex by remember { mutableStateOf(item.colorNum) }
    val declarations = listOf("light blue","yellow","purple")

    if (!showEditDialog) return
    Dialog(onDismissRequest = { setShowEditDialog(false) }) {
        Surface(
            modifier= Modifier.padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth().heightIn(30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My Memo",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    TextField(
                        modifier = Modifier
                            .heightIn(25.dp)
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(
                                        id = if (txtTitleField.value.isEmpty()) R.color.moreOrange else R.color.lightOrange
                                    )
                                ),
                                shape = RoundedCornerShape(20)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ThumbUp,
                                contentDescription = "",
                                tint = colorResource(R.color.moreOrange),
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                        },
                        placeholder = { Text(text = "what is your Memo Title?") },
                        value = txtTitleField.value,
                        onValueChange = {
                            txtTitleField.value = it.take(100)
                        })

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        declarations.forEachIndexed { index, item ->
                            //디스플레이 크기에 따라 버튼 뭉게짐
                            //색상 문제
                            OutlinedButton(
                                onClick = { selectedIndex = index },
                                modifier = when (index) {
                                    0 ->
                                        Modifier
                                            .offset(0.dp, 0.dp)
                                            .zIndex(if (selectedIndex == index) 1f else 0f)
                                            .weight(1f)
                                    else ->
                                        Modifier
                                            .offset((-1 * index).dp, 0.dp)
                                            .zIndex(if (selectedIndex == index) 1f else 0f)
                                            .weight(1f)
                                },

                                border = BorderStroke(
                                    2.dp, lightOrange
                                ),
                                colors = if (selectedIndex == index) {
                                    ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = color_chart.get(index),
                                        contentColor = Color.Black
                                    )
                                } else {
                                    ButtonDefaults.outlinedButtonColors(
                                        backgroundColor = MaterialTheme.colors.background,
                                        contentColor = Color.Black
                                    )
                                }
                            ) {
                                ResponsiveExampleText(text = item)
                            }

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        modifier = Modifier
                            .fillMaxHeight(0.6f)
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(
                                        id = if (txtFieldError.value.isEmpty()) R.color.moreOrange else R.color.lightOrange
                                    )
                                ),
                                shape = RoundedCornerShape(5)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text(text = "what is your Memo?") },
                        value = txtField.value,
                        onValueChange = {
                            txtField.value = it.take(3000)
                        })


                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if (txtField.value.isBlank() || txtTitleField.value.isBlank()) {
                                    txtFieldError.value = "Field can not be empty"
                                } else {
                                    item.memoTitle = txtTitleField.value
                                    item.memoThing = txtField.value
                                    item.colorNum=selectedIndex
                                    vm.updateMemo(item)
                                    setShowEditDialog(false)
                                }
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(text = "Done")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ResponsiveExampleText(
    modifier: Modifier = Modifier,
    text: String,
) {
    ResponsiveText(
        modifier = modifier,
        text = text,
        textStyle = TextStyle(fontSize = 18.sp)
    )
}

