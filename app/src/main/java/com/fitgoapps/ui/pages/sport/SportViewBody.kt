package com.fitgoapps.ui.pages.sport

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun SportViewBody(navController: NavHostController, viewModel: SportViewModel = viewModel()){
    Column() {
        Text(text = "SportViewBody")
    }
}