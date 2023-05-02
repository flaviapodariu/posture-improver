package com.licenta.postureimprover.screens


import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.pose.PoseLandmark
import com.licenta.postureimprover.R
import com.licenta.postureimprover.data.api.dto.request.CaptureReq
import com.licenta.postureimprover.data.util.Task
import com.licenta.postureimprover.screens.components.CameraTimer
import com.licenta.postureimprover.screens.components.CommonDialog
import com.licenta.postureimprover.screens.components.TimerOptions
import com.licenta.postureimprover.screens.viewmodels.CameraViewModel
import com.licenta.postureimprover.theme.AcidYellow
import com.licenta.postureimprover.theme.CameraShutter
import com.licenta.postureimprover.theme.Orange50
import com.licenta.postureimprover.theme.Purple40
import com.licenta.postureimprover.theme.Purple80
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import timber.log.Timber

@ExperimentalPermissionsApi
@Composable
fun CameraScreen(
    reopenCamera: () -> Unit,
    goToWorkouts: () -> Unit,
    cameraViewModel: CameraViewModel = hiltViewModel()
) {

    val permissions = rememberPermissionState(
        permission = android.Manifest.permission.CAMERA,
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
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

    val context = LocalContext.current
    var landmarks: List<PoseLandmark>? by remember {
        mutableStateOf(null)
    }
    var capture: CaptureReq? = null
    val imageAnalysis = cameraViewModel.getImageAnalysis(
        context,
        getLandmarks = { list ->  landmarks = list },
        getPostureCapture = { postureCapture -> capture = postureCapture }
    )
    val backLens = CameraSelector.LENS_FACING_BACK

    if(permissions.status.isGranted){
        Box(modifier = Modifier.fillMaxSize()){
            CameraView(
                cameraViewModel.provider,
                cameraViewModel.selector,
                cameraViewModel.preview,
                imageAnalysis,
                context,
                LocalLifecycleOwner.current
            )
            Canvas(modifier = Modifier.fillMaxSize()) {
                if(landmarks == null) {
                    drawRect(color = Color.Transparent, topLeft = Offset(0f, 0f))
                }
                landmarks?.let {
                    Timber.tag("points").d("${it[0].position.x} ${it[0].position.y}")
                    val shX = (it[12].position.x + it[11].position.x)/2
                    val shY = (it[12].position.y + it[11].position.y)/2

                    val c7X = shX - (it[0].position.x - (it[7].position.x + it[8].position.x)/2) /2
                    val c7Y = (it[0].position.y + shY)/2

                    val earsX = (it[7].position.x + it[8].position.x)/2
                    val earsY = (it[7].position.y + it[8].position.y)/2

                    val kneesX = (it[25].position.x + it[26].position.x)/2
                    val kneesY = (it[25].position.y + it[26].position.y)/2

                    val brush = Brush.linearGradient(listOf(Orange50, Orange50))
                    val brushDistance = Brush.linearGradient(listOf(Purple80, Purple80))
                    val offsets = listOf(
                        Offset(1080 - it[12].position.x, it[12].position.y - 80),
                        Offset(1080 - it[11].position.x, it[11].position.y - 80),
                        Offset(1080 - c7X , c7Y - 80),
                        Offset(1080 - earsX, earsY - 80)
                    )
                    drawCircle(brush, radius= 5f, center= offsets[2])
                    drawPoints(
                        offsets,
                        PointMode.Lines,
                        brush,
                        strokeWidth = 10.0f
                    )
                    drawLine(brush, offsets[0], Offset(1080 - it[0].position.x, it[0].position.y - 80), strokeWidth = 10.0f)
                    drawLine(brush, offsets[0], Offset(1080 - it[24].position.x, it[24].position.y - 80), strokeWidth = 10.0f)
                    drawLine(brushDistance, offsets[3], Offset(1080 - kneesX, kneesY - 80), strokeWidth = 13f)
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Purple40),

                ) {
                    if(!cameraViewModel.settingTimerOption) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 50.dp, vertical = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_cameraswitch_40),
                                contentDescription = "camera selector",
                                modifier = Modifier.clickable {
                                    landmarks = null
                                    cameraViewModel.changeSelector()
                                    },
                                tint = Color.White
                            )
                            CameraShutter { cameraViewModel.shutterPressed() }
                            Icon(
                                painter = painterResource(id = cameraViewModel.timerIcon),
                                contentDescription = "timer selector",
                                modifier = Modifier.clickable {
                                    cameraViewModel.onClickTimerIcon()
                                },
                                tint = if(cameraViewModel.lens == backLens) Color.Gray else AcidYellow
                            )

                        }
                    }
                    else {
                        TimerOptions(
                            { cameraViewModel.toggleTimerOptionsVisibility() },
                            { cameraViewModel.selectTimerSeconds(it) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
            }


            runBlocking {
                delay(100)
            }
        if(cameraViewModel.timerRuns) {
            CameraTimer(
                seconds = cameraViewModel.timerSeconds,
                isTimerRunning = { runs -> cameraViewModel.timerRuns = runs }
            )
        }
        if((cameraViewModel.lens == backLens && cameraViewModel.shutterFired) ||
            (cameraViewModel.lens != backLens && !cameraViewModel.timerRuns)) {
            LaunchedEffect(key1 = capture) {
                capture?.let {
//                    imageAnalysis.clearAnalyzer()
//                    Timber.tag("capturez").d("${it.lordosis}, ${it.headForward},  ${it.roundedShoulders}")
                    cameraViewModel.sendPosture(it)?.let { res ->
                        when(res) {
                            is Task.Success -> {
                                cameraViewModel.isErrorDialogShowing = false
                                goToWorkouts()
                            }

                            is Task.Failure -> {
                                cameraViewModel.isErrorDialogShowing = true
                            }
                            else -> Unit
                        }
                    }

                }
            }

        }
        }

        if(cameraViewModel.isErrorDialogShowing) {
            CommonDialog(
                onClickAction = reopenCamera,
                dialogHeading = "Could not capture posture",
                dialogText = "Something went wrong! Please try again!",
                buttonText = "Retry"
            )
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
    LaunchedEffect(key1 = selector , block = {
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






