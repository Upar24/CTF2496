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
import com.example.ctf.R
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
import java.text.NumberFormat
import java.util.*

@Composable
fun CalculationScreen() {
    val listCalc = listOf(dvp, upgrade, ts, maxplunder)
    var visibleCalc by remember { mutableStateOf(dvp) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp),verticalArrangement = Arrangement.Center) {
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
                        style = if (visibleCalc == text) MaterialTheme.typography.button else MaterialTheme.typography.body2,
                        color = if (visibleCalc == text) MaterialTheme.colors.secondaryVariant else MaterialTheme.colors.onSecondary
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
            .padding(top = 24.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally){
        var visibleDvp by remember { mutableStateOf("") }
        var textString by remember { mutableStateOf("")}
        val initState = remember { TextFieldState("0") }
        val limitState = remember { TextFieldState("0") }
        Spacer(Modifier.padding(6.dp))
        EditTextItem(desc = init,state =initState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(Modifier.padding(3.dp))
        EditTextItem(desc = limit,state =limitState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(Modifier.padding(6.dp))
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround,verticalAlignment = Alignment.CenterVertically){
            ButtonClickItem(
                desc = volley,
                onClick = {
                visibleDvp= volley
                textString=hoppingValueFunction(initState.text.filter { it.isLetterOrDigit()}.toLong(),limitState.text.filter { it.isLetterOrDigit()}.toLong(),"A")},
                warna=if(visibleDvp==volley)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
            ButtonClickItem(
                desc = interest,
                onClick = {
                visibleDvp= interest
                textString=hoppingValueFunction(initState.text.filter { it.isLetterOrDigit()}.toLong(),limitState.text.filter { it.isLetterOrDigit()}.toLong(),"B")},
                warna=if(visibleDvp==interest)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
            ButtonClickItem(
                desc = result,
                onClick = {
                visibleDvp= result
                textString=hoppingValueFunction(initState.text.filter { it.isLetterOrDigit()}.toLong(),limitState.text.filter { it.isLetterOrDigit()}.toLong(),"C")},
                warna=if(visibleDvp==result)MaterialTheme.colors.primaryVariant else MaterialTheme.colors.onSurface
            )
        }
        Spacer(modifier = Modifier.padding(6.dp))
        val result =if(!textString.isEmpty())NumberFormat.getNumberInstance(Locale.US).format(textString.toLong()) else "0"
        Text(result,color = MaterialTheme.colors.secondaryVariant,style = MaterialTheme.typography.button)

    }
}
@Composable
fun UpgradeDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val currentStatusState = remember { TextFieldState("0") }
        val dropDormState = remember { TextFieldState(nope) }
        val hireDormState = remember { TextFieldState(nope) }

        Spacer(modifier = Modifier.padding(6.dp))
        EditTextItem(desc = "Current Status",state = currentStatusState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        DropDownListItem(desc = "Drop a dormmate?",dropDormState)
        DropDownListItem(desc = "Hiring a dormmate?",hireDormState)
        Spacer(modifier = Modifier.padding(6.dp))
        ButtonClickItem(desc = "Calculate", onClick = { textString= upgradeFunction(currentStatusState.text.filter { it.isLetterOrDigit()}.toLong(),
            valueOfTheTier(dropDormState.text), valueOfTheTier(hireDormState.text)) })
        Spacer(modifier = Modifier.padding(6.dp))
        val result =if(!textString.isEmpty())NumberFormat.getNumberInstance(Locale.US).format(textString.toLong()) else "0"
        Text(result,color = MaterialTheme.colors.secondaryVariant,style = MaterialTheme.typography.button)
    }
}
@Composable
fun TSDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val tsStatus = remember { TextFieldState("0") }
        Spacer(modifier = Modifier.padding(6.dp))
        EditTextItem(desc = "Current Status",state = tsStatus,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.padding(6.dp))
        ButtonClickItem(desc = "Calculate", onClick = { textString= tsmaxplunder(tsStatus.text.filter { it.isLetterOrDigit()}.toLong(),ts) })
        Spacer(modifier = Modifier.padding(6.dp))
        val result =if(!textString.isEmpty())NumberFormat.getNumberInstance(Locale.US).format(textString.toLong()) else "0"
        Text(result,color = MaterialTheme.colors.secondaryVariant,style = MaterialTheme.typography.button)
    }
}
@Composable
fun MaxPlunderDisplay(){
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
        var textString by remember { mutableStateOf("")}
        val statusState = remember { TextFieldState("0") }
        Spacer(modifier = Modifier.padding(6.dp))
        EditTextItem(desc = "Current Status",state = statusState,keyboard = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.padding(6.dp))
        ButtonClickItem(desc = "Calculate", onClick = { textString= tsmaxplunder(statusState.text.filter { it.isLetterOrDigit()}.toLong(),
            maxplunder) })
        Spacer(modifier = Modifier.padding(6.dp))
        val result =if(!textString.isEmpty())NumberFormat.getNumberInstance(Locale.US).format(textString.toLong()) else "0"
        Text(result,color = MaterialTheme.colors.secondaryVariant,style = MaterialTheme.typography.button)
    }
}