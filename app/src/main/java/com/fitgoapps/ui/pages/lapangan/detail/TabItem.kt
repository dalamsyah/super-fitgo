package com.fitgoapps.ui.pages.lapangan.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.FA_Regular
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int = 0, var title: String, var screen: ComposableFun) {
    object Court : TabItem(0, "Court", {  })
    object Review : TabItem(0, "Review", {  })
    object Contact : TabItem(0, "Contact", {  })
    object MoreInfo : TabItem(0, "More Info", {  })
}

@Composable
fun CourtScreen(navController: NavHostController = rememberNavController()){
    LazyColumn(modifier = Modifier
        .fillMaxWidth()) {

        items(10){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(paddingLeftRight),
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    Text(text = "Lapangan Sintetis")
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                                append("Rp100.00$it.-")
                            }
                            append("/ jam")
                        }
                    )
                }

                Button(
                    shape = RoundedCornerShape(roundedCorner),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                    onClick = {
                        navController.navigate(FitgoScreen.CalendarDetailView.name)
                    }
                ) {
                    Text(text = stringResource(id = R.string.booknow), color = Color.White, fontWeight = FontWeight.Bold )
                }

            }

            Divider()
        }

    }

}

@Composable
fun ReviewScreen(navController: NavHostController){
    LazyColumn(modifier = Modifier
        .fillMaxWidth()) {

        items(10){
            
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(paddingLeftRight)) {

                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically){
                    for (i in 1..4){
                        Text(
                            modifier = Modifier,
                            text = stringResource(id = R.string.icon_fa_icon_star),
                            fontFamily = FA,
                            color = YellowStar,
                            fontSize = 10.sp
                        )
                    }
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.icon_fa_icon_star),
                        fontFamily = FA_Regular,
                        color = YellowStar,
                        fontSize = 10.sp
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "$it Hari lalu", fontSize = 10.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar01),
                        contentDescription = "avatar01",
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Dimas Alamsyah")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(fontSize = 12.sp, text = "Aplikasinya bagus, lapangan nya keren dan juga cara pembayarannya mudah. Sukses terus untuk fitgo apps.")

            }

            Divider()
        }


    }
}

@Composable
fun ContactScreen(navController: NavHostController){

    val state = rememberScrollState()

    Column(modifier = Modifier
        .verticalScroll(state)
        .padding(paddingLeftRight)
        .fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.icon_fa_icon_location_dot),
                fontFamily = FA,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Jl. Raya Walang No. 13 RT. 04 RW. 06 Cilincing, Jakarta Utara 14130")
        }

        Row(verticalAlignment = Alignment.CenterVertically){
            Text(
                text = stringResource(id = R.string.icon_fa_icon_phone),
                fontFamily = FA,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "089635781185 (Fauzi)")
        }
        
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier
                .weight(1f)
                .padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = stringResource(id = R.string.icon_fa_icon_route),
                        fontFamily = FA,
                        color = Green
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Direction")
                }
            }
            OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier
                .weight(1f)
                .padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = stringResource(id = R.string.icon_fa_icon_phone),
                        fontFamily = FA,
                        color = Green
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Call Outlet")
                }
            }
        }


        /**
         * Maps
         */
        Image(
            painter = painterResource(id = R.drawable.maps),
            contentDescription = "maps",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp))

    }
}

@Composable
fun MoreInfoScreen(navController: NavHostController){

    val state = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(state)
        .padding(paddingLeftRight)) {
        Text(text = "Jadwal", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Senin: 10.00 - 22.00\n" +
                "Selasa: 10.00 - 22.00\n" +
                "Rabu: 10.00 - 22.00\n" +
                "Kamis: 10.00 - 22.00\n" +
                "Jum’at: Close\n" +
                "Sabtu: 08.00 - 24.00\n" +
                "Minggu: 08.00 - 24.00\n" +
                "\n" +
                "Note: Jadwal dapat berubah sesuai operasional.")
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        indicator = {
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(it[pagerState.currentPage]),
                color = Green
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            // OR Tab()
            LeadingIconTab(
                selectedContentColor = Green,
                unselectedContentColor = Color.Black,
                icon = {

                },
                text = {
                    Text(tab.title)
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->

        when(tabs[page].title){
            TabItem.Court.title -> {
                CourtScreen(navController = navController)
            }
            TabItem.Review.title -> {
                ReviewScreen(navController = navController)
            }
            TabItem.Contact.title -> {
                ContactScreen(navController = navController)
            }
            TabItem.MoreInfo.title -> {
                MoreInfoScreen(navController = navController)
            }
        }
    }
}



@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun TabScreen(navController: NavHostController){
    val tabs = listOf(TabItem.Court, TabItem.Review, TabItem.Contact, TabItem.MoreInfo)
    val pagerState = rememberPagerState()

    Column {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState, navController = navController)
    }

}
