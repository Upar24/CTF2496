package com.example.ctf.ui.profile

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
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
import com.example.ctf.data.local.entities.Trading
import com.example.ctf.data.local.entities.User
import com.example.ctf.data.local.entities.Wall
import com.example.ctf.ui.add.AddViewModel
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.component.*
import com.example.ctf.util.listString.NO_USERNAME
import com.example.ctf.util.listString.TIMERKEYPREF
import com.example.ctf.util.Status
import com.example.ctf.util.listString.edit
import com.example.ctf.util.listString.post
import com.example.ctf.util.listString.showless
import com.example.ctf.util.listString.showmore
import com.example.ctf.util.listString.wall
import kotlinx.coroutines.launch
@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AdvertView()
        val authVM = hiltViewModel<AuthViewModel>()
        val profileVM = hiltViewModel<ProfileViewModel>()
        val addVM = hiltViewModel<AddViewModel>()
        var user by remember { mutableStateOf(User("Fina", "", "", "", "", "Please check your connection.",  System.currentTimeMillis()-61000,"")) }
        GetAuthenticateFunction()
        val wallList = mutableListOf<Wall>()
        val postList = mutableListOf<Trading>()
        var trading by remember { mutableStateOf(Trading("", "", "")) }
        val scaffoldState = rememberScaffoldState()
        val snackbarCoroutineScope = rememberCoroutineScope()
        val uiState = profileVM.user.observeAsState()
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
        val saveWallState = profileVM.saveWallStatus.observeAsState()
        saveWallState.value?.let {
            val result = it.peekContent()
            when (result.status) {
                Status.SUCCESS -> {
                    authVM.sharedPref.edit()
                        .putString(TIMERKEYPREF, (System.currentTimeMillis() + 25000L).toString())
                        .apply()
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val getWallState = profileVM.getWallStatus.observeAsState()
        getWallState.value?.let {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { listWall ->
                        wallList.clear()
                        listWall.forEach { wall ->
                            wallList.add(wall)
                        }
                    } ?: wallList.clear()
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val allUserTradingState = addVM.allUserTrading.observeAsState()
        allUserTradingState.value?.let {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { listTrading ->
                        postList.clear()
                        listTrading.forEach { trading ->
                            postList.add(trading)
                        }
                    } ?: postList.clear()
                }
                Status.ERROR -> { }
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val deleteWallState = profileVM.deleteWallStatus.observeAsState()
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
        val deletionState= profileVM.deletionStatus.observeAsState()
        deletionState.value?.let{
            when(it.status){
                Status.SUCCESS ->{
                    Toast.makeText(
                    LocalContext.current, "Success",
                    Toast.LENGTH_SHORT).show()}
                Status.ERROR -> {
                    Toast.makeText(
                    LocalContext.current, "Try again",
                    Toast.LENGTH_SHORT).show()}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val username= getUsernameLoginFunction()
        var seeMore by remember { mutableStateOf(false) }
        var visibleEdit by remember { mutableStateOf(false) }
        var visibleProfile by remember { mutableStateOf(wall) }
        if (visibleEdit) {
            EditProfileDialog(user = user,onClick={visibleEdit=!visibleEdit},username)
        }
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text(username,style=MaterialTheme.typography.subtitle1,color=MaterialTheme.colors.onBackground)
            Text("DELETE",Modifier.clickable(onClick = {profileVM.deletion()}))
            Text(if (!seeMore) showmore else showless,
                modifier = Modifier
                    .clickable {
                        if (seeMore) {
                            seeMore = !seeMore
                        } else {
                            seeMore = !seeMore
                            profileVM.getUser(username)
                        }
                    },style=MaterialTheme.typography.subtitle1,color=MaterialTheme.colors.onBackground
            )
        }
        if (seeMore) {
            UserProfile(user = user)
            if (user.username == username){
                ButtonClickItem(desc = edit, onClick = { visibleEdit = !visibleEdit })
            } else {
                Text("")
            }
        }

        var tabIndex by remember { mutableStateOf(0) }
        val profileList = listOf(wall, post)
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent
        ) {
            profileList.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                    visibleProfile = text
                    if (text == wall) profileVM.getWall(username) else addVM.getAllUserTrading(username)
                }, text = {
                    Text(text,
                        style = if (visibleProfile == text) MaterialTheme.typography.h2 else MaterialTheme.typography.button,
                        color = if (visibleProfile == text) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground)
                })
            }
        }
        Scaffold(scaffoldState = scaffoldState) {
            if (visibleProfile == wall) {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ){
                    val wallDescState = remember { TextFieldState ()}
                    val (wallConstraint, wallChat, spacerText) = createRefs()
                    Spacer(
                        modifier = Modifier
                            .constrainAs(spacerText) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }
                            .padding(0.dp)
                    )
                    Column(Modifier.constrainAs(wallConstraint){
                        start.linkTo(parent.start)
                        top.linkTo(spacerText.bottom)
                        end.linkTo(parent.end)
                    }) {
                        WallList(
                            wallList, navController,Modifier
                                .verticalScroll(rememberScrollState()))
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .constrainAs(wallChat) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                            },verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                        Card(
                            Modifier.fillMaxWidth(),
                            backgroundColor = MaterialTheme.colors.background
                        ){
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                                Row(Modifier.weight(5f),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Center){
                                    EditTextStringItem(state=wallDescState)
                                }
                                Spacer(Modifier.padding(2.dp))
                                Row(Modifier.weight(1f),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.Start){
                                    val context= LocalContext.current
                                    IconButton(onClick ={
                                        if(username == NO_USERNAME){
                                            Toast.makeText( context,"Please Login First.", Toast.LENGTH_SHORT).show()
                                        }else{
                                            if (authVM.timerLeft() <= 0) {
                                                profileVM.saveWall(
                                                    Wall(
                                                        username,
                                                        user.ign,
                                                        user.clubName,
                                                        username,
                                                        if(wallDescState.text.length > 101)wallDescState.text.substring(0,100) else wallDescState.text,
                                                    ),
                                                    username
                                                )
                                            } else {
                                                snackbarCoroutineScope.launch {
                                                    scaffoldState.snackbarHostState.showSnackbar(
                                                        "wait ${authVM.timerLeft()} seconds left"
                                                    )
                                                }
                                            }
                                        }
                                    },modifier = Modifier
                                        .then(Modifier.size(50.dp))
                                        .border(
                                            1.dp,
                                            MaterialTheme.colors.background,
                                            shape = CircleShape
                                        )
                                        .background(
                                            MaterialTheme.colors.primary,
                                            shape = CircleShape
                                        )
                                    ){
                                        Icon(imageVector = Icons.Filled.Send, contentDescription = "",
                                            tint=MaterialTheme.colors.background)
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    postList.forEach { trading1 ->
                        Card(
                            Modifier.fillMaxWidth(),
                            border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
                            shape = RoundedCornerShape(8.dp),
                            backgroundColor = MaterialTheme.colors.secondary
                        ) {
                            var opendDialog by remember { mutableStateOf(false) }
                            if (opendDialog) AddTradingDialog(trading = trading1)
                            TradingCard(username, trading1,
                                editClick = {
                                    trading = trading1
                                    opendDialog = !opendDialog
                                },
                                deleteClick = {
                                    authVM.isLoggedIn()
                                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                                    addVM.deleteTrading(trading1)
                                    addVM.getAllUserTrading(username)
                                },navController
                            )
                        }
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                }
            }
        }
    }
}








