package com.pixelbait.app.ui.monitor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*

/**
 * ATENCIÓN — FUERA DE ALCANCE FUNCIONAL EN ESTA FASE.
 *
 * El documento de requerimientos (sección 3.2) define el monitoreo de
 * navegación posterior al acceso (WebView) como una ampliación para fases
 * futuras. Esta pantalla existe únicamente como referencia visual (UI-only),
 * replicando el mockup de Figma, pero NO implementa:
 *   - Interceptación real de tráfico WebView.
 *   - Detección de anomalías (permisos, geolocalización, etc.).
 *   - Lista negra local persistente.
 *   - Bloqueo real de descargas/cámara/ubicación.
 *
 * TODO: fuera de alcance en esta fase — ver sección 3.2 del documento de
 * requerimientos. Implementar la lógica real cuando se apruebe esta
 * ampliación en una fase futura.
 */
@Composable
fun MonitorStubScreen(url: String, onExitSite: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(PbBlack)) {

        // Barra de navegación simulada
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PbSurfaceElevated, RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Warning, contentDescription = null, tint = PbError, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text(url, color = PbError, fontSize = 11.sp, maxLines = 1)
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PbError.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Text(
                    stringResource(R.string.monitor_unsafe_site),
                    color = PbError,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(Modifier.weight(1f))

        // Modal central de bloqueo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(PbSurface, RoundedCornerShape(20.dp))
                .border(1.dp, PbError.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
                .padding(18.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.NotificationsActive, contentDescription = null, tint = PbError, modifier = Modifier.size(22.dp))
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.monitor_blocked_title), color = PbError, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text(stringResource(R.string.monitor_blocked_subtitle), color = PbTextMuted, fontSize = 11.sp)
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(stringResource(R.string.monitor_anomaly_body), color = PbTextSecondary, fontSize = 12.sp, lineHeight = 17.sp)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .background(PbError.copy(alpha = 0.12f), RoundedCornerShape(50))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Lock, contentDescription = null, tint = PbError, modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(6.dp))
                Text(stringResource(R.string.monitor_blacklist_badge), color = PbError, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(
                    onClick = onExitSite,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = PbError)
                ) { Text(stringResource(R.string.monitor_exit_site), fontSize = 12.sp) }
                OutlinedButton(
                    onClick = { /* TODO: fuera de alcance en esta fase */ },
                    enabled = false,
                    modifier = Modifier.weight(1f)
                ) { Text(stringResource(R.string.monitor_continue_risk), fontSize = 12.sp) }
            }
        }

        // Panel inferior de monitor de seguridad
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(stringResource(R.string.monitor_panel_title), color = PbVioletLight, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text(stringResource(R.string.status_active), color = PbSuccess, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                MonitorStatusCard(Icons.Filled.Download, stringResource(R.string.monitor_downloads), stringResource(R.string.monitor_downloads_status), PbSuccess, Modifier.weight(1f))
                MonitorStatusCard(Icons.Filled.PhotoCamera, stringResource(R.string.monitor_camera), stringResource(R.string.monitor_camera_status), PbSuccess, Modifier.weight(1f))
                MonitorStatusCard(Icons.Filled.LocationOn, stringResource(R.string.monitor_location), stringResource(R.string.monitor_location_status), PbError, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun MonitorStatusCard(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, status: String, color: androidx.compose.ui.graphics.Color, modifier: Modifier) {
    Column(
        modifier = modifier
            .background(PbSurface, RoundedCornerShape(12.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(16.dp))
        Spacer(Modifier.height(4.dp))
        Text(title, color = PbTextSecondary, fontSize = 10.sp)
        Text(status, color = color, fontSize = 9.sp, fontWeight = FontWeight.Bold)
    }
}
