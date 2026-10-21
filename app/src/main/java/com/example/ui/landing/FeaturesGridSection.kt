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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeading
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val isHighlighted: Boolean = false,
    val badge: String? = null,
    val statTag: String
)

@Composable
fun FeaturesGridSection(
    onFeatureClick: (FeatureItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val features = listOf(
        FeatureItem(
            title = "AI Growth Chat",
            description = "Ask YT Growth AI anything about your channel and get a data-backed action plan with real-time video recommendations.",
            icon = Icons.Default.AutoAwesome,
            isHighlighted = true,
            badge = "Most Powerful",
            statTag = "Real-time AI Reasoning"
        ),
        FeatureItem(
            title = "Keyword Research",
            description = "Find high-demand, low-competition keywords with Google & YouTube search trend graphs, difficulty scores, and audience velocity.",
            icon = Icons.Default.Search,
            statTag = "Live Search Volume"
        ),
        FeatureItem(
            title = "Thumbnail Analyzer",
            description = "Grade thumbnails for clarity, emotion, contrast, visual focal points, and click potential before you hit publish.",
            icon = Icons.Default.Image,
            statTag = "Vision AI Scoring"
        ),
        FeatureItem(
            title = "Transcript Outer Cycle",
            description = "Extract transcripts instantly using YT Growth’s self-hosted runner to find hook moments, retention drops, and reuse clips.",
            icon = Icons.Default.Description,
            statTag = "Instant Audio-to-Text"
        ),
        FeatureItem(
            title = "Competitor Tracker",
            description = "Track competitor uploads, growth velocity, title patterns, and detect viral outlier videos early.",
            icon = Icons.Default.People,
            statTag = "Outlier Detection"
        ),
        FeatureItem(
            title = "Daily Streak",
            description = "Build consistency with daily credits, streak bonuses, personalized creator missions, and gamified channel milestones.",
            icon = Icons.Default.LocalFireDepartment,
            statTag = "+15 Daily Credits"
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .testTag("features_grid_section"),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SectionHeading(
            title = "One Platform.\nEvery Growth Advantage.",
            badgeText = "Core Features",
            subtitle = "Everything you need to systematically research, produce, test, and rank your content on YouTube."
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            features.forEach { feature ->
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    isHighlighted = feature.isHighlighted,
                    onClick = { onFeatureClick(feature) }
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Glowing red icon container
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .drawBehind {
                                        drawCircle(
                                            brush = Brush.radialGradient(
                                                colors = listOf(CrimsonGlow, Color.Transparent),
                                                center = center,
                                                radius = size.maxDimension * 0.9f
                                            )
                                        )
                                    }
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFF1C0A0B))
                                    .border(1.dp, CrimsonPrimary.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = feature.icon,
                                    contentDescription = feature.title,
                                    tint = CrimsonPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            if (feature.badge != null) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(100.dp))
                                        .background(CrimsonPrimary)
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = feature.badge.uppercase(),
                                        fontFamily = InterFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.6.sp,
                                        color = Color.White
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(100.dp))
                                        .background(Color(0xFF141414))
                                        .border(0.5.dp, Color(0x33FFFFFF), RoundedCornerShape(100.dp))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = feature.statTag,
                                        fontFamily = InterFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 10.sp,
                                        color = TextSecondary
                                    )
                                }
                            }
                        }

                        Text(
                            text = feature.title,
                            fontFamily = SoraFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = TextPrimary
                        )

                        Text(
                            text = feature.description,
                            fontFamily = InterFontFamily,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = TextSecondary
                        )
                    }
                }
            }
        }
    }
}
