package com.jaysdevapp.stickymemory

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jaysdevapp.stickymemory.dataclasses.Dday
import com.jaysdevapp.stickymemory.datePicker.DatePicker
import com.jaysdevapp.stickymemory.viewModel.DdayViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun editOrsendDialog_dday(
    context: Context,
    item: Dday,
    showEditDialog: Boolean,
    setShowEditDialog: (Boolean) -> Unit,
    vm: DdayViewModel = viewModel(factory = DdayViewModel.Factory(context)),
    onConfirm: () -> Unit
) {
    val TAG = object {}.javaClass.enclosingClass.name

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(item.ddayThing) }
    val dates= item.date!!.split("-")
    Log.d(TAG,dates.size.toString())
    var day : Int? = dates[2].toInt()
    var month : Int? =dates[1].toInt()
    var year: Int? =dates[0].toInt()

    if (!showEditDialog) return
    Dialog(onDismissRequest = { setShowEditDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            //Dialog name
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
                            text = "My D-day",
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
//                            .wrapContentSize(Alignment.Center)
                    ) {
                        DatePicker(
//                            size = DpSize(200.dp, 100.dp),
                            start =true,
                            startDay = day!!,
                            startMonth = month!!,
                            startYear= year!!,
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

                    //To-do data
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(23.dp)
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
                                imageVector = Icons.Filled.Check,
                                contentDescription = "",
                                tint = colorResource(R.color.moreOrange),
                                modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                            )
                        },
                        placeholder = { Text(text = "what is your D-day?") },
                        value = txtField.value,
                        onValueChange = {
                            txtField.value = it.take(50)
                        })

                    //Done
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if(txtField.value.isBlank()){
                                    txtFieldError.value = "Field can not be empty"
                                }else{
                                    item.date="${year}-${month}-${day}"
                                    item.ddayThing=txtField.value
                                    vm.updateDday(item)
                                    setShowEditDialog(false)
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


