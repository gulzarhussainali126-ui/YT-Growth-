package com.example.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.GlassCard
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonButtonStyle
import com.example.ui.components.SectionHeading
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CrimsonLight
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.LiveGreen
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

data class PricingPlan(
    val name: String,
    val monthlyPrice: String,
    val yearlyPrice: String,
    val credits: String,
    val features: List<String>,
    val isPopular: Boolean = false,
    val ctaText: String
)

@Composable
fun PricingPreviewSection(
    onSelectPlan: (PricingPlan) -> Unit,
    modifier: Modifier = Modifier
) {
    var isYearly by remember { mutableStateOf(false) }

    val plans = listOf(
        PricingPlan(
            name = "Free",
            monthlyPrice = "$0",
            yearlyPrice = "$0",
            credits = "50 credits",
            features = listOf(
                "50 credits on signup",
                "Basic channel performance audit",
                "3 keyword ideas / day",
                "Standard thumbnail test"
            ),
            isPopular = false,
            ctaText = "Start Free"
        ),
        PricingPlan(
            name = "Pro",
            monthlyPrice = "$29",
            yearlyPrice = "$23",
            credits = "500 credits",
            features = listOf(
                "500 AI credits / month",
                "Full YT Studio analytics sync",
                "Deep competitor tracker",
                "AI Growth Chat assistant"
            ),
            isPopular = false,
            ctaText = "Choose Pro"
        ),
        PricingPlan(
            name = "Ultra",
            monthlyPrice = "$79",
            yearlyPrice = "$63",
            credits = "2,000 credits",
            features = listOf(
                "2,000 AI credits / month",
                "Real-time outlier alerts",
                "Multi-channel support (up to 3)",
                "Unlimited thumbnail grades & AI ideas"
            ),
            isPopular = true,
            ctaText = "Choose Ultra"
        ),
        PricingPlan(
            name = "Max",
            monthlyPrice = "$199",
            yearlyPrice = "$159",
            credits = "Unlimited credits",
            features = listOf(
                "High-limit / Unlimited credits",
                "Agency multi-seat (5 creators)",
                "Dedicated growth strategist",
                "Custom runner API webhooks"
            ),
            isPopular = false,
            ctaText = "Choose Max"
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .testTag("pricing_preview_section"),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionHeading(
            title = "Simple Plans.\nSerious Growth.",
            badgeText = "Pricing Plans",
            subtitle = "Invest in your channel’s future with transparent creator plans. Upgrade or cancel anytime."
        )

        // Monthly / Yearly billing toggle
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Color(0xFF141414))
                .border(1.dp, BorderGlass, RoundedCornerShape(100.dp))
                .padding(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(if (!isYearly) CrimsonPrimary else Color.Transparent)
                        .clickable { isYearly = false }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "Monthly",
                        fontFamily = InterFontFamily,
                        fontWeight = if (!isYearly) FontWeight.Bold else FontWeight.Medium,
                        fontSize = 13.sp,
                        color = if (!isYearly) Color.White else TextSecondary
                    )
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(if (isYearly) CrimsonPrimary else Color.Transparent)
                        .clickable { isYearly = true }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Yearly",
                            fontFamily = InterFontFamily,
                            fontWeight = if (isYearly) FontWeight.Bold else FontWeight.Medium,
                            fontSize = 13.sp,
                            color = if (isYearly) Color.White else TextSecondary
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(100.dp))
                                .background(LiveGreen)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Save 20%",
                                fontFamily = InterFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }

        // Pricing Cards
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            plans.forEach { plan ->
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    isHighlighted = plan.isPopular
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = plan.name,
                                    fontFamily = SoraFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = TextPrimary
                                )
                                Text(
                                    text = plan.credits,
                                    fontFamily = InterFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    color = CrimsonLight
                                )
                            }

                            if (plan.isPopular) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(100.dp))
                                        .background(CrimsonPrimary)
                                        .padding(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "MOST POPULAR",
                                        fontFamily = InterFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.6.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        // Price
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = if (isYearly) plan.yearlyPrice else plan.monthlyPrice,
                                fontFamily = SoraFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp,
                                color = TextPrimary
                            )
                            Text(
                                text = "/month",
                                fontFamily = InterFontFamily,
                                fontSize = 13.sp,
                                color = TextTertiary,
                                modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                            )
                            if (isYearly && plan.monthlyPrice != "$0") {
                                Text(
                                    text = " (billed yearly)",
                                    fontFamily = InterFontFamily,
                                    fontSize = 11.sp,
                                    color = LiveGreen,
                                    modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
                                )
                            }
                        }

                        // Features List
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            plan.features.forEach { feat ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .clip(CircleShape)
                                            .background(if (plan.isPopular) CrimsonPrimary.copy(alpha = 0.2f) else Color(0xFF1C1C1C)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = if (plan.isPopular) CrimsonLight else LiveGreen,
                                            modifier = Modifier.size(11.dp)
                                        )
                                    }
                                    Text(
                                        text = feat,
                                        fontFamily = InterFontFamily,
                                        fontSize = 13.sp,
                                        color = TextSecondary
                                    )
                                }
                            }
                        }

                        NeonButton(
                            text = plan.ctaText,
                            style = if (plan.isPopular) NeonButtonStyle.PRIMARY else NeonButtonStyle.GHOST,
                            onClick = { onSelectPlan(plan) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Pakistani Creators Note
        Text(
            text = "All payments support global methods and convert to PKR for Pakistani creators.",
            fontFamily = InterFontFamily,
            fontSize = 12.sp,
            color = TextTertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
