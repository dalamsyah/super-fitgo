package com.fitgoapps.ui.pages

enum class FitgoScreen {
    IndexView,
    LapanganDetailView,

    LoginView,
    RegisterView,

    BookingDetailView,
    CalendarDetailView,
    JamDetailView,
    SearchView,
    NotificationView,
    AccountView,
    FavoritesView,
    SportView,
    ;

    companion object {
        fun fromRoute(route: String?): FitgoScreen =
            when (route?.substringBefore("/")) {
                IndexView.name -> IndexView
                LapanganDetailView.name -> LapanganDetailView
                BookingDetailView.name -> BookingDetailView
                CalendarDetailView.name -> CalendarDetailView
                JamDetailView.name -> JamDetailView
                SearchView.name -> SearchView
                NotificationView.name -> NotificationView
                AccountView.name -> AccountView
                FavoritesView.name -> FavoritesView
                SportView.name -> SportView
                LoginView.name -> LoginView
                RegisterView.name -> RegisterView
                null -> IndexView
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}