package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LiveGreen
import com.example.ui.theme.LiveGreenGlow

@Composable
fun LiveDot(
    modifier: Modifier = Modifier,
    dotSize: Dp = 8.dp,
    dotColor: Color = LiveGreen,
    glowColor: Color = LiveGreenGlow
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse_transition")
    
    val haloScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "halo_scale"
    )

    val haloAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "halo_alpha"
    )

    Box(
        modifier = modifier.size(dotSize * 2.5f),
        contentAlignment = Alignment.Center
    ) {
        // Outer pulsing wave
        Box(
            modifier = Modifier
                .size(dotSize)
                .scale(haloScale)
                .background(
                    color = glowColor.copy(alpha = haloAlpha),
                    shape = CircleShape
                )
        )

        // Center solid dot
        Box(
            modifier = Modifier
                .size(dotSize)
                .background(
                    color = dotColor,
                    shape = CircleShape
                )
        )
    }
}
