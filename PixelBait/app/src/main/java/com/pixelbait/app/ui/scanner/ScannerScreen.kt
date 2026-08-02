package com.pixelbait.app.ui.scanner

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelbait.app.R
import com.pixelbait.app.core.theme.*
import com.pixelbait.app.ui.result.ResultOverlay
import java.util.concurrent.Executors

@Composable
fun ScannerScreen(
    viewModel: ScannerViewModel = hiltViewModel(),
    onNavigateHistory: () -> Unit,
    onNavigateSettings: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    Box(modifier = Modifier.fillMaxSize().background(PbBlack)) {

        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)
                val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    val analysis = androidx.camera.core.ImageAnalysis.Builder()
                        .setTargetResolution(Size(1280, 720))
                        .setBackpressureStrategy(androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .also {
                            it.setAnalyzer(cameraExecutor, QrAnalyzer { qr ->
                                viewModel.onQrDetected(qr)
                            })
                        }
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            CameraSelector.DEFAULT_BACK_CAMERA,
                            preview,
                            analysis
                        )
                    } catch (_: Exception) { /* cámara no disponible en este dispositivo/preview */ }
                }, ContextCompat.getMainExecutor(ctx))
                previewView
            }
        )

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(Color.Black.copy(alpha = 0.55f))
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.scanner_title), color = PbTextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            StatusPill()
        }

        // Marco de escaneo + hint
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScannerFrame()
            Spacer(Modifier.height(20.dp))
            Text(stringResource(R.string.scanner_hint), color = PbVioletLight, fontSize = 14.sp)
        }

        // Overlay de resultado / estados de verificación
        when (val state = uiState) {
            is ScanUiState.Verifying -> VerifyingBanner(slow = state.slow)
            is ScanUiState.Result -> ResultOverlay(result = state.result, onDismiss = viewModel::dismiss)
            ScanUiState.Unverified -> UnverifiedBanner(onDismiss = viewModel::dismiss)
            ScanUiState.QuotaExceeded -> QuotaBanner(onDismiss = viewModel::dismiss)
            else -> {}
        }
    }
}

@Composable
private fun StatusPill() {
    Row(
        modifier = Modifier
            .background(PbSuccess.copy(alpha = 0.15f), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(6.dp).background(PbSuccess, androidx.compose.foundation.shape.CircleShape))
        Spacer(Modifier.width(6.dp))
        Text(stringResource(R.string.status_active), color = PbSuccess, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ScannerFrame() {
    val transition = rememberInfiniteTransition(label = "scanline")
    val lineProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1800, easing = LinearEasing), RepeatMode.Reverse),
        label = "lineProgress"
    )
    val frameSize = 220.dp

    Box(modifier = Modifier.size(frameSize)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cornerLen = size.width * 0.18f
            val stroke = 5f
            val color = PbViolet

            // Esquina superior izquierda
            drawLine(color, Offset(0f, 0f), Offset(cornerLen, 0f), stroke)
            drawLine(color, Offset(0f, 0f), Offset(0f, cornerLen), stroke)
            // Superior derecha
            drawLine(color, Offset(size.width, 0f), Offset(size.width - cornerLen, 0f), stroke)
            drawLine(color, Offset(size.width, 0f), Offset(size.width, cornerLen), stroke)
            // Inferior izquierda
            drawLine(color, Offset(0f, size.height), Offset(cornerLen, size.height), stroke)
            drawLine(color, Offset(0f, size.height), Offset(0f, size.height - cornerLen), stroke)
            // Inferior derecha
            drawLine(color, Offset(size.width, size.height), Offset(size.width - cornerLen, size.height), stroke)
            drawLine(color, Offset(size.width, size.height), Offset(size.width, size.height - cornerLen), stroke)

            // Línea de escaneo animada
            val y = size.height * lineProgress
            drawLine(Color.Cyan.copy(alpha = 0.85f), Offset(8f, y), Offset(size.width - 8f, y), 3f)
        }
    }
}

@Composable
private fun BoxScope.VerifyingBanner(slow: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(PbSurfaceElevated, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(color = PbViolet, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
        Spacer(Modifier.width(12.dp))
        Text(
            stringResource(R.string.checking_message),
            color = if (slow) PbWarning else PbTextSecondary,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun BoxScope.UnverifiedBanner(onDismiss: () -> Unit) {
    StatusBanner(
        message = stringResource(R.string.not_verified),
        color = PbWarning,
        onDismiss = onDismiss
    )
}

@Composable
private fun BoxScope.QuotaBanner(onDismiss: () -> Unit) {
    StatusBanner(
        message = stringResource(R.string.quota_reached),
        color = PbError,
        onDismiss = onDismiss
    )
}

@Composable
private fun BoxScope.StatusBanner(message: String, color: Color, onDismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .background(PbSurfaceElevated, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(20.dp)
    ) {
        Text(message, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(Modifier.height(10.dp))
        TextButton(onClick = onDismiss) {
            Text("OK", color = PbVioletLight)
        }
    }
}
