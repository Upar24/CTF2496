package com.example.ctf.ui.profile

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.R
import com.example.ctf.data.local.entities.Trading
import com.example.ctf.data.local.entities.User
import com.example.ctf.data.local.entities.Wall
import com.example.ctf.ui.add.AddViewModel
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.component.*
import com.example.ctf.util.Constants.TIMERKEYPREF
import com.example.ctf.util.Status
import com.example.ctf.util.listString.edit
import com.example.ctf.util.listString.post
import com.example.ctf.util.listString.send
import com.example.ctf.util.listString.showless
import com.example.ctf.util.listString.showmore
import com.example.ctf.util.listString.wall
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp, top = 3.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        val authVM = hiltViewModel<AuthViewModel>()
        val profileVM= hiltViewModel<ProfileViewModel>()
        val addVM= hiltViewModel<AddViewModel>()
        var user by remember { mutableStateOf(User("Fina","","","","","",0,0,"")) }
        authVM.isLoggedIn()
        authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
        var wallList= listOf<Wall>()
        var postList=listOf<Trading>()
        var trading by remember{mutableStateOf(Trading("","",""))}
        val scaffoldState = rememberScaffoldState()
        val snackbarCoroutineScope = rememberCoroutineScope()
        val uiState=profileVM.user.observeAsState()
        uiState.value?.let {
            when(it.status){
                Status.SUCCESS ->{
                    user= it.data!!
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
        val saveWallState=profileVM.saveWallStatus.observeAsState()
        saveWallState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS ->{authVM.sharedPref.edit().putString(TIMERKEYPREF,(System.currentTimeMillis() + 25000L ).toString()).apply()
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,result.message ?: "An unknown error occured", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val getWallState=profileVM.getWallStatus.observeAsState()
        getWallState.value?.let {
            when(it.status){
                Status.SUCCESS ->{
                    wallList = it.data ?: return@let
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
        val allUserTradingState=addVM.allUserTrading.observeAsState()
        allUserTradingState.value?.let {
            when(it.status){
                Status.SUCCESS ->{
                    postList = it.data ?: return@let
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
        val deleteWallState=profileVM.deleteWallStatus.observeAsState()
        deleteWallState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS ->{
                    Toast.makeText(
                        LocalContext.current,result.message ?: "Wall Deleted", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.ERROR -> {
                    Toast.makeText(
                        LocalContext.current,result.message ?: "An unknown error occured", Toast.LENGTH_SHORT
                    ).show()
                }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val username = getUsernameLoginFunction()

        var seeMore by remember { mutableStateOf(false) }
        var visibleEdit by remember { mutableStateOf(false) }
        var visibleProfile by remember { mutableStateOf("")}

        if(visibleEdit){
            EditProfileDialog(user = user)
        }


        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(username)
            Text(if (!seeMore) showmore else showless,
                modifier = Modifier
                    .height(36.dp)
                    .clickable {
                        if (seeMore) {
                            seeMore = !seeMore
                        } else {
                            seeMore = !seeMore
                            profileVM.getUser(username)
                        }
                    })
            Image(
                painterResource(id = if (!seeMore) R.drawable.down_arrow else R.drawable.up_arrow),
                contentDescription = "Search Menu",
                modifier = Modifier
                    .height(36.dp)
                    .clickable {
                        if (seeMore) {
                            seeMore = !seeMore
                        } else {
                            seeMore = !seeMore
                            profileVM.getUser(username)
                        }
                    }
            )
        }
        if(seeMore){
            UserProfile(user = user)
            if(user.username != ""){
                ButtonClickItem(desc = edit,onClick = {visibleEdit=!visibleEdit})
            }else{
                Text("Cant get the user info profile.")
            }
        }

        var tabIndex by remember { mutableStateOf(0)}
        val profileList = listOf(post,wall)
        TabRow(selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent) {
            profileList.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleProfile=text
                    if(text==wall) profileVM.getWall(username) else addVM.getAllUserTrading(username)
                },text={
                    Text(text)
                })
            }
        }

        Scaffold(scaffoldState = scaffoldState) {
            if (visibleProfile == wall) {
                Column() {
                    val wallDescState = remember { TextFieldState() }
                    Row(Modifier.fillMaxWidth()) {
                        TextFieldOutlined(desc = wall, wallDescState)
                        ButtonClickItem(desc = send, onClick = {
                            if (authVM.timerLeft() <= 0) {
                                profileVM.saveWall(
                                    Wall(
                                        username,
                                        user.username,
                                        user.clubName,
                                        username,
                                        wallDescState.text,
                                    ),
                                    username
                                )
                            } else {
                                snackbarCoroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar("wait ${authVM.timerLeft()} seconds left")
                                }
                            }
                        })
                    }
                    WallList(wallList, navController,Modifier
                        .verticalScroll(rememberScrollState()))
                    Text("wall $username")
                }
            }else{
                Column (
                    Modifier.verticalScroll(rememberScrollState())
                ){
                    postList.forEach { trading1 ->
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
                                }
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.padding(6.dp))
    }
}




