package com.example.ctf.ui.add

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.data.local.entities.Trading
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.component.*
import com.example.ctf.util.Status
import com.example.ctf.util.listString
import com.example.ctf.util.listString.all
import com.example.ctf.util.listString.buying
import com.example.ctf.util.listString.search
import com.example.ctf.util.listString.selling
import com.example.ctf.util.listString.title

@Composable
fun AddScreen(navController: NavHostController){
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp),
        verticalArrangement = Arrangement.Top,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AddTradingDialog(Trading("","","","","","","","",""))
        val addVM = hiltViewModel<AddViewModel>()
        val authVM = hiltViewModel<AuthViewModel>()
        val username = getUsernameLoginFunction()
        var visibleScreen by remember { mutableStateOf(buying) }
        val queryState = remember { TextFieldState() }
        val allTradingList = mutableListOf<Trading>()
        val titleTradingList = mutableListOf<Trading>()
        val buyingTradingList = mutableListOf<Trading>()
        val sellingTradingList = mutableListOf<Trading>()
        var trading by remember{mutableStateOf(Trading("","",""))}
        val saveState= addVM.saveStatus.observeAsState()
        saveState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS -> {}
                Status.ERROR -> {}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val deleteState= addVM.deleteStatus.observeAsState()
        deleteState.value?.let {
            val result= it.peekContent()
            when(result.status){
                Status.SUCCESS -> {}
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val tradingState= addVM.tradingStatus.observeAsState()
        tradingState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {trading1->
                        trading = trading1
                    }
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val allTradingState= addVM.allTradingStatus.observeAsState()
        allTradingState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {listTrading->
                        allTradingList.clear()
                        titleTradingList.clear()
                        buyingTradingList.clear()
                        sellingTradingList.clear()
                        listTrading.forEach { trading ->
                            allTradingList.add(trading)
                            titleTradingList.add(trading)
                            buyingTradingList.add(trading)
                            sellingTradingList.add(trading)
                        }
                    }
                }
                Status.ERROR -> {  }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val titleSearchState= addVM.titleSearchStatus.observeAsState()
        titleSearchState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {listTrading->
                        titleTradingList.clear()
                        listTrading.forEach {trading ->
                            titleTradingList.add(trading)
                        }
                    }
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val buyingSearchState= addVM.buyingSearchStatus.observeAsState()
        buyingSearchState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {listTrading->
                        buyingTradingList.clear()
                        listTrading.forEach {trading ->
                            buyingTradingList.add(trading)
                        }
                    }
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val sellingSearchState= addVM.sellingSearchStatus.observeAsState()
        sellingSearchState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {listTrading->
                        sellingTradingList.clear()
                        listTrading.forEach { trading ->
                            sellingTradingList.add(trading)
                        }
                    }
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        TextFieldOutlined(desc = search,queryState)
        Spacer(modifier = Modifier.padding(6.dp))
        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            ButtonClickItem(
                desc = buying, onClick = {
                    visibleScreen = buying
                    if(queryState.text.isEmpty()){
                        addVM.getAllTrading()}
                    else{
                        addVM.getBuyingSearch(queryState.text)
                    }
                },warna=if(visibleScreen== buying)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
            ButtonClickItem(
                desc = all, onClick = {
                    visibleScreen = all
                    addVM.getAllTrading()
                },warna=if(visibleScreen== all)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
            ButtonClickItem(
                desc = title, onClick = {
                    visibleScreen = title
                    if(queryState.text.isEmpty()){
                        addVM.getAllTrading()
                    }
                    else{
                        addVM.getTitleSearch(queryState.text) }
                },warna=if(visibleScreen== title)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
            ButtonClickItem(
                desc =selling, onClick = {
                    visibleScreen = selling
                    if(queryState.text.isEmpty()){
                        addVM.getAllTrading()
                    }
                    else{
                        addVM.getSellingSearch(queryState.text) }
                },warna=if(visibleScreen== selling)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
        }
        DividerItem()
        val listDisplay:MutableList<Trading> = when (visibleScreen){
            buying -> buyingTradingList
            selling -> sellingTradingList
            title -> titleTradingList
            else -> allTradingList
        }
        Column (
            Modifier.verticalScroll(rememberScrollState())
        ){
            listDisplay.forEach { trading1 ->
                Card(
                    Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.secondary
                ){
                    var opendDialog by remember { mutableStateOf(false)}
                    if(opendDialog) AddTradingDialog(trading = trading1)
                    TradingCard(username,trading1,
                        editClick= {
                            trading = trading1
                            opendDialog = !opendDialog
                        },
                        deleteClick = {
                            authVM.isLoggedIn()
                            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                            addVM.deleteTrading(trading1)
                        },navController
                    )
                }
                Spacer(modifier = Modifier.padding(3.dp))
            }
        }

    }

}