package com.fitgoapps.ui.util.kalender

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.theme.Grid
import com.himanshoe.kalendar.common.theme.KalendarShape


/** [KalendarStyle]sets the style for the calendar
 * @param [kalendarBackgroundColor] represents the color in background of calendar
 * @param [kalendarColor] represents the color of the view
 * @param [kalendarSelector] represents the design for selector
 * @param [hasRadius] gives the radius check for monthView
 * @param [elevation] provides info for Elevation for Firey
 * @param [shape] provides shape for views
 */

data class KalenderStyle(
    val kalendarBackgroundColor: Color? = null,
    val kalendarColor: Color? = null,
    val kalendarSelector: KalendarSelector = KalendarSelector.DiamondShape(),
    val hasRadius: Boolean = true,
    val elevation: Dp = if (hasRadius) Grid.One else Grid.Zero,
    val shape: Shape = KalendarShape.ButtomCurvedShape,
)