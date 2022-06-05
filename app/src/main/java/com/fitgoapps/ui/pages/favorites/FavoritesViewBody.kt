package com.fitgoapps.ui.pages.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun FavortiesViewBody(navController: NavHostController, viewModel: FavoritesViewModel = viewModel()){
    Column() {
        Text(text = "FavortiesViewBody")
    }
}