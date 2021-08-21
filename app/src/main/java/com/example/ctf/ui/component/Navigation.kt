package com.example.ctf.ui.component

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ctf.ui.BottomNavigationScreens
import com.example.ctf.ui.DrawerNavigationScreens
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.theme.ThemeState
import com.example.ctf.util.Constants.KEY_LOGGED_IN_PASSWORD
import com.example.ctf.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf.util.Constants.LOGIN
import com.example.ctf.util.Constants.NO_PASSWORD
import com.example.ctf.util.Constants.NO_USERNAME
import com.example.ctf.util.listString.darkmodestring

@Composable
fun CTFAppTopNavigation(
    onIconClick: () -> Unit,
    navController: NavHostController
) {
    Row(
        Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TopBarItem(onIconClick)
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
                style=MaterialTheme.typography.subtitle2
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
                .padding(12.dp)
                .clickable {
                    navigateRouteFunction(navController, item.route)
                    closeDrawerAction()
                }
                .height(27.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(item.icon),
                contentDescription = "apa aj",
                modifier = Modifier
                    .height(36.dp))
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(item.resourceId),
                style = MaterialTheme.typography.body1
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        val x by authVM.desc.collectAsState()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(48.dp)
                .padding(12.dp)
        ){
            Text(
                darkmodestring,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.clickable {val theme =  when (ThemeState.darkModeState){
                    true -> AppCompatDelegate.MODE_NIGHT_NO
                    else -> AppCompatDelegate.MODE_NIGHT_YES
                }
                    AppCompatDelegate.setDefaultNightMode(theme)
                    ThemeState.darkModeState = !ThemeState.darkModeState
                }
            )
            Switch(
                checked = ThemeState.darkModeState,
                onCheckedChange = { ThemeState.darkModeState = it }
            )
        }
        ButtonClickItem(desc = x, onClick = {
            closeDrawerAction()
            if (x == LOGIN) {
                navigateRouteFunction(navController, "LoginRoute")
                authVM.getDesc()
            } else {
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_USERNAME, NO_USERNAME).apply()
                authVM.sharedPref.edit().putString(KEY_LOGGED_IN_PASSWORD, NO_PASSWORD).apply()
                navController.navigate("Home")
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
                    tint=MaterialTheme.colors.primaryVariant
                )},
                label={
                    Text(
                        stringResource(id = screen.resourceId),
                        color= MaterialTheme.colors.onPrimary,
                        fontSize = 18.sp,fontWeight = FontWeight.Bold
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


