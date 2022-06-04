package com.fitgoapps.ui.pages.booking.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.ui.util.kalender.KalenderKonfig
import com.fitgoapps.ui.util.kalender.KalenderMonth
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarViewBody(navController: NavHostController, viewModel: CalendarViewModel = viewModel()){

    val kalendarStyle = KalendarStyle()

    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        
        Spacer(modifier = Modifier.height(100.dp))

        Column(modifier = Modifier) {
            KalenderMonth(KalenderKonfig(), kalendarSelector = kalendarStyle.kalendarSelector, selectedDay = LocalDate.now())
        }

    }
}