package com.fitgoapps.ui.pages.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.ShareViewModel
import com.fitgoapps.ui.theme.ButtonPrimary
import com.fitgoapps.ui.theme.paddingLeftRight
import com.fitgoapps.ui.util.TopAppBarCustom

@Composable
fun AccountViewBody(navController: NavHostController, viewModel: AccountViewModel = viewModel(), shareViewModel: ShareViewModel){
    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.account))

        Column(modifier = Modifier.padding(paddingLeftRight)) {
            ButtonPrimary(text = stringResource(id = R.string.logout), modifier = Modifier.fillMaxWidth(), onClick = {
                /**
                 * logout
                 */
                shareViewModel.logOut()
                navController.navigate(FitgoScreen.LoginView.name){
                    popUpTo(FitgoScreen.IndexView.name) { inclusive = true }
                }
            })
        }

    }
}