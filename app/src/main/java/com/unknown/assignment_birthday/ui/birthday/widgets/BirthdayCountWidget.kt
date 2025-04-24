package com.unknown.assignment_birthday.ui.birthday.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.unknown.assignment_birthday.R

@Composable
fun BirthdayCountWidget(
    date: Pair<Int, Int?>,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_left_swirls),
                contentDescription = null,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.birthday_count_left_swirls_padding))
            )

            Image(
                painter = painterResource(id = date.first),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.birthday_count_image_padding))
            )

            date.second?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.birthday_count_image_padding))
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_right_swirls),
                contentDescription = null,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.birthday_count_right_swirls_padding))
            )
        }

        Text(
            text = text,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.birthday_count_description_padding_top))
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 18.sp
            )
        )
    }
}


