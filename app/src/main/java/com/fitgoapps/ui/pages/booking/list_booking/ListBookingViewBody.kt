package com.fitgoapps.ui.pages.booking.list_booking

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ListBookingViewBody(navController: NavHostController, viewModel: ListBookingViewModel = viewModel() ){

    Column() {
        Text(text = "ListBookingViewBody")
    }

}