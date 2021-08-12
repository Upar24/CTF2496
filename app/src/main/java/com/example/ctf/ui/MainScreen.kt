package com.example.ctf.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.ctf.R
import com.example.ctf.ui.add.AddScreen
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.auth.LoginScreen
import com.example.ctf.ui.auth.RegisterScreen
import com.example.ctf.ui.component.CTFAppBottomNavigation
import com.example.ctf.ui.component.CTFAppDrawerNavigation
import com.example.ctf.ui.component.CTFAppTopNavigation
import com.example.ctf.ui.home.CalculationScreen
import com.example.ctf.ui.home.HomeScreen
import com.example.ctf.ui.home.PartyScreen
import com.example.ctf.ui.profile.OtherProfileScreen
import com.example.ctf.ui.profile.ProfileScreen
import kotlinx.coroutines.launch

sealed class BottomNavigationScreens(
    val route:String,
    @StringRes val resourceId:Int,
    val icon: Int
){
    object Home:BottomNavigationScreens("Home", R.string.home_screen_route, R.drawable.home)
    object Add:BottomNavigationScreens("Add",R.string.add_screen_route, R.drawable.add)
    object Profile:BottomNavigationScreens("Profile",R.string.profile_screen_route, R.drawable.profile)
    object Search:BottomNavigationScreens("Search",R.string.search_screen_route, R.drawable.search)
    object MyProfile:BottomNavigationScreens("MyProfile",R.string.myprofile_screen_route, R.drawable.profile)
    object TipsTricks:BottomNavigationScreens("TipsTricks",R.string.tipstricks_screen_route, R.drawable.lamp)
    object Dictionary:BottomNavigationScreens("Dictionary",R.string.dictionary_screen_route, R.drawable.kamus)
    object Support:BottomNavigationScreens("Support",R.string.support_screen_route, R.drawable.support)
    object OtherProfile:BottomNavigationScreens("OtherProfile",R.string.otherprofile_screen_route, R.drawable.support)
    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
@Composable
fun MainScreen(){
    Card(
        modifier= Modifier.fillMaxSize(),
        backgroundColor= MaterialTheme.colors.background
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(start = 3.dp, top = 2.dp, end = 3.dp, bottom = 2.dp),
            shape= RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.background){
            val navController: NavHostController = rememberNavController()
            val scaffoldState: ScaffoldState = rememberScaffoldState()
            val coroutineScope= rememberCoroutineScope()
            val bottomNavigationItems= listOf(
                BottomNavigationScreens.Home,
                BottomNavigationScreens.Add,
                BottomNavigationScreens.Profile
            )
            val drawerNavigationItems= listOf(
                BottomNavigationScreens.MyProfile,
                BottomNavigationScreens.TipsTricks,
                BottomNavigationScreens.Dictionary,
                BottomNavigationScreens.Support
            )
            val authVM = hiltViewModel<AuthViewModel>()

            Scaffold (
                topBar={
                    CTFAppTopNavigation(
                        onIconClick = {
                            coroutineScope.launch {
                                scaffoldState.drawerState.open()
                            }
                            authVM.getDesc()
                        },
                        navController
                    )
                },
                scaffoldState=scaffoldState,
                drawerContent={
                    CTFAppDrawerNavigation(
                        closeDrawerAction = {
                            coroutineScope.launch {
                                scaffoldState.drawerState.close()
                            }
                        },
                        navController = navController,
                        items = drawerNavigationItems,
                    )
                },
                bottomBar ={
                    CTFAppBottomNavigation(navController , bottomNavigationItems )
                }
            ){
                MainScreenNavigationConfiguration(navController)
            }
        }
    }
}
@Composable
fun MainScreenNavigationConfiguration(
    navController: NavHostController
){
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route){
        composable(BottomNavigationScreens.Home.route){
            HomeScreen()
        }
        composable("Party"){
            PartyScreen()
        }
        composable(BottomNavigationScreens.OtherProfile.route + "/{username}",arguments = listOf(
            navArgument("username"){
                type= NavType.StringType
                defaultValue=""
                nullable=true
            }
        )){
            it.arguments?.getString("username")?.let { it1 -> OtherProfileScreen(it1,navController) }
        }
        composable(BottomNavigationScreens.Add.route){
            AddScreen()
        }
        composable(BottomNavigationScreens.Profile.route){
            ProfileScreen(navController)
        }
        composable(BottomNavigationScreens.Search.route){
            SearchScreen()
        }
        composable(BottomNavigationScreens.MyProfile.route){
            MyProfileScreen()
        }
        composable("Calculate"){
            CalculationScreen()
        }
        composable(BottomNavigationScreens.TipsTricks.route){
            TipsTricksScreen()
        }
        composable("Dictionary"){
            DictionaryScreen()
        }
        composable("Support"){
            SupportScreen()
        }
        composable("LoginRoute"){
            LoginScreen(navController)
        }
        composable("RegisterRoute"){
            RegisterScreen(navController)
        }
    }
}
