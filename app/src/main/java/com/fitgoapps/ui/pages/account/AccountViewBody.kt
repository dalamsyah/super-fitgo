package com.fitgoapps.ui.pages.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.theme.paddingLeftRight
import com.fitgoapps.ui.util.TopAppBarCustom

@Composable
fun AccountViewBody(navController: NavHostController, viewModel: AccountViewModel = viewModel()){
    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.account))

        Column(modifier = Modifier.padding(paddingLeftRight)) {
            Text(text = "AccountViewBody")
        }

    }
}