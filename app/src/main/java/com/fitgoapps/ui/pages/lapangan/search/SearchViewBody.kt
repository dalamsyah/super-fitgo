package com.fitgoapps.ui.pages.lapangan.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.util.TopAppBarCustom

@Composable
fun SearchViewBody(navController: NavHostController, viewModel: SearchViewModel = viewModel()){
    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.choose_date))

        Text(text = "SearchViewBody")
    }
}