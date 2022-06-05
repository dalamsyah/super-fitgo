package com.fitgoapps.ui.util.kalender

import java.time.LocalDate


/**
 * [KalendarEvent] handles the event marked on any
 * @param[date] with specific
 * @param[eventName] and its
 * @param[eventDescription]
 */
data class KalenderEvent(
    val date: LocalDate,
    val eventName: String,
    val eventDescription: String? = null,
)