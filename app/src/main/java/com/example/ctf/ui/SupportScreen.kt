package com.example.ctf.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ctf.R
import com.example.ctf.ui.component.AdvertView
@Composable
fun SupportScreen(){
    Column (Modifier.fillMaxSize().padding(top=12.dp,bottom=60.dp,start = 12.dp,end = 12.dp),horizontalAlignment = Alignment.CenterHorizontally){
        AdvertView()
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically){
            Image(painter=painterResource(R.drawable.instagram),modifier=Modifier.size(48.dp),contentDescription="")
            Spacer(Modifier.padding(5.dp))
            Column {
                Text("Follow us on Instagram",color= MaterialTheme.colors.onBackground)
                Text("ctforever24",color= MaterialTheme.colors.onBackground,style=MaterialTheme.typography.button)
            }
        }
        Text("We can share post at there and the credit will give to you. Follow us for more!!",color= MaterialTheme.colors.onBackground,textAlign = TextAlign.Justify)
        Spacer(Modifier.padding(8.dp))
        Image(painter = painterResource(R.drawable.ctfig), contentDescription = "",modifier=Modifier.size(180.dp))
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically){
            Image(painter=painterResource(R.drawable.email),modifier=Modifier.size(48.dp),contentDescription="")
            Spacer(Modifier.padding(5.dp))
            Column {
                Text("Send your Feedback & Support",color= MaterialTheme.colors.onBackground)
                SelectionContainer {
                    Text("chattingtrading@gmail.com",color= MaterialTheme.colors.onBackground)
                }
            }
        }
    }
}