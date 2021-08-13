package com.example.ctf.ui.component

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.R
import com.example.ctf.data.local.entities.*
import com.example.ctf.data.remote.requests.UpdateUserRequest
import com.example.ctf.ui.BottomNavigationScreens
import com.example.ctf.ui.add.AddViewModel
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.home.HomeViewModel
import com.example.ctf.ui.profile.ProfileViewModel
import com.example.ctf.util.Status
import com.example.ctf.util.listString

import com.example.ctf.util.listString.T10L1
import com.example.ctf.util.listString.T10L2
import com.example.ctf.util.listString.T10L3
import com.example.ctf.util.listString.T10L4
import com.example.ctf.util.listString.T11L1
import com.example.ctf.util.listString.T11L2
import com.example.ctf.util.listString.T11L3
import com.example.ctf.util.listString.T11L4
import com.example.ctf.util.listString.T1L1
import com.example.ctf.util.listString.T1L2
import com.example.ctf.util.listString.T1L3
import com.example.ctf.util.listString.T1L4
import com.example.ctf.util.listString.T2L1
import com.example.ctf.util.listString.T2L2
import com.example.ctf.util.listString.T2L3
import com.example.ctf.util.listString.T2L4
import com.example.ctf.util.listString.T3L1
import com.example.ctf.util.listString.T3L2
import com.example.ctf.util.listString.T3L3
import com.example.ctf.util.listString.T3L4
import com.example.ctf.util.listString.T4L1
import com.example.ctf.util.listString.T4L2
import com.example.ctf.util.listString.T4L3
import com.example.ctf.util.listString.T4L4
import com.example.ctf.util.listString.T5L1
import com.example.ctf.util.listString.T5L2
import com.example.ctf.util.listString.T5L3
import com.example.ctf.util.listString.T5L4
import com.example.ctf.util.listString.T6L1
import com.example.ctf.util.listString.T6L2
import com.example.ctf.util.listString.T6L3
import com.example.ctf.util.listString.T6L4
import com.example.ctf.util.listString.T7L1
import com.example.ctf.util.listString.T7L2
import com.example.ctf.util.listString.T7L3
import com.example.ctf.util.listString.T7L4
import com.example.ctf.util.listString.T8L1
import com.example.ctf.util.listString.T8L2
import com.example.ctf.util.listString.T8L3
import com.example.ctf.util.listString.T8L4
import com.example.ctf.util.listString.T9L1
import com.example.ctf.util.listString.T9L2
import com.example.ctf.util.listString.T9L3
import com.example.ctf.util.listString.T9L4
import com.example.ctf.util.listString.nope


