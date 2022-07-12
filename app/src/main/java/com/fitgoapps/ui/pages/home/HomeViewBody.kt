package com.fitgoapps.ui.pages.home

import android.util.Log
import androidx.compose.animation.core.*
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.fitgoapps.R
import com.fitgoapps.model.Lapangan
import com.fitgoapps.repository.response.LapanganResponse
import com.fitgoapps.ui.pages.FitgoScreen
import com.fitgoapps.ui.pages.ShareViewModel
import com.fitgoapps.ui.theme.*
import com.fitgoapps.ui.util.FA
import com.fitgoapps.ui.util.MyAlert
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun HomeViewBody(navController: NavHostController = rememberNavController(), viewModel : HomeViewModel = viewModel(), shareViewModel: ShareViewModel){

    val context = LocalContext.current
    val items = mutableListOf<Lapangan>()

    val searchText = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit){
        delay(500)
        viewModel.fetchLapangan(shareViewModel.token ?: shareViewModel.tokenParam)
    }

    if (viewModel.result.value is LapanganResponse){

        val res = viewModel.result.value as LapanganResponse

        res.data!!.lapangan!!.forEach {
            items.add(it)
        }
    }

//    if (viewModel.indicator.value){
//        MyAlert(context).indicator()
//    }

    if (viewModel.result.value is String && viewModel.result.value != ""){

        MyAlert(context = context)
            .setMessage(viewModel.result.value as String)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                viewModel.result.value = ""
            }
            .show()
    }

    val searchResult = items.filter {
        it.address!!.lowercase(Locale.getDefault()).contains( searchText.value.lowercase(Locale.getDefault()) )
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
                                            modifier = Modifier
                                                .padding(end = 2.dp)
                                                .clickable {
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

        if (searchResult.isEmpty() || viewModel.indicator.value ){
            LazyRow(modifier = Modifier){
                repeat(5) {
                    item {
                        ShimmerAnimation()
                    }
                }
            }
        } else {
            LazyRow(modifier = Modifier){
                itemsIndexed(searchResult){ index, item ->
                    CardLapangan(item, navController)
                }
            }
        }



        Text(text = stringResource(id = R.string.nearbymerchant), modifier = Modifier.padding(
            paddingLeftRight
        ))

        if (searchResult.isEmpty() || viewModel.indicator.value ){
            LazyColumn(modifier = Modifier){
                repeat(5) {
                    item {
                        ShimmerAnimation()
                    }
                }
            }
        } else {
            LazyColumn(modifier = Modifier){
                itemsIndexed(searchResult){ index, item ->
                    CardLapangan(item, index == (items.size - 1), navController)
                }
            }
        }


    }

}


@Composable
fun CardLapangan(lapangan: Lapangan, navController: NavHostController) {
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
                painter = rememberAsyncImagePainter(model = lapangan.lapanganImages!![0].image ?: "null"),
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
                    Text(text = lapangan.name ?: "null")
                    Text(text = "1.25 km", fontSize = 12.sp)
                }

                Text(text = lapangan.address ?: "null", fontSize = 12.sp)

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
fun CardLapangan(lapangan: Lapangan, last: Boolean, navController: NavHostController) {
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
//            Image(
//                painter = rememberAsyncImagePainter(model = lapangan.lapanganImages!![0].image ?: "null"),
//                modifier = Modifier
//                    .heightIn(0.dp, 150.dp)
//                    .fillMaxWidth(),
//                contentScale = ContentScale.Crop,
//                contentDescription = "dummy1")

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(lapangan.lapanganImages!![0].image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.dummy1),
                contentDescription =lapangan.name ?: "null",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(0.dp, 150.dp)
                    .fillMaxWidth()
            )

            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = lapangan.name ?: "null")
                    Text(text = "1.25 km", fontSize = 12.sp)
                }

                Text(text = lapangan.address ?: "null", fontSize = 12.sp)

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

@Composable
fun ShimmerAnimation(
) {

    /*
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
    */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
        Specify animation positions,
        initial Values 0F means it starts from 0 position
        */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(

            /*
             Tween Animates between values over specified [durationMillis]
            */
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end= Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush)

}

@Composable
fun ShimmerItem(
    brush: Brush
) {

    /*
      Column composable shaped like a rectangle,
      set the [background]'s [brush] with the
      brush receiving from [ShimmerAnimation]
      which will get animated.
      Add few more Composable to test
    */
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .background(brush = brush)
        )
    }
}