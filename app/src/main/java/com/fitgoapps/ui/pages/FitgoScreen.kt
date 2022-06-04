package com.fitgoapps.ui.pages

enum class FitgoScreen {
    IndexView,
    LapanganDetailView,

    LoginView,
    RegisterView,

    BookingDetailView,
    CalendarDetailView,
    JamDetailView,
    ;

    companion object {
        fun fromRoute(route: String?): FitgoScreen =
            when (route?.substringBefore("/")) {
                IndexView.name -> IndexView
                LapanganDetailView.name -> LapanganDetailView
                BookingDetailView.name -> BookingDetailView
                CalendarDetailView.name -> CalendarDetailView
                JamDetailView.name -> JamDetailView
                LoginView.name -> LoginView
                RegisterView.name -> RegisterView
                null -> IndexView
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}