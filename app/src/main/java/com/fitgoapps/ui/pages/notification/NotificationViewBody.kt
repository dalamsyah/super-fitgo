package com.fitgoapps.ui.pages.notification

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
fun NotificationViewBody(navController: NavHostController, viewModel: NotificationViewModel = viewModel()){
    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.notification))


        Column(modifier = Modifier.padding(paddingLeftRight)) {
            Text(text = "NotificationViewBody")
        }

    }
}