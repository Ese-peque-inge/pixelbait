package com.pixelbait.app.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val PixelBaitTypography = Typography(
    headlineSmall = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp, color = PbTextPrimary),
    titleLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, color = PbTextPrimary),
    titleMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = PbTextPrimary),
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 15.sp, color = PbTextSecondary),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 13.sp, color = PbTextSecondary),
    labelLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 13.sp, color = PbTextPrimary),
    labelSmall = TextStyle(fontWeight = FontWeight.Bold, fontSize = 11.sp, color = PbTextMuted)
)
