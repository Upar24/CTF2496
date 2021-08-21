package com.example.ctf.ui.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.ctf.util.Constants
import com.example.ctf.util.Status
import com.example.ctf.util.listString.edit
import com.example.ctf.util.listString.post
import com.example.ctf.util.listString.send
import com.example.ctf.util.listString.showless
import com.example.ctf.util.listString.showmore
import com.example.ctf.util.listString.wall
import kotlinx.coroutines.launch

@Composable
fun OtherProfileScreen(pengguna:String,navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        val authVM = hiltViewModel<AuthViewModel>()
        val addVM = hiltViewModel<AddViewModel>()
        val profileVM= hiltViewModel<ProfileViewModel>()
        var user by remember { mutableStateOf(User("Fina","","","","","","")) }
        GetAuthenticateFunction()
        var wallList= listOf<Wall>()
        var postList=listOf<Trading>()
        var trading by remember{mutableStateOf(Trading("","",""))}
        val username= getUsernameLoginFunction()
        val scaffoldState = rememberScaffoldState()
        val snackbarCoroutineScope = rememberCoroutineScope()
        val uiState=profileVM.user.observeAsState()
        uiState.value?.let {
            when(it.status){
                Status.SUCCESS ->{
                    user= it.data!!
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val saveWallState=profileVM.saveWallStatus.observeAsState()
        saveWallState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS -> {
                    authVM.sharedPref.edit()
                        .putString(Constants.TIMERKEYPREF, (System.currentTimeMillis() + 25000L).toString())
                        .apply()
                }
                Status.ERROR -> {  }
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
                Status.ERROR -> {  }
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
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val deleteWallState=profileVM.deleteWallStatus.observeAsState()
        deleteWallState.value?.let {
            val result = it.peekContent()
            when(result.status){
                Status.SUCCESS ->{ }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        var seeMore by remember { mutableStateOf(false) }
        var visibleEdit by remember { mutableStateOf(false) }
        var visibleProfile by remember { mutableStateOf(wall)}
        if(visibleEdit){
            EditProfileDialog(user = user,onClick={visibleEdit=!visibleEdit},pengguna)
        }
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(pengguna)
            Row(
                verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.End
            ){
                Text(if (!seeMore) showmore else showless,
                modifier = Modifier
                    .height(36.dp)
                    .clickable {
                        if (seeMore) {
                            seeMore = !seeMore
                        } else {
                            seeMore = !seeMore
                            profileVM.getUser(pengguna)
                        }
                    })
                Spacer(Modifier.padding(6.dp))
                Image(
                    painterResource(id = if (!seeMore) R.drawable.down_arrow else R.drawable.up_arrow),
                    contentDescription = "Search Menu",
                    modifier = Modifier
                        .height(27.dp)
                        .clickable {
                            if (seeMore) {
                                seeMore = !seeMore
                            } else {
                                seeMore = !seeMore
                                profileVM.getUser(pengguna)
                            }
                        }
                )
            }
        }
        if(seeMore){
            UserProfile(user = user,navController)
            if(user.username != ""){
                ButtonClickItem(desc = edit,onClick = {visibleEdit=!visibleEdit})
            }else{
                Text("Cant get the user info profile.")
            }
        }

        var tabIndex by remember { mutableStateOf(0)}
        val profileList = listOf(wall, post)
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent
        ) {
            profileList.forEachIndexed { index,text ->
                Tab(selected=tabIndex==index,onClick={
                    tabIndex=index
                    visibleProfile=text
                    if(text==wall) profileVM.getWall(pengguna) else addVM.getAllUserTrading(pengguna)
                },text={
                    Text(text, modifier = Modifier
                        .padding(6.dp),
                        style = if (visibleProfile == text) MaterialTheme.typography.button else MaterialTheme.typography.body2,
                        color = if (visibleProfile == text) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onSecondary)
                })
            }
        }
        Scaffold(scaffoldState = scaffoldState) {
            if(visibleProfile==wall){
                Column (horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center){
                    val wallDescState = remember { TextFieldState() }
                    Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                        Row(Modifier.weight(4f),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                            TextFieldOutlined(desc = wall,wallDescState)
                        }
                        Row(Modifier.weight(1f),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                            ButtonClickItem(desc = send,onClick = {
                                if(authVM.timerLeft()<=0){
                                    profileVM.saveWall(
                                        Wall(
                                            username,
                                            user.name,
                                            user.clubName,
                                            pengguna,
                                            wallDescState.text),
                                        pengguna
                                    )
                                }else{
                                    snackbarCoroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar("wait ${authVM.timerLeft()} seconds left")
                                    }
                                }
                            })
                        }
                    }
                    DividerItem()
                    WallList(
                        wallList,navController,Modifier
                        .verticalScroll(rememberScrollState()))
                }
            }else{
                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    DividerItem()
                    postList.forEach{trading1 ->
                        Card (
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
    }
}
