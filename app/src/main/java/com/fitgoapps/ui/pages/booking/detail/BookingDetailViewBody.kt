package com.fitgoapps.ui.pages.booking.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun BookingDetailViewBody(navController: NavHostController = rememberNavController(), viewModel: BookingDetailViewModel = viewModel() ) {

    Column() {
        Text(text = "BookingDetailViewBody")
    }

}