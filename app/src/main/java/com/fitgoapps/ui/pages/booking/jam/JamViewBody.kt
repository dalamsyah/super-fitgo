package com.fitgoapps.ui.pages.booking.jam

import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.FA_Regular
import com.fitgoapps.ui.util.TopAppBarCustom
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType

@Composable
fun JamViewBody(navController: NavHostController = rememberNavController(), viewModel: JamViewModel = viewModel()) {

    val jam = remember {
        mutableStateListOf("")
    }

    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.choose_schedule))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(paddingLeftRight), verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.icon_fa_icon_calendar),
                fontFamily = FA_Regular,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(text = "10 Agustus 2022")
        }

        LazyColumn(modifier = Modifier
            .padding(paddingLeftRight)
            .weight(1f)){
            items(50){
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                        val check = jam.find { jam ->
                            jam == "$it"
                        }

                        if (check != null) {
                            jam.remove("$it")
                        } else {
                            jam.add("$it")
                        }

                    }
                    .border(
                        border = BorderStroke(1.dp, GreenTopBar),
                        shape = RoundedCornerShape(5.dp)
                    ),  verticalAlignment = Alignment.CenterVertically
                ) {

                    val check = jam.find { jam ->
                        jam == "$it"
                    }

                    if (check != null){
                        Text(text = " ", modifier = Modifier.padding(start = paddingLeftRight))
                    }

                    Text(text = "1$it:00", textAlign = TextAlign.Center, modifier = Modifier.weight(1f)
                        .padding(10.dp))

                    if (check != null){
                        Text(
                            modifier = Modifier.padding(end = paddingLeftRight),
                            text = stringResource(id = R.string.icon_fa_icon_circle_check),
                            fontFamily = FA_Regular,
                            color = Green,
                            fontSize = fontSizeIcon
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        ButtonPrimary(text = stringResource(id = R.string.confirm_and_pay),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = paddingLeftRight,
                    end = paddingLeftRight,
                    bottom = navigationBottomPaddingCustom
                ), onClick = {
                navController.navigate(FitgoScreen.IndexView.name)
            })

    }

}