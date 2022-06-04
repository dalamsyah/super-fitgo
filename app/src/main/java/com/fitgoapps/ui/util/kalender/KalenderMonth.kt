package com.fitgoapps.ui.util.kalender

import android.os.Build
import androidx.annotation.IntRange
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.util.getMonthNameFormatter
import java.time.LocalDate
import java.time.YearMonth
import java.util.*

private const val DAYS_IN_WEEK = 7

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalenderMonth(
    kalenderKonfig: KalenderKonfig,
    selectedDay: LocalDate,
    kalendarSelector: KalendarSelector,
) {

    val yearMonth =  YearMonth.now()

    val monthState = remember {
        mutableStateOf(yearMonth)
    }
    val clickedDay = remember {
        mutableStateOf(selectedDay)
    }

    val days: List<LocalDate> = getDays(monthState)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ){

        KalenderHeader(
            text = monthState.value.format(getMonthNameFormatter(kalenderKonfig.locale)),
            onNextMonthClick = {
                val year = monthState.value.year
                val isLimitAttached = year.validateMaxDate(kalenderKonfig.yearRange.max)
                if (isLimitAttached) {
                    monthState.value = monthState.value.plusMonths(1)
                } else {
//                    errorMessageLogged("Minimum year limit reached")
                }
            },
            onPreviousMonthClick = {
                val year = monthState.value.year
                val isLimitAttached = year.validateMinDate(kalenderKonfig.yearRange.min)
                if (isLimitAttached) {
                    monthState.value = monthState.value.minusMonths(1)
                } else {
//                    errorMessageLogged("Minimum year limit reached")
                }
            }
        )

        days.chunked(DAYS_IN_WEEK).forEach { weekDays ->
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val size = (maxWidth / DAYS_IN_WEEK)
                Row(horizontalArrangement = Arrangement.spacedBy(0.dp)) {
                    weekDays.forEach { localDate ->
                        val isFromCurrentMonth = YearMonth.from(localDate) == monthState.value

                        if(isFromCurrentMonth){
                            val isSelected = monthState.value.month == clickedDay.value.month &&
                                    monthState.value.year == clickedDay.value.year &&
                                    localDate == clickedDay.value

                            KalenderDay(
                                size = size,
                                date = localDate,
                                isSelected = isSelected,
                                kalendarSelector = kalendarSelector
                            )

                        } else {

                            KalenderDay(
                                size = size,
                                date = localDate,
                                isSelected = false,
                                kalendarSelector = kalendarSelector
                            )

                        }

                    }
                }
            }
        }

    }


}

/**
 * [KalendarKonfig] represents the config needed for rendering calendar
 * @param[yearRange] gives the min/max year range
 * @param[weekCharacters] helps you set the number of character in Week name, default is 3
 * @param[locale] helps you set the locale where default is [Locale.ENGLISH]
 */
data class KalenderKonfig(
    val yearRange: YearRange = YearRange(),
    @IntRange(from = 1, to = 4)
    val weekCharacters: Int = 3,
    val locale: Locale = Locale.ENGLISH,
)

/**
 * [YearRange] represents range from
 * [min] years to
 * [max] years
 */
data class YearRange(val min: Int = 0, val max: Int = 0)

internal fun Int.validateMaxDate(year: Int): Boolean {
    return if (year == 0) {
        true
    } else {
        year > this
    }
}

internal fun Int.validateMinDate(year: Int): Boolean {
    return if (year == 0) {
        true
    } else {
        year < this
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getDays(monthState: MutableState<YearMonth>): List<LocalDate> {
    return mutableListOf<LocalDate>().apply {
        val firstDay = monthState.value.atDay(1)
        val firstSunday = if (firstDay.dayOfWeek == java.time.DayOfWeek.SUNDAY) {
            firstDay
        } else {
            firstDay.minusDays(firstDay.dayOfWeek.value.toLong())
        }
        repeat(6) { weekIndex ->
            (0..6).forEach { dayIndex ->
                add(firstSunday.plusDays((7 * weekIndex + dayIndex).toLong()))
            }
        }
    }
}