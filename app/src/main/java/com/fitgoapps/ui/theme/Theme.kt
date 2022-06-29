package com.fitgoapps.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitgoapps.R
import com.fitgoapps.ui.util.FA


val roundedCorner = 8.dp
val paddingTextFieldLayout = 10.dp

val paddingStart = 10.dp
val paddingEnd = 10.dp
val paddingLeftRight = 15.dp
val fontSizeIcon = 20.sp

val bottomNavigationHeight = 56.dp
val appBarDefaultHeight = 56.dp
val appBarDefaultHeightCustom = 90.dp
val appBarCustomPadding = 35.dp
val navigationBottomPaddingCustom = 60.dp

private val DarkColorPalette = darkColors(
    primary = Green,
    primaryVariant = GreenTopBar,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = GreenTopBar,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SuperFitgoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun ButtonPrimary (
    darkTheme: Boolean = isSystemInDarkTheme(),
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    enabled : Boolean = true,
    backgroundColor: Color = Green
){

    Button(
        shape = RoundedCornerShape(roundedCorner),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = modifier.height(50.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(text = text, color = Color.White, fontWeight = FontWeight.Bold )
    }
}

@Composable
fun EditTextPrimary(
    icon: ImageVector? = null,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        visualTransformation = visualTransformation,
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(roundedCorner),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            disabledLabelColor = Color.LightGray,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.LightGray,
            unfocusedLabelColor = Color.LightGray,
            textColor = Color.Black,
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = keyboardType),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label, modifier = Modifier)
        },
//        placeholder = {
//            Text(
//                text = label,
//                style = TextStyle(
//                    color = MaterialTheme.colors.primaryVariant,
//                    textAlign = TextAlign.Center
//                )
//            )
//        },
        trailingIcon = trailingIcon
    )

}
