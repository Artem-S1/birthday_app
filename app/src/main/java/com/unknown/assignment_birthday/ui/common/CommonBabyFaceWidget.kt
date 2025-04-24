package com.unknown.assignment_birthday.ui.common

import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.unknown.assignment_birthday.R
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CommonBabyFaceWidget(
    faceColor: Color,
    circleColor: Color,
    borderColor: Color,
    photoUri: Uri?,
    isHideCameraButton: Boolean = false,
    onTap: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.run {
            clickable(
                onClick = onTap,
                interactionSource = interactionSource,
                indication = null
            )
        }
    ) {
        Canvas(modifier = Modifier.size(dimensionResource(id = R.dimen.common_baby_face_circle_size))) {
            drawCircle(color = circleColor)
        }

        Canvas(modifier = Modifier.size(dimensionResource(id = R.dimen.common_baby_face_circle_size))) {
            drawCircle(
                color = borderColor,
                radius = size.minDimension / 2,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 14f)
            )
        }

        if (photoUri != null) {
            Image(
                painter = rememberAsyncImagePainter(photoUri),
                contentDescription = stringResource(R.string.content_description_picked_image_desc),
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.common_baby_face_image_size))
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_baby_face),
                contentDescription = stringResource(R.string.content_description_svg_face),
                colorFilter = ColorFilter.tint(faceColor),
                modifier = Modifier.size(dimensionResource(id = R.dimen.common_baby_face_size))
            )
        }

        if(!isHideCameraButton)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.common_baby_face_photo_size))
                .offset(
                    x = (100 * cos(Math.toRadians(-45.0))).toFloat().dp,
                    y = (100 * sin(Math.toRadians(-45.0))).toFloat().dp
                )
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(color = faceColor)
            }

            Image(
                painter = painterResource(id = R.drawable.ic_photo),
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.common_baby_face_photo_icon_padding_start),
                        top = dimensionResource(id = R.dimen.common_baby_face_photo_icon_padding_end)
                    ),
                contentDescription = stringResource(R.string.content_description_camera_icon),
            )
        }
    }
}


