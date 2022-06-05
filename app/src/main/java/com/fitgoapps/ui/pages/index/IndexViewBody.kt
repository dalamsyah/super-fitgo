package com.fitgoapps.ui.pages.index

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.TemplateNavigation
import com.fitgoapps.ui.pages.booking.detail.BookingDetailViewBody
import com.fitgoapps.ui.pages.favorites.FavortiesViewBody
import com.fitgoapps.ui.pages.home.HomeViewBody
import com.fitgoapps.ui.pages.login.LoginViewModel
import com.fitgoapps.ui.pages.sport.SportViewBody
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

sealed class BottomNavigationItem(var icon : Int, var title: Int, var layout: @Composable () -> Unit){
    object Home : BottomNavigationItem( R.string.icon_fa_icon_home, R.string.home, {  })
    object Favorites : BottomNavigationItem( R.string.icon_fa_icon_heart, R.string.favorites, { })
    object Booking : BottomNavigationItem( R.string.icon_fa_icon_book, R.string.booking, {  })
    object Sport : BottomNavigationItem( R.string.icon_fa_icon_futbol, R.string.sport, {  })
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun IndexViewBody(navController: NavHostController, viewModel: LoginViewModel = viewModel()){

    val currentPage = remember { mutableStateOf(0) }

    val tabs = listOf<BottomNavigationItem>(
        BottomNavigationItem.Home, BottomNavigationItem.Favorites, BottomNavigationItem.Booking, BottomNavigationItem.Sport
    )

    Box(modifier = Modifier.fillMaxSize()){

        Column(modifier = Modifier) {

            /**
             * Content Body
             */
            when(tabs[currentPage.value].title){
                BottomNavigationItem.Home.title -> {
                    HomeViewBody(navController = navController)
                }
                BottomNavigationItem.Booking.title -> {
                    BookingDetailViewBody(navController = navController)
                }
                BottomNavigationItem.Favorites.title -> {
                    FavortiesViewBody(navController = navController)
                }
                BottomNavigationItem.Sport.title -> {
                    SportViewBody(navController = navController)
                }
                else -> {
                    HomeViewBody(navController = navController)
                }
            }

        }

        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start = paddingLeftRight,
                    end = paddingLeftRight,
                    bottom = navigationBottomPaddingCustom
                )
                .shadow(10.dp, RoundedCornerShape(20.dp))
        ) {

            tabs.forEachIndexed { index, item ->

                var color = Green
                if (currentPage.value != index) {
                    color = Green.copy(0.4f)
                }

                BottomNavigationItem(
                    icon = { Text(
                        text = stringResource(id = item.icon),
                        fontFamily = FA,
                        color = color
                    ) },
                    label = { Text(text = stringResource(id = item.title)) },
                    selectedContentColor = Green,
                    unselectedContentColor = Green.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentPage.value == index,
                    onClick = {
                        currentPage.value = index
                    }
                )
            }

        }
    }

}


@Composable
fun DefaultPreview() {
    SuperFitgoTheme {

    }
}