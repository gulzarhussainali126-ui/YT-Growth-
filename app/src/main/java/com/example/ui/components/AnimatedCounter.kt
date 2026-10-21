package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AnimatedCounter(
    targetValue: Int,
    modifier: Modifier = Modifier,
    prefix: String = "",
    suffix: String = "",
    textStyle: TextStyle? = null,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = TextPrimary,
    durationMillis: Int = 1800
) {
    var countTo by remember { mutableIntStateOf(0) }

    LaunchedEffect(targetValue) {
        countTo = targetValue
    }

    val animatedValue by animateIntAsState(
        targetValue = countTo,
        animationSpec = tween(durationMillis = durationMillis, easing = FastOutSlowInEasing),
        label = "counter_anim"
    )

    val formattedNumber = NumberFormat.getNumberInstance(Locale.US).format(animatedValue)

    Text(
        text = "$prefix$formattedNumber$suffix",
        modifier = modifier,
        style = textStyle ?: TextStyle(
            fontFamily = SoraFontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color
        )
    )
}
