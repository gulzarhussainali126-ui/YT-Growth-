package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.ui.theme.CrimsonGlow
import com.example.ui.theme.CrimsonPrimary
import kotlin.math.sin
import kotlin.random.Random

data class Particle(
    val xRatio: Float,
    val yRatio: Float,
    val radius: Float,
    val alpha: Float,
    val speed: Float
)

@Composable
fun SilkWaveBackground(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "silk_transition")

    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = (2 * Math.PI).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(14000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave_phase"
    )

    val particles = remember {
        List(25) {
            Particle(
                xRatio = Random.nextFloat(),
                yRatio = Random.nextFloat(),
                radius = Random.nextFloat() * 2.5f + 1f,
                alpha = Random.nextFloat() * 0.4f + 0.15f,
                speed = Random.nextFloat() * 0.8f + 0.2f
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        // Top-center soft red radial glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    CrimsonGlow.copy(alpha = 0.22f),
                    CrimsonGlow.copy(alpha = 0.08f),
                    Color.Transparent
                ),
                center = Offset(width * 0.5f, height * 0.18f),
                radius = width * 0.8f
            )
        )

        // Middle right ambient glow
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    CrimsonGlow.copy(alpha = 0.12f),
                    Color.Transparent
                ),
                center = Offset(width * 0.85f, height * 0.45f),
                radius = width * 0.5f
            )
        )

        // Draw flowing silk wave lines
        val waveCount = 4
        for (i in 0 until waveCount) {
            val path = Path()
            val baseHeight = height * (0.28f + i * 0.08f)
            val amplitude = 35f + i * 15f
            val frequency = 0.0035f + i * 0.001f
            val phaseOffset = wavePhase * (0.7f + i * 0.3f) + (i * 1.2f)

            path.moveTo(0f, baseHeight)
            var x = 0f
            while (x <= width) {
                val y = baseHeight + (sin(x * frequency + phaseOffset) * amplitude).toFloat()
                path.lineTo(x, y)
                x += 16f
            }

            drawPath(
                path = path,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        CrimsonPrimary.copy(alpha = 0.18f - i * 0.03f),
                        CrimsonPrimary.copy(alpha = 0.40f - i * 0.06f),
                        CrimsonPrimary.copy(alpha = 0.12f),
                        Color.Transparent
                    )
                ),
                style = Stroke(width = 1.6f - i * 0.2f)
            )
        }

        // Floating particles
        particles.forEach { p ->
            val py = ((p.yRatio + (wavePhase * 0.05f * p.speed)) % 1f) * height
            val px = p.xRatio * width
            drawCircle(
                color = CrimsonPrimary.copy(alpha = p.alpha),
                radius = p.radius,
                center = Offset(px, py)
            )
        }
    }
}
