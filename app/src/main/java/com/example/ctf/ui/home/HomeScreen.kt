package com.example.ctf.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun HomeScreen(
) {
    val homeVM= hiltViewModel<HomeViewModel>()
    var visibleHome by remember { mutableStateOf("calculation")}
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 60.dp)
    ) {
        var tabIndex by remember { mutableStateOf(1)}
        var homeItem = listOf("party","calculation","chat")
        TabRow(selectedTabIndex = tabIndex,Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent) {
            homeItem.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleHome=text
                },text={
                    Text(text,color=if (visibleHome == text) Color.Magenta else Color.Unspecified)
                })
            }
        }
        if(visibleHome=="party"){
            homeVM.getToday()
            homeVM.getDropList()
            homeVM.getPartyList()
            PartyScreen()
        }else if(visibleHome=="calculation"){
            CalculationScreen()
        }else{
            ChatScreen()
            homeVM.getChat()
        }
    }
}
