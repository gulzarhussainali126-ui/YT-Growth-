package com.example.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.LiveDot
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonButtonStyle
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.LiveGreen
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun FinalCtaSection(
    onLoginWithYouTube: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1F0608),
                        Color(0xFF0C0203),
                        Color(0xFF050102)
                    )
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(CrimsonPrimary.copy(alpha = 0.6f), Color.Transparent)
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .drawBehind {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(CrimsonGlow.copy(alpha = 0.35f), Color.Transparent),
                        center = center,
                        radius = size.maxDimension * 0.75f
                    )
                )
            }
            .padding(horizontal = 24.dp, vertical = 36.dp)
            .testTag("final_cta_section"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color(0xFF2C0A0C))
                    .padding(horizontal = 12.dp, vertical = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    LiveDot(dotSize = 5.dp, dotColor = CrimsonLight, glowColor = CrimsonGlow)
                    Text(
                        text = "GROWTH LAUNCHPAD",
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        letterSpacing = 1.sp,
                        color = CrimsonLight
                    )
                }
            }

            Text(
                text = "Your Next Viral Video Starts Here.",
                fontFamily = SoraFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                lineHeight = 32.sp,
                textAlign = TextAlign.Center,
                color = TextPrimary
            )

            Text(
                text = "Connect your channel and let YT Growth AI find your fastest growth path in under 3 minutes.",
                fontFamily = InterFontFamily,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                color = TextSecondary
            )

            NeonButton(
                text = "Login with YouTube — Get Free Analysis",
                style = NeonButtonStyle.PRIMARY,
                onClick = onLoginWithYouTube,
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = CrimsonPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("final_cta_button")
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(LiveGreen, CircleShape)
                )
                Text(
                    text = "50 Free Credits • No Credit Card Required • Instant Setup",
                    fontFamily = InterFontFamily,
                    fontSize = 11.sp,
                    color = TextTertiary
                )
            }
        }
    }
}
