package com.example.ctf.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
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
import com.example.ctf.util.Constants.KEY_LOGGED_IN_PASSWORD
import com.example.ctf.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf.util.Status
import com.example.ctf.util.listString.donthaveanaccountyet
import com.example.ctf.util.listString.login
import com.example.ctf.util.listString.password
import com.example.ctf.util.listString.register
import com.example.ctf.util.listString.repeatpassword
import com.example.ctf.util.listString.unknownerrortoast
import com.example.ctf.util.listString.username
import timber.log.Timber

@Composable
fun RegisterScreen(
    navController: NavHostController
){
    val authVM = hiltViewModel<AuthViewModel>()
    val uiState= authVM.registerStatus.observeAsState()
    uiState.value?.let {
        val result = it.peekContent()
        when(result.status){
            Status.SUCCESS -> {
                Toast.makeText(LocalContext.current,result.data ?: "successfully registered",
                    Toast.LENGTH_SHORT).show()}
            Status.ERROR -> {
                Toast.makeText(LocalContext.current,result.message ?: "An unknown error occured",
                    Toast.LENGTH_SHORT).show()}
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
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.size(70.dp))
        TextFieldOutlined(username,usernameState)
        Spacer(Modifier.size(7.dp))
        TextFieldOutlined(password,passwordState)
        Spacer(Modifier.size(7.dp))
        TextFieldOutlined(repeatpassword,repeatPasswordState)
        Spacer(Modifier.size(40.dp))
        ButtonClickItem(desc= register,onClick={
            authVM.registerUser(usernameState.text,passwordState.text,repeatPasswordState.text)
        })
        Spacer(modifier = Modifier.padding(24.dp))
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
    val authVM= hiltViewModel<AuthViewModel>()
    val uiState= authVM.loginStatus.observeAsState()
    uiState.value?.let {
        val result = it.peekContent()
        when(result.status){
            Status.SUCCESS -> {
                Toast.makeText(
                    LocalContext.current,result.data ?: "successfully logged in",Toast.LENGTH_SHORT
                ).show()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME,authVM.usernamevm).apply()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_PASSWORD,authVM.passwordvm).apply()
                authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
                authVM.getDesc()
                navController.navigate("Home")
                Timber.d("Called")
            }
            Status.ERROR -> {
                Toast.makeText(
                    LocalContext.current,result.message ?: unknownerrortoast,Toast.LENGTH_SHORT
                ).show()
            }
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
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.size(70.dp))
        TextFieldOutlined(username,usernameState)
        Spacer(Modifier.size(7.dp))
        TextFieldOutlined(password,passwordState)
        Spacer(Modifier.size(40.dp))
        ButtonClickItem(desc= login,onClick={
            authVM.loginUser(usernameState.text,passwordState.text)
        })
        Spacer(modifier = Modifier.padding(24.dp))
        SwitchTOLoginOrRegisterTexts(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text1 = donthaveanaccountyet,
            text2 = register
        ) {
            navigateRouteFunction(navController,"RegisterRoute")
        }
    }
}
