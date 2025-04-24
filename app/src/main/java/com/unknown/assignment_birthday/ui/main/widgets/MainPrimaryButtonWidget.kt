package com.unknown.assignment_birthday.ui.main.widgets

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.unknown.assignment_birthday.R

@Composable
fun MainPrimaryButtonWidget(
    text: String,
    modifier: Modifier = Modifier,
    containerColor: Color? = null,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor ?: colorResource(id = R.color.colorSkyBlue),
            contentColor = colorResource(id = R.color.black)
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.main_primary_button_radius))
    ) {
        Text(text)
    }
}