package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderGlass
import com.example.ui.theme.CrimsonPrimary
import com.example.ui.theme.InterFontFamily
import com.example.ui.theme.SoraFontFamily
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SectionHeading(
    title: String,
    modifier: Modifier = Modifier,
    badgeText: String? = null,
    subtitle: String? = null,
    highlightWord: String? = null,
    alignCenter: Boolean = true
) {
    val alignment = if (alignCenter) Alignment.CenterHorizontally else Alignment.Start
    val textAlign = if (alignCenter) TextAlign.Center else TextAlign.Start

    Column(
        modifier = modifier,
        horizontalAlignment = alignment,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (!badgeText.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color(0xFF140708))
                    .border(
                        width = 1.dp,
                        color = CrimsonPrimary.copy(alpha = 0.35f),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(CrimsonPrimary, CircleShape)
                    )
                    Text(
                        text = badgeText.uppercase(),
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        letterSpacing = 1.2.sp,
                        color = CrimsonPrimary
                    )
                }
            }
        }

        Text(
            text = title,
            fontFamily = SoraFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 32.sp,
            letterSpacing = (-0.5).sp,
            textAlign = textAlign,
            color = TextPrimary
        )

        if (!subtitle.isNullOrEmpty()) {
            Text(
                text = subtitle,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                textAlign = textAlign,
                color = TextSecondary
            )
        }
    }
}
