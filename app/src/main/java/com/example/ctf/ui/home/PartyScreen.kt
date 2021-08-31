package com.example.ctf.ui.home
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.ctf.data.local.entities.Dropped
import com.example.ctf.data.local.entities.Party
import com.example.ctf.data.local.entities.Today
import com.example.ctf.ui.component.*
import com.example.ctf.util.Constants.NA
import com.example.ctf.util.Status
import com.example.ctf.util.listString.all
import com.example.ctf.util.listString.anime
import com.example.ctf.util.listString.anime7
import com.example.ctf.util.listString.arts
import com.example.ctf.util.listString.arts7
import com.example.ctf.util.listString.dropped
import com.example.ctf.util.listString.hpstr
import com.example.ctf.util.listString.hpstr7
import com.example.ctf.util.listString.nope
import com.example.ctf.util.listString.reguler
import com.example.ctf.util.listString.thebc
import com.example.ctf.util.listString.thebc7
import com.example.ctf.util.listString.vipp
import com.example.ctf.util.listString.vipp7

@Composable
fun PartyScreen(navController: NavHostController) {
    val homeVM = hiltViewModel<HomeViewModel>()
    var today = Today("", "", "")
    val regulerDropList = mutableListOf<Dropped>()
    val ultraDropList = mutableListOf<Dropped>()
    val listParty = listOf(arts,thebc,hpstr,vipp,anime,arts7,thebc7,hpstr7,vipp7,anime7,dropped,nope,all)
    val artsList = mutableListOf<Party>()
    val thebcList = mutableListOf<Party>()
    val hpstrList = mutableListOf<Party>()
    val vippList = mutableListOf<Party>()
    val animeList = mutableListOf<Party>()
    val arts7List = mutableListOf<Party>()
    val thebc7List = mutableListOf<Party>()
    val hpstr7List = mutableListOf<Party>()
    val vipp7List = mutableListOf<Party>()
    val anime7List = mutableListOf<Party>()
    val droppedList = mutableListOf<Party>()
    val nopeList = mutableListOf<Party>()
    val allList = mutableListOf<Party>()
    var visibleParty by remember { mutableStateOf(arts) }
    var saveTodayDialog by remember { mutableStateOf(false) }
    var saveDropDialog by remember { mutableStateOf(false) }
    var savePartyDialog by remember { mutableStateOf(false) }
    var userListDialog by remember { mutableStateOf(false) }
    val savePartyState = homeVM.savePartyStatus.observeAsState()
    savePartyState.value?.let {
        val result= it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {}
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val partyListState = homeVM.partyListStatus.observeAsState()
    partyListState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.forEach {party->
                    if(party.role == arts && party.status == NA){
                        artsList.add(party)
                    }else if(party.role == thebc && party.status ==NA){
                        thebcList.add(party)
                    }else if(party.role == hpstr && party.status == NA){
                        hpstrList.add(party)
                    }else if(party.role == vipp && party.status == NA){
                        vippList.add(party)
                    }else if(party.role== anime && party.status == NA){
                        animeList.add(party)
                    }else if(party.role==arts7 && party.status == NA){
                        arts7List.add(party)
                    }else if(party.role== thebc7 && party.status ==NA){
                        thebc7List.add(party)
                    }else if(party.role== hpstr7 && party.status==NA){
                        hpstr7List.add(party)
                    }else if(party.role==vipp7 && party.status == NA){
                        vipp7List.add(party)
                    }else if(party.role==anime7 && party.status == NA){
                        anime7List.add(party)
                    }else if(party.status != nope && party.status != NA){
                        droppedList.add(party)
                    }else{
                        nopeList.add(party)
                    }
                }
                it.data?.forEach {party ->
                    allList.add(party)
                }
            }
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val saveDropState = homeVM.saveDropStatus.observeAsState()
    saveDropState.value?.let {
        val result = it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {}
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val dropListState = homeVM.dropListStatus.observeAsState()
    dropListState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                it.data?.forEach {dropped ->
                    if(dropped.role == reguler){
                        regulerDropList.add(dropped)
                    }else{
                        ultraDropList.add(dropped)
                    }
                }
            }
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val saveTodayState = homeVM.saveTodayStatus.observeAsState()
    saveTodayState.value?.let {
        val result= it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {}
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val todayState = homeVM.todayStatus.observeAsState()
    todayState.value?.let {
        when (it.status) {
            Status.SUCCESS -> {
                today = it.data ?: return@let
            }
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    val deleteDropState = homeVM.deleteDropStatus.observeAsState()
    deleteDropState.value?.let {
        val result =it.peekContent()
        when (result.status) {
            Status.SUCCESS -> {}
            Status.ERROR -> {}
            Status.LOADING -> {
                ProgressCardToastItem()
            }
        }
    }
    Column{
        if (saveTodayDialog) SaveTodayDialog(today,onClick={saveTodayDialog = !saveTodayDialog})
        if (saveDropDialog) SaveDropDialog(onClick={saveDropDialog=!saveDropDialog})
        if (savePartyDialog) SavePartyDialog(Party(role= visibleParty,"","",NA))
        if (userListDialog) ObserveUserList(onClick={userListDialog= !userListDialog},navController = navController)
        Spacer(Modifier.padding(2.dp))
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            val (spacerText, regulerText, listReguler, ultraText, listUltra) = createRefs()

            Spacer(
                modifier = Modifier
                    .constrainAs(spacerText) {
                        centerVerticallyTo(regulerText)
                        centerHorizontallyTo(parent)
                        height = Dimension.fillToConstraints
                    }
            )
            Text(
                "Today Reguler: ${today.reguler}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(bottom = 8.dp,top=8.dp)
                    .constrainAs(regulerText) {
                        start.linkTo(parent.start)
                        bottom.linkTo(listReguler.top)
                    }
                    .clickable { saveTodayDialog = !saveTodayDialog }
            )
            Text(
                "Today Ultra: ${today.ultra}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(bottom = 8.dp,top=8.dp)
                    .constrainAs(ultraText) {
                        start.linkTo(spacerText.end)
                        bottom.linkTo(listUltra.top)
                    }
                    .clickable { saveDropDialog = !saveDropDialog })
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .horizontalScroll(rememberScrollState())
                    .constrainAs(listReguler) {
                        start.linkTo(parent.start)
                        end.linkTo(spacerText.start)
                    }
            ) {
                regulerDropList.forEach {
                    CardDrop(it)
                    Spacer(modifier = Modifier.padding(start = 2.dp))
                }
            }
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .horizontalScroll(rememberScrollState())
                    .constrainAs(listUltra) {
                        start.linkTo(spacerText.end)
                        end.linkTo(parent.end)
                    }
            ) {
                ultraDropList.forEach {
                    CardDrop(it)
                    Spacer(modifier = Modifier.padding(start = 2.dp))
                }
            }
        }
        Spacer(modifier = Modifier.padding(4.dp))
        var tabIndex by remember { mutableStateOf(0) }
        ScrollableTabRow(
            selectedTabIndex = tabIndex, modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.Transparent
        ) {
            listParty.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                    visibleParty = text
                    savePartyDialog = !savePartyDialog
                }, text = {
                    Text(
                        text,
                        style = if (visibleParty == text) MaterialTheme.typography.h2 else MaterialTheme.typography.button,
                        color = if (visibleParty == text)colors.primary else colors.onBackground
                    )
                })
            }
        }
        val listDisplay:MutableList<Party> = when(visibleParty){
            arts -> artsList
            thebc -> thebcList
            hpstr -> hpstrList
            vipp -> vippList
            anime -> animeList
            arts7 -> arts7List
            thebc7 -> thebc7List
            hpstr7 -> hpstr7List
            vipp7 -> vipp7List
            anime7 -> anime7List
            dropped -> droppedList
            nope -> nopeList
            else -> allList
        }
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {

            listDisplay.forEach { party->
                Card(
                    Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp,colors.primaryVariant),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor =colors.secondary
                ) {
                    var editPartyDialog by remember { mutableStateOf(false) }
                    if(editPartyDialog) SavePartyDialog(party = party)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { editPartyDialog = !editPartyDialog }
                            .padding(8.dp),
                        horizontalArrangement=Arrangement.SpaceBetween,
                        verticalAlignment=Alignment.CenterVertically
                    ) {
                        val username = getUsernameLoginFunction()
                        GetAuthenticateFunction()
                        val check by remember { mutableStateOf(username in party.check) }
                        val nope by remember { mutableStateOf(username in party.nope) }
                        val drop by remember { mutableStateOf(username in party.drop) }
                        val countChecked by remember{ mutableStateOf(party.check.size)}
                        val countNope by remember { mutableStateOf(party.nope.size)}
                        val countDropped by remember { mutableStateOf(party.drop.size)}
                        val status by remember { mutableStateOf("Status")}
                        Row(Modifier.fillMaxWidth(0.2f), Arrangement.Start) {
                            ProfileInfoItem(number = party.name, desc = party.duration)
                        }
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(party.status,color=colors.onBackground,style=MaterialTheme.typography.body1)
                                Spacer(modifier = Modifier.padding(2.dp))
                                Text(status,color=colors.onBackground,style=MaterialTheme.typography.subtitle2)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if(check){
                                    Text(countChecked.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.check)
                                            userListDialog = !userListDialog},colors.error,style=MaterialTheme.typography.body1)
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text("Checked",Modifier.clickable {
                                        homeVM.toggleCheck(party)},colors.error,style=MaterialTheme.typography.subtitle2)
                                }else{
                                    Text(countChecked.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.check)
                                            userListDialog = !userListDialog},colors.onBackground,style=MaterialTheme.typography.body1)
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text("Check",Modifier.clickable {
                                        homeVM.toggleCheck(party)},colors.onBackground,style=MaterialTheme.typography.body1
                                    )
                                }
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if(nope){
                                    Text(countNope.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.nope)
                                            userListDialog = !userListDialog},Color.Red,style=MaterialTheme.typography.body1)
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text("Nope",Modifier.clickable {
                                        homeVM.toggleNope(party)},Color.Red,style=MaterialTheme.typography.subtitle2)
                                }else{
                                    Text(countNope.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.nope)
                                            userListDialog = !userListDialog},colors.onBackground,style=MaterialTheme.typography.body1)
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text("Nope",Modifier.clickable {
                                        homeVM.toggleNope(party)},colors.onBackground,style=MaterialTheme.typography.body1)
                                }
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if(drop){

                                    Text(countDropped.toString(),
                                        Modifier
                                            .clickable{homeVM.getListUser(party.drop)
                                                userListDialog = !userListDialog},colors.onError,style=MaterialTheme.typography.body1)
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text("Dropped",Modifier.clickable {
                                        homeVM.toggleDrop(party)},colors.onError,style=MaterialTheme.typography.subtitle2)
                                }else{
                                    Text(countDropped.toString(),Modifier
                                        .clickable{homeVM.getListUser(party.drop,)
                                            userListDialog = !userListDialog},colors.onBackground,style=MaterialTheme.typography.body1)
                                    Spacer(modifier = Modifier.padding(2.dp))
                                    Text("Drop",Modifier.clickable {
                                        homeVM.toggleDrop(party) },colors.onBackground,style=MaterialTheme.typography.body1)
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(2.dp))
            }
        }
    }
}