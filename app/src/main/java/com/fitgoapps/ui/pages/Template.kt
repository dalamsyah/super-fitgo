package com.fitgoapps.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA

@Composable
fun TemplateNavigation(
    navController: NavHostController,
    contentTopBar: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    bottomNavigationContent: @Composable () -> Unit,
){

    Box(modifier = Modifier.fillMaxWidth()) {

        Column() {
            TopAppBar(modifier = Modifier.height(appBarDefaultHeightCustom), content = contentTopBar)

            Column(modifier = Modifier, content = content)
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

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_home),
                    fontFamily = FA,
                    color = Green
                ) },
                label = { Text(text = stringResource(id = R.string.home)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = true,
                onClick = {  }
            )

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_heart),
                    fontFamily = FA,
                    color = Color.Gray
                ) },
                label = { Text(text = stringResource(id = R.string.favorites)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {  }
            )

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_book),
                    fontFamily = FA,
                    color = Color.Gray
                ) },
                label = { Text(text = stringResource(id = R.string.booking)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigate(FitgoScreen.BookingDetailView.name)
                }
            )

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_futbol),
                    fontFamily = FA,
                    color = Color.Gray
                ) },
                label = { Text(text = stringResource(id = R.string.sport)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {  }
            )

        }

    }
}