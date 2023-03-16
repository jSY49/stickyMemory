package com.jaysdevapp.stickymemory

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.jaysdevapp.stickymemory.dataclasses.Dday
import com.jaysdevapp.stickymemory.ui.theme.StickyMemoryTheme
import com.jaysdevapp.stickymemory.viewModel.DdayViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun dday_ui(value: String, setShowDialog: (Boolean) -> Unit, application: Application, vm : DdayViewModel = viewModel(factory = DdayViewModel.Factory(application))) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf("") }
    var day : Int? = 1
    var month :Int? =1
    var year: Int?=2023

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
                            text = "My D-Day",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        WheelDatePicker(
                            size = DpSize(200.dp, 100.dp),
                            textStyle = MaterialTheme.typography.h6,
                            textColor = Color.Black,
                            infiniteLoopEnabled = true,
                            selectorEnabled = true,
                            selectorShape = RoundedCornerShape(0.dp),
                            selectorColor = Color(0xFFFF8A65).copy(alpha = 0.2f),
                            selectorBorder = BorderStroke(2.dp, Color(0xFFE57373))
                        ) { snappedDate ->
                            day= snappedDate?.dayOfMonth
                            month=snappedDate?.monthValue
                            year=snappedDate?.year
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    TextField(
                        modifier = Modifier
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
                        placeholder = { Text(text = "what is your D-Day?") },
                        value = txtField.value,
                        onValueChange = {
                            txtField.value = it.take(50)
                        })

                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if(txtField.value.isBlank()){
                                    txtFieldError.value = "Field can not be empty"
                                }else{
                                    val date="${year}-${month}-${day}"
                                    val mem=txtField.value
                                    vm.addDday(Dday(date,mem))
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
fun DdayPreview() {
    StickyMemoryTheme {
        var showDdayDialog by remember { mutableStateOf(true) }
        if (showDdayDialog) {
            //dday_ui(value = "", setShowDialog = { showDdayDialog = it })
        }

    }
}
