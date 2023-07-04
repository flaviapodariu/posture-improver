package com.licenta.postureimprover.screens

import android.graphics.Typeface
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarEvent
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColor
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.component.header.KalendarTextKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.screens.viewmodels.DashboardViewModel
import com.licenta.postureimprover.theme.AcidYellow
import com.licenta.postureimprover.theme.Orange50
import com.licenta.postureimprover.theme.Purple40
import com.licenta.postureimprover.util.ChartDateEntry
import com.licenta.postureimprover.util.HEAD_FORWARD_NORMAL
import com.licenta.postureimprover.util.ROUNDED_SHOULDERS_NORMAL
import com.licenta.postureimprover.util.axisValueFormatter
import com.licenta.postureimprover.util.rememberMarker
import com.licenta.postureimprover.util.yHFAxisValueOverrider
import com.licenta.postureimprover.util.yRSAxisValueOverrider
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.legend.verticalLegendItem
import com.patrykandpatrick.vico.core.chart.decoration.ThresholdLine
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import kotlinx.datetime.toKotlinLocalDate
import java.time.LocalDate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DashboardScreen(
    nickname: String,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = dashboardViewModel.userCaptures , key2 = context) {
        dashboardViewModel.getUserHistory()
    }

    val chartDataHf =  if(dashboardViewModel.userCaptures != emptyList<CaptureEntity>()) {
        dashboardViewModel.userCaptures.slice(setOf(0,
            dashboardViewModel.userCaptures.size/2,
            dashboardViewModel.userCaptures.size-1)
        ).mapIndexed { idx, value ->
            ChartDateEntry(value.date, idx.toFloat(), value.headForward)
        }
    } else {
        emptyList()
    }

    val chartDataRs =  if(dashboardViewModel.userCaptures != emptyList<CaptureEntity>()) {
        dashboardViewModel.userCaptures.slice(setOf(0,
            dashboardViewModel.userCaptures.size/2,
            dashboardViewModel.userCaptures.size-1)
        ).mapIndexed { idx, value ->
            ChartDateEntry(value.date, idx.toFloat(), value.roundedShoulders)
        }
    } else {
        emptyList()
    }

    val chartEntryModelHf = ChartEntryModelProducer(chartDataHf).getModel()
    val chartEntryModelRs = ChartEntryModelProducer(chartDataRs).getModel()
    val chartEntryModelCombined = ChartEntryModelProducer(chartDataHf,chartDataRs).getModel()

    val lastIndex = chartDataHf.lastIndex
    val marker = rememberMarker()

    var events: List<KalendarEvent> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(key1 = dashboardViewModel.userCaptures) {
        events = dashboardViewModel.userCaptures.map {
            dashboardViewModel.asKalendarEvent(it)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Hello, $nickname!",
                fontWeight = FontWeight.W400,
                fontSize = 23.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        val today = LocalDate.now().toKotlinLocalDate()
        Kalendar(
            currentDay = today,
            kalendarType = KalendarType.Oceanic,
            events = KalendarEvents(events),
            daySelectionMode = DaySelectionMode.Single,
            kalendarHeaderTextKonfig = KalendarTextKonfig(Purple40, 20.sp),
            kalendarColors = KalendarColors(MutableList(12) {KalendarColor(Color.White, Purple40, Orange50)}),
            kalendarDayKonfig = KalendarDayKonfig(50.dp, 14.sp, Color.Black, AcidYellow, Purple40),
            modifier = Modifier
                .fillMaxWidth(fraction = 0.95f)
                .padding(vertical = 15.dp)
                .border(BorderStroke(2.dp, Purple40), shape = RoundedCornerShape(22.dp))
                .align(Alignment.CenterHorizontally),
            onDayClick = { date, event ->
                val filteredByDate = event.filter { it.date == date }
                if(filteredByDate != emptyList<KalendarEvent>()){
                    dashboardViewModel.displayEvents(date, filteredByDate[filteredByDate.size - 1])
                }
                else {
                    lateinit var emptyEvent: KalendarEvent
                    if(date < today) {
                        emptyEvent = KalendarEvent(
                            date = date,
                            eventName = "Skipped day",
                            eventDescription = "Believe in yourself, you can do it!"
                        )
                    }
                    else if(date == today) {
                        emptyEvent =  KalendarEvent(
                            date = date,
                            eventName = "Today",
                            eventDescription = "Get your daily set of exercises."
                        )
                    }
                    else {
                        emptyEvent =  KalendarEvent(
                            date = date,
                            eventName = "Nothing here yet...",
                        )
                    }
                    dashboardViewModel.displayEvents(date, emptyEvent)
                }
            }
        )

        if(dashboardViewModel.userCaptures.isNotEmpty()) {
            AnimatedVisibility(
                visible = dashboardViewModel.displayEvents,
                enter = slideInVertically(),
                exit = slideOutVertically() + fadeOut(),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                EventDetails(
                    dashboardViewModel.dayEventDetails?.eventName?: "",
                    dashboardViewModel.dayEventDetails?.eventDescription?: "",
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.95f)
                        .border(
                            border = BorderStroke(2.dp, Purple40),
                            shape = RoundedCornerShape(22.dp)
                        )
                )
            }

            AnimatedVisibility(
                visible = !dashboardViewModel.displayHfGraphic && !dashboardViewModel.displayRsGraphic && !dashboardViewModel.displayGraphic,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                Column(
                    modifier = Modifier.height(320.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                            .padding(vertical = 10.dp, horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight()
                            .clip(shape = RoundedCornerShape(22.dp))
                            .border(BorderStroke(2.dp, Purple40))
                            .background(color = Purple40)
                            .clickable {
                                dashboardViewModel.displayHfGraphic = true
                            },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text= "Head Forward",
                                fontSize = 19.sp,
                                fontStyle = FontStyle.Italic,
                                color = AcidYellow,
                                modifier = Modifier.wrapContentWidth()
                            )
                        }

                        Column(modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight()
                            .clip(shape = RoundedCornerShape(22.dp))
                            .border(BorderStroke(2.dp, Purple40))
                            .background(color = Purple40)
                            .clickable {
                                dashboardViewModel.displayRsGraphic = true
                            },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Rounded Shoulders",
                                fontSize = 19.sp,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.Center,
                                color = AcidYellow
                            )
                        }
                    }

                    Column(modifier = Modifier
                        .width(340.dp)
                        .fillMaxHeight()
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                        .clip(shape = RoundedCornerShape(22.dp))
                        .border(BorderStroke(2.dp, Purple40))
                        .background(color = Purple40)
                        .clickable {
                            dashboardViewModel.displayGraphic = true
                        },
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Combined progress",
                            fontSize = 19.sp,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            color = AcidYellow
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = dashboardViewModel.displayHfGraphic,
                enter = scaleIn(),
                exit = scaleOut() + fadeOut(),
            ) {

                Spacer(modifier = Modifier.padding(vertical= 15.dp))
                Column(Modifier.fillMaxSize()) {
                    Text(text = "x",
                        modifier= Modifier
                            .align(alignment = Alignment.End)
                            .clickable {
                                dashboardViewModel.displayHfGraphic = false
                            })


                    Chart(
                        chart = lineChart(
                            lines = listOf(lineSpec(Purple40, lineCap = StrokeCap.Round)),
                            decorations= listOf(ThresholdLine(
                                thresholdValue = HEAD_FORWARD_NORMAL,
                                thresholdLabel = "Normal values",
                                lineComponent = ShapeComponent(
                                    shape = DashedShape(gapLengthDp = 7f),
                                    strokeWidthDp = 2f,
                                    strokeColor = android.graphics.Color.parseColor("#0b5212"),
                                    margins = MutableDimensions(1f, 1f)
                                )
                            )
                            ),
                            axisValuesOverrider = yHFAxisValueOverrider,
                            persistentMarkers = remember(marker) {
                                mapOf(chartDataHf[lastIndex].x to marker)
                            }
                        ),
                        marker = marker,
                        model = chartEntryModelHf,
                        startAxis = startAxis(maxLabelCount = 4),
                        bottomAxis = bottomAxis(
                            title = "Date",
                            valueFormatter = axisValueFormatter,
                            labelRotationDegrees = -30f,
                        ),
                        modifier = Modifier.wrapContentHeight()
                    )
                }

            }

            AnimatedVisibility(
                visible = dashboardViewModel.displayRsGraphic,
                enter = scaleIn(),
                exit = slideOutVertically(),
            ) {

                Spacer(modifier = Modifier.padding(vertical= 15.dp))
                Column(Modifier.fillMaxSize()) {
                    Text(text = "x",
                        modifier= Modifier
                            .align(alignment = Alignment.End)
                            .clickable {
                                dashboardViewModel.displayRsGraphic = false
                            })
                    Chart(
                        chart = lineChart(
                            lines = listOf(lineSpec(Purple40)),
                            decorations= listOf(ThresholdLine(
                                thresholdValue = ROUNDED_SHOULDERS_NORMAL,
                                thresholdLabel = "Normal values",
                                lineComponent = ShapeComponent(
                                    shape = DashedShape(gapLengthDp = 7f),
                                    strokeWidthDp = 2f,
                                    strokeColor = android.graphics.Color.parseColor("#0b5212"),
                                    margins = MutableDimensions(1f, 1f)
                                )
                            )
                            ),
                            axisValuesOverrider = yRSAxisValueOverrider,
                            persistentMarkers = remember(marker) {
                                mapOf(chartDataRs[lastIndex].x to marker)
                            }
                        ),
                        model = chartEntryModelRs,
                        startAxis = startAxis(maxLabelCount = 4),
                        bottomAxis = bottomAxis(
                            title = "Date",
                            valueFormatter = axisValueFormatter,
                            labelRotationDegrees = -30f,
                        ),
                        marker = marker,
                        modifier = Modifier.wrapContentHeight()
                    )
                }

            }

            AnimatedVisibility(
                visible = dashboardViewModel.displayGraphic,
                enter = scaleIn(),
                exit = slideOutVertically(),
            ) {

                Spacer(modifier = Modifier.padding(vertical= 15.dp))
                Column(Modifier.fillMaxSize()) {
                    Text(text = "x",
                        modifier= Modifier
                            .align(alignment = Alignment.End)
                            .clickable {
                                dashboardViewModel.displayGraphic = false
                            })
                    Chart(
                        chart = lineChart(
                            lines = listOf(
                                LineChart.LineSpec(
                                    lineColor = android.graphics.Color.BLUE
                                ),
                                LineChart.LineSpec(
                                    lineColor = android.graphics.Color.RED
                                )
                            ),
                            decorations = listOf(
                                ThresholdLine(
                                    thresholdValue = ROUNDED_SHOULDERS_NORMAL,
                                    thresholdLabel = "",
                                    lineComponent = ShapeComponent(
                                        shape = DashedShape(gapLengthDp = 7f),
                                        strokeWidthDp = 2f,
                                        strokeColor = android.graphics.Color.RED,
                                        margins = MutableDimensions(1f, 1f)
                                    )
                                ),
                                ThresholdLine(
                                    thresholdValue = HEAD_FORWARD_NORMAL,
                                    thresholdLabel = "",
                                    lineComponent = ShapeComponent(
                                        shape = DashedShape(gapLengthDp = 7f),
                                        strokeWidthDp = 2f,
                                        strokeColor = android.graphics.Color.BLUE,
                                        margins = MutableDimensions(1f, 1f)
                                    )
                                )
                            ),
                            axisValuesOverrider = yHFAxisValueOverrider
                        ),
                        model = chartEntryModelCombined,
                        startAxis = startAxis(maxLabelCount = 4),
                        bottomAxis = bottomAxis(
                            title = "Date",
                            valueFormatter = axisValueFormatter,
                            labelRotationDegrees = -30f,
                        ),
                        legend = verticalLegend(
                            items = listOf(
                                verticalLegendItem(
                                    icon = shapeComponent(
                                        shape = Shapes.pillShape,
                                        color = Color.Blue
                                    ),
                                    label = textComponent(
                                        color = Color.Black,
                                        typeface = Typeface.MONOSPACE,
                                        textSize = 15.sp,
                                    ),
                                    labelText = "Head forward values"
                                ),
                                verticalLegendItem(
                                    icon = shapeComponent(
                                        shape = Shapes.pillShape,
                                        color = Color.Red
                                    ),
                                    label = textComponent(
                                        color = Color.Black,
                                        typeface = Typeface.MONOSPACE,
                                        textSize = 15.sp,
                                    ),
                                    labelText = "Rounded Shoulders values"
                                )
                            ),
                            iconSize = 10.dp,
                            iconPadding = 10.dp,
                            spacing = 7.dp,
                            padding = MutableDimensions(horizontalDp = 0f, verticalDp = 2f)
                        ),
                        modifier = Modifier.fillMaxHeight(0.75f)
                    )


                }

            }

        }




    }

}

@Composable
fun EventDetails(title: String, details: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp),
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
            )
            Text(
                text = details,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
            )
        }

    }
}



