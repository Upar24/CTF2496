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
fun ChatScreen(){
    Column (
        Modifier
            .fillMaxSize()
            .padding(6.dp),
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
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,result.message ?: unknownerrortoast, Toast.LENGTH_SHORT
                    ).show()
                }
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
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,it.message ?: "An unknown error occured", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        Button(onClick={} ){
            Text("Click")
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
                                .padding(6.dp)
                                .clickable { visibleChat = text },
                            style = if (visibleChat == text) MaterialTheme.typography.button else MaterialTheme.typography.body1,
                            color = if (visibleChat == text) Color.Magenta else MaterialTheme.colors.onBackground
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
                        .fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(Modifier.weight(5f)) {
                        EditTextItem(desc = visibleChat, state = typeState)
                    }
                    Row(Modifier.weight(2f)) {

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

                    Spacer(Modifier.padding(3.dp))
                    Row(Modifier.weight(1f)) {
                        ImageItemClick(
                            id = R.drawable.refresh,
                            onIconClick = { homeVM.getChat() })
                    }
                }
                DividerItem()
                Text(text = visibleChat)
                val listChatDisplay: MutableList<Chat> = when (visibleChat) {
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
                        ChatCard(chat = chat)
                    }
                }
            }
        }
    }
}