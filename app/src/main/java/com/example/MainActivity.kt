package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.example.ui.components.SilkWaveBackground
import com.example.ui.landing.AnalysisModal
import com.example.ui.landing.FaqSection
import com.example.ui.landing.FeaturesGridSection
import com.example.ui.landing.FinalCtaSection
import com.example.ui.landing.FooterSection
import com.example.ui.landing.HeroSection
import com.example.ui.landing.HowItWorksSection
import com.example.ui.landing.LandingSection
import com.example.ui.landing.Navbar
import com.example.ui.landing.PricingPreviewSection
import com.example.ui.landing.ProductShowcaseSection
import com.example.ui.landing.SocialProofSection
import com.example.ui.theme.BackgroundPure
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                YTGrowthApp()
            }
        }
    }
}

@Composable
fun YTGrowthApp() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var showAnalysisModal by remember { mutableStateOf(false) }

    val sectionIndexMap = mapOf(
        LandingSection.HERO to 0,
        LandingSection.FEATURES to 2,
        LandingSection.HOW_IT_WORKS to 3,
        LandingSection.SHOWCASE to 4,
        LandingSection.PRICING to 5,
        LandingSection.FAQ to 6,
        LandingSection.FINAL_CTA to 7
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundPure)
    ) {
        // Ambient background silk wave canvas
        SilkWaveBackground()

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                Navbar(
                    onNavigateToSection = { section ->
                        sectionIndexMap[section]?.let { index ->
                            coroutineScope.launch {
                                listState.animateScrollToItem(index)
                            }
                        }
                    },
                    onLoginClick = {
                        showAnalysisModal = true
                    },
                    onLoginWithYouTube = {
                        showAnalysisModal = true
                    },
                    modifier = Modifier.statusBarsPadding()
                )
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.navigationBarsPadding()
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag("landing_page_list")
            ) {
                // Item 0: Hero Section
                item(key = "hero") {
                    HeroSection(
                        onLoginWithYouTube = { showAnalysisModal = true },
                        onExploreFeatures = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(2)
                            }
                        },
                        onApplyIdea = { idea ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("AI Title Idea '$idea' copied to clipboard!")
                            }
                        }
                    )
                }

                // Item 1: Social Proof Strip
                item(key = "social_proof") {
                    SocialProofSection()
                }

                // Item 2: Features Grid
                item(key = "features") {
                    FeaturesGridSection(
                        onFeatureClick = { feature ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Feature: ${feature.title} — ${feature.statTag}")
                            }
                        }
                    )
                }

                // Item 3: How It Works
                item(key = "how_it_works") {
                    HowItWorksSection(
                        onStepClick = { step ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Step ${step.stepNumber}: ${step.title}")
                            }
                        }
                    )
                }

                // Item 4: Product Showcase
                item(key = "showcase") {
                    ProductShowcaseSection()
                }

                // Item 5: Pricing Preview
                item(key = "pricing") {
                    PricingPreviewSection(
                        onSelectPlan = { plan ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Selected ${plan.name} Plan (${plan.credits})")
                            }
                        }
                    )
                }

                // Item 6: FAQ
                item(key = "faq") {
                    FaqSection()
                }

                // Item 7: Final CTA
                item(key = "final_cta") {
                    FinalCtaSection(
                        onLoginWithYouTube = { showAnalysisModal = true }
                    )
                }

                // Item 8: Footer
                item(key = "footer") {
                    FooterSection(
                        onLinkClick = { link ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Navigating to $link...")
                            }
                        },
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }

        // Interactive Analysis & Free Credit Claim Sheet
        if (showAnalysisModal) {
            AnalysisModal(
                onDismiss = { showAnalysisModal = false }
            )
        }
    }
}
