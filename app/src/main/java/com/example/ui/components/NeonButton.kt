package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CrimsonDark
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

enum class NeonButtonStyle {
    PRIMARY,
    GHOST,
    OUTLINE
}

@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: NeonButtonStyle = NeonButtonStyle.PRIMARY,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: Shape = RoundedCornerShape(100.dp), // pill shape
    contentPadding: PaddingValues = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
    testTag: String = "neon_button"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = tween(durationMillis = 120),
        label = "btn_scale"
    )

    val backgroundModifier = when (style) {
        NeonButtonStyle.PRIMARY -> {
            Modifier
                .drawBehind {
                    // Neon red glow shadow
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(CrimsonGlow, Color.Transparent),
                            center = center,
                            radius = size.maxDimension * 0.8f
                        )
                    )
                }
                .clip(shape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(CrimsonPrimary, CrimsonLight)
                    )
                )
        }
        NeonButtonStyle.GHOST -> {
            Modifier
                .clip(shape)
                .background(Color(0xFF141414))
                .border(
                    width = 1.dp,
                    color = BorderGlass,
                    shape = shape
                )
        }
        NeonButtonStyle.OUTLINE -> {
            Modifier
                .clip(shape)
                .background(Color.Transparent)
                .border(
                    width = 1.dp,
                    color = BorderGlass,
                    shape = shape
                )
        }
    }

    Box(
        modifier = modifier
            .testTag(testTag)
            .scale(scale)
            .defaultMinSize(minHeight = 48.dp)
            .then(backgroundModifier)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            leadingIcon?.invoke()

            Text(
                text = text,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = if (style == NeonButtonStyle.PRIMARY) TextPrimary else TextSecondary
            )

            trailingIcon?.invoke()
        }
    }
}
