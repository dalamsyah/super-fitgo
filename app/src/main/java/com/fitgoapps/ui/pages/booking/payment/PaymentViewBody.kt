package com.fitgoapps.ui.pages.booking.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.ButtonPrimary
import com.fitgoapps.ui.theme.RedPayment
import com.fitgoapps.ui.theme.navigationBottomPaddingCustom
import com.fitgoapps.ui.theme.paddingLeftRight
import com.fitgoapps.ui.util.TopAppBarCustom

@Composable
fun PaymentViewBody( navController: NavHostController, viewModel: PaymentViewModel = viewModel() ){

    Column() {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.choose_schedule))


        Column() {

            Row(modifier = Modifier.background(RedPayment), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(id = R.string.please_complete_booking_before))
                Text(text = "05:00")
            }
            
            Row(modifier = Modifier.padding(paddingLeftRight)) {
                Text(text = stringResource(id = R.string.information))
            }

            Row() {
                Image(painter = painterResource(id = R.drawable.dummy1), contentDescription = "dummy1", contentScale = ContentScale.Crop,
                    modifier = Modifier.height(50.dp).width(100.dp)
                )
            }

            Row(modifier = Modifier.padding(paddingLeftRight)) {
                Text(text = stringResource(id = R.string.choose_payment))
            }

        }

        ButtonPrimary(text = stringResource(id = R.string.submit),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = paddingLeftRight,
                    end = paddingLeftRight,
                    bottom = navigationBottomPaddingCustom
                ), onClick = {
                navController.navigate(FitgoScreen.PaymentView.name)
            })


    }

}