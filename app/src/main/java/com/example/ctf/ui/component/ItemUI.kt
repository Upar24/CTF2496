package com.example.ctf.ui.component

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
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
import com.example.ctf.util.listString.awal
import com.example.ctf.util.listString.cancel
import com.example.ctf.util.listString.editprofile
import com.example.ctf.util.listString.nope
import com.example.ctf.util.listString.pleasewait
import com.example.ctf.util.listString.reguler
import com.example.ctf.util.listString.save
import com.example.ctf.util.listString.title
import com.example.ctf.util.listString.ultra
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

@Composable
fun ProfileInfoItem(number:String,desc:String){
    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = number,
            style = MaterialTheme.typography.button,
            color=MaterialTheme.colors.onBackground
        )
        Spacer(Modifier.padding(2.dp))
        Text(
            text=desc,
            style= MaterialTheme.typography.body1,
            color=MaterialTheme.colors.onBackground
        )
    }
}
@Composable
fun DividerItem(){
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = .2f),
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 2.dp,top=7.dp)
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
        label={Text(text=desc,style=MaterialTheme.typography.body1)},
        value =state.text,
        onValueChange = {
            state.text = it
        },
        textStyle = MaterialTheme.typography.body2,
        shape=RoundedCornerShape(8.dp)
    )
}
//lam9
@Composable
fun EditTextStringItem(state: TextFieldState = remember {TextFieldState()},text:String="Type Something"){
    TextField(
        value =state.text,
        onValueChange = {
            state.text = it
        },
        textStyle=MaterialTheme.typography.body2,
        placeholder={Text(text,style=MaterialTheme.typography.body1)},
        shape= RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),

        )
}
@Composable
fun EditTextItem(modifier: Modifier=Modifier, desc: String, state: TextFieldState=remember{ TextFieldState() }, keyboard : KeyboardOptions= KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)) {
    TextField(
        value = state.text,
        onValueChange ={string ->
            if(string.isNotEmpty()){
                val result = string.filter{it.isLetterOrDigit()}
                state.text=NumberFormat.getNumberInstance(Locale.US).format(result.toLong())
            }else{
                state.text="0"
            }
        },
        placeholder = {Text(desc,style = MaterialTheme.typography.body1)},
        modifier = modifier,
        keyboardOptions = keyboard,
        shape= RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle=MaterialTheme.typography.body2
    )
}
@Composable
fun ButtonClickItem(
    modifier: Modifier = Modifier,
    desc: String,
    onClick: () -> Unit,
    bordercolor: Color = MaterialTheme.colors.background,
    colors:ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
) {
    Button(
        onClick =  onClick,
        border= BorderStroke(2.dp,bordercolor),
        colors = colors,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ){
        Text(desc,style=MaterialTheme.typography.button,color= bordercolor)
    }
}
@Composable
fun CardDrop(dropped: Dropped){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.primaryVariant),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column(
            Modifier
                .padding(8.dp),
            horizontalAlignment = CenterHorizontally,
        ){
            Text("Day ${dropped.day}",style=MaterialTheme.typography.body1,color=MaterialTheme.colors.onBackground)
            Text(dropped.name,style = MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
            Text("${dropped.duration} hrs",style = MaterialTheme.typography.body1,color=MaterialTheme.colors.onBackground)
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
            Text(text=pleasewait,style=MaterialTheme.typography.body1,color=MaterialTheme.colors.onBackground)
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
                .padding(16.dp),
            shape= RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.secondary
        ){
            ProgressBarItem()
        }
    }
}
@Composable
fun SvgItemClick(icon:Int,onClick: () -> Unit){
    Icon(
        painter = painterResource(id = icon),"",
        tint=MaterialTheme.colors.primary,
        modifier=Modifier.clickable(onClick=onClick))
}
@Composable
fun IconItemClick(icon: ImageVector, onIconClick: () -> Unit){
    Icon(
        imageVector = icon,
        contentDescription ="",
        tint=MaterialTheme.colors.primary,
        modifier= Modifier
            .clickable(onClick = onIconClick)
            .size(24.dp)
    )
}
@Composable
fun TopBarItem(onIconClick: () -> Unit){
    Row(Modifier.padding(8.dp),verticalAlignment = Alignment.CenterVertically){
        IconItemClick(icon = Icons.Filled.Menu,onIconClick = onIconClick)
        Text(
            text = "CTForever",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h1,
            modifier=Modifier.padding(start=16.dp)
        )
    }
}
@Composable
fun ChatCard(chat: Chat,navController: NavHostController){
    Card(
        border=BorderStroke(1.dp,MaterialTheme.colors.primaryVariant),
        shape= RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ){
        Column(Modifier.padding(8.dp)) {
            HeaderCardItem(text1 = getTimePost(chat.date), text2 = chat.username.toString(), text3 = chat.clubName.toString(),navController)
            DividerItem()
            Text(chat.chat.toString(),textAlign=TextAlign.Justify,color=MaterialTheme.colors.onBackground,style=MaterialTheme.typography.body1)
        }
    }
}
@Composable
fun TradingCard(username:String, trading: Trading, editClick: () -> Unit, deleteClick: () -> Unit ,navController: NavHostController){
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    )
    {
        Column(Modifier.padding(8.dp)) {

            HeaderCardItem(getTimePost(trading.date),trading.username.toString(),trading.ign.toString(),navController)
            DividerItem()
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween,Alignment.CenterVertically){
                Row(Modifier.weight(5f),horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically){
                    TwoTextItem(title,trading.title.toString())
                }
                if(username==trading.username){
                    Row(Modifier.weight(1f),horizontalArrangement=Arrangement.Center,verticalAlignment=Alignment.CenterVertically){
                        IconItemClick(Icons.Filled.Edit,editClick )
                    }
                    Row(Modifier.weight(1f),horizontalArrangement=Arrangement.Center,verticalAlignment=Alignment.CenterVertically){
                        Icon(imageVector = Icons.Filled.Delete, contentDescription ="",Modifier.clickable(onClick = deleteClick),tint=Color.Red )
                    }
                }
            }
            TwoTextItem("Description", trading.desc.toString())
            Row(
                Modifier
                    .fillMaxWidth(),horizontalArrangement =Arrangement.SpaceBetween){
                TwoTextItem("Buying", "${trading.amountBuying} ${trading.itemBuying}" )
                TwoTextItem("Selling", "${trading.amountSelling} ${trading.itemSelling}" )
            }
        }
    }
}
@Composable
fun TwoTextItem(text1:String,text2:String){
    Column {
        Text(text1, color=MaterialTheme.colors.onBackground,style = MaterialTheme.typography.subtitle2)
        Text(text2,color=MaterialTheme.colors.onBackground,textAlign = TextAlign.Justify,style=MaterialTheme.typography.body2)
    }
}
@Composable
fun HeaderCardItem(text1:String,text2: String,text3:String,navController: NavHostController){
    SelectionContainer {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable(onClick = {
                    navController.navigate(BottomNavigationScreens.OtherProfile.withArgs(text2))
                })
        ) {
            Row(Modifier.weight(1f)) {
                Text(text1,Modifier.fillMaxWidth(),style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
            }
            Row(Modifier.weight(1f)) {
                Text(text2,Modifier.fillMaxWidth(),textAlign = TextAlign.Center,style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
            }
            Row(Modifier.weight(1f)) {
                Text(text3,Modifier.fillMaxWidth(),textAlign = TextAlign.End,style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
            }
        }
    }
}
@Composable
fun WallCard(wall: Wall?, onWall: () -> Unit, onDelete:() ->Unit,navController: NavHostController){
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column (Modifier.padding(8.dp),horizontalAlignment= CenterHorizontally,verticalArrangement = Arrangement.Center){
            wall?.let {
                HeaderCardItem(text1 = getTimePost(it.date), text2 = it.username.toString(), text3 =it.ign.toString(),navController)
                DividerItem()
                Row(Modifier.fillMaxWidth(),Arrangement.Center,verticalAlignment= Alignment.CenterVertically) {
                    Row(Modifier.weight(5f),Arrangement.Start,verticalAlignment=Alignment.CenterVertically){
                        it.chat?.let { it1 -> Text(it1,style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground) }}
                    Row(Modifier.weight(1f),Arrangement.Center,verticalAlignment=Alignment.CenterVertically){
                        if((getUsernameLoginFunction()==it.wallOwner)) SvgItemClick(icon = R.drawable.ic_reply,onWall)}
                    Row(Modifier.weight(1f),Arrangement.Center,verticalAlignment=Alignment.CenterVertically){
                        if(getUsernameLoginFunction()==it.wallOwner) Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "",
                            modifier = Modifier.clickable(onClick = onDelete),
                            tint=Color.Red
                        )}
                }
            }
        }
    }
}
@Composable
fun UserProfile(user: User?){
    if(user?.username!=""){
        user?.let {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = CenterHorizontally
            ){
                DividerItem()
                SelectionContainer {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(Modifier.weight(1f)) {
                            Text(user.name.toString(),Modifier.fillMaxWidth(),style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
                        }
                        Row(Modifier.weight(1f)) {
                            Text(user.ign.toString(),Modifier.fillMaxWidth(),textAlign = TextAlign.Center,style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
                        }
                        Row(Modifier.weight(1f)) {
                            Text(user.clubName.toString(),Modifier.fillMaxWidth(),textAlign = TextAlign.End,style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.onBackground)
                        }
                    }
                }
                DividerItem()
                Text("Age",style=MaterialTheme.typography.subtitle2)
                Text(getTimePost(user.created),textAlign = TextAlign.Center,style = MaterialTheme.typography.body1)
                DividerItem()
                Text("Bio",style=MaterialTheme.typography.subtitle2)
                Text(it.bio.toString(),textAlign = TextAlign.Justify,style=MaterialTheme.typography.body1)
                DividerItem()
            }
        }
    }
}
@Composable
fun EditProfileDialog(user: User,onClick: () -> Unit,username: String){
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
            Status.SUCCESS ->{onClick}
            Status.ERROR -> {onClick}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }

    if(openEdit){
        AlertDialog(
            backgroundColor=MaterialTheme.colors.secondary,
            modifier= Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.7f)
                .padding(8.dp),
            onDismissRequest=onClick,
            title = {
                Text(text = editprofile,style=MaterialTheme.typography.h2,color=MaterialTheme.colors.onBackground)
            },
            text = {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    TextFieldOutlined(desc = "Name", nameState)
                    TextFieldOutlined(desc = "Club Name", clubNameState)
                    TextFieldOutlined(desc = "IGN", ignState)
                    TextFieldOutlined(desc = "Bio", bioState)
                }
            },
            confirmButton ={
                Text(save,modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = {
                        profileVM.updateProfile(
                            UpdateUserRequest(
                                name = nameState.text,
                                clubName = clubNameState.text,
                                ign = ignState.text,
                                bio = bioState.text
                            ), username
                        )
                        openEdit = false
                    }),color=MaterialTheme.colors.primary,style=MaterialTheme.typography.subtitle2)
            },
            dismissButton = {
                Text(cancel,
                    Modifier
                        .padding(8.dp)
                        .clickable(onClick = onClick),color=Color.Red)
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
            WallCard(
                wall = it,
                onWall = {
                    navController.navigate(BottomNavigationScreens.OtherProfile.withArgs(it.username.toString()))},
                onDelete = {
                    authVM.isLoggedIn()
                    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                    profileVM.deleteWall(it.wallOwner.toString(),it)},navController
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }

}
@Composable
fun SaveTodayDialog(today: Today,onClick: () -> Unit){
    Column {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val idState = remember { TextFieldState(today._id) }
        val regulerState = remember { TextFieldState(today.reguler) }
        val ultraState = remember { TextFieldState(today.ultra) }
        val authVM= hiltViewModel<AuthViewModel>()
        if(openDialog){
            AlertDialog(
                backgroundColor=MaterialTheme.colors.secondary,
                onDismissRequest=onClick,
                title = { Text(text = "POTD",style=MaterialTheme.typography.h2,color=MaterialTheme.colors.onBackground) },
                text = {
                    Row(
                        Modifier
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(Modifier.weight(2f),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically){
                            TextFieldOutlined(desc = "id", idState)
                        }
                        Row(Modifier.weight(4f),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically){
                            TextFieldOutlined(desc = reguler, regulerState)
                        }
                        Row(Modifier.weight(4f),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically){
                            TextFieldOutlined(desc = ultra, ultraState)
                        }
                    }
                },
                confirmButton = {
                    Text(
                        save,
                        Modifier
                            .padding(8.dp)
                            .clickable(onClick = {
                                authVM.isLoggedIn()
                                authVM.authenticateApi(
                                    authVM.usernamevm ?: "",
                                    authVM.passwordvm ?: ""
                                )
                                homeVM.saveToday(
                                    Today(
                                        regulerState.text,
                                        ultraState.text,
                                        idState.text
                                    )
                                )
                                openDialog = false
                            }),
                        style = MaterialTheme.typography.subtitle2,
                        color = MaterialTheme.colors.primary
                    )
                },
                dismissButton = {
                    Text(
                        cancel,
                        Modifier
                            .padding(8.dp)
                            .clickable(onClick = onClick),
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.Red
                    )
                }
            )
        }
    }
}
@Composable
fun AddTradingDialog(trading: Trading? ){
    Column {
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
                backgroundColor=MaterialTheme.colors.secondary,
                onDismissRequest={openDialog = false},
                title = { Row(
                    Modifier
                        .fillMaxWidth(),horizontalArrangement = Arrangement.Center)
                {Text(text = "Trading",style = MaterialTheme.typography.h2,color=MaterialTheme.colors.onBackground) }},
                text = {
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.padding(8.dp))
                        TextFieldOutlined(desc = "Title", titleState)
                        Spacer(modifier = Modifier.padding(4.dp))
                        TextFieldOutlined(desc = "Description", descState)
                        Spacer(modifier = Modifier.padding(4.dp))
                        Text("Item Buying",style=MaterialTheme.typography.subtitle2)
                        Row(Modifier.fillMaxWidth()){
                            Row(Modifier.weight(3f)){
                                TextFieldOutlined(desc = "Name Item", itemBuyingState)
                            }
                            Row(Modifier.weight(1.6f)){
                                TextFieldOutlined(desc = "Amount", amountBuyingState)
                            }
                        }
                        Spacer(Modifier.padding(4.dp))
                        Text("Item Selling",style=MaterialTheme.typography.subtitle2)
                        Row(Modifier.fillMaxWidth()){
                            Row(Modifier.weight(3f)){
                                TextFieldOutlined(desc = "Name Item", itemSellingState)
                            }
                            Row(Modifier.weight(1.6f)){
                                TextFieldOutlined(desc = "Amount", amountSellingState)
                            }
                        }
                    }
                },
                confirmButton ={
                    Text(save,
                        Modifier
                            .padding(8.dp)
                            .clickable(onClick = {
                                authVM.isLoggedIn()
                                authVM.authenticateApi(
                                    authVM.usernamevm ?: "",
                                    authVM.passwordvm ?: ""
                                )
                                addVM.saveTrading(
                                    Trading(
                                        _id = trading?._id.toString(),
                                        title = titleState.text,
                                        desc = descState.text,
                                        itemBuying = itemBuyingState.text,
                                        amountBuying = amountBuyingState.text,
                                        itemSelling = itemSellingState.text,
                                        amountSelling = amountSellingState.text
                                    )
                                )
                                addVM.getAllTrading()
                                openDialog = false
                            }),color=MaterialTheme.colors.primary,style=MaterialTheme.typography.subtitle2)},
                   dismissButton = {
                       Text(cancel,
                           Modifier
                               .padding(8.dp)
                               .clickable(onClick = {
                                   openDialog = false
                               }),color=Color.Red,style=MaterialTheme.typography.subtitle2)
                  }
            )
        }
    }
}
@Composable
fun SaveDropDialog(onClick: () -> Unit){
    Column {
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
                backgroundColor=MaterialTheme.colors.secondary,
                onDismissRequest=onClick,
                title = { Text(text = "Dropped",style=MaterialTheme.typography.h2,color=MaterialTheme.colors.onBackground) },
                text = {
                    Column(
                        Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ){
                        ButtonClickItem(desc = "Close", onClick = { openDialog = false})
                        Row(Modifier.fillMaxWidth()){
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "id", idState)}
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "day", dayState)}
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "role", roleState)}
                        }
                        Row(Modifier.fillMaxWidth()){
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "name", nameState)}
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "duration", durationState)}
                        }
                    }
                },
                confirmButton ={
                    Text(save,
                        Modifier
                            .padding(8.dp)
                            .clickable(onClick = {
                                authVM.isLoggedIn()
                                authVM.authenticateApi(
                                    authVM.usernamevm ?: "",
                                    authVM.passwordvm ?: ""
                                )
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
                                openDialog = false
                            }),style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.primary)},
                    dismissButton = {
                        Text("Delete",
                            Modifier
                                .padding(8.dp)
                                .clickable(onClick = {
                                    openDialog = false
                                    authVM.isLoggedIn()
                                    authVM.authenticateApi(
                                        authVM.usernamevm ?: "",
                                        authVM.passwordvm ?: ""
                                    )
                                    homeVM.deleteDrop(
                                        Dropped(
                                            role = roleState.text,
                                            name = nameState.text,
                                            duration = durationState.text,
                                            day = dayState.text,
                                            _id = idState.text
                                        )
                                    )
                                    homeVM.getDropList()
                                }), color = Color.Red, style = MaterialTheme.typography.subtitle2)
                    }
            )
        }
    }
}
@Composable
fun SavePartyDialog(party:Party){
    Column {
        var openDialog by remember { mutableStateOf(true)}
        val homeVM = hiltViewModel<HomeViewModel>()
        val authVM = hiltViewModel<AuthViewModel>()
        val idState = remember { TextFieldState(party._id) }
        val noState = remember { TextFieldState(party.no) }
        val roleState = remember { TextFieldState(party.role) }
        val nameState = remember { TextFieldState(party.name) }
        val durationState = remember { TextFieldState(party.duration) }
        val statusState = remember { TextFieldState(party.status) }
        if(openDialog){
            AlertDialog(
                backgroundColor=MaterialTheme.colors.secondary,
                onDismissRequest={openDialog = false},
                title = { Text(text = "Party") },
                text = {
                    Column(
                        Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = CenterHorizontally
                    ) {
                        ButtonClickItem(desc = "Close", onClick = { openDialog= false},bordercolor = MaterialTheme.colors.onSurface,colors = ButtonDefaults.buttonColors(MaterialTheme.colors.surface))
                        Row(Modifier.fillMaxWidth()){
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "id", idState)}
                            Row(Modifier.weight(1f)){
                                TextFieldOutlined(desc = "no", noState)}
                            Row(Modifier.weight(2f)){
                                TextFieldOutlined(desc = "duration", durationState)}
                        }
                        Row(Modifier.fillMaxWidth()){
                            Row(Modifier.weight(1.5f)){
                                TextFieldOutlined(desc = "status", statusState)}
                            Row(Modifier.weight(1.5f)){
                                TextFieldOutlined(desc = "role", roleState)}
                            Row(Modifier.weight(2f)){
                                TextFieldOutlined(desc = "name", nameState)}
                        }
                    }
                },
                confirmButton ={
                    Text(save,
                        Modifier
                            .padding(8.dp)
                            .clickable(onClick = {
                                authVM.isLoggedIn()
                                authVM.authenticateApi(
                                    authVM.usernamevm ?: "",
                                    authVM.passwordvm ?: ""
                                )
                                homeVM.saveParty(
                                    Party(
                                        role = roleState.text,
                                        no = noState.text,
                                        name = nameState.text,
                                        duration = durationState.text,
                                        status = statusState.text,
                                        _id = idState.text
                                    )
                                )
                                homeVM.getPartyList()
                                openDialog = false
                            }),
                        style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.primary)},
                dismissButton = {
                    Text(awal,
                        Modifier
                            .padding(8.dp)
                            .clickable(onClick = {
                                authVM.isLoggedIn()
                                authVM.authenticateApi(
                                    authVM.usernamevm ?: "",
                                    authVM.passwordvm ?: ""
                                )
                                homeVM.saveParty(
                                    Party(
                                        role = roleState.text,
                                        no = noState.text,
                                        name = nameState.text,
                                        duration = durationState.text,
                                        status = statusState.text,
                                        check = listOf(),
                                        nope = listOf(),
                                        drop = listOf(),
                                        _id = idState.text
                                    )
                                )
                                homeVM.getPartyList()
                                openDialog = false
                            }),style=MaterialTheme.typography.subtitle2,color=MaterialTheme.colors.primary)
                }
            )
        }
    }
}
@Composable
fun ObserveUserList(onClick: () -> kotlin.Unit,navController: NavHostController){
    val homeVM = hiltViewModel<HomeViewModel>()
    val userList = mutableListOf<User>()
    val listUserState = homeVM.listUserStatus.observeAsState()
    listUserState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.let {listUser ->
                    userList.clear()
                    listUser.forEach { user ->
                        userList.add(user)
                    }
                }  ?: userList.clear()
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
        .padding(8.dp),
        Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(verticalArrangement = Arrangement.Center) {
            userList.forEach {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(8.dp),
                    shape= RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.secondary
                ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        Arrangement.SpaceBetween
                    ){
                        HeaderCardItem(text1 = it.ign.toString(), text2 = it.username, text3 = it.clubName.toString(), navController = navController)
                    }
                }
            }
            Text("CLOSE",style=MaterialTheme.typography.subtitle2,color=Color.Red)
        }
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
        Column (horizontalAlignment = CenterHorizontally){
            TextFieldOutlined(desc = desc,state)
            DropdownMenu(
                modifier= Modifier
                    .background(MaterialTheme.colors.secondary)
                    .align(CenterHorizontally)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.7f),
                expanded = isOpen.value,
                onDismissRequest = { openCloseOfDropDownList(false) }
            ) {
                countryList.forEach {
                    DropdownMenuItem(
                        onClick = {
                            openCloseOfDropDownList(false)
                            userSelectedString(it)
                        }) {
                        Text(it,style=MaterialTheme.typography.body1)
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
@Composable
fun TermTextItem(title:String,desc:String){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(6.dp)) {
        Text(title)
        Text(desc,textAlign= TextAlign.Justify)
        Spacer(modifier = Modifier.padding(6.dp))
    }
}
@Composable
fun AdvertView(modifier: Modifier = Modifier) {
    val isInEditMode = LocalInspectionMode.current
    if (isInEditMode) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(horizontal = 2.dp, vertical = 6.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            text = "Advert Here",
        )
    } else {
        AndroidView(
            modifier = modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    adSize = AdSize.BANNER
                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}
//Row(
//Modifier
//.fillMaxWidth()
//.padding(start = 12.dp, top = 6.dp, end = 12.dp, bottom = 6.dp)){
//    Row(Modifier.weight(1f)) {
//        Text(getTimePost(chat.date),Modifier.fillMaxWidth())
//    }
//    Row(Modifier.weight(1f)) {
//        Text(chat.username.toString(),Modifier.fillMaxWidth())
//    }
//    Spacer(Modifier.padding(3.dp))
//    Row(Modifier.weight(1f)) {
//        Text(chat.clubName.toString(),Modifier.fillMaxWidth())
//    }
//}