package com.example.ui.landing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlassCard
import com.example.ui.components.SectionHeading
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class FaqItem(
    val question: String,
    val answer: String
)

@Composable
fun FaqSection(
    modifier: Modifier = Modifier
) {
    var expandedIndex by remember { mutableIntStateOf(0) } // First FAQ open by default

    val faqs = listOf(
        FaqItem(
            question = "Is YT Growth free to use?",
            answer = "Yes! You start with 50 free credits upon connecting your channel—no credit card required. You can run your first full channel health audit, test keywords, and grade thumbnails completely free."
        ),
        FaqItem(
            question = "Do I need to connect my YouTube channel?",
            answer = "Yes, connecting via Google OAuth provides read-only access to your YouTube Studio metrics (retention curve, CTR, impressions, audience velocity) so the AI can diagnose real bottlenecks rather than generic guesses."
        ),
        FaqItem(
            question = "How does the AI growth strategy work?",
            answer = "Our proprietary algorithm benchmarks your video performance against millions of YouTube creator trajectories. It pinpoints exactly where viewers click away, predicts title CTRs, and reveals high-demand topics with low competitor coverage."
        ),
        FaqItem(
            question = "Is my YouTube data secure?",
            answer = "100% secure. We utilize standard Google OAuth 2.0 with read-only scopes. We never have permission to delete videos, alter channel settings, or upload on your behalf. All data is encrypted at rest using AES-256."
        ),
        FaqItem(
            question = "Can I analyze competitor channels?",
            answer = "Yes! The Competitor Tracker allows you to monitor any public creator in your niche. You can track their upload cadence, spot viral outlier videos, and detect rising trends before they saturate."
        ),
        FaqItem(
            question = "Does YT Growth work for new channels?",
            answer = "Yes, absolutely. New channels with zero subscribers benefit the most by utilizing our Keyword Research and Thumbnail Analyzer to guarantee high-interest topics and competitive click-through rates right from upload one."
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .testTag("faq_section"),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        SectionHeading(
            title = "Frequently Asked Questions",
            badgeText = "FAQ",
            subtitle = "Everything you need to know about connecting your channel, AI credits, and privacy."
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            faqs.forEachIndexed { index, faq ->
                val isOpen = expandedIndex == index

                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    isHighlighted = isOpen,
                    onClick = {
                        expandedIndex = if (isOpen) -1 else index
                    }
                ) {
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
                                text = faq.question,
                                fontFamily = SoraFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                color = if (isOpen) TextPrimary else TextSecondary,
                                modifier = Modifier.weight(1f)
                            )

                            Icon(
                                imageVector = if (isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = if (isOpen) "Collapse" else "Expand",
                                tint = if (isOpen) CrimsonPrimary else TextSecondary,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = isOpen,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Text(
                                text = faq.answer,
                                fontFamily = InterFontFamily,
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                color = TextSecondary,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
