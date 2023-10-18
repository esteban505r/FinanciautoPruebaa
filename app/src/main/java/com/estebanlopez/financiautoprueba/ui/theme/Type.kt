package com.estebanlopez.financiautoprueba.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.estebanlopez.financiautoprueba.R

// Set of Material typography styles to start with
fun createTypography():Typography{

        val provider = GoogleFont.Provider(
                providerAuthority = "com.google.android.gms.fonts",
                providerPackage = "com.google.android.gms",
                certificates = R.array.com_google_android_gms_fonts_certs
        )

        val montserratFontName = GoogleFont("Montserrat")
        val arialBlackFontName = GoogleFont("Archivo Black")
        val arimoFontName = GoogleFont("Arimo")

        val montserratFontFamily = FontFamily(
                Font(googleFont = montserratFontName, fontProvider = provider)
        )

        val arialBlackFontFamily = FontFamily(
                Font(googleFont = arialBlackFontName, fontProvider = provider)
        )

        val arimoFontFamily = FontFamily(
                Font(googleFont = arimoFontName, fontProvider = provider)
        )

        return Typography(
                bodyLarge = TextStyle(
                        fontFamily = arimoFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                ),
                bodyMedium = TextStyle(
                        fontFamily = arimoFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                ),
                titleLarge = TextStyle(
                        fontFamily = arialBlackFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 30.sp,
                        lineHeight = 36.sp,
                        letterSpacing = 0.5.sp
                ),
                labelMedium = TextStyle(
                        fontFamily = montserratFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                ),
                /* Other default text styles to override
            titleLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
            ),
            labelSmall = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.5.sp
            )
            */
        )
}