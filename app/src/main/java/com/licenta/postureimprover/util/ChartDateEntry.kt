package com.licenta.postureimprover.util

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntry
import java.time.LocalDate

class ChartDateEntry(
    val date: LocalDate,
    override val x: Float,
    override val y: Float,

    ) : ChartEntry {

    override fun withY(y: Float) = ChartDateEntry(date, x, y)
}

val axisValueFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, chartValues ->
    (chartValues.chartEntryModel.entries.first().getOrNull(value.toInt()) as? ChartDateEntry)
        ?.date
        ?.run { "$dayOfMonth/$monthValue" }
        .orEmpty()
}

val yHFAxisValueOverrider = AxisValuesOverrider.fixed(maxY = HEAD_FORWARD_NORMAL + 20)
val yRSAxisValueOverrider = AxisValuesOverrider.fixed(maxY = ROUNDED_SHOULDERS_NORMAL + 20)
