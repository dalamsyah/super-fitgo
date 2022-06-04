package com.fitgoapps.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.theme.Green
import com.fitgoapps.ui.theme.paddingLeftRight
import com.fitgoapps.ui.theme.roundedCorner

@Composable
fun HomeViewBody(navController: NavHostController = rememberNavController(), viewModel : HomeViewModel = viewModel()){

    val items = listOf(
        "Walang Futsal",
        "Sisilia Futsal",
        "Oso Futsal",
        "Java Futsal",
        "Puri Futsal",
        "Red Soccer",
        "Ganda Agung Futsal",
    )

    Column() {
        Text(text = stringResource(id = R.string.toppick), modifier = Modifier.padding(
            paddingLeftRight
        ))

        LazyRow(modifier = Modifier){
            itemsIndexed(items){ index, item ->
                CardLapangan("$item", navController)
            }
        }

        Text(text = stringResource(id = R.string.nearbymerchant), modifier = Modifier.padding(
            paddingLeftRight
        ))

        LazyColumn(modifier = Modifier){
            itemsIndexed(items){ index, item ->
                CardLapangan("$item", index == (items.size - 1), navController)
            }
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

                    Text(text = "Available: Vinyl, Sintetis", color = Color.Gray, fontSize = 11.sp)

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

                    Text(text = "Available: Vinyl, Sintetis", color = Color.Gray, fontSize = 11.sp)

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
        Spacer(modifier = Modifier.height(110.dp))
    } else {
        Spacer(modifier = Modifier.height(0.dp))
    }
}