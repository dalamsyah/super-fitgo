package com.fitgoapps.ui.util.kalender

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.theme.KalendarShape
import java.time.LocalDate

@Preview(showBackground = true)
@Composable
fun KalenderHeader(
    text: String,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
){

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = onPreviousMonthClick) {
            Text(text = "<")
        }
        Text(text = text)
        Button(onClick = onNextMonthClick) {
            Text(text = ">")
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun KalenderDay(
    size: Dp,
    date: LocalDate,
    isSelected: Boolean,
    kalendarSelector: KalendarSelector
){

    val isDot = kalendarSelector is KalendarSelector.Dot

    var localModifier = Modifier
        .size(size)
        .clickable { }

    Surface(
        color = if (isSelected && !isDot) kalendarSelector.selectedColor else kalendarSelector.defaultColor,
        shape = if (!isDot) kalendarSelector.shape else KalendarShape.DefaultRectangle
    ){
        Column(
            modifier = localModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.dayOfMonth.toString(),
                maxLines = 1,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body1,
            )

        }
    }


}

@Preview(showBackground = true)
@Composable
fun KalenderContent(){

}