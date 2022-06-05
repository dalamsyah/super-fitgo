package com.fitgoapps.ui.util.kalender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fitgoapps.R
import com.fitgoapps.ui.theme.Green
import com.fitgoapps.ui.theme.fontSizeIcon
import com.fitgoapps.ui.util.FA
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.data.KalendarEvent
import com.himanshoe.kalendar.common.theme.Grid
import com.himanshoe.kalendar.common.theme.KalendarShape
import java.text.DateFormatSymbols
import java.time.LocalDate

private const val ZERO = 0

@Composable
fun KalenderHeader(
    text: String,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
){

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        IconButton(onClick = onPreviousMonthClick) {
            Text(
                text = stringResource(id = R.string.icon_fa_icon_circle_arrow_left),
                fontFamily = FA,
                color = Green,
                fontSize = fontSizeIcon
            )
        }

        Text(text = text)

        IconButton(onClick = onNextMonthClick) {
            Text(
                text = stringResource(id = R.string.icon_fa_icon_circle_arrow_right),
                fontFamily = FA,
                color = Green,
                fontSize = fontSizeIcon
            )
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalenderDay(
    index: Int,
    size: Dp,
    isToday: Boolean,
    date: LocalDate,
    isSelected: Boolean,
    kalenderEvents: List<KalenderEvent> = listOf(),
    kalendarSelector: KalendarSelector,
    onDayClick: (LocalDate, KalenderEvent?) -> Unit
){

    var color = if (index == 0) Color.Red else Color.Black
    color = if (isSelected) Color.White else color


    val isDot = kalendarSelector is KalendarSelector.Dot
    val event = kalenderEvents.find { it.date == date }

    var localModifier = Modifier
        .size(size)
        .clickable {
            onDayClick(date, event)
        }

    Surface(
        color = if (isSelected && !isDot) kalendarSelector.selectedColor else kalendarSelector.defaultColor,
        shape = if (!isDot) kalendarSelector.shape else KalendarShape.DefaultRectangle
    ){

        if (isToday && !isDot) {
            localModifier = localModifier.border(
                width = 2.dp,
                color = kalendarSelector.todayColor,
                shape = kalendarSelector.shape
            )
        }

        Column(
            modifier = localModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                color = color,
                text = date.dayOfMonth.toString(),
                maxLines = 1,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body1,
            )

            if (isDot) {
                KalendarDot(
                    kalendarSelector = kalendarSelector,
                    isSelected = isSelected,
                    isToday = isToday
                )
            }

        }
    }


}

fun getColor(
    isSelected: Boolean,
    isToday: Boolean,
    kalendarSelector: KalendarSelector,
): Color {
    return when {
        isToday -> kalendarSelector.todayColor
        isSelected -> kalendarSelector.selectedColor
        else -> kalendarSelector.defaultColor
    }
}

@Composable
fun KalendarDot(
    isSelected: Boolean,
    isToday: Boolean,
    kalendarSelector: KalendarSelector,
) {
    Surface(
        shape = kalendarSelector.shape,
        color = getColor(isSelected, isToday, kalendarSelector),
        modifier = Modifier
            .size(Grid.One),
        content = {}
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalenderWeekDayName(kalenderKonfig: KalenderKonfig){
    val weekdays = DateFormatSymbols(kalenderKonfig.locale).weekdays.takeLast(DAYS_IN_WEEK)

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val width = (maxWidth / DAYS_IN_WEEK)
        Row(modifier = Modifier.fillMaxWidth()) {
            weekdays.forEachIndexed() { index, weekDay: String ->
                KalenderWeekDay(
                    modifier = Modifier
                        .requiredWidth(width)
                        .wrapContentHeight(),
                    text = weekDay.subSequence(ZERO, kalenderKonfig.weekCharacters).toString(),
                    index = index
                )
            }
        }
    }


}

@Composable
internal fun KalenderWeekDay(
    modifier: Modifier = Modifier,
    text: String,
    index: Int
) {

    val color = if (index == 0) Color.Red else Color.Black

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = color, modifier = Modifier.alpha(0.5F), style = MaterialTheme.typography.body1)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalenderEmptyDay(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = " ",
        maxLines = 1,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun KalenderContent(){

}