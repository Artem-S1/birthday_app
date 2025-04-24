package com.unknown.assignment_birthday.ui.birthday

import androidx.compose.ui.graphics.Color
import com.unknown.assignment_birthday.R

enum class BirthdayScreenType(
    val backgroundColor: Color,
    val faceColor: Color,
    val faceContainerColor: Color,
    val backgroundImage: Int
) {
    TYPE1(Color(0xFFB9E5EF), (Color(0xFF8BD3E4)), (Color(0xFFDAF1F6)), R.drawable.bg_first_type_screen),
    TYPE2(Color(0xFFFEE7B7), (Color(0xFFFEBE21)), (Color(0xFFFEEFCB)), R.drawable.bg_second_type_screen),
    TYPE3(Color(0xFFA9DCCF), (Color(0xFF6FC5AF)), (Color(0xFFC5E8DF)), R.drawable.bg_third_type_screen),
}