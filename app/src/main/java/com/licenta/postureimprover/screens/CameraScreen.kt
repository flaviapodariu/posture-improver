package com.licenta.postureimprover.screens


import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.screens.viewmodels.CameraViewModel
import com.licenta.postureimprover.ui.theme.Orange50
import timber.log.Timber

@ExperimentalPermissionsApi
@Composable
fun CameraScreen(cameraViewModel: CameraViewModel = hiltViewModel()) {
    val permissions = rememberPermissionState(
        permission = android.Manifest.permission.CAMERA
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect ={
        val observer = LifecycleEventObserver { _, event ->
            if(event == Lifecycle.Event.ON_RESUME){
                permissions.launchPermissionRequest()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    } )

    var landmarks: List<PoseLandmark>? = null
    if(permissions.status.isGranted){
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize()){

            CameraView(
                cameraViewModel.provider,
                cameraViewModel.selector,
                cameraViewModel.preview,
                cameraViewModel.getImageAnalysis(context, getLandmarks = { list ->  landmarks = list}),
                context,
                LocalLifecycleOwner.current,
            )



            Canvas(modifier = Modifier.fillMaxSize()) {
                landmarks?.let {
                    Timber.tag("points").d("${it[0].position.x} ${it[0].position.y}")

                    val brush = Brush.linearGradient(listOf(Orange50, Orange50))
                    val offsets = listOf(
                        Offset(1080 - it[12].position.x, it[12].position.y),
                        Offset(1080 - it[11].position.x, it[11].position.y)
                    )
                    drawPoints(
                        offsets,
                        PointMode.Lines,
                        brush,
                        strokeWidth = 10.0f
                    )
                    drawLine(brush, offsets[0], Offset(1080 - it[0].position.x, it[0].position.y), strokeWidth = 10.0f)
                    drawLine(brush, offsets[0], Offset(1080 - it[24].position.x, it[24].position.y), strokeWidth = 10.0f)
                }


            }
        }

    }

}

@Composable
fun CameraView(
    provider: ProcessCameraProvider,
    selector: CameraSelector,
    preview: Preview,
    imageAnalysis: ImageAnalysis,
    context: Context,
    lifecycleOwner: LifecycleOwner,

) {
    val previewView = remember {
        PreviewView(context).apply {
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
        }
    }


//    only recompose if selector is changed (from back to front facing camera)
    LaunchedEffect(key1 = selector , block ={
        try{

            provider.unbindAll()
            provider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis

            )
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
        catch (e: Exception){
            Timber.tag("Camera").d("Could not bind camera to lifecycle, ${e.stackTrace}")
        }
    } )
    Box(contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()){

        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )
    }

}









