package com.example.ctf.ui.component

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ctf.R
import com.example.ctf.ui.BottomNavigationScreens
import com.example.ctf.ui.DrawerNavigationScreens
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.theme.ThemeState
import com.example.ctf.util.listString.KEY_LOGGED_IN_PASSWORD
import com.example.ctf.util.listString.KEY_LOGGED_IN_USERNAME
import com.example.ctf.util.listString.LOGIN
import com.example.ctf.util.listString.NO_PASSWORD
import com.example.ctf.util.listString.NO_USERNAME

@Composable
fun CTFAppTopNavigation(
    onIconClick: () -> Unit
) {
    Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically){
        Row(Modifier.weight(4f)){
            TopBarItem(onIconClick)
        }
        Row(Modifier.weight(1f).padding(end=16.dp),horizontalArrangement = Arrangement.End){
            SvgItemClick(icon = R.drawable.ic_dark, onClick = {val theme =  when (ThemeState.darkModeState){
                true -> AppCompatDelegate.MODE_NIGHT_NO
                else -> AppCompatDelegate.MODE_NIGHT_YES
            }
                AppCompatDelegate.setDefaultNightMode(theme)
                ThemeState.darkModeState = !ThemeState.darkModeState})
        }
    }
}


@Composable
fun CTFAppDrawerNavigation(
    modifier: Modifier = Modifier,
    closeDrawerAction: () -> Unit,
    navController: NavHostController,
    items: List<DrawerNavigationScreens>
){
    Column(
        modifier= modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
    ){
        AppdrawerHeader(
            closeDrawerAction
        )
        Divider()
        AppdrawerBody(
            closeDrawerAction,
            navController,
            items
        )
        Divider()
        AppdrawerFooter(
            navController,
            closeDrawerAction
        )
    }
}
@Composable
fun AppdrawerHeader(
    closeDrawerAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically){
            TopBarItem(
                closeDrawerAction
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
            Text(
                text=getUsernameLoginFunction(),
                style=MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun AppdrawerBody(
    closeDrawerAction: () -> Unit,
    navController: NavHostController,
    items: List<DrawerNavigationScreens>
) {
    items.forEach {item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navigateRouteFunction(navController, item.route)
                    closeDrawerAction()
                },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(painter = painterResource(id = item.icon), contentDescription = "",tint=MaterialTheme.colors.primaryVariant)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(item.resourceId),
                style = MaterialTheme.typography.body2
            )
        }
    }
}
@Composable
fun AppdrawerFooter(
    navController: NavHostController,
    closeDrawerAction: () -> Unit
){

    val authVM = hiltViewModel<AuthViewModel>()
    authVM.getDesc()
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        val x by authVM.desc.collectAsState()
        ButtonClickItem(desc = x, onClick = {
            closeDrawerAction()
            if (x == LOGIN) {
                navigateRouteFunction(navController, "LoginRoute")
                authVM.getDesc()
            } else {
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME, NO_USERNAME).apply()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_PASSWORD, NO_PASSWORD).apply()
                navigateRouteFunction(navController,"Home")
                authVM.getDesc()
            } })
    }
}
@Composable
fun CTFAppBottomNavigation(
    navController: NavHostController,
    items:List<BottomNavigationScreens>
){
    BottomNavigation (backgroundColor=MaterialTheme.colors.background){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute= navBackStackEntry?.destination?.route
        items.forEach{screen ->
            BottomNavigationItem(
                icon= { Icon(imageVector = screen.icon, contentDescription = "",
                    tint=MaterialTheme.colors.primary
                )},
                label={
                    Text(
                        stringResource(id = screen.resourceId),
                        color= MaterialTheme.colors.onBackground,
                        style=MaterialTheme.typography.body1
                    )
                },
                selected = currentRoute==screen.route,
                alwaysShowLabel= false,
                onClick = {
                    navigateRouteFunction(navController,screen.route)
                }

            )
        }
    }
}


