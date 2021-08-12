package com.example.ctf.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.util.Constants.BUYING_RATE
import com.example.ctf.util.Constants.INTEREST_RATE
import com.example.ctf.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf.util.Constants.NO_USERNAME
import kotlin.math.roundToLong

class TextFieldState(string: String=""){
    var text : String by mutableStateOf(string)
}
fun getTimePost(timePost:Long):String{
    val startTime= timePost
    val endTime= System.currentTimeMillis()
    val diff= (endTime - startTime)/1000
    return diff.toString()
}
@Composable
fun getUsernameLoginFunction():String{
    val authVM = hiltViewModel<AuthViewModel>()
    return authVM.sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) ?: NO_USERNAME
}
@Composable
fun GetAuthenticateFunction(){
    val authVM = hiltViewModel<AuthViewModel>()
    authVM.isLoggedIn()
    authVM.authenticateApi(authVM.usernamevm ?: "", authVM.passwordvm ?: "")
}

fun navigateRouteFunction(
    navController: NavHostController,
    route:String
){
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
fun hoppingValueFunction(init:Long,limit:Long,request:String):String{
    var initValue = init
    var hoppingTimes=0
    var interest : Long= 0
    if(limit < initValue) return "Limit value cant be less than init value"
    else if(limit == initValue) return "0"
    else{
        while(limit > initValue){
            if((initValue * BUYING_RATE).roundToLong() > limit)break else{
                hoppingTimes += 1
                interest += (initValue * INTEREST_RATE).roundToLong()
                initValue = (initValue * BUYING_RATE).roundToLong()
            }
        }
    }
    return if(request =="A") "$hoppingTimes" else if(request=="B") "$interest" else "$initValue"
}
fun upgradeFunction(current:Long,drop:Long,hire:Long):String{
    val dropStat=drop
    val hireStat= hire
    return (current - dropStat + hireStat).toString()
}

