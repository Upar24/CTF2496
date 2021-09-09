package com.example.ctf.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ctf.ui.component.*
import com.example.ctf.util.Status
import com.example.ctf.util.listString.KEY_LOGGED_IN_PASSWORD
import com.example.ctf.util.listString.KEY_LOGGED_IN_USERNAME
import com.example.ctf.util.listString.donthaveanaccountyet
import com.example.ctf.util.listString.login
import com.example.ctf.util.listString.password
import com.example.ctf.util.listString.register
import com.example.ctf.util.listString.registerRoute
import com.example.ctf.util.listString.repeatpassword
import com.example.ctf.util.listString.username
import timber.log.Timber

@Composable
fun RegisterScreen(
    navController: NavHostController,
){
    val context= LocalContext.current
    val authVM = hiltViewModel<AuthViewModel>()
    val uiState= authVM.registerStatus.observeAsState()


    var success by remember { mutableStateOf(false) }
    uiState.value?.let {
        when(it.status){
            Status.SUCCESS -> {success = true
                Toast.makeText( context,"Successfully registered.", Toast.LENGTH_SHORT,
                ).show()}
            Status.ERROR -> {
                Toast.makeText( context,it.data ?: "Something wrong or try another username", Toast.LENGTH_SHORT,
            ).show()}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val usernameState= remember{ TextFieldState() }
    val passwordState= remember{ TextFieldState() }
    val repeatPasswordState= remember{ TextFieldState() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.size(80.dp))
        TextFieldOutlined(username,usernameState)
        Spacer(Modifier.size(8.dp))
        TextFieldOutlined(password,passwordState)
        Spacer(Modifier.size(8.dp))
        TextFieldOutlined(repeatpassword,repeatPasswordState)
        Spacer(Modifier.size(8.dp))
        if(success)Text("${usernameState.text} is successfully registered.")
        Spacer(Modifier.size(40.dp))
        ButtonClickItem(desc= register,onClick={
            if(usernameState.text.isEmpty() || passwordState.text.isEmpty() || repeatPasswordState.text.isEmpty()){
                Toast.makeText( context,"Please fill out all the fields.", Toast.LENGTH_SHORT,
                ).show()
            }else if(passwordState.text != repeatPasswordState.text){
                Toast.makeText( context,"The passwords do not match.", Toast.LENGTH_SHORT,
                ).show()
            }else if(usernameState.text.length < 3 || passwordState.text.length < 3){
                Toast.makeText( context,"must be at least 3 characters.", Toast.LENGTH_SHORT,
                ).show()
            }else if(usernameState.text.length > 24 || passwordState.text.length > 24){
                Toast.makeText( context,"characters are too long.", Toast.LENGTH_SHORT,
                ).show()
            }else{
                authVM.registerUser(usernameState.text,passwordState.text)
            }
        })
        Spacer(modifier = Modifier.padding(16.dp))
        SwitchTOLoginOrRegisterTexts(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text1 = "Already have an account?",
            text2 = login
        ) {
            navigateRouteFunction(navController,"LoginRoute")
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController
){
    val context= LocalContext.current
    val authVM= hiltViewModel<AuthViewModel>()
    val uiState= authVM.loginStatus.observeAsState()
    var success by remember { mutableStateOf(false) }
    uiState.value?.let {
        when(it.status){
            Status.SUCCESS -> {
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME,authVM.usernamevm).apply()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_PASSWORD,authVM.passwordvm).apply()
                authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                authVM.getDesc()
                success=true
                Timber.d("Called")
            }
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }

    val usernameState= remember{ TextFieldState() }
    val passwordState= remember{ TextFieldState() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.size(80.dp))
        TextFieldOutlined(username,usernameState)
        Spacer(Modifier.size(8.dp))
        TextFieldOutlined(password,passwordState)
        Spacer(Modifier.size(8.dp))
        if(success)Text("${usernameState.text} is successfully registered.")
        Spacer(Modifier.size(40.dp))
        ButtonClickItem(desc= login,onClick={
            if(usernameState.text.isEmpty() || passwordState.text.isEmpty()){
                Toast.makeText( context,"Please fill out all the fields", Toast.LENGTH_SHORT,
                ).show()
            }else{
                authVM.loginUser(usernameState.text,passwordState.text)
            }
        })
        Spacer(modifier = Modifier.padding(16.dp))
        SwitchTOLoginOrRegisterTexts(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text1 = donthaveanaccountyet,
            text2 = register
        ) {
            navigateRouteFunction(navController, registerRoute)
        }
    }
}
