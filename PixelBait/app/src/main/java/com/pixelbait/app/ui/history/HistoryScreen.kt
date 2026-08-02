package com.pixelbait.app.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*
import com.pixelbait.app.data.local.HistoryEntity
import com.pixelbait.app.domain.model.RiskLevel
import com.pixelbait.app.ui.common.BrandHeaderRow
import com.pixelbait.app.ui.common.MainTab
import com.pixelbait.app.ui.common.MainTabSwitcher
import java.util.concurrent.TimeUnit

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onNavigateSettings: () -> Unit
) {
    val records by viewModel.history.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PbBlack)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        BrandHeaderRow()
        MainTabSwitcher(current = MainTab.HISTORY) { if (it == MainTab.SETTINGS) onNavigateSettings() }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(stringResource(R.string.history_title), color = PbTextPrimary, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                Text(
                    stringResource(R.string.history_records_count, records.size),
                    color = PbTextMuted,
                    fontSize = 12.sp
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(Color.Transparent, RoundedCornerShape(50))
                        .border(androidx.compose.foundation.BorderStroke(1.dp, PbVioletLight.copy(alpha = 0.5f)), RoundedCornerShape(50))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text(stringResource(R.string.history_retention_badge), color = PbVioletLight, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.width(8.dp))
                IconButton(onClick = { viewModel.clearHistory() }, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Filled.Delete, contentDescription = null, tint = PbTextMuted, modifier = Modifier.size(18.dp))
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        if (records.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.history_records_count, 0), color = PbTextMuted, fontSize = 13.sp)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(records) { record -> HistoryCard(record) }
                item { Spacer(Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
private fun HistoryCard(record: HistoryEntity) {
    val risk = runCatching { RiskLevel.valueOf(record.riskLevel) }.getOrDefault(RiskLevel.UNVERIFIED)
    val color = when (risk) {
        RiskLevel.SAFE -> PbSuccess
        RiskLevel.SUSPICIOUS -> PbWarning
        RiskLevel.DANGEROUS -> PbError
        RiskLevel.UNVERIFIED -> PbTextMuted
    }
    val label = when (risk) {
        RiskLevel.SAFE -> stringResource(R.string.risk_low)
        RiskLevel.SUSPICIOUS -> stringResource(R.string.risk_medium)
        RiskLevel.DANGEROUS -> stringResource(R.string.risk_high)
        RiskLevel.UNVERIFIED -> stringResource(R.string.not_verified)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PbSurface, RoundedCornerShape(14.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(36.dp).background(color.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Shield, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(record.url, color = PbTextPrimary, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(color.copy(alpha = 0.15f), RoundedCornerShape(50))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(label, color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                if (risk == RiskLevel.DANGEROUS || risk == RiskLevel.SUSPICIOUS) {
                    Spacer(Modifier.width(6.dp))
                    Text(
                        stringResource(R.string.history_engines_count, record.maliciousCount + record.suspiciousCount),
                        color = PbTextMuted,
                        fontSize = 10.sp
                    )
                }
            }
        }
        Spacer(Modifier.width(8.dp))
        Text(relativeTime(record.analyzedAtMillis), color = PbTextMuted, fontSize = 10.sp)
    }
}

private fun relativeTime(millis: Long): String {
    val diff = System.currentTimeMillis() - millis
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)
    return when {
        hours < 1 -> "hace unos min"
        hours < 24 -> "hace ${hours}h"
        days == 1L -> "ayer"
        else -> "hace ${days} días"
    }
}