@Composable
fun ProfileInfoItem(number:String,desc:String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = number,
            style = MaterialTheme.typography.button
        )
        Text(
            text=desc,
            style= MaterialTheme.typography.body2
        )
    }
}
@Composable
fun DividerItem(){
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 7.dp,top=7.dp)
    )
}
@Composable
fun SwitchTOLoginOrRegisterTexts(modifier: Modifier,text1: String,text2: String,onClick: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text2,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button,
            modifier = Modifier.clickable { onClick() }
        )
    }
}
@Composable
fun TextFieldOutlined(desc:String,state: TextFieldState = remember {TextFieldState()}){
    OutlinedTextField(
        label={Text(text=desc)},
        value =state.text,
        onValueChange = {
            state.text = it
        }
    )
}
@Composable
fun EditTextItem(modifier: Modifier=Modifier, desc: String, state: TextFieldState=remember{ TextFieldState() }, keyboard : KeyboardOptions= KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),){
    TextField(
        value = state.text,
        onValueChange ={
            state.text=it
        },
        label = {Text(desc)},
        modifier = modifier,
        keyboardOptions = keyboard
    )
}
@Composable
fun ButtonClickItem(
    modifier: Modifier = Modifier,
    desc: String,
    onClick: () -> Unit,
    warna: Color = Color.Unspecified
) {
    Button(
        onClick =  onClick,
        border= BorderStroke(2.dp,warna),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ){
        Text(desc,style=MaterialTheme.typography.button,color= MaterialTheme.colors.onSurface)
    }
}
@Composable
fun CardDrop(dropped: Dropped){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.onSurface),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column(
            Modifier
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text("Day ${dropped.day}",style=MaterialTheme.typography.body2)
            Text(dropped.name,style = MaterialTheme.typography.h1)
            Text("${dropped.duration} hrs",style = MaterialTheme.typography.caption)
        }
    }
}
@Composable
fun ProgressBarItem(){
    Row(
        verticalAlignment =Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator()
            Spacer(Modifier.size(10.dp))
            Text(text="Please wait..")
        }
    }
}
@Composable
fun ProgressCardToastItem(){
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(12.dp),
            shape= RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.primaryVariant
        ){
            ProgressBarItem()
        }
    }
}
@Composable
fun ImageItemClick(id:Int,onIconClick: () -> Unit,modifier: Modifier=Modifier){
    Image(
        painterResource(id),
        contentDescription = null,
        modifier = Modifier
            .clickable(
                onClick = onIconClick
            )
    )
}
@Composable
fun TopBarItem(onIconClick: () -> Unit,modifier: Modifier){
    Row(modifier= modifier
        .height(45.dp)
        .background(color = MaterialTheme.colors.onError),
        Arrangement.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageItemClick(id = R.drawable.list,onIconClick)
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = "CTForever",
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5
            )
        }
    }
}
@Composable
fun ChatCard(chat: Chat){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.onSurface),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(6.dp)){
                Row(Modifier.weight(1f)) {
                    Text(getTimePost(chat.date),Modifier.fillMaxWidth())
                }
                Row(Modifier.weight(1f)) {
                    Text(chat.clubName.toString(),Modifier.fillMaxWidth())
                }
                Spacer(Modifier.padding(3.dp))
                Row(Modifier.weight(1f)) {
                    Text(chat.username.toString(),Modifier.fillMaxWidth())
                }
            }
            DividerItem()
            Text(chat.chat.toString(),Modifier.padding(6.dp),textAlign=TextAlign.Justify)
        }
    }
}
@Composable
fun TradingCard(username:String, trading: Trading, editClick: () -> Unit, deleteClick: () -> Unit ){
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(Modifier.padding(6.dp)) {

            HeaderCardItem(getTimePost(trading.date),trading.username.toString(),trading.clubName.toString())
            DividerItem()
            TwoTextItem("title",trading.title.toString())
            TwoTextItem("description", trading.desc.toString())
            Row(Modifier.fillMaxWidth(),horizontalArrangement =Arrangement.SpaceBetween){
                TwoTextItem("Buying", "${trading.amountBuying} ${trading.itemBuying}" )
                TwoTextItem("Selling", "${trading.amountSelling} ${trading.itemSelling}" )
            }
            if(username==trading.username) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    ButtonClickItem(desc = "Edit", onClick = editClick,)
                    ButtonClickItem(desc = "Delete", onClick = deleteClick)
                }
            }
        }
    }
}
@Composable
fun TwoTextItem(text1:String,text2:String){
    Column() {
        Text(text1, style = MaterialTheme.typography.caption)
        Text(text2,textAlign = TextAlign.Justify)
    }
}
@Composable
fun HeaderCardItem(text1:String,text2: String,text3:String){
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            Text(text1,Modifier.fillMaxWidth())
        }
        Row(Modifier.weight(1f)) {
            Text(text2,Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        }
        Spacer(Modifier.padding(3.dp))
        Row(Modifier.weight(1f),) {
            Text(text3,Modifier.fillMaxWidth(),textAlign = TextAlign.End)
        }
    }
}
@Composable
fun WallCard(wall: Wall?, onWall: () -> Unit, onDelete:() ->Unit){
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column {
            wall?.let {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    it.username?.let { it1 -> Text(it1) }
//                    val dateFormat=SimpleDateFormat("dd.MM.yy, HH:mm", Locale.getDefault())
//                    val dateString= dateFormat.format(it.date ?: "Date")
//                    Text(dateString)
                }
                DividerItem()
                Row(Modifier.fillMaxWidth(),Arrangement.SpaceBetween) {
                    it.chat?.let { it1 -> Text(it1) }
                    ButtonClickItem(desc = "Wall", onClick = onWall)
                    if(it.username==it.wallOwner) ButtonClickItem(desc = "Delete",onClick =onDelete )
                }
            }
        }
    }
}
@Composable
fun UserProfile(user: User?){

    if(user?.username!=""){

        user?.let {
            Column() {
                Text(it.name.toString())
                Text(it.ign.toString())
                Text(it.clubName.toString())
                Text(it.bio.toString())
                it.bio?.let { Text(it) }
                Text(it.bio!!)
                Text(it.bio!!)
                Text(text = it.bio!!)
                Text(it.bio!!)
            }
        }
    }
}
@Composable
fun EditProfileDialog(user: User){
    var openEdit by remember { mutableStateOf(true)}
    val profileVM = hiltViewModel<ProfileViewModel>()
    val nameState = remember { TextFieldState(user.name.toString()) }
    val clubNameState = remember { TextFieldState(user.clubName.toString()) }
    val ignState = remember { TextFieldState(user.ign.toString()) }
    val bioState = remember { TextFieldState(user.bio.toString()) }
    val uiState=profileVM.updateProfile.observeAsState()
    uiState.value?.let {
        val result= it.peekContent()
        when(result.status){
            Status.SUCCESS ->{
                Toast.makeText(
                    LocalContext.current,result.message ?: "Profile Updated", Toast.LENGTH_SHORT
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

    if(openEdit){
        AlertDialog(
            onDismissRequest={openEdit = false},
            title = { Text(text = "Edit Profilfe") },
            text = {
                Column(
                    Modifier
                        .padding(top = 24.dp, bottom = 12.dp, start = 6.dp, end = 6.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(12.dp))
                    Spacer(modifier = Modifier.padding(12.dp))
                    TextFieldOutlined(desc = "Name", nameState)
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextFieldOutlined(desc = "Club Name", clubNameState)
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextFieldOutlined(desc = "IGN", ignState)
                    Spacer(modifier = Modifier.padding(6.dp))
                    TextFieldOutlined(desc = "Bio", bioState)
                    Spacer(modifier = Modifier.padding(6.dp))
                }
            },
            confirmButton ={ Button(onClick = {
                profileVM.updateProfile(
                    UpdateUserRequest(
                        name = nameState.text,
                        clubName = clubNameState.text,
                        ign = ignState.text,
                        bio = bioState.text
                    )
                )
            }){
                Text("Save")
            }},
            dismissButton = {
                Button(
                    onClick = {openEdit= false}
                ){
                    Text("Cancel")
                }
            }
        )
    }
}
@Composable
fun WallList(wallList: List<Wall>, navController: NavHostController, modifier: Modifier) {
    Column( modifier=modifier) {
        wallList.forEach {
            val profileVM= hiltViewModel<ProfileViewModel>()
            val authVM= hiltViewModel<AuthViewModel>()
            WallCard(wall = it,onWall = {navController.navigate(BottomNavigationScreens.OtherProfile.withArgs(it.username.toString()))},onDelete = {
                authVM.isLoggedIn()
                authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                profileVM.deleteWall(it)})
            Spacer(modifier = Modifier.padding(6.dp))
        }
    }

}
@Composable
fun SaveTodayDialog(today: Today){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val idState = remember { TextFieldState(today._id) }
        val regulerState = remember { TextFieldState(today.reguler) }
        val ultraState = remember { TextFieldState(today.ultra) }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false},
                title = { Text(text = "POTD") },
                text = {
                    Column(
                        Modifier
                            .padding(top = 24.dp, bottom = 12.dp, start = 6.dp, end = 6.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(12.dp))
                        Spacer(modifier = Modifier.padding(12.dp))
                        TextFieldOutlined(desc = "id", idState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "reguler", regulerState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "ultra", ultraState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    homeVM.saveToday(
                        Today(
                            regulerState.text,
                            ultraState.text,
                            idState.text
                        )
                    )
                    homeVM.getToday()
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {openDialog= false}
                    ){
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
@Composable
fun AddTradingDialog(trading: Trading?,onClick: () -> Unit={}){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val addVM = hiltViewModel<AddViewModel>()
        val titleState = remember { TextFieldState(trading?.title.toString()) }
        val descState= remember {TextFieldState(trading?.desc.toString())}
        val itemBuyingState = remember { TextFieldState(trading?.itemBuying.toString()) }
        val amountBuyingState = remember { TextFieldState(trading?.amountBuying.toString()) }
        val itemSellingState = remember { TextFieldState(trading?.itemSelling.toString()) }
        val amountSellingState = remember { TextFieldState(trading?.amountSelling.toString()) }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false
                    addVM.getAllTrading()
                },
                title = { Text(text = "Trading") },
                text = {
                    Column(
                        Modifier
                            .padding(top = 24.dp, bottom = 12.dp, start = 6.dp, end = 6.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(12.dp))
                        Spacer(modifier = Modifier.padding(12.dp))
                        TextFieldOutlined(desc = "Title", titleState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Description", descState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Item Buying", itemBuyingState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Amount Buying", amountBuyingState)
                        TextFieldOutlined(desc = "Item Selling", itemSellingState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "Amount Selling", amountSellingState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    addVM.saveTrading(
                        Trading(
                            _id= trading?._id.toString(),
                            title=titleState.text,
                            desc = descState.text,
                            itemBuying = itemBuyingState.text,
                            amountBuying = amountBuyingState.text,
                            itemSelling = itemSellingState.text,
                            amountSelling= amountSellingState.text
                        )
                    )
                    addVM.getAllTrading()
                    openDialog= false
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {openDialog= false
                            addVM.getAllTrading()
                        }
                    ){
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
@Composable
fun SaveDropDialog(){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val idState = remember { TextFieldState() }
        val dayState= remember {TextFieldState()}
        val roleState = remember { TextFieldState() }
        val nameState = remember { TextFieldState() }
        val durationState = remember { TextFieldState() }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false},
                title = { Text(text = "Dropped") },
                text = {
                    Column(
                        Modifier
                            .padding(top = 24.dp, bottom = 12.dp, start = 6.dp, end = 6.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(12.dp))
                        Spacer(modifier = Modifier.padding(12.dp))
                        TextFieldOutlined(desc = "id", idState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "day", dayState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "role", roleState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "name", nameState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "duration", durationState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    homeVM.saveDrop(
                        Dropped(
                            roleState.text,
                            nameState.text,
                            durationState.text,
                            dayState.text,
                            idState.text
                        )
                    )
                    homeVM.getDropList()
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {openDialog= false
                            authVM.isLoggedIn()
                            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                            homeVM.deleteDrop(Dropped(
                                roleState.text,
                                nameState.text,
                                durationState.text,
                                dayState.text,
                                idState.text
                            ))
                            homeVM.getDropList()
                        }
                    ){
                        Text("Delete")
                    }
                }
            )
        }
    }
}
@Composable
fun SavePartyDialog(party:Party){
    Column() {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val authVM = hiltViewModel<AuthViewModel>()
        val idState = remember { TextFieldState(party._id) }
        val noState = remember { TextFieldState(party.no) }
        val roleState = remember { TextFieldState(party.role) }
        val nameState = remember { TextFieldState(party.name) }
        val durationState = remember { TextFieldState(party.duration) }
        val statusState = remember { TextFieldState(party.duration) }
        if(openDialog){
            AlertDialog(
                onDismissRequest={openDialog = false},
                title = { Text(text = "Party") },
                text = {
                    Column(
                        Modifier
                            .padding(top = 24.dp, bottom = 12.dp, start = 6.dp, end = 6.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(12.dp))
                        Spacer(modifier = Modifier.padding(12.dp))
                        TextFieldOutlined(desc = "id", idState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "no", noState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "role", roleState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "name", nameState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "duration", durationState)
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextFieldOutlined(desc = "status", statusState)
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                },
                confirmButton ={ Button(onClick = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    homeVM.saveParty(
                        Party(
                            roleState.text,
                            noState.text,
                            nameState.text,
                            durationState.text,
                            statusState.text,
                            _id=idState.text
                        )
                    )
                    homeVM.getPartyList()
                }){
                    Text("Save")
                }},
                dismissButton = {
                    Button(
                        onClick = {
                            authVM.isLoggedIn()
                            authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                            homeVM.saveParty(
                                Party(
                                    roleState.text,
                                    noState.text,
                                    nameState.text,
                                    durationState.text,
                                    statusState.text,
                                    listOf(),
                                    listOf(),
                                    listOf(),
                                    idState.text
                                )
                            )
                            homeVM.getPartyList()
                            openDialog= false
                        }
                    ){
                        Text("Awal")
                    }
                }
            )
        }
    }
}
@Composable
fun ObserveUserList(onClick: () -> Unit){
    val homeVM = hiltViewModel<HomeViewModel>()
    var userList = listOf<User>()
    val listUserState = homeVM.listUserStatus.observeAsState()
    listUserState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                userList= it.data ?: return@let
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current, it.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(6.dp),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            userList.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(12.dp),
                    shape= RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.primaryVariant
                ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(6.dp),
                        Arrangement.SpaceBetween
                    ){
                        TwoTextItem(text1 = it.username, text2 = it.name.toString())
                        TwoTextItem(text1 = it.ign.toString(), text2 = it.clubName.toString())
                    }
                }
            }
            ButtonClickItem(desc = "Close", onClick = onClick)
        }
    }
}
@Composable
fun SearchRefreshItem(desc: String,state: TextFieldState=remember{ TextFieldState() },onClick: () -> Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(6.dp),Arrangement.Center,Alignment.CenterVertically){
        OutlinedTextField(
            label={Text(text=desc)},
            value =state.text,
            onValueChange = {
                state.text = it
            }
        )
        Spacer(Modifier.padding(6.dp))
        Image(
            painterResource(id = R.drawable.search),
            contentDescription = "Search Menu",
            modifier= Modifier
                .height(24.dp)
                .clickable(
                    onClick = onClick
                )
        )
        Spacer(Modifier.padding(6.dp))
        Image(
            painterResource(id = R.drawable.refresh),
            contentDescription = "Search Menu",
            modifier= Modifier
                .height(24.dp)
                .clickable {}
        )

    }
}

@Composable
fun DropDownListItem(
    desc:String,
    state: TextFieldState = remember {TextFieldState()}
){
    val countryList= listOf(nope,
        T1L1, T1L2, T1L3, T1L4,
        T2L1, T2L2, T2L3, T2L4,
        T3L1, T3L2, T3L3, T3L4,
        T4L1, T4L2, T4L3, T4L4,
        T5L1, T5L2, T5L3, T5L4,
        T6L1, T6L2, T6L3, T6L4,
        T7L1, T7L2, T7L3, T7L4,
        T8L1, T8L2, T8L3, T8L4,
        T9L1, T9L2, T9L3, T9L4,
        T10L1, T10L2, T10L3, T10L4,
        T11L1, T11L2, T11L3, T11L4
    )
    val isOpen= remember{ mutableStateOf(false)}
    val openCloseOfDropDownList:(Boolean) -> Unit = {isOpen.value = it}
    val userSelectedString:(String) -> Unit = {state.text = it}
    Box{
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            TextFieldOutlined(desc = desc,state)
            DropdownMenu(
                modifier= Modifier.fillMaxHeight(0.6f).align(CenterHorizontally),
                expanded = isOpen.value,
                onDismissRequest = { openCloseOfDropDownList(false) }
            ) {
                countryList.forEach {
                    DropdownMenuItem(
                        onClick = {
                            openCloseOfDropDownList(false)
                            userSelectedString(it)
                        }) {
                        Text(it)
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }

}