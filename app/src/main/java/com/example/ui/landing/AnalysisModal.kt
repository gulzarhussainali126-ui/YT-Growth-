package com.example.ui.landing

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalysisModal(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var channelHandle by remember { mutableStateOf("@MyCreatorChannel") }
    var isAnalyzing by remember { mutableStateOf(false) }
    var analysisComplete by remember { mutableStateOf(false) }
    var statusText by remember { mutableStateOf("Connecting to Google OAuth...") }
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = BackgroundCard,
        dragHandle = null,
        modifier = modifier.testTag("analysis_modal")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                        text = "Free Channel Analysis",
                        fontFamily = SoraFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = TextPrimary
                    )
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = TextSecondary
                    )
                }
            }

            if (!analysisComplete && !isAnalyzing) {
                Text(
                    text = "Connect your YouTube channel to claim 50 free credits and receive instant AI analysis on your CTR, retention bottlenecks, and keyword opportunities.",
                    fontFamily = InterFontFamily,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
                    color = TextSecondary
                )

                OutlinedTextField(
                    value = channelHandle,
                    onValueChange = { channelHandle = it },
                    label = { Text("YouTube Handle / Channel Name", color = TextTertiary) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CrimsonPrimary,
                        unfocusedBorderColor = BorderGlass,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        cursorColor = CrimsonPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    LiveDot(dotSize = 6.dp, dotColor = LiveGreen)
                    Text(
                        text = "50 credits will be deposited immediately into your account.",
                        fontFamily = InterFontFamily,
                        fontSize = 11.sp,
                        color = LiveGreen
                    )
                }

                NeonButton(
                    text = "Start Free Instant Scan",
                    style = NeonButtonStyle.PRIMARY,
                    onClick = {
                        isAnalyzing = true
                        scope.launch {
                            statusText = "Authenticating YouTube Studio read credentials..."
                            delay(800)
                            statusText = "Scanning recent 20 uploads & CTR metrics..."
                            delay(900)
                            statusText = "Evaluating keyword search gaps and retention spikes..."
                            delay(900)
                            isAnalyzing = false
                            analysisComplete = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            } else if (isAnalyzing) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CircularProgressIndicator(
                        color = CrimsonPrimary,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = statusText,
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }
            } else {
                // Analysis Complete results
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFF0F1E14))
                            .border(1.dp, LiveGreen.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                            .padding(14.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = LiveGreen,
                                modifier = Modifier.size(24.dp)
                            )
                            Column {
                                Text(
                                    text = "Channel Audit Complete!",
                                    fontFamily = SoraFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = TextPrimary
                                )
                                Text(
                                    text = "+50 credits added to $channelHandle",
                                    fontFamily = InterFontFamily,
                                    fontSize = 12.sp,
                                    color = LiveGreen
                                )
                            }
                        }
                    }

                    // Key metrics found
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(BackgroundElevated)
                                .padding(12.dp)
                        ) {
                            Column {
                                Text("Predicted CTR Boost", fontSize = 11.sp, color = TextTertiary)
                                Text("+3.8% CTR", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = LiveGreen)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .background(BackgroundElevated)
                                .padding(12.dp)
                        ) {
                            Column {
                                Text("Viral Topic Detected", fontSize = 11.sp, color = TextTertiary)
                                Text("High Search Vol", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = CrimsonLight)
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(BackgroundElevated)
                            .padding(12.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
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
                                    text = "Top Action for $channelHandle",
                                    fontFamily = SoraFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = TextPrimary
                                )
                            }
                            Text(
                                text = "Your last video had a 44% drop in the first 30 seconds. Shortening your intro hook by 8 seconds will significantly boost suggested video impressions.",
                                fontFamily = InterFontFamily,
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                        }
                    }

                    NeonButton(
                        text = "Close & Explore Features",
                        style = NeonButtonStyle.PRIMARY,
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
