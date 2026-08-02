package com.pixelbait.app.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*
import com.pixelbait.app.ui.common.BrandHeaderRow
import com.pixelbait.app.ui.common.MainTab
import com.pixelbait.app.ui.common.MainTabSwitcher

@Composable
fun SettingsScreen(
    onNavigateHistory: () -> Unit,
    onOpenTerms: () -> Unit,
    onUpdateApiKey: () -> Unit,
    onClearHistory: () -> Unit,
    onOpenOfficialSite: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PbBlack)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        BrandHeaderRow()
        MainTabSwitcher(current = MainTab.SETTINGS) { if (it == MainTab.HISTORY) onNavigateHistory() }

        Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {

            // Card de identidad
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PbSurface, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(48.dp).background(PbViolet, RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Filled.Security, contentDescription = null, tint = Color.White, modifier = Modifier.size(24.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(stringResource(R.string.app_name), color = PbTextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(stringResource(R.string.settings_version), color = PbTextMuted, fontSize = 11.sp)
                    Spacer(Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .background(PbSuccess.copy(alpha = 0.15f), RoundedCornerShape(50))
                            .padding(horizontal = 8.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(PbSuccess, CircleShape))
                        Spacer(Modifier.width(5.dp))
                        Text(stringResource(R.string.settings_license_valid), color = PbSuccess, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            SectionLabel(stringResource(R.string.settings_appearance))
            Spacer(Modifier.height(8.dp))

            SettingsRow(
                icon = Icons.Filled.Palette,
                title = stringResource(R.string.settings_brand_palette),
                subtitle = stringResource(R.string.settings_brand_palette_desc),
                trailing = {
                    Box(modifier = Modifier.size(18.dp).background(PbViolet, CircleShape))
                }
            )

            var darkThemeOn by remember { mutableStateOf(true) }
            SettingsRow(
                icon = Icons.Filled.DarkMode,
                title = stringResource(R.string.settings_dark_theme),
                subtitle = stringResource(R.string.settings_dark_theme_desc),
                trailing = {
                    Switch(
                        checked = darkThemeOn,
                        onCheckedChange = { darkThemeOn = it },
                        colors = SwitchDefaults.colors(checkedTrackColor = PbViolet)
                    )
                }
            )

            Spacer(Modifier.height(20.dp))
            SectionLabel(stringResource(R.string.settings_legal_support))
            Spacer(Modifier.height(8.dp))

            SettingsNavRow(
                icon = Icons.Filled.Description,
                title = stringResource(R.string.settings_terms),
                subtitle = stringResource(R.string.settings_terms_desc),
                onClick = onOpenTerms
            )
            SettingsNavRow(
                icon = Icons.Filled.Key,
                title = stringResource(R.string.settings_update_apikey),
                subtitle = stringResource(R.string.settings_update_apikey_desc),
                onClick = onUpdateApiKey
            )
            SettingsNavRow(
                icon = Icons.Filled.Refresh,
                title = stringResource(R.string.settings_clear_history),
                subtitle = stringResource(R.string.settings_clear_history_desc),
                onClick = onClearHistory
            )
            SettingsNavRow(
                icon = Icons.Filled.Language,
                title = stringResource(R.string.settings_official_site),
                subtitle = null,
                onClick = onOpenOfficialSite
            )

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(text, color = PbVioletLight, fontSize = 11.sp, fontWeight = FontWeight.Bold)
}

@Composable
private fun SettingsRow(icon: ImageVector, title: String, subtitle: String, trailing: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(PbSurface, RoundedCornerShape(14.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(34.dp).background(PbSurfaceElevated, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = PbVioletLight, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = PbTextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = PbTextMuted, fontSize = 11.sp)
        }
        trailing()
    }
}

@Composable
private fun SettingsNavRow(icon: ImageVector, title: String, subtitle: String?, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(PbSurface, RoundedCornerShape(14.dp))
            .clickableRow(onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(34.dp).background(PbSurfaceElevated, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = PbVioletLight, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = PbTextPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            if (subtitle != null) Text(subtitle, color = PbTextMuted, fontSize = 11.sp)
        }
        Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = PbTextMuted, modifier = Modifier.size(18.dp))
    }
}

@Composable
private fun Modifier.clickableRow(onClick: () -> Unit): Modifier = this.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)
