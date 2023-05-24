package com.licenta.postureimprover.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.licenta.postureimprover.screens.viewmodels.DashboardViewModel
import com.licenta.postureimprover.theme.AcidYellow
import com.licenta.postureimprover.theme.Purple40
import com.licenta.postureimprover.util.ChartDateEntry
import com.licenta.postureimprover.util.axisValueFormatter
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import kotlinx.datetime.toKotlinLocalDate
import java.time.LocalDate

@Composable
fun DashboardScreen(
    nickname: String,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
    ) {

    val context = LocalContext.current
    LaunchedEffect(key1 = dashboardViewModel.userCaptures , key2 = context) {
       dashboardViewModel.getUserHistory()
    }
    val chartData = dashboardViewModel.userCaptures.mapIndexed { idx, value ->
       ChartDateEntry(value.date, idx.toFloat(), value.headForward)
    }

    val chartEntryModel = ChartEntryModelProducer(chartData).getModel()


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

        Chart(
            chart = lineChart(),
            model = chartEntryModel,
            startAxis = startAxis(),
            bottomAxis = bottomAxis(
                title = "Date",
                valueFormatter = axisValueFormatter,
                labelRotationDegrees = -50f
            )
        )
        
        Spacer(modifier = Modifier.padding(vertical= 15.dp))

        if(dashboardViewModel.userCaptures.isNotEmpty()) {
            val events = dashboardViewModel.userCaptures.map {
                KalendarEvent(
                    date= it.date.toKotlinLocalDate(),
                    eventName = "Posture Scan",
                    eventDescription = "Head forward was ${it.headForward}" )
            }
            val today = LocalDate.now().toKotlinLocalDate()
            Kalendar(
                currentDay = today,
                kalendarType = KalendarType.Oceanic,
                events = KalendarEvents(events),
                daySelectionMode = DaySelectionMode.Single,
                kalendarHeaderTextKonfig = KalendarTextKonfig(Purple40, 20.sp),
                kalendarColors = KalendarColors(MutableList(12) {KalendarColor(Color.White, Purple40, AcidYellow)}),
                kalendarDayKonfig = KalendarDayKonfig(50.dp, 14.sp, Color.Black, AcidYellow, Purple40),
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.95f)
                    .padding(vertical = 15.dp)
                    .border(BorderStroke(2.dp, Purple40), shape = RoundedCornerShape(22.dp))
                    .align(Alignment.CenterHorizontally),
                onDayClick = { date, event ->
                    if(event.filter { it.date == date } != emptyList<KalendarEvent>()){
                        dashboardViewModel.displayEvents(date, event[0])
                    }
                    else {
                        lateinit var emptyEvent: KalendarEvent
                        if(date < today) {
                            emptyEvent = KalendarEvent(
                                date = date,
                                eventName = "Skipped day",
                                eventDescription = "Believe in yourself ,you can do it!"
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
                textAlign = TextAlign.Center
            )
            Text(
                text = details,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}









