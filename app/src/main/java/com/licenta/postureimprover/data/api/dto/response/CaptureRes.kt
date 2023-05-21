package com.licenta.postureimprover.data.api.dto.response

import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.screens.viewmodels.AuthenticationViewModel.Companion.USER_ID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Serializable
data class CaptureRes(
    val id: Int,
    val userId: Int,
    val headForward: Float,
    val lordosis: Float,
    val roundedShoulders: Float,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate
)

fun CaptureRes.asEntity() = CaptureEntity(
    id = id,
    userId = USER_ID,
    headForward = headForward,
    lordosis = lordosis,
    roundedShoulders = roundedShoulders,
    date = date
)


object LocalDateSerializer : KSerializer<LocalDate> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val result = value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        encoder.encodeString(result)
    }

    override fun deserialize(decoder: Decoder): LocalDate {
        return LocalDate.parse(decoder.decodeString())
    }
}