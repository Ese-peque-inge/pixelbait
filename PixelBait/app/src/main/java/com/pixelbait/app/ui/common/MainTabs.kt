package com.pixelbait.app.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*

enum class MainTab { HISTORY, SETTINGS }

@Composable
fun BrandHeaderRow() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .size(32.dp)
                .background(PbViolet, RoundedCornerShape(9.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Security, contentDescription = null, tint = Color.White, modifier = Modifier.size(17.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(stringResource(R.string.app_name), color = PbTextPrimary, fontWeight = FontWeight.Bold, fontSize = 17.sp)
    }
}

@Composable
fun MainTabSwitcher(current: MainTab, onSelect: (MainTab) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(PbSurfaceElevated, RoundedCornerShape(14.dp))
            .padding(4.dp)
    ) {
        TabButton(
            modifier = Modifier.weight(1f),
            selected = current == MainTab.HISTORY,
            icon = Icons.Filled.History,
            label = stringResource(R.string.tab_history),
            onClick = { onSelect(MainTab.HISTORY) }
        )
        TabButton(
            modifier = Modifier.weight(1f),
            selected = current == MainTab.SETTINGS,
            icon = Icons.Filled.Settings,
            label = stringResource(R.string.tab_settings),
            onClick = { onSelect(MainTab.SETTINGS) }
        )
    }
}

@Composable
private fun TabButton(
    modifier: Modifier,
    selected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    val bg = if (selected) PbViolet else Color.Transparent
    val fg = if (selected) Color.White else PbTextMuted
    Row(
        modifier = modifier
            .clickable(
                interactionSource = androidx.compose.runtime.remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .background(bg, RoundedCornerShape(10.dp))
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = fg, modifier = Modifier.size(15.dp))
        Spacer(Modifier.width(6.dp))
        Text(label, color = fg, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}
