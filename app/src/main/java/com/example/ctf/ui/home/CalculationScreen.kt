package com.example.ctf.ui.home

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ctf.R
import com.example.ctf.ui.auth.AuthViewModel
import com.example.ctf.ui.component.*
import com.example.ctf.util.Constants
import com.example.ctf.util.listString.dvp
import com.example.ctf.util.listString.init
import com.example.ctf.util.listString.interest
import com.example.ctf.util.listString.limit
import com.example.ctf.util.listString.maxplunder
import com.example.ctf.util.listString.nope
import com.example.ctf.util.listString.result
import com.example.ctf.util.listString.ts
import com.example.ctf.util.listString.upgrade
import com.example.ctf.util.listString.volley
import java.text.NumberFormat
import java.util.*

@Composable
fun CalculationScreen() {
    val listCalc = listOf(dvp, upgrade, ts, maxplunder)
    var visibleCalc by remember { mutableStateOf(dvp) }

    Column(Modifier.fillMaxWidth()){
        var tabIndex by remember { mutableStateOf(0) }

        ScrollableTabRow(
            selectedTabIndex = tabIndex, modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent
        ) {
            listCalc.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                    visibleCalc = text
                }, text = {
                    Text(
                        text,
                        style = if (visibleCalc == text) MaterialTheme.typography.h2 else MaterialTheme.typography.button,
                        color = if (visibleCalc == text) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                    )
                })
            }
        }
        when(visibleCalc){
            dvp -> DvpDisplay()
            upgrade -> UpgradeDisplay()
            ts-> TSDisplay()
            maxplunder -> MaxPlunderDisplay()
            else -> (Text("Ash"))
        }

    }
}
@Composable
fun DvpDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally){
        var visibleDvp by remember { mutableStateOf("") }
        var textString by remember { mutableStateOf("")}
        val initState = remember { TextFieldState("") }
        val limitState = remember { TextFieldState("") }
        EditTextItem(desc = init,state =initState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(Modifier.padding(8.dp))
        EditTextItem(desc = limit,state =limitState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(Modifier.padding(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically){
            ButtonClickItem(
                desc = volley,
                onClick = {
                    visibleDvp= volley
                    textString=if(initState.text.isEmpty() || limitState.text.isEmpty())"Please fill both of the fields" else
                        hoppingValueFunction(initState.text.filter { it.isLetterOrDigit()}.toLong(),limitState.text.filter { it.isLetterOrDigit()}.toLong(),"A")},
                bordercolor= if(visibleDvp==volley)MaterialTheme.colors.primary else MaterialTheme.colors.background,
                colors=if(visibleDvp==volley)ButtonDefaults.buttonColors( MaterialTheme.colors.background ) else ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            )
            Spacer(Modifier.padding(8.dp))
            ButtonClickItem(
                desc = interest,
                onClick = {
                    visibleDvp= interest
                    textString=if(initState.text.isEmpty() || limitState.text.isEmpty())"Please fill both of the fields" else hoppingValueFunction(initState.text.filter { it.isLetterOrDigit()}.toLong(),limitState.text.filter { it.isLetterOrDigit()}.toLong(),"B")},
                bordercolor= if(visibleDvp==interest)MaterialTheme.colors.primary else MaterialTheme.colors.background,
                colors=if(visibleDvp==interest)ButtonDefaults.buttonColors( MaterialTheme.colors.background ) else ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            )
            Spacer(Modifier.padding(8.dp))
            ButtonClickItem(
                desc = result,
                onClick = {
                    visibleDvp= result
                    textString=if(initState.text.isEmpty() || limitState.text.isEmpty())"Please fill both of the fields" else hoppingValueFunction(initState.text.filter { it.isLetterOrDigit()}.toLong(),limitState.text.filter { it.isLetterOrDigit()}.toLong(),"C")},
                bordercolor= if(visibleDvp==result)MaterialTheme.colors.primary else MaterialTheme.colors.background,
                colors=if(visibleDvp==result)ButtonDefaults.buttonColors( MaterialTheme.colors.background ) else ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(textString,color = MaterialTheme.colors.primaryVariant,style = MaterialTheme.typography.h2)

    }
}
@Composable
fun UpgradeDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val currentStatusState = remember { TextFieldState("") }
        val dropDormState = remember { TextFieldState(nope) }
        val hireDormState = remember { TextFieldState(nope) }
        EditTextItem(desc = "Current Status",state = currentStatusState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(Modifier.padding(8.dp))
        DropDownListItem(desc = "Drop a dormmate?",dropDormState)
        Spacer(Modifier.padding(8.dp))
        DropDownListItem(desc = "Hiring a dormmate?",hireDormState)
        Spacer(modifier = Modifier.padding(16.dp))
        ButtonClickItem(desc = "Calculate", onClick = { textString= if(currentStatusState.text.isEmpty())"Please the the field" else upgradeFunction(currentStatusState.text.filter { it.isLetterOrDigit()}.toLong(),
            valueOfTheTier(dropDormState.text), valueOfTheTier(hireDormState.text)) })
        Spacer(modifier = Modifier.padding(8.dp))
        Text(textString,color = MaterialTheme.colors.primaryVariant,style = MaterialTheme.typography.h2)
    }
}
@Composable
fun TSDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val tsStatus = remember { TextFieldState("") }
        EditTextItem(desc = "Current Status",state = tsStatus,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.padding(16.dp))
        ButtonClickItem(desc = "Calculate", onClick = { textString= if(tsStatus.text.isEmpty())"Please the the field" else tsmaxplunder(tsStatus.text.filter { it.isLetterOrDigit()}.toLong(),ts) })
        Spacer(modifier = Modifier.padding(8.dp))
        Text(textString,color = MaterialTheme.colors.primaryVariant,style = MaterialTheme.typography.h2)
    }
}
@Composable
fun MaxPlunderDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val statusState = remember { TextFieldState("") }
        EditTextItem(desc = "Current Status",state = statusState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.padding(16.dp))
        ButtonClickItem(desc = "Calculate", onClick = { textString= if(statusState.text.isEmpty())"Please the the field" else tsmaxplunder(statusState.text.filter { it.isLetterOrDigit()}.toLong(),
            maxplunder) })
        Spacer(modifier = Modifier.padding(8.dp))
        Text(textString,color = MaterialTheme.colors.primaryVariant,style = MaterialTheme.typography.h2)
    }
}