package com.example.ctf.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.data.local.entities.User
import com.example.ctf.ui.component.*
import com.example.ctf.ui.profile.ProfileViewModel
import com.example.ctf.util.Status
import com.example.ctf.util.listString.search
import com.example.ctf.util.listString.club
import com.example.ctf.util.listString.ign
import com.example.ctf.util.listString.username

@Composable
fun SearchScreen(navController: NavHostController){
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp),horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AdvertView()
        val profileVM = hiltViewModel<ProfileViewModel>()
        var userSearch by remember{mutableStateOf(User("","No user found",""))}
        val userIgnList = mutableListOf<User>()
        val userClubList = mutableListOf<User>()
        var visibleScreen by remember { mutableStateOf("") }

        val searchState = remember { TextFieldState("") }
        Spacer(Modifier.padding(16.dp))
        TextFieldOutlined(desc = search,searchState)
        Spacer(Modifier.padding(8.dp))
        Text("Search for",style=MaterialTheme.typography.body1)
        Spacer(Modifier.padding(8.dp))
        Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceAround){
            ButtonClickItem(desc = ign, onClick = {
                profileVM.listUserIgn(searchState.text)
                visibleScreen = ign },
                bordercolor = if(visibleScreen==ign)MaterialTheme.colors.primary else MaterialTheme.colors.background,
                colors=if(visibleScreen==ign) ButtonDefaults.buttonColors(MaterialTheme.colors.background) else ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            )
            ButtonClickItem(desc = username, onClick = {
                userSearch=User("","","")
                profileVM.getUser(searchState.text)
                visibleScreen = username},
                bordercolor = if(visibleScreen==username)MaterialTheme.colors.primary else MaterialTheme.colors.background,
                colors=if(visibleScreen==username) ButtonDefaults.buttonColors(MaterialTheme.colors.background) else ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            )
            ButtonClickItem(desc = club, onClick = {
                profileVM.listUserClub(searchState.text)
                visibleScreen = club },
                bordercolor = if(visibleScreen==club)MaterialTheme.colors.primary else MaterialTheme.colors.background,
                colors=if(visibleScreen==club) ButtonDefaults.buttonColors(MaterialTheme.colors.background) else ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            )
        }
        DividerItem()
        val userState= profileVM.user.observeAsState()
        userState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    userSearch = it.data ?: User("","","")
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val listUserListClubState= profileVM.listUserClubStatus.observeAsState()
        listUserListClubState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {listUser->
                        userClubList.clear()
                        listUser.forEach {user->
                            userClubList.add(user)
                        }
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val listUserIgnListState= profileVM.listUserIgnStatus.observeAsState()
        listUserIgnListState.value?.let {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let {listUser->
                        userIgnList.clear()
                        listUser.forEach {user->
                            userIgnList.add(user)
                        }
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {
                    ProgressCardToastItem()
                }
            }
        }
        val listDisplay:MutableList<User> = when (visibleScreen){
            club-> userClubList
            ign -> userIgnList
            else -> mutableListOf()
        }
        Column (
            Modifier.verticalScroll(rememberScrollState())
        ){
            listDisplay.forEach {
                Card(
                    border=BorderStroke(1.dp,MaterialTheme.colors.primaryVariant),
                    shape= RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.secondary
                ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    Arrangement.SpaceBetween
                ){
                    HeaderCardItem(text1 = it.ign.toString(), text2 = it.username, text3 =it.clubName.toString(),navController )
                }
                }
                Spacer(modifier = Modifier.padding(2.dp))
            }
        }
        if(visibleScreen==username){
            if(userSearch.username != ""){
                Card(
                    border=BorderStroke(1.dp,MaterialTheme.colors.primaryVariant),
                    shape= RoundedCornerShape(8.dp),
                    backgroundColor = MaterialTheme.colors.secondary
                ){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        Arrangement.SpaceBetween
                    ){
                        HeaderCardItem(text1 = userSearch.ign.toString(), text2 = userSearch.username, text3 = userSearch.clubName.toString(),navController)
                    }
                }
                Spacer(modifier = Modifier.padding(2.dp))
            }
        }
    }
}
