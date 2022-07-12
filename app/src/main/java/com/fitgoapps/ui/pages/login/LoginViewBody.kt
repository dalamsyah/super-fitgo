package com.fitgoapps.ui.pages.login

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.EditText
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fitgoapps.R
import com.fitgoapps.repository.response.LoginResponse
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.ShareViewModel
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA_Regular
import com.fitgoapps.ui.util.MyAlert
import com.fitgoapps.utils.SessionManager
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import retrofit2.Response

@Composable
fun LoginViewBody(navController: NavHostController, viewModel: LoginViewModel = viewModel(), shareViewModel: ShareViewModel){

    val context = LocalContext.current
    val session = SessionManager(context)

    var passwordVisibility = remember {
        mutableStateOf(false)
    }

    if (viewModel.indicator.value){
        MyAlert(context).indicator()
    }

    if (viewModel.loginObserver.value is String && viewModel.loginObserver.value != ""){

        MyAlert(context = context)
            .setMessage(viewModel.loginObserver.value as String)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                viewModel.loginObserver.value = ""
            }
            .show()
    }

    if (viewModel.loginObserver.value is Response<*>) {

        val loginRes : Response<LoginResponse> = viewModel.loginObserver.value as  Response<LoginResponse>

        if (loginRes.isSuccessful){
            if (loginRes.body()?.token ?: "" != ""){

                shareViewModel.isLogged.value = true
                shareViewModel.tokenParam = loginRes.body()!!.token
                session.saveUser(loginRes.body()!!.data)
                session.saveAuthToken(loginRes.body()!!.token)

                navController.navigate(FitgoScreen.IndexView.name){
                    popUpTo(FitgoScreen.LoginView.name) { inclusive = true }
                }
            }
        }


    }

    Column(modifier = Modifier.padding(start = paddingStart, end = paddingEnd)) {

        Image(painter = painterResource(id = R.drawable.logo2), contentDescription = "", modifier = Modifier.padding(50.dp))

        EditTextPrimary(label = stringResource(id = R.string.email), value = viewModel.email.value, onValueChange = {
            viewModel.email.value = it
        })

        EditTextPrimary(
            label = stringResource(id = R.string.password),
            value = viewModel.password.value,
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
            viewModel.password.value = it
            },
            trailingIcon = {

                val image = if (passwordVisibility.value)
                    stringResource(id = R.string.icon_fa_icon_eye)
                else stringResource(id = R.string.icon_fa_icon_eye_slash)

                IconButton(onClick = {
                    passwordVisibility.value = !passwordVisibility.value
                }) {
                    Text(
                        text = image,
                        fontFamily = FA_Regular,
                        color = Color.Gray
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        ButtonPrimary(text = stringResource(id = R.string.login), modifier = Modifier.fillMaxWidth(), onClick = {
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
