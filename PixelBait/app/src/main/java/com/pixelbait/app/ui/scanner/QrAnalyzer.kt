package com.pixelbait.app.ui.scanner

import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

/**
 * ImageAnalysis.Analyzer que detecta códigos QR usando ML Kit.
 * Todo el procesamiento ocurre en el dispositivo (Requerimiento 3.1: "Procesamiento 100% local").
 */
class QrAnalyzer(
    private val onQrDetected: (String) -> Unit
) : ImageAnalysis.Analyzer {

    private val scanner = BarcodeScanning.getClient()
    private var isProcessing = false

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage == null || isProcessing) {
            imageProxy.close()
            return
        }
        isProcessing = true
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                val qr = barcodes.firstOrNull { it.format == Barcode.FORMAT_QR_CODE }
                val value = qr?.rawValue
                if (!value.isNullOrBlank()) {
                    onQrDetected(value)
                }
            }
            .addOnCompleteListener {
                isProcessing = false
                imageProxy.close()
            }
    }
}
