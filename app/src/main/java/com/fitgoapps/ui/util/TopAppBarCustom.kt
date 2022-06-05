package com.fitgoapps.ui.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fitgoapps.R
import com.fitgoapps.ui.theme.appBarCustomPadding
import com.fitgoapps.ui.theme.appBarDefaultHeightCustom
import com.fitgoapps.ui.theme.fontSizeIcon

@Composable
fun TopAppBarCustom(
    title: String = "",
    onPreviousClick: () -> Unit
){

    TopAppBar(modifier = Modifier.height(appBarDefaultHeightCustom)){

        Box(modifier = Modifier){

            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = appBarCustomPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {

                IconButton(onClick = onPreviousClick ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.icon_fa_icon_arrow_left),
                        fontFamily = FA,
                        fontSize = fontSizeIcon
                    )
                }
            }


            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = appBarCustomPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                Text(text = title)
            }
        }


    }

}