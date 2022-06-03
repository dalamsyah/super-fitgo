package com.fitgoapps.ui.pages.lapangan.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.FA_Regular
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LapanganDetailViewBody(navController: NavHostController, viewModel: LapanganDetailViewModel = viewModel()){

    val pagerState = rememberPagerState()

    val items = listOf(
        R.drawable.dummy1,
        R.drawable.dummy2,
        R.drawable.dummy1,
        R.drawable.dummy2,
        R.drawable.dummy1,
        R.drawable.dummy2,
    )

    Box(modifier = Modifier) {

        Column() {

            HorizontalPager(count = items.size, state = pagerState) { page ->

                Box() {

                    Image(
                        painter = painterResource(id = items[page]),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp))

                }

            }
        }

        Column(modifier = Modifier.padding(top = 250.dp)) {
            DotsIndicator(totalDots = items.size, selectedIndex = pagerState.currentPage)

            /**
             * Auto slide
             */
            /*LaunchedEffect(key1 = pagerState.currentPage) {
                delay(3000)
                var newPosition = pagerState.currentPage + 1
                if (newPosition > items.size - 1) newPosition = 0
                // scrolling to the new position.
                pagerState.animateScrollToPage(newPosition)
            }*/
        }

        Column(modifier = Modifier
            .padding(AppBarDefaults.ContentPadding)
            .height(appBarDefaultHeight)) {

            IconButton(onClick = { navController.popBackStack() }) {
                Text(
                    text = stringResource(id = R.string.icon_fa_icon_arrow_left),
                    fontFamily = FA,
                    color = Color.White,
                    fontSize = fontSizeIcon
                )
            }

        }
        
        Column(modifier = Modifier.padding(top = 280.dp)) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ) {

                Column() {

                    Column(modifier = Modifier
                        .padding(paddingLeftRight)
                        .fillMaxWidth()) {


                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {

                            Row(){
                                Text(text = "Walang Futsal", fontWeight = FontWeight.Bold)

                                Spacer(modifier = Modifier.width(10.dp))

                                Card(modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp)),
                                    backgroundColor = GreenTopBar
                                ){
                                    Text(text = "Open", color = Color.White, fontSize = 12.sp, modifier = Modifier.padding(5.dp))
                                }

                            }

                            Text(text = "2.5 km")
                        }

                        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()) {

                            Row(verticalAlignment = Alignment.CenterVertically){
                                Text(
                                    text = stringResource(id = R.string.icon_fa_icon_location_dot),
                                    fontFamily = FA,
                                    color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Jakarta Utara")
                            }

                            Row(){
                                IconButton(onClick = { /*TODO*/ }) {
                                    Text(
                                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                        text = stringResource(id = R.string.icon_fa_icon_heart),
                                        fontFamily = FA_Regular,
                                        fontSize = fontSizeIcon
                                    )
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Text(
                                        text = stringResource(id = R.string.icon_fa_icon_share_nodes),
                                        fontFamily = FA,
                                        color = Color.Black,
                                        fontSize = fontSizeIcon
                                    )
                                }

                            }

                        }

                        Row(modifier = Modifier){
                            for (i in 1..4){
                                Text(
                                    modifier = Modifier,
                                    text = stringResource(id = R.string.icon_fa_icon_star),
                                    fontFamily = FA,
                                    color = YellowStar,
                                    fontSize = fontSizeIcon
                                )
                            }
                            Text(
                                modifier = Modifier,
                                text = stringResource(id = R.string.icon_fa_icon_star),
                                fontFamily = FA_Regular,
                                color = YellowStar,
                                fontSize = fontSizeIcon
                            )
                        }


                    }

                    Column() {


                        /**
                         * TAB
                         */
                        TabScreen()

                    }
                }

            }
        }

    }

}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun DefaultPreview() {
    SuperFitgoTheme {

    }
}