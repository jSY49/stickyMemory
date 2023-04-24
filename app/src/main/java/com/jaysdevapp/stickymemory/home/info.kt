package com.jaysdevapp.stickymemory.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jaysdevapp.stickymemory.R

@Composable
fun info(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
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
            Text(text = stringResource(R.string.How_info),fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(R.string.How_info1) )
            Image(painter = painterResource(R.drawable.info1), contentDescription ="info1" , modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(R.string.How_info2) )
            Image(painter = painterResource(R.drawable.info2), contentDescription ="info2"  , modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(R.string.How_info3) )
            Image(painter = painterResource(R.drawable.info3), contentDescription ="info3"  , modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = stringResource(R.string.How_info4) )
            Image(painter = painterResource(R.drawable.info4), contentDescription ="info4"  , modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), contentScale = ContentScale.FillWidth)
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}
