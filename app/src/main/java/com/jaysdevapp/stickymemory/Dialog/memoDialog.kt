package com.jaysdevapp.stickymemory

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaysdevapp.stickymemory.dataclasses.Memo
import com.jaysdevapp.stickymemory.ui.theme.StickyMemoryTheme
import com.jaysdevapp.stickymemory.viewModel.MemoViewModel

@Composable
fun memo_ui(value: String, setShowDialog: (Boolean) -> Unit, application: Application, vm : MemoViewModel = viewModel(factory = MemoViewModel.Factory(application))) {
    val txtFieldError = remember { mutableStateOf("") }
    val txtTitleField = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf("") }
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
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
                            .height(50.dp)
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(
                                        id = if (txtFieldError.value.isEmpty()) R.color.moreOrange else R.color.lightOrange)
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

                    TextField(
                        modifier = Modifier
                            .height(500.dp)
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(
                                        id = if (txtFieldError.value.isEmpty()) R.color.moreOrange else R.color.lightOrange)
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
                                if(txtField.value.isBlank()){
                                    txtFieldError.value = "Field can not be empty"
                                }else{
                                    val memoTitle= txtTitleField.value
                                    val mem=txtField.value
                                    vm.addMemo(Memo(memoTitle,mem))
                                    setShowDialog(false)
                                }
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Done")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoPreview() {
    StickyMemoryTheme {
        var showMemoDialog by remember { mutableStateOf(true) }
        if (showMemoDialog) {
            //memo_ui(value = "", setShowDialog = { showMemoDialog = it })
        }

    }
}
