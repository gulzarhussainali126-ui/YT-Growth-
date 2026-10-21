package com.example.ui.landing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.NeonButton
import com.example.ui.components.NeonButtonStyle
import com.example.ui.theme.BackgroundCard
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

enum class LandingSection {
    HERO,
    FEATURES,
    HOW_IT_WORKS,
    SHOWCASE,
    PRICING,
    FAQ,
    FINAL_CTA
}

@Composable
fun Navbar(
    onNavigateToSection: (LandingSection) -> Unit,
    onLoginClick: () -> Unit,
    onLoginWithYouTube: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xE60A0A0A)) // Glassmorphism backdrop
            .border(width = 0.5.dp, color = BorderSubtle)
            .testTag("navbar")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left: YT Growth Logo
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onNavigateToSection(LandingSection.HERO) }
                    .padding(4.dp)
                    .testTag("nav_logo")
            ) {
                // Red glowing square with Play icon
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .drawBehind {
                            drawCircle(
                                brush = Brush.radialGradient(
                                    colors = listOf(CrimsonGlow, Color.Transparent),
                                    center = center,
                                    radius = size.maxDimension * 0.9f
                                )
                            )
                        }
                        .clip(RoundedCornerShape(8.dp))
                        .background(CrimsonPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "YT Growth Play Icon",
                        tint = TextPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "YT",
                        fontFamily = SoraFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = CrimsonPrimary
                    )
                    Text(
                        text = " Growth",
                        fontFamily = SoraFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                }
            }

            // Right action buttons + Hamburger
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NeonButton(
                    text = "Login",
                    style = NeonButtonStyle.GHOST,
                    onClick = onLoginClick,
                    modifier = Modifier.testTag("nav_login_ghost")
                )

                IconButton(
                    onClick = { isMenuOpen = !isMenuOpen },
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, BorderGlass, RoundedCornerShape(8.dp))
                        .testTag("nav_hamburger")
                ) {
                    Icon(
                        imageVector = if (isMenuOpen) Icons.Default.Close else Icons.Default.Menu,
                        contentDescription = "Toggle Navigation Menu",
                        tint = TextPrimary
                    )
                }
            }
        }

        // Expanded Glass Drawer on Mobile
        AnimatedVisibility(
            visible = isMenuOpen,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundCard.copy(alpha = 0.98f))
                    .border(width = 1.dp, color = BorderGlass)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                val navItems = listOf(
                    "Features" to LandingSection.FEATURES,
                    "How It Works" to LandingSection.HOW_IT_WORKS,
                    "Showcase" to LandingSection.SHOWCASE,
                    "Pricing" to LandingSection.PRICING,
                    "FAQ" to LandingSection.FAQ
                )

                navItems.forEach { (label, section) ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                isMenuOpen = false
                                onNavigateToSection(section)
                            }
                            .padding(vertical = 10.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = label,
                            fontFamily = InterFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            color = TextSecondary
                        )
                    }
                }

                // Primary Red Button in Mobile Menu
                NeonButton(
                    text = "Login with YouTube",
                    style = NeonButtonStyle.PRIMARY,
                    onClick = {
                        isMenuOpen = false
                        onLoginWithYouTube()
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = TextPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .testTag("nav_menu_primary_cta")
                )
            }
        }
    }
}
