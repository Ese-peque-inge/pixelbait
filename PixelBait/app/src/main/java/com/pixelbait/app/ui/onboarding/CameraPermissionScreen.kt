package com.pixelbait.app.ui.onboarding

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionScreen(onNext: () -> Unit) {
    val context = LocalContext.current
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PbBlack)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        PixelBaitHeader()
        OnboardingStepper(current = OnboardingStep.CAMERA)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(PbSurface, RoundedCornerShape(20.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier.size(72.dp).background(PbSurfaceElevated, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.PhotoCamera, contentDescription = null, tint = PbVioletLight, modifier = Modifier.size(32.dp))
            }
            Spacer(Modifier.height(20.dp))
            Text(stringResource(R.string.camera_title), color = PbTextPrimary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text(
                stringResource(R.string.camera_description),
                color = PbTextSecondary,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
            Spacer(Modifier.height(20.dp))
            CameraBullet(stringResource(R.string.camera_bullet_1))
            CameraBullet(stringResource(R.string.camera_bullet_2))
            CameraBullet(stringResource(R.string.camera_bullet_3))
            Spacer(Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .background(Color.Transparent, RoundedCornerShape(50))
                    .border(BorderStroke(1.dp, PbVioletLight.copy(alpha = 0.5f)), RoundedCornerShape(50))
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text(
                    stringResource(R.string.camera_required_badge),
                    color = PbVioletLight,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (cameraPermission.status.shouldShowRationale) {
                Spacer(Modifier.height(12.dp))
                Text(
                    stringResource(R.string.permission_denied_message),
                    color = PbWarning,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        PixelBaitPrimaryButton(text = stringResource(R.string.btn_next)) {
            if (cameraPermission.status.isGranted) {
                onNext()
            } else if (cameraPermission.status.shouldShowRationale) {
                // Denegado permanentemente: llevar a Ajustes del sistema.
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                }
                context.startActivity(intent)
            } else {
                cameraPermission.launchPermissionRequest()
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun CameraBullet(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(PbSurfaceElevated, RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(20.dp).background(PbSuccess.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Check, contentDescription = null, tint = PbSuccess, modifier = Modifier.size(12.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(text, color = PbTextSecondary, fontSize = 13.sp)
    }
}
