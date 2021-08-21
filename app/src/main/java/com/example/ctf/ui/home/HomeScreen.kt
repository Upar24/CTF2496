package com.example.ctf.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ctf.util.listString.calculation
import com.example.ctf.util.listString.chatchar
import com.example.ctf.util.listString.partychar

@Composable
fun HomeScreen(navController: NavHostController
) {
    val homeVM= hiltViewModel<HomeViewModel>()
    var visibleHome by remember { mutableStateOf(calculation)}
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp)
    ) {
        var tabIndex by remember { mutableStateOf(1)}
        val homeItem = listOf(partychar, calculation,chatchar)
        TabRow(selectedTabIndex = tabIndex,Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent) {
            homeItem.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleHome=text
                },text={
                    Text(text,color=if (visibleHome == text) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onSecondary,
                        style = if (visibleHome == text) MaterialTheme.typography.subtitle2 else MaterialTheme.typography.body2
                    )
                })
            }
        }
        if(visibleHome== partychar){
            homeVM.getToday()
            homeVM.getDropList()
            homeVM.getPartyList()
            PartyScreen()
        }else if(visibleHome== chatchar){
            ChatScreen(navController)
            homeVM.getChat()
        }else{
            CalculationScreen()
        }
    }
}
