package com.fitgoapps.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fitgoapps.R
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.ShareViewModel
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import java.util.*

@Composable
fun HomeViewBody(navController: NavHostController = rememberNavController(), viewModel : HomeViewModel = viewModel(), shareViewModel: ShareViewModel){


    val items = listOf(
        "Walang Futsal",
        "Sisilia Futsal",
        "Oso Futsal",
        "Java Futsal",
        "Puri Futsal",
        "Red Soccer",
        "Ganda Agung Futsal",
    )

    val searchText = remember {
        mutableStateOf("")
    }

    val searchResult = items.filter {
        it.lowercase(Locale.getDefault()).contains( searchText.value.lowercase(Locale.getDefault()) )
    }

    Column() {

        TopAppBar(modifier = Modifier.height(appBarDefaultHeightCustom)){

            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = appBarCustomPadding)) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 15.dp)
                        .weight(1f),
                    value = searchText.value,
                    onValueChange = {
                        searchText.value = it
                    },
                    decorationBox = { innerTextField ->
                        Row(modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(5.dp), verticalAlignment = Alignment.CenterVertically) {

                            Text(
                                modifier = Modifier.padding(start = 2.dp),
                                text = stringResource(id = R.string.icon_fa_icon_magnifying_glass),
                                fontFamily = FA,
                                color = Color.Gray
                            )

                            Box(modifier = Modifier.padding(start = 2.dp),) {
                                Row() {
                                    if (searchText.value.isEmpty()) {
                                        Text(
                                            stringResource(id = R.string.cari_lapangan),
                                            color = Color.Gray, fontSize = 11.sp,
                                            modifier = Modifier.padding(start = 2.dp, top = 2.dp),
                                        )
                                    }
                                }

                                Row(horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {

                                    innerTextField()

                                    if (searchText.value.isNotEmpty()) {
                                        Text(
                                            modifier = Modifier.padding(end = 2.dp).clickable {
                                                  searchText.value = ""
                                            },
                                            text = stringResource(id = R.string.icon_fa_icon_xmark),
                                            fontFamily = FA,
                                            color = Color.Gray
                                        )
                                    }
                                }

                            }

                        }
                    }
                )


                IconButton(onClick = {
                    navController.navigate(FitgoScreen.NotificationView.name)
                }) {
                    Text(
                        text = stringResource(id = R.string.icon_fa_icon_bell),
                        fontFamily = FA,
                        color = Color.White
                    )
                }

                IconButton(modifier = Modifier.padding(end = paddingLeftRight), onClick = {
                    navController.navigate(FitgoScreen.AccountView.name)
                }) {
                    Icon(
                        Icons.Filled.Person,
                        "contentDescription",
                        tint = Color.White)
                }
            }

        }

        Text(text = stringResource(id = R.string.toppick), modifier = Modifier.padding(
            paddingLeftRight
        ))

        if (searchResult.isEmpty()){
            Box(modifier = Modifier.heightIn(0.dp, 150.dp).fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = stringResource(id = R.string.not_found), modifier = Modifier, textAlign = TextAlign.Center)
            }
        }

        LazyRow(modifier = Modifier){
            itemsIndexed(searchResult){ index, item ->
                CardLapangan("$item", navController)
            }
        }

        Text(text = stringResource(id = R.string.nearbymerchant), modifier = Modifier.padding(
            paddingLeftRight
        ))

        if (searchResult.isEmpty()){
            Box(modifier = Modifier.heightIn(0.dp, 150.dp).fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = stringResource(id = R.string.not_found), modifier = Modifier, textAlign = TextAlign.Center)
            }
        }

        LazyColumn(modifier = Modifier){
            itemsIndexed(searchResult){ index, item ->
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