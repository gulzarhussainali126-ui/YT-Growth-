package com.example.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class StepItem(
    val stepNumber: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val highlightText: String
)

@Composable
fun HowItWorksSection(
    onStepClick: (StepItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val steps = listOf(
        StepItem(
            stepNumber = "01",
            title = "Connect YouTube",
            description = "Login securely with Google and connect your YouTube channel + YT Studio data in 30 seconds with read-only OAuth.",
            icon = Icons.Default.Sync,
            highlightText = "Google Verified OAuth"
        ),
        StepItem(
            stepNumber = "02",
            title = "Analyze Your Channel",
            description = "YT Growth scans historical analytics, audience retention spikes, search impressions, competitor gaps, and top video transcripts.",
            icon = Icons.Default.Analytics,
            highlightText = "Full Studio Audit"
        ),
        StepItem(
            stepNumber = "03",
            title = "Get Your Growth Plan",
            description = "AI generates prioritized high-leverage actions: winning thumbnail concepts, title variations, peak upload windows, and retention fixes.",
            icon = Icons.Default.RocketLaunch,
            highlightText = "Weekly Action Blueprint"
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .testTag("how_it_works_section"),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        SectionHeading(
            title = "Start Growing in 3 Minutes",
            badgeText = "Workflow",
            subtitle = "Zero complicated setups. Connect your channel and let the AI growth engine do the heavy lifting."
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            steps.forEachIndexed { index, step ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Left timeline line & number badge
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(36.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(CrimsonPrimary, Color(0xFF6B0206))
                                    )
                                )
                                .border(1.5.dp, CrimsonLight, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = step.stepNumber,
                                fontFamily = SoraFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        }

                        if (index < steps.size - 1) {
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(100.dp)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                CrimsonPrimary,
                                                CrimsonPrimary.copy(alpha = 0.2f)
                                            )
                                        )
                                    )
                            )
                        }
                    }

                    // Step card
                    GlassCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(bottom = 8.dp),
                        onClick = { onStepClick(step) }
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = step.title,
                                    fontFamily = SoraFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = TextPrimary
                                )

                                Icon(
                                    imageVector = step.icon,
                                    contentDescription = null,
                                    tint = CrimsonPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Text(
                                text = step.description,
                                fontFamily = InterFontFamily,
                                fontSize = 13.sp,
                                lineHeight = 19.sp,
                                color = TextSecondary
                            )

                            Box(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFF140708))
                                    .padding(horizontal = 8.dp, vertical = 3.dp)
                            ) {
                                Text(
                                    text = step.highlightText,
                                    fontFamily = InterFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 11.sp,
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
