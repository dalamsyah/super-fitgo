package com.fitgoapps.ui.pages.login

import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.*

@Composable
fun LoginViewBody(navController: NavHostController, viewModel: LoginViewModel = viewModel() ){

    Column(modifier = Modifier.padding(start = paddingStart, end = paddingEnd)) {

        Image(painter = painterResource(id = R.drawable.logo2), contentDescription = "", modifier = Modifier.padding(50.dp))

        EditTextPrimary(label = stringResource(id = R.string.email), value = viewModel.email.value, onValueChange = {
            viewModel.email.value = it
        })

        EditTextPrimary(label = stringResource(id = R.string.password), value = viewModel.password.value, onValueChange = {
            viewModel.password.value = it
        })

        Spacer(modifier = Modifier.height(10.dp))

        ButtonPrimary(text = stringResource(id = R.string.login), modifier = Modifier.fillMaxWidth(), onClick = {
//            navController.navigate(FitgoScreen.IndexView.name)

            viewModel.login()
        })

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.align(CenterHorizontally)) {

            Text(text = stringResource(R.string.dont_have_account)+" ", modifier = Modifier)

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = BlueDark)) {
                        append(stringResource(R.string.register_here))
                    }
                },
                modifier = Modifier.clickable {
                    navController.navigate(FitgoScreen.RegisterView.name)
                })

        }

    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    LoginViewBody(navController)
}