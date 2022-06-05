package com.fitgoapps.ui.pages.booking.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.TopAppBarCustom
import com.fitgoapps.ui.util.kalender.KalenderKonfig
import com.fitgoapps.ui.util.kalender.KalenderMonth
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.next
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarViewBody(navController: NavHostController, viewModel: CalendarViewModel = viewModel()){

    var kalendarStyle = KalendarStyle(
        kalendarSelector = KalendarSelector.Rounded(
            selectedColor = Green,
            todayColor = Green.copy(0.5f),
        ),
    )

    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.choose_date))

        Column(modifier = Modifier.padding(start = paddingLeftRight, end = paddingLeftRight, top = appBarCustomPadding)) {

            KalenderMonth(
                KalenderKonfig(locale = Locale.getDefault()),
                kalendarSelector = kalendarStyle.kalendarSelector,
                selectedDay = LocalDate.now(), onCurrentDayClick = { date, event ->

                })

            ButtonPrimary(text = stringResource(id = R.string.next), modifier = Modifier.fillMaxWidth(), onClick = {
//                navController.navigate(FitgoScreen.IndexView.name)
            })

        }

    }

}