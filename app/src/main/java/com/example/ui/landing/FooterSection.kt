package com.example.ui.landing

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
    onLinkClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, color = BorderSubtle)
            .background(Color(0xFF030303))
            .padding(horizontal = 20.dp, vertical = 32.dp)
            .testTag("footer_section"),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Brand row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(CrimsonPrimary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
            Text(
                text = "YT Growth",
                fontFamily = SoraFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextPrimary
            )
        }

        Text(
            text = "The AI operating system for YouTube creators. Scale your views, optimize CTR, and dominate recommendations.",
            fontFamily = InterFontFamily,
            fontSize = 13.sp,
            lineHeight = 18.sp,
            color = TextTertiary
        )

        // Footer columns
        val columns = listOf(
            "Product" to listOf("Features", "Pricing", "AI Chat", "Thumbnail Analyzer"),
            "Resources" to listOf("Growth Blog", "Creator Tools", "Algorithm Guide", "Documentation"),
            "Legal" to listOf("Privacy Policy", "Terms of Service", "Google API Disclosure", "Security")
        )

        columns.forEach { (colTitle, links) ->
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = colTitle.uppercase(),
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp,
                    color = TextPrimary
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    links.forEach { link ->
                        Text(
                            text = link,
                            fontFamily = InterFontFamily,
                            fontSize = 13.sp,
                            color = TextSecondary,
                            modifier = Modifier
                                .clickable { onLinkClick(link) }
                                .padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }

        // Social Icons / Links
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("YouTube", "X (Twitter)", "Instagram", "Discord").forEach { social ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0F0F0F))
                        .border(1.dp, BorderGlass, RoundedCornerShape(8.dp))
                        .clickable { onLinkClick(social) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = social,
                        fontFamily = InterFontFamily,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .background(BorderSubtle)
        )

        // Copyright line (strictly no admin link or admin reference)
        Text(
            text = "© 2026 YT Growth. All rights reserved.",
            fontFamily = InterFontFamily,
            fontSize = 12.sp,
            color = TextTertiary
        )
    }
}
