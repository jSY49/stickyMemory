package com.jaysdevapp.stickymemory

import android.app.Application
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

    val selectedValue = remember { mutableStateOf(0) } // 선택된 라디오 버튼에 해당하는 내용
    val isSelectedItem: (Int) -> Boolean = {selectedValue.value == it}
    val onChangeState : (Int) -> Unit = { selectedValue.value = it}
    val declarations = listOf("blue","yellow","purple")

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier= Modifier
                .fillMaxHeight()
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier
                    .padding(20.dp)
                    ) {
                    //name
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(30.dp),
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
                    //title
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(25.dp)
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(
                                        id = if (txtFieldError.value.isEmpty()) R.color.moreOrange else R.color.lightOrange
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

                    Row() {
                        declarations.forEachIndexed { index,item ->
                            Column {
                                Row(
                                    modifier = Modifier
                                        .selectable( // 선택 가능한 상태
                                            // selectedValue가 해당 item 과 같을때 선택된 상태를 의미
                                            selected = isSelectedItem(index),
                                            // 해당 라인 클릭시 selectedValue 값을 변경해 상태 변경
                                            onClick = { onChangeState(index) },
                                            role = Role.RadioButton
                                        )
                                        .padding(end = 3.dp)
                                ) {
                                    RadioButton(
                                        // 선택됨을 나타내는 인수와 같음
                                        // selectedValue가 해당 item 과 같을때 선택됨 표시
                                        selected = isSelectedItem(index),// 선택됨을 나타내는 인수와 같음
                                        onClick = null,
                                        modifier = Modifier.padding(end = 5.dp)
                                    )
                                    Text(text = item, fontSize = 10.sp)
                                }
                            }
                        }
                    }


                    Spacer(modifier = Modifier.height(15.dp))
                    //memo
                    TextField(
                        modifier = Modifier
                            .fillMaxHeight(0.8f)
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
                                if(txtField.value.isBlank()||txtTitleField.value.isBlank()){
                                    txtFieldError.value = "Field can not be empty"
                                }else{
                                    val memoTitle= txtTitleField.value
                                    val mem=txtField.value
                                    val color_code = selectedValue.value
                                    vm.addMemo(Memo(memoTitle,mem,color_code))
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
