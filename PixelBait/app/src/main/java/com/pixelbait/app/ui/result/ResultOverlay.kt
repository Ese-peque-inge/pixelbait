package com.pixelbait.app.ui.result

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*
import com.pixelbait.app.domain.model.EngineVerdict
import com.pixelbait.app.domain.model.RiskLevel
import com.pixelbait.app.domain.model.ScanResult

/**
 * Overlay con el resultado del análisis (Requerimiento 3.2).
 * El nivel de riesgo siempre se comunica con color + ícono + texto (accesibilidad).
 * La app nunca bloquea automáticamente el acceso: la decisión final es del usuario.
 */
@Composable
fun BoxScope.ResultOverlay(result: ScanResult, onDismiss: () -> Unit) {
    var showConsentConfirm by remember { mutableStateOf(false) }
    val riskColor = riskColorFor(result.riskLevel)

    Box(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .fillMaxHeight(0.72f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PbSurface, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .border(1.dp, riskColor.copy(alpha = 0.4f), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(18.dp)
        ) {
            // Header: ícono + nivel de riesgo + badge + cerrar
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(riskColor.copy(alpha = 0.15f), CircleShape)
                        .border(1.dp, riskColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(riskIconFor(result.riskLevel), contentDescription = null, tint = riskColor, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(riskLabelFor(result.riskLevel), color = riskColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        if (result.riskLevel == RiskLevel.DANGEROUS) {
                            Spacer(Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .background(riskColor.copy(alpha = 0.15f), RoundedCornerShape(50))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(stringResource(R.string.risk_malicious_tag), color = riskColor, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    Text(stringResource(R.string.detected_by), color = PbTextMuted, fontSize = 11.sp)
                }
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Filled.Close, contentDescription = null, tint = PbTextMuted)
                }
            }

            Spacer(Modifier.height(14.dp))

            // URL detectada
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PbSurfaceElevated, RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Link, contentDescription = null, tint = PbTextMuted, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text(
                    result.url,
                    color = PbTextSecondary,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(Modifier.height(14.dp))

            // Nivel de criticidad
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(R.string.criticality_level), color = PbTextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text("${result.criticalityScore} / 100", color = riskColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(6.dp))
            CriticalityBar(score = result.criticalityScore)

            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.detection_engines), color = PbTextMuted, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(result.engines) { engine -> EngineCard(engine) }
            }

            Spacer(Modifier.height(12.dp))

            // Contadores resumen
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CountStat(result.maliciousCount.toString(), stringResource(R.string.count_malicious), PbError)
                CountStat(result.suspiciousCount.toString(), stringResource(R.string.count_suspicious), PbWarning)
                CountStat(result.cleanCount.toString(), stringResource(R.string.count_clean), PbTextMuted)
            }

            Spacer(Modifier.height(14.dp))

            if (!showConsentConfirm) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PbError),
                        border = BorderStroke(1.dp, PbError.copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Filled.Block, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(stringResource(R.string.btn_block), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                    OutlinedButton(
                        onClick = { showConsentConfirm = true },
                        modifier = Modifier.weight(1f).height(48.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PbTextPrimary),
                        border = BorderStroke(1.dp, PbBorder),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(stringResource(R.string.btn_view_report), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            } else {
                // Decisión final explícita del usuario: la app nunca bloquea el acceso por sí sola.
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = riskColor),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(stringResource(R.string.btn_continue_own_risk), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
private fun CriticalityBar(score: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(50))
            .background(PbSurfaceElevated)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(score / 100f)
                .fillMaxHeight()
                .background(Brush.horizontalGradient(listOf(PbWarning, PbError)))
        )
    }
}

@Composable
private fun EngineCard(engine: EngineVerdict) {
    val color = if (engine.isThreat) PbError else PbSuccess
    Row(
        modifier = Modifier
            .background(PbSurfaceElevated, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            if (engine.isThreat) Icons.Filled.Close else Icons.Filled.Check,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(14.dp)
        )
        Spacer(Modifier.width(6.dp))
        Column {
            Text(engine.engineName, color = PbTextPrimary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(engine.verdict, color = color, fontSize = 10.sp)
        }
    }
}

@Composable
private fun CountStat(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(label, color = PbTextMuted, fontSize = 10.sp)
    }
}

@Composable
fun riskColorFor(level: RiskLevel): Color = when (level) {
    RiskLevel.SAFE -> PbSuccess
    RiskLevel.SUSPICIOUS -> PbWarning
    RiskLevel.DANGEROUS -> PbError
    RiskLevel.UNVERIFIED -> PbTextMuted
}

@Composable
fun riskIconFor(level: RiskLevel) = when (level) {
    RiskLevel.SAFE -> Icons.Filled.Shield
    RiskLevel.SUSPICIOUS -> Icons.Filled.Warning
    RiskLevel.DANGEROUS -> Icons.Filled.GppBad
    RiskLevel.UNVERIFIED -> Icons.Filled.HelpOutline
}

@Composable
fun riskLabelFor(level: RiskLevel): String = when (level) {
    RiskLevel.SAFE -> stringResource(R.string.risk_low)
    RiskLevel.SUSPICIOUS -> stringResource(R.string.risk_medium)
    RiskLevel.DANGEROUS -> stringResource(R.string.risk_high)
    RiskLevel.UNVERIFIED -> stringResource(R.string.not_verified)
}
