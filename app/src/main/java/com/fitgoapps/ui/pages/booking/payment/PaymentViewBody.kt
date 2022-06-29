package com.fitgoapps.ui.pages.booking.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.model.PaymentType
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.FA_Regular
import com.fitgoapps.ui.util.TopAppBarCustom

@Composable
fun PaymentViewBody( navController: NavHostController, viewModel: PaymentViewModel = viewModel() ){

    val paymentTypes = mutableListOf(
        PaymentType(1, "CASH", "Bayar di tempat"),
        PaymentType(2, "OVO", "Pembayaran via ovo akan terkonfirmasi otomatis"),
        PaymentType(3, "GOPAY", "Pembayaran via ovo akan terkonfirmasi otomatis")
    )

    Column(modifier = Modifier.background(BackgroundColorLayout)) {

        TopAppBarCustom(onPreviousClick = {
            navController.popBackStack()
        }, title = stringResource(id = R.string.payment))


        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(weight = 1f, fill = false)) {

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier
                .background(RedPayment)
                .fillMaxWidth()
                .padding(paddingLeftRight), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(id = R.string.please_complete_booking_before), color = Color.White)
                Text(text = "05:00", color = Color.White)
            }
            
            Row(modifier = Modifier.padding(paddingLeftRight)) {
                Text(text = stringResource(id = R.string.information))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)){

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingLeftRight)) {
                    Image(painter = painterResource(id = R.drawable.dummy1), contentDescription = "dummy1", contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(65.dp)
                            .width(130.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Column() {
                        Text(text = "Walang Futsal", fontWeight = FontWeight.Bold)

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.icon_fa_icon_calendar),
                                fontFamily = FA_Regular,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "Wed. 20 April 2022")
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(id = R.string.icon_fa_icon_alarm_clock),
                                fontFamily = FA_Regular,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "16:00 - 18:00 (2 jam)")
                        }
                    }
                }
            }

            Row(modifier = Modifier.padding(paddingLeftRight)) {
                Text(text = stringResource(id = R.string.choose_payment))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)){

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingLeftRight)) {

                    repeat(paymentTypes.size) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = paymentTypes[it].payment_name)
                                Text(text = paymentTypes[it].desc, )
                            }
                            RadioButton(selected = false, onClick = {})
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            }

            Row(modifier = Modifier.padding(paddingLeftRight)) {
                Text(text = stringResource(id = R.string.voucher_and_promo))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)){

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingLeftRight), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                    Text(text = stringResource(id = R.string.masukan_kode_voucher))
                    ButtonPrimary(text = stringResource(id = R.string.claim), backgroundColor = OrenClaim,
                        modifier = Modifier, onClick = {

                        })

                }

            }

            Row(modifier = Modifier.padding(paddingLeftRight)) {
                Text(text = stringResource(id = R.string.details))
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)){

                Column(modifier = Modifier
                    .fillMaxWidth().padding(paddingLeftRight)) {

                    Row(modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Sewa Lapangan")
                        Text(text = "200.000")
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Promo")
                        Text(text = "-10.000")
                    }

                    Row(modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Total", fontWeight = FontWeight.Bold)
                        Text(text = "190.000", fontWeight = FontWeight.Bold)
                    }
                }

            }

        }

        Spacer(modifier = Modifier.height(paddingLeftRight))

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