package com.licenta.postureimprover.data.api.dto.request
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel.Companion.USER_ID
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class CaptureReq(
    val headForward: Float,
    val lordosis: Float,
    val roundedShoulders: Float
)


fun CaptureReq.asCaptureEntity() = CaptureEntity(
    userId = USER_ID,
    headForward = this.headForward,
    lordosis = this.lordosis,
    roundedShoulders = this.roundedShoulders,
    date = LocalDate.now()
)