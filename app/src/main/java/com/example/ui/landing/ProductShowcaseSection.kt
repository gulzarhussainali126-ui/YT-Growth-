package com.example.ui.landing

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeading
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
fun ProductShowcaseSection(
    modifier: Modifier = Modifier
) {
    var animatedScoreTarget by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        animatedScoreTarget = 0.82f // 82/100
    }

    val animatedScore by animateFloatAsState(
        targetValue = animatedScoreTarget,
        animationSpec = tween(durationMillis = 1600),
        label = "health_score"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .testTag("product_showcase_section"),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SectionHeading(
            title = "Built for Serious Creators",
            badgeText = "Deep Intelligence",
            subtitle = "See what's working, where viewers drop off, and how to command the recommendation algorithm."
        )

        // Floating highlight chips
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(
                "CTR +42%" to LiveGreen,
                "Best time: 6–8 PM" to CrimsonLight,
                "Retention drop fixed" to Color(0xFF60A5FA)
            ).forEach { (label, tint) ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color(0xFF111111))
                        .border(1.dp, tint.copy(alpha = 0.4f), RoundedCornerShape(100.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .background(tint, CircleShape)
                        )
                        Text(
                            text = label,
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            color = TextPrimary
                        )
                    }
                }
            }
        }

        // Showcase Container
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            isHighlighted = true,
            contentPadding = 20.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Channel Health Score Ring
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Canvas(modifier = Modifier.size(100.dp)) {
                            val strokeWidth = 10.dp.toPx()
                            // Background track
                            drawArc(
                                color = Color(0xFF1F1F1F),
                                startAngle = -90f,
                                sweepAngle = 360f,
                                useCenter = false,
                                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                            )
                            // Progress arc
                            drawArc(
                                brush = Brush.sweepGradient(
                                    colors = listOf(CrimsonPrimary, CrimsonLight, LiveGreen)
                                ),
                                startAngle = -90f,
                                sweepAngle = 360f * animatedScore,
                                useCenter = false,
                                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                            )
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "${(animatedScore * 100).toInt()}",
                                fontFamily = SoraFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = TextPrimary
                            )
                            Text(
                                text = "out of 100",
                                fontFamily = InterFontFamily,
                                fontSize = 10.sp,
                                color = TextTertiary
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Channel Health Score",
                            fontFamily = SoraFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Top 8% in Tech & Education. High search traction with strong return audience.",
                            fontFamily = InterFontFamily,
                            fontSize = 12.sp,
                            lineHeight = 17.sp,
                            color = TextSecondary
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            listOf("SEO 88%", "CTR 84%", "Retention 76%").forEach { stat ->
                                Text(
                                    text = stat,
                                    fontFamily = InterFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    color = LiveGreen
                                )
                            }
                        }
                    }
                }

                // Traffic Source Breakdown Bars
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Traffic Source Breakdown",
                        fontFamily = SoraFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = TextPrimary
                    )

                    val trafficSources = listOf(
                        Triple("YouTube Search", 0.46f, "46%"),
                        Triple("Suggested Videos", 0.32f, "32%"),
                        Triple("Browse Features", 0.15f, "15%"),
                        Triple("External & Direct", 0.07f, "7%")
                    )

                    trafficSources.forEach { (name, percent, label) ->
                        Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = name,
                                    fontFamily = InterFontFamily,
                                    fontSize = 12.sp,
                                    color = TextSecondary
                                )
                                Text(
                                    text = label,
                                    fontFamily = InterFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    color = TextPrimary
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(Color(0xFF1E1E1E))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(percent)
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(
                                            Brush.horizontalGradient(
                                                colors = listOf(CrimsonPrimary, CrimsonLight)
                                            )
                                        )
                                )
                            }
                        }
                    }
                }

                // Top Search Terms
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "High Opportunity Search Terms",
                            fontFamily = SoraFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = TextPrimary
                        )

                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = CrimsonLight,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    val queries = listOf(
                        "ai automation tools 2026" to "+184% Vol",
                        "how to grow on youtube 2026" to "+92% Vol",
                        "youtube studio analytics guide" to "Low Comp",
                        "shorts algorithm viral hack" to "Breakout"
                    )

                    queries.forEach { (query, tag) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(BackgroundElevated)
                                .padding(horizontal = 12.dp, vertical = 9.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = query,
                                fontFamily = InterFontFamily,
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFF1A1A1A))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = tag,
                                    fontFamily = InterFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    color = CrimsonLight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
