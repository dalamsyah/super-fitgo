package com.fitgoapps.ui.pages.index

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.login.LoginViewModel
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA


@Composable
fun IndexViewBody(navController: NavHostController, viewModel: LoginViewModel = viewModel()){

    val items = listOf(
        "Walang Futsal",
        "Sisilia Futsal",
        "Oso Futsal",
        "Java Futsal",
        "Puri Futsal",
        "Red Soccer",
        "Ganda Agung Futsal",
    )

    Box(modifier = Modifier.fillMaxWidth()) {

        Column(modifier = Modifier) {

            TopAppBar(){

                Row() {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, start = 15.dp)
                            .weight(1f),
                        value = viewModel.email.value,
                        onValueChange = {
                            viewModel.email.value = it
                        },
                        decorationBox = { innerTextField ->
                            Row(modifier = Modifier
                                .background(color = White, shape = RoundedCornerShape(8.dp))
                                .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {

                                Text(
                                    modifier = Modifier.padding(start = 2.dp),
                                    text = stringResource(id = R.string.icon_fa_icon_magnifying_glass),
                                    fontFamily = FA,
                                    color = Gray
                                )

                                Box(modifier = Modifier.padding(start = 2.dp),) {
                                    Column() {
                                        if (viewModel.email.value.isEmpty()) {
                                            Text(
                                                stringResource(id = R.string.cari_lapangan),
                                                color = Color.Gray, fontSize = 11.sp,
                                                modifier = Modifier.padding(start = 2.dp, top = 2.dp),
                                            )
                                        }
                                    }
                                    innerTextField()
                                }

                            }
                        }
                    )


                    IconButton(onClick = {  }) {
                        Text(
                            text = stringResource(id = R.string.icon_fa_icon_bell),
                            fontFamily = FA,
                            color = Color.White
                        )
                    }

                    IconButton(modifier = Modifier.padding(end = paddingLeftRight), onClick = {  }) {
                        Icon(
                            Icons.Filled.Person,
                            "contentDescription",
                            tint = Color.White)
                    }
                }

            }

            Text(text = stringResource(id = R.string.toppick), modifier = Modifier.padding(
                paddingLeftRight))

            LazyRow(modifier = Modifier){
                itemsIndexed(items){ index, item ->
                    CardLapangan("$item", navController)
                }
            }

            Text(text = stringResource(id = R.string.nearbymerchant), modifier = Modifier.padding(
                paddingLeftRight))
            
            LazyColumn(modifier = Modifier){
                itemsIndexed(items){ index, item ->
                    CardLapangan("$item", index == (items.size - 1), navController)
                }
            }

        }

        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(paddingLeftRight)
                .shadow(10.dp, RoundedCornerShape(20.dp))
        ) {

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_home),
                    fontFamily = FA,
                    color = Green
                ) },
                label = { Text(text = stringResource(id = R.string.home)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = true,
                onClick = {  }
            )

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_heart),
                    fontFamily = FA,
                    color = Gray
                ) },
                label = { Text(text = stringResource(id = R.string.favorites)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {  }
            )

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_book),
                    fontFamily = FA,
                    color = Gray
                ) },
                label = { Text(text = stringResource(id = R.string.booking)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {  }
            )

            BottomNavigationItem(
                icon = { Text(
                    text = stringResource(id = R.string.icon_fa_icon_futbol),
                    fontFamily = FA,
                    color = Gray
                ) },
                label = { Text(text = stringResource(id = R.string.sport)) },
                selectedContentColor = Green,
                unselectedContentColor = Green.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {  }
            )

        }

    }



}

@Composable
fun CardLapangan(name: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(paddingLeftRight)
            .clickable {
                navController.navigate(FitgoScreen.LapanganDetailView.name)
            }
            .shadow(10.dp, RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large
    ) {

        Column(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy1),
                modifier = Modifier
                    .heightIn(0.dp, 150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = "dummy1")

            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = name)
                    Text(text = "1.25 km", fontSize = 12.sp)
                }

                Text(text = "Jln Raya Walang no. 12", fontSize = 12.sp)

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom) {

                    Text(text = "Available: Vinyl, Sintetis", color = Gray, fontSize = 11.sp)

                    Button(
                        shape = RoundedCornerShape(roundedCorner),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                        onClick = {

                        }
                    ) {
                        Text(text = stringResource(id = R.string.booknow), color = Color.White, fontWeight = FontWeight.Bold )
                    }

                }
            }
        }

    }

}

@Composable
fun CardLapangan(name: String, last: Boolean, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingLeftRight)
            .clickable {
                navController.navigate(FitgoScreen.LapanganDetailView.name)
            }
            .shadow(10.dp, RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.large
    ) {

        Column(
            modifier = Modifier
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummy1),
                modifier = Modifier
                    .heightIn(0.dp, 150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                contentDescription = "dummy1")

            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = name)
                    Text(text = "1.25 km", fontSize = 12.sp)
                }

                Text(text = "Jln Raya Walang no. 12", fontSize = 12.sp)

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom) {

                    Text(text = "Available: Vinyl, Sintetis", color = Gray, fontSize = 11.sp)

                    Button(
                        shape = RoundedCornerShape(roundedCorner),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                        onClick = {

                        }
                    ) {
                        Text(text = stringResource(id = R.string.booknow), color = Color.White, fontWeight = FontWeight.Bold )
                    }

                }
            }
        }

    }

    if (last){
        Spacer(modifier = Modifier.height(70.dp))
    } else {
        Spacer(modifier = Modifier.height(0.dp))
    }
}

@Composable
fun DefaultPreview() {
    SuperFitgoTheme {

    }
}