package com.example.ui.landing

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AnimatedCounter
import com.example.ui.components.GlassCard
import com.example.ui.components.LiveDot
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonButtonStyle
import com.example.ui.theme.BackgroundCard
import com.example.ui.theme.BackgroundElevated
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.LiveGreen
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroSection(
    onLoginWithYouTube: () -> Unit,
    onExploreFeatures: () -> Unit,
    onApplyIdea: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTimeframe by remember { mutableIntStateOf(2) } // 1M default
    var ideaApplied by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Pill badge
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xFF16090A))
                .border(1.dp, CrimsonPrimary.copy(alpha = 0.4f), RoundedCornerShape(100.dp))
                .padding(horizontal = 16.dp, vertical = 7.dp)
                .testTag("hero_pill_badge")
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LiveDot(
                    dotSize = 6.dp,
                    dotColor = CrimsonPrimary,
                    glowColor = CrimsonGlow
                )
                Text(
                    text = "AI-POWERED YOUTUBE GROWTH OS",
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp,
                    color = CrimsonLight
                )
            }
        }

        // H1 Heading
        Text(
            text = buildAnnotatedString {
                append("Grow Your YouTube\nChannel ")
                withStyle(
                    style = SpanStyle(
                        color = CrimsonPrimary,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("10x Faster")
                }
                append(" with AI")
            },
            fontFamily = SoraFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 38.sp,
            letterSpacing = (-0.8).sp,
            textAlign = TextAlign.Center,
            color = TextPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("hero_title")
        )

        // Subtext
        Text(
            text = "YT Studio intelligence, keyword research, competitor analysis, transcript insights and AI strategy — all in one premium platform.",
            fontFamily = InterFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            textAlign = TextAlign.Center,
            color = TextSecondary,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .testTag("hero_subtext")
        )

        // CTAs
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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
                    .testTag("hero_primary_cta")
            )

            NeonButton(
                text = "Explore Features",
                style = NeonButtonStyle.GHOST,
                onClick = onExploreFeatures,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .testTag("hero_secondary_cta")
            )
        }

        // Live Counter & Creator Avatars
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(top = 4.dp)
                .testTag("hero_live_counter")
        ) {
            LiveDot(
                dotSize = 7.dp,
                dotColor = LiveGreen,
                glowColor = Color(0x6622C55E)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                AnimatedCounter(
                    targetValue = 12482,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = " channels growing now",
                    fontFamily = InterFontFamily,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            // Creator avatar rings
            Row(horizontalArrangement = Arrangement.spacedBy((-6).dp)) {
                listOf(
                    Color(0xFFE50914),
                    Color(0xFF3B82F6),
                    Color(0xFF10B981)
                ).forEachIndexed { index, color ->
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .border(1.5.dp, BackgroundCard, CircleShape)
                            .background(color, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (index) {
                                0 -> "A"
                                1 -> "M"
                                else -> "K"
                            },
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Floating Glass Dashboard Preview
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("hero_dashboard_card"),
            isHighlighted = true,
            contentPadding = 16.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Header of the preview window
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Channel Performance",
                            fontFamily = SoraFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextPrimary
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "Subscribers",
                                fontFamily = InterFontFamily,
                                fontSize = 12.sp,
                                color = TextTertiary
                            )
                            Text(
                                text = "+104%",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = LiveGreen
                            )
                        }
                    }

                    // Live badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .background(Color(0xFF0F1E14))
                            .border(1.dp, LiveGreen.copy(alpha = 0.3f), RoundedCornerShape(100.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            LiveDot(dotSize = 5.dp, dotColor = LiveGreen)
                            Text(
                                text = "LIVE",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = LiveGreen
                            )
                        }
                    }
                }

                // Timeframe Chips (1d, 1w, 1M, 3M, 6M, 1y)
                val timeframes = listOf("1d", "1w", "1M", "3M", "6M", "1y")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    timeframes.forEachIndexed { index, tf ->
                        val isSelected = selectedTimeframe == index
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) CrimsonPrimary else Color.Transparent)
                                .clickable { selectedTimeframe = index }
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = tf,
                                fontFamily = InterFontFamily,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 11.sp,
                                color = if (isSelected) TextPrimary else TextTertiary
                            )
                        }
                    }
                }

                // Glowing Area Sparkline Graph
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFF070707))
                ) {
                    Canvas(modifier = Modifier.fillMaxWidth().height(110.dp)) {
                        val w = size.width
                        val h = size.height

                        // Points for curve
                        val points = listOf(
                            Offset(0f, h * 0.85f),
                            Offset(w * 0.18f, h * 0.72f),
                            Offset(w * 0.35f, h * 0.65f),
                            Offset(w * 0.52f, h * 0.50f),
                            Offset(w * 0.70f, h * 0.58f),
                            Offset(w * 0.85f, h * 0.30f),
                            Offset(w, h * 0.15f)
                        )

                        // Path for area gradient
                        val fillPath = Path().apply {
                            moveTo(0f, h)
                            points.forEach { lineTo(it.x, it.y) }
                            lineTo(w, h)
                            close()
                        }

                        drawPath(
                            path = fillPath,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    CrimsonPrimary.copy(alpha = 0.45f),
                                    CrimsonPrimary.copy(alpha = 0.12f),
                                    Color.Transparent
                                )
                            )
                        )

                        // Stroke path
                        val strokePath = Path().apply {
                            moveTo(points[0].x, points[0].y)
                            for (i in 1 until points.size) {
                                lineTo(points[i].x, points[i].y)
                            }
                        }

                        drawPath(
                            path = strokePath,
                            brush = Brush.horizontalGradient(
                                colors = listOf(CrimsonPrimary.copy(alpha = 0.6f), CrimsonLight)
                            ),
                            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                        )

                        // Glowing point at the tip
                        val endPoint = points.last()
                        drawCircle(
                            color = CrimsonGlow,
                            radius = 12.dp.toPx(),
                            center = endPoint
                        )
                        drawCircle(
                            color = Color.White,
                            radius = 4.dp.toPx(),
                            center = endPoint
                        )
                    }
                }

                // Metric Cards: CTR & Retention
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // CTR Card
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(BackgroundElevated)
                            .border(1.dp, BorderGlass, RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "Thumbnail CTR",
                                fontFamily = InterFontFamily,
                                fontSize = 11.sp,
                                color = TextTertiary
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "14.2%",
                                    fontFamily = SoraFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = TextPrimary
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                                    contentDescription = null,
                                    tint = LiveGreen,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Text(
                                text = "Excellent (Top 5%)",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = LiveGreen
                            )
                        }
                    }

                    // Retention Card
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(BackgroundElevated)
                            .border(1.dp, BorderGlass, RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                text = "Avg View Duration",
                                fontFamily = InterFontFamily,
                                fontSize = 11.sp,
                                color = TextTertiary
                            )
                            Text(
                                text = "4:32",
                                fontFamily = SoraFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = TextPrimary
                            )
                            Text(
                                text = "Shorts +34% boost",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = CrimsonLight
                            )
                        }
                    }
                }

                // AI Strategist Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF130A0B))
                        .border(1.dp, CrimsonPrimary.copy(alpha = 0.35f), RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = CrimsonPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "AI Strategist Recommendation",
                                fontFamily = SoraFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = TextPrimary
                            )
                        }

                        Text(
                            text = "Optimize 'How I Got 1M Views' to 'I Spent 24 Hours Studying the YT Algorithm' for +28% predicted CTR.",
                            fontFamily = InterFontFamily,
                            fontSize = 12.sp,
                            lineHeight = 17.sp,
                            color = TextSecondary
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (ideaApplied) LiveGreen.copy(alpha = 0.2f) else CrimsonPrimary)
                                    .border(
                                        1.dp,
                                        if (ideaApplied) LiveGreen else CrimsonGlow,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        ideaApplied = !ideaApplied
                                        onApplyIdea("I Spent 24 Hours Studying the YT Algorithm")
                                    }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    if (ideaApplied) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = LiveGreen,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                    Text(
                                        text = if (ideaApplied) "Idea Applied!" else "Apply Idea",
                                        fontFamily = InterFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 11.sp,
                                        color = if (ideaApplied) LiveGreen else Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
