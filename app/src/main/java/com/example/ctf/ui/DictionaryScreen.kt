package com.example.ctf.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ctf.ui.component.AdvertView
import com.example.ctf.ui.component.TermTextItem

@Composable
fun DictionaryScreen(){
    Column {
        AdvertView()
        Spacer(modifier = Modifier.padding(4.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 60.dp)
                .verticalScroll(rememberScrollState())
        ){
            TermTextItem(title = "Alt", desc = "Alternative account. Someone may have a main account, and many 'alts', smaller accounts they use less.")
            TermTextItem(title = "ATA", desc = "A Thinking Ape. The company that designed PIMD.")
            TermTextItem(title = "Avi", desc = "Avatar. The picture/person a user uses as their profile. Avatars give out different statistic boosts as well")
            TermTextItem(title = "B2B", desc = "Back to Back. Commonly used in reference to parties or cat café.")
            TermTextItem(title = "BC", desc = "Build Complete (referring to dorm). Filled all of the dorm rooms with a particular level of dorm mates.")
            TermTextItem(title = "Bump", desc = "Hire a persons tutor a set amount of times in order to increase their hire value.")
            TermTextItem(title = "BL", desc = "Battle List. You find this by clicking the 'people' button and there you can hit other users around the same statistics.")
            TermTextItem(title = "CA", desc = "Club announcement. In club chat you have a header with a description that admins can change.")
            TermTextItem(title = "CC", desc = "Cat Café, Counter Culture, or Cabana Cool. All party types, usually the first. Can also mean club chat, where you can talk to members of your club")
            TermTextItem(title = "CCBC", desc = "This is an informal term used for those who appear to have gained their high stats through pure back-to-back cat café parties.")
            TermTextItem(title = "Cow", desc = "A user on the opposite side of war that is Defender Too Weak, and therefore used for milking for plunder.")
            TermTextItem(title = "CF", desc = "Cease Fire. Used for making conditions for someone to discontinue hitting another player.")
            TermTextItem(title = "D", desc = "Dance. A battle action. Generally associated with being called a fairy, try fight instead.")
            TermTextItem(title = "Devs", desc = "Developers! The ones who made our game, they pop up in red every now and then.")
            TermTextItem(title = "DN", desc = "Doctors Note. Used for instant regenerating of energy.")
            TermTextItem(title = "DTS", desc = "Defender Too Strong. This notice is given when a user has much higher statistics than you, therefore you can't hit them.")
            TermTextItem(title = "DTW", desc = "Defender Too Weak. This notice is given when a user you are hitting is too low on energy for you to hit.")
            TermTextItem(title = "DVP", desc = "Drop Volley Profit. Hiring a tutor back and forth in order to get money, a term used for buying things on PIMD. For Example, people were selling Sweet Temptations, for 1B dvp each.")
            TermTextItem(title = "EC", desc = "Extra Credit. Used for buying special items in the store and other things.")
            TermTextItem(title = "ED", desc = "Eavesdrop. A battle action for seeing other people's dorms.")
            TermTextItem(title = "F", desc = "Fight. A battle action (You should mainly use this one)")
            TermTextItem(title = "FA", desc = "Fake Art. A type of party.")
            TermTextItem(title = "Farming", desc = "A user targeting another user by hitting them many times. How many hits define farming vary from user to user.")
            TermTextItem(title = "FV", desc = "Force Volley. When you hire someone's tutor that doesn't want to give them up, forcing them to keep hiring back.")
            TermTextItem(title = "HS", desc = "Honour Student. The cool blue people you see popping around here and there that help out.")
            TermTextItem(title = "INC", desc = "Incoming. Refers to hits you've received.")
            TermTextItem(title = "Insect", desc = "Someone who was defeated easily in an attack. Used for reporting in war, you can tell if they are an insect by reading on the bottom of the attack notification and it says \"you crushed them like an insect!\".")
            TermTextItem(title = "KCS", desc = "Thousand stats combined. Can be calculated by adding your strength and intelligence statistics (see your profile) and dividing by a thousand.")
            TermTextItem(title = "LBH", desc = "Last Bar Hitters. Commonly used for advertising for party hitters.")
            TermTextItem(title = "LCBC", desc = "Level Complete Build Complete. For those who have filled all their dorms with the highest level of dormmates, therefore obtaining the highest stats you can on PIMD. This differs from CCBC as usually it refers to someone who has achieved it over time by spending little money in the game.")
            TermTextItem(title = "Main", desc = "Main Account. As in the account a player uses the most, as opposed to other accounts they may own.")
            TermTextItem(title = "MCS", desc = "Million stats combined. Calculated same as KCS but divided by a million.")
            TermTextItem(title = "Merc", desc = "Player roaming from club to club helping with parties.")
            TermTextItem(title = "Milking", desc = "Hitting a player/party in a certain pattern in order to gain most money from them.")
            TermTextItem(title = "Mod", desc = "Moderators. The green users that keep our community clean and safe by silencing and reinforcing the rules.")
            TermTextItem(title = "Noob", desc = "Informal name for a new player/someone who doesn't appear to know how to play.")
            TermTextItem(title = "NP", desc = "Not Pinned. When an opponent is still not Defender Too Weak/low on energy. Used in reporting hits for war.")
            TermTextItem(title = "OP", desc = "Either means Original Poster (referring to a thread) or Over Priced (referring to hire value being higher than usual for your statistics)")
            TermTextItem(title = "P", desc = "Prank. Attack type. No point really in it other than to lower the opponents energy.")
            TermTextItem(title = "Perm", desc = "A Permanent member of a club.")
            TermTextItem(title = "Pesting", desc = "Hitting a much larger player and failing in order to lower their energy. A term used in war.")
            TermTextItem(title = "Pin", desc = "Get another player to low energy, to Defender Too Weak. Often used in wars.")
            TermTextItem(title = "Plunder", desc = "Cash earned through parties or wars.")
            TermTextItem(title = "PM", desc = "Private Message. When another users messages you.")
            TermTextItem(title = "Pots", desc = "One time attack/defense items that can be bought from the store in order to aid with attacks and defence. Mainly used for hitting players but attack items can also be used in parties.")
            TermTextItem(title = "PP", desc = "Prank Primer. A type of party.")
            TermTextItem(title = "PPA", desc = "Pizza Pop Art. A party started by using a pizza bikini")
            TermTextItem(title = "Pup", desc = "Pupil. The lovely user who bought you.")
            TermTextItem(title = "PvP", desc = "Player vs. Player (aka hitting off the Battle List or elsewhere)")
            TermTextItem(title = "Regen", desc = "Regenerating. Refers to someone gaining energy to be full again.")
            TermTextItem(title = "RP", desc = "Role Play (or as I prefer, roast potatoes). Avoid.")
            TermTextItem(title = "RS", desc = "Relationship. The person you are \"dating\"/sharing statistics with.")
            TermTextItem(title = "SF", desc = "Strip Farm. Where someone strips a player of their tutors and farms them in order to make them lose all their money.")
            TermTextItem(title = "SFW", desc = "Strip Farm War. When two or more groups of people (or clubs) are strip farming eachother.")
            TermTextItem(title = "Stats", desc = "Statistics. Your strength and intelligence combined")
            TermTextItem(title = "Strip", desc = "NOT that kind of strip. When someone asks if you can strip their tutors, they mean hire them all.")
            TermTextItem(title = "Tag", desc = "lub Tag, this is a few letters specific to a club that some users have added to their username to show they are a permanent member there.")
            TermTextItem(title = "TB", desc = "Tutor Bonus. The amount of stat bonus that comes from the tutors you hire. (Also means bovine tuberculosis but not here!)")
            TermTextItem(title = "TC", desc = "utor Chat. A chat where you can talk to your pupil and your tutors similar to club chat.")
            TermTextItem(title = "Tuts", desc = "Tutors. The users you buy in order to increase payout and stats.")
            TermTextItem(title = "UG", desc = "Upgrade. When a player increases in statistics usually by adding another dormmate to their dorm.")
            TermTextItem(title = "UL", desc = "Unload. When a player hits a party/another person to the point where they have no energy")
            TermTextItem(title = "UP", desc = "Under Priced. Refers to your hire value being lower than usual for your statistics.")
            TermTextItem(title = "Volley", desc = "Hiring a person multiple times to increase their hire value and/or to gain money.")
            TermTextItem(title = "WA", desc = "World Announcement. The header on the top of the screen of world chat where players can make public announcements for 26 speakers.")
            TermTextItem(title = "WC", desc = "World Chat. Where players can use speakers in order to message the public.")
            TermTextItem(title = "WW", desc = "Wedding War. Fun wars against two people and their selected sides that they organise.")
        }

    }
}