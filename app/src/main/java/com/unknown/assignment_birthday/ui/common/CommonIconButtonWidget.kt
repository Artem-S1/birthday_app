package com.unknown.assignment_birthday.ui.common

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.unknown.assignment_birthday.R

@Composable
fun CommonIconButtonWidget(
    icon: ImageVector,
    modifier: Modifier,
    onTap: () -> Unit
) {
    IconButton(
        onClick = onTap,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.content_description_icon)
        )
    }
}

