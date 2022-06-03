package com.fitgoapps.ui.pages.register

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.*

@Composable
fun RegisterViewBody(navController: NavHostController, viewModel: RegisterViewModel = viewModel() ){

    Column() {

        TopAppBar(
            backgroundColor = GreenTopBar,
            title = {
                Text(text = "Register")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(modifier = Modifier.padding(start = paddingStart, end = paddingEnd)) {

            EditTextPrimary(label = stringResource(id = R.string.email), value = viewModel.email.value, onValueChange = {
                viewModel.email.value = it
            })

            EditTextPrimary(label = stringResource(id = R.string.nama), value = viewModel.nama.value, onValueChange = {
                viewModel.nama.value = it
            })

            EditTextPrimary(label = stringResource(id = R.string.nama_tim), value = viewModel.nama_tim.value, onValueChange = {
                viewModel.nama_tim.value = it
            })

            EditTextPrimary(label = stringResource(id = R.string.no_hp), value = viewModel.no_hp.value, onValueChange = {
                viewModel.no_hp.value = it
            })

            EditTextPrimary(label = stringResource(id = R.string.password), value = viewModel.password.value, onValueChange = {
                viewModel.password.value = it
            })

            EditTextPrimary(label = stringResource(id = R.string.konfirmasi_password), value = viewModel.conf_password.value, onValueChange = {
                viewModel.conf_password.value = it
            })

            Spacer(modifier = Modifier.height(10.dp))

            ButtonPrimary(text = stringResource(id = R.string.register), modifier = Modifier.fillMaxWidth(), onClick = {
                navController.navigate(FitgoScreen.LoginView.name)
            })

        }
    }



}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    RegisterViewBody(navController)
}