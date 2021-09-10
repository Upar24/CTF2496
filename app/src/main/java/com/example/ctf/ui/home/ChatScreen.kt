package com.example.ctf.ui.home

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.data.local.entities.Chat
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.component.*
import com.example.ctf.util.listString.NO_USERNAME
import com.example.ctf.util.Status
import com.example.ctf.util.listString.TIMERKEYPREF
import com.example.ctf.util.listString.hotsale
import com.example.ctf.util.listString.lbhneeded
import com.example.ctf.util.listString.lbhpost
import com.example.ctf.util.listString.random
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(navController:NavHostController) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val homeVM = hiltViewModel<HomeViewModel>()
        val authVM = hiltViewModel<AuthViewModel>()
        val listChat = listOf(lbhpost, lbhneeded, hotsale, random)
        var visibleChat by remember { mutableStateOf(lbhpost) }
        val typeState = remember { TextFieldState() }
        val lbhpostList = mutableListOf<Chat>()
        val lbhneededList = mutableListOf<Chat>()
        val hotsaleList = mutableListOf<Chat>()
        val randomList = mutableListOf<Chat>()
        val username = getUsernameLoginFunction()
        val scaffoldState = rememberScaffoldState()
        val snackbarCoroutineScope = rememberCoroutineScope()
        val saveChatState = homeVM.saveChatStatus.observeAsState()
        saveChatState.value?.let {
            val result = it.peekContent()
            when (result.status) {
                Status.SUCCESS -> {
                    authVM.sharedPref.edit().putString(
                        TIMERKEYPREF,
                        (System.currentTimeMillis() + 25000L).toString()
                    ).apply()
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val getChatsState = homeVM.getChatStatus.observeAsState()
        getChatsState.value?.let {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.forEach { chat ->
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
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        var tabIndex by remember { mutableStateOf(0) }
        ScrollableTabRow(
            selectedTabIndex = tabIndex, Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent
        ) {
            listChat.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                    visibleChat = text
                }, text = {
                    Text(
                        text,
                        Modifier
                            .clickable { visibleChat = text },
                        style = if (visibleChat == text) MaterialTheme.typography.h2 else MaterialTheme.typography.button,
                        color = if (visibleChat == text) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                    )
                }
                )
            }
        }
        Scaffold(scaffoldState = scaffoldState) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (chatListConst, chatMsg, spacerText) = createRefs()
                val listChatDisplay: MutableList<Chat> = when (visibleChat) {
                    lbhpost -> lbhpostList
                    lbhneeded -> lbhneededList
                    hotsale -> hotsaleList
                    else -> randomList
                }
                Spacer(
                    modifier = Modifier
                        .constrainAs(spacerText) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .padding(0.dp)
                )
                Column(
                    Modifier
                        .constrainAs(chatListConst) {
                            start.linkTo(parent.start)
                            top.linkTo(spacerText.bottom)
                            end.linkTo(parent.end)
                        }
                        .verticalScroll(rememberScrollState())
                ) {
                    listChatDisplay.forEach { chat ->
                        ChatCard(chat = chat, navController)
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                }
                Card(
                    Modifier
                        .fillMaxWidth()
                        .constrainAs(chatMsg) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        },
                    backgroundColor = MaterialTheme.colors.background
                ) {
                    Row(
                        Modifier.fillMaxWidth().padding(3.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            Modifier.weight(5f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            EditTextStringItem(state = typeState, text = visibleChat)
                        }
                        Row(
                            Modifier.weight(1f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val login : Boolean = getUsernameLoginFunction()==NO_USERNAME
                            val context = LocalContext.current
                            IconButton(
                                onClick = {
                                    if(login){
                                        Toast.makeText( context,"Please Login First.", Toast.LENGTH_SHORT,
                                        ).show()
                                    }else{
                                        if (authVM.timerLeft() <= 0) {
                                            authVM.isLoggedIn()
                                            authVM.authenticateApi(
                                                authVM.usernamevm ?: "",
                                                authVM.passwordvm ?: ""
                                            )
                                            homeVM.saveChat(
                                                Chat(
                                                    username = username,
                                                    chat = if(typeState.text.length > 101) typeState.text.substring(0,100) else typeState.text,
                                                    type = visibleChat
                                                )
                                            )
                                        } else {
                                            snackbarCoroutineScope.launch {
                                                scaffoldState.snackbarHostState.showSnackbar("wait ${authVM.timerLeft()} seconds left")
                                            }
                                        }
                                    }
                                }, modifier = Modifier
                                    .then(Modifier.size(50.dp))
                                    .border(
                                        1.dp,
                                        MaterialTheme.colors.background,
                                        shape = CircleShape
                                    )
                                    .background(MaterialTheme.colors.primary, shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Send, contentDescription = "",
                                    tint = MaterialTheme.colors.background
                                )
                            }
                        }
                        Row(
                            Modifier.weight(1f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { homeVM.getChat() }, modifier = Modifier
                                    .then(Modifier.size(50.dp))
                                    .border(
                                        1.dp,
                                        MaterialTheme.colors.background,
                                        shape = CircleShape
                                    )
                                    .background(MaterialTheme.colors.primary, shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Refresh, contentDescription = "",
                                    tint = MaterialTheme.colors.background
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}