package com.example.ctf.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ctf.ui.component.*
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

@Composable
fun CalculationScreen() {
    val listCalc = listOf(dvp, upgrade, ts, maxplunder)
    var visibleCalc by remember { mutableStateOf(dvp) }
    Column(Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center) {

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
                        text, Modifier
                            .padding(6.dp),
                        style = if (visibleCalc == text) MaterialTheme.typography.button else MaterialTheme.typography.body1,
                        color = if (visibleCalc == text) Color.Magenta else MaterialTheme.colors.onBackground
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
    Column(Modifier.fillMaxWidth()){
        var textString by remember { mutableStateOf("")}
        val initState = remember { TextFieldState("0") }
        val limitState = remember { TextFieldState("0") }
        EditTextItem(desc = init,state =initState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        EditTextItem(desc = limit,state =limitState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Row(Modifier.fillMaxWidth()){
            ButtonClickItem(desc = volley, onClick = {textString=hoppingValueFunction(initState.text.toLong(),limitState.text.toLong(),"A")})
            ButtonClickItem(desc = interest, onClick = {textString=hoppingValueFunction(initState.text.toLong(),limitState.text.toLong(),"B")})
            ButtonClickItem(desc = result, onClick = {textString=hoppingValueFunction(initState.text.toLong(),limitState.text.toLong(),"C")})
        }
        Text(textString)
    }
}
@Composable
fun UpgradeDisplay(){
    Column(Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val currentStatusState = remember { TextFieldState("0") }
        val dropDormState = remember { TextFieldState(nope) }
        val hireDormState = remember { TextFieldState(nope) }

        EditTextItem(desc = "Current Status",state = currentStatusState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        DropDownListItem(desc = "Drop a dormmate?",dropDormState)
        DropDownListItem(desc = "Hiring a dormmate?",hireDormState)
        ButtonClickItem(desc = "Calculate", onClick = { textString= upgradeFunction(currentStatusState.text.toLong(),
            valueOfTheTier(dropDormState.text), valueOfTheTier(hireDormState.text)) })
        Text(textString)
    }
}
@Composable
fun TSDisplay(){
    Column {
        var textString by remember { mutableStateOf("")}
        val tsStatus = remember { TextFieldState("0") }
        EditTextItem(desc = "Current Status",state = tsStatus,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        ButtonClickItem(desc = "Calculate", onClick = { textString= tsmaxplunder(tsStatus.text.toLong(),ts) })
        Text(textString)
    }
}
@Composable
fun MaxPlunderDisplay(){
    Column {
        var textString by remember { mutableStateOf("")}
        val statusState = remember { TextFieldState("0") }
        EditTextItem(desc = "Current Status",state = statusState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        ButtonClickItem(desc = "Calculate", onClick = { textString= tsmaxplunder(statusState.text.toLong(),
            maxplunder) })
        Text(textString)
    }
}