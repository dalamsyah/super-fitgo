package com.fitgoapps.ui.pages.lapangan.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.fitgoapps.R
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.FA_Regular
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var icon: Int = 0, var title: String, var screen: ComposableFun) {
    object Court : TabItem(0, "Court", { CourtScreen() })
    object Review : TabItem(0, "Review", { ReviewScreen() })
    object Contact : TabItem(0, "Contact", { ContactScreen() })
    object MoreInfo : TabItem(0, "More Info", { MoreInfoScreen() })
}

@Composable
fun CourtScreen(){
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
fun ReviewScreen(){
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
fun ContactScreen(){
    Column() {
        Text(text = "ContactScreen")
    }
}

@Composable
fun MoreInfoScreen(){
    Column() {
        Text(text = "MoreInfoScreen")
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
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}



@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun TabScreen(){
    val tabs = listOf(TabItem.Court, TabItem.Review, TabItem.Contact, TabItem.MoreInfo)
    val pagerState = rememberPagerState()

    Column {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }

}
