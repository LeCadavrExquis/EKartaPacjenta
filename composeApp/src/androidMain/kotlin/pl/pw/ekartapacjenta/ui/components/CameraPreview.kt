package pl.pw.ekartapacjenta.ui.components

import android.Manifest
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import me.dm7.barcodescanner.zxing.ZXingScannerView.ResultHandler
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@androidx.annotation.OptIn(ExperimentalGetImage::class)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    scannerView: ZXingScannerView,
    onScan: (code: String) -> Unit
) {
    DisposableEffect(Unit) {
        // To run onDispose when the AndroidView composable is removed from the hierarchy
        onDispose {
            scannerView.stopCamera()
        }
    }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(cameraPermissionState) {
        if (cameraPermissionState.status.isGranted) {
            scannerView.startCamera()
        } else {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        AndroidView(
            factory = { context ->
                scannerView.apply {
                    this.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    this.setResultHandler { rawResult: Result ->
                        // Do something with the result here
                        Log.v("SOME", rawResult.text) // Prints scan results
                        Log.v("SOME", rawResult.barcodeFormat.name) // Prints the scan format (qrcode, pdf417 etc.)
                        onScan(rawResult.text)
                    }
                }
            }
        )
    }
}

suspend fun Context.getCameraProvider(): ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { future ->
        future.addListener({
            continuation.resume(future.get())
        }, executor)
    }
}

val Context.executor: Executor
    get() = ContextCompat.getMainExecutor(this)