package com.fitgoapps.ui.util

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

class MyAlert(context: Context?) : AlertDialog.Builder(context) {

    init {

    }

    @Composable
    fun indicator() {
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
            ){
                Box(modifier = Modifier
                    .background(Color.White)
                    .padding(20.dp)) {

                    Row(modifier = Modifier
                        .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,) {

                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = " Please wait...")

                    }

                }
            }
        }
    }

    override fun setMessage(message: CharSequence?): AlertDialog.Builder {
        return super.setMessage(message)
    }

    override fun show(): AlertDialog? {
        return super.show()
    }

}