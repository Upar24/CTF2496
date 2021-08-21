package com.example.ctf.ui.home

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.R
import com.example.ctf.data.local.entities.Chat
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.component.*
import com.example.ctf.util.Constants
import com.example.ctf.util.Status
import com.example.ctf.util.listString.hotsale
import com.example.ctf.util.listString.lbhneeded
import com.example.ctf.util.listString.lbhpost
import com.example.ctf.util.listString.random
import com.example.ctf.util.listString.send
import com.example.ctf.util.listString.unknownerrortoast
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(navController:NavHostController){
    Column (
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        val homeVM = hiltViewModel<HomeViewModel>()
        val authVM = hiltViewModel<AuthViewModel>()
        val listChat = listOf(lbhpost,lbhneeded,hotsale,random)
        var visibleChat by remember{ mutableStateOf(lbhpost) }
        val typeState = remember { TextFieldState() }
        val lbhpostList= mutableListOf<Chat>()
        val lbhneededList= mutableListOf<Chat>()
        val hotsaleList= mutableListOf<Chat>()
        val randomList= mutableListOf<Chat>()
        val username= getUsernameLoginFunction()
        val scaffoldState = rememberScaffoldState()
        val snackbarCoroutineScope = rememberCoroutineScope()
        val saveChatState=homeVM.saveChatStatus.observeAsState()
        saveChatState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS ->{
                    authVM.sharedPref.edit().putString(Constants.TIMERKEYPREF,(System.currentTimeMillis() + 25000L ).toString()).apply()
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val getChatsState=homeVM.getChatStatus.observeAsState()
        getChatsState.value?.let {
            when(it.status){
                Status.SUCCESS ->{
                    it.data?.forEach { chat->
                        when (chat.type) {
                            lbhpost -> {
                                lbhpostList.add(chat)
                            }
                            lbhneeded -> {
                                lbhneededList.add(chat)
                            }
                            hotsale -> {
                                hotsaleList.add(chat)
                            }
                            else -> {
                                randomList.add(chat)
                            }
                        }
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        Card(
            border= BorderStroke(1.dp, MaterialTheme.colors.onSurface),
            shape= RoundedCornerShape(8.dp),
            backgroundColor = Color.Transparent
        ){
            var tabIndex by remember { mutableStateOf(1)}
            ScrollableTabRow(selectedTabIndex = tabIndex,Modifier.fillMaxWidth(),
                backgroundColor =Color.Transparent) {
                listChat.forEachIndexed { index,text ->
                    Tab(selected=tabIndex==index,onClick={
                        tabIndex=index
                        visibleChat=text
                    },text= {
                        Text( text,
                            Modifier
                                .padding(2.dp)
                                .clickable { visibleChat = text },
                            style = if (visibleChat == text) MaterialTheme.typography.button else MaterialTheme.typography.body2,
                            color = if (visibleChat == text) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onSecondary
                        )
                    }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.padding(6.dp))
        Scaffold(scaffoldState = scaffoldState) {
            Column {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(Modifier.weight(6f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        TextFieldOutlined(desc = visibleChat, state = typeState)
                    }
                    Row(Modifier.weight(2f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        ButtonClickItem(desc = send, onClick = {
                            if (authVM.timerLeft() <= 0) {
                                authVM.isLoggedIn()
                                authVM.authenticateApi(
                                    authVM.usernamevm ?: "",
                                    authVM.passwordvm ?: ""
                                )
                                homeVM.saveChat(
                                    Chat(
                                        username = username,
                                        chat = typeState.text,
                                        type = visibleChat
                                    )
                                )
                            } else {
                                snackbarCoroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("wait ${authVM.timerLeft()} seconds left")
                                }
                            }
                        })
                    }
                    Row(Modifier.weight(0.8f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        ImageItemClick(
                            id = R.drawable.refresh,
                            onIconClick = { homeVM.getChat() })
                    } 
                }
                Spacer(modifier = Modifier.padding(3.dp))
                DividerItem()
                val listChatDisplay: MutableList<Chat> = when (visibleChat){
                    lbhpost -> lbhpostList
                    lbhneeded -> lbhneededList
                    hotsale -> hotsaleList
                    else -> randomList
                }
                Column(
                    Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    listChatDisplay.forEach { chat ->
                        ChatCard(chat = chat,navController)
                        Spacer(modifier = Modifier.padding(3.dp))
                    }
                }
            }
        }
    }
}