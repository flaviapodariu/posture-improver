package com.licenta.postureimprover.screens

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.licenta.postureimprover.screens.viewmodels.CameraViewModel
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

    if(permissions.status.isGranted){
        val context = LocalContext.current
        CameraView(
            cameraViewModel.provider,
            cameraViewModel.selector,
            cameraViewModel.preview,
            cameraViewModel.getImageAnalysis(context),
            context,
            LocalLifecycleOwner.current,
        )
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












