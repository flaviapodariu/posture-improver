package com.licenta.postureimprover.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.licenta.postureimprover.data.local.entities.CaptureEntity
import com.licenta.postureimprover.screens.viewmodels.DashboardViewModel
import com.licenta.postureimprover.util.ChartDateEntry
import com.licenta.postureimprover.util.axisValueFormatter
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import org.jetbrains.kotlinx.serialization.compiler.backend.jvm.FLOAT
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
        modifier = Modifier.fillMaxSize().padding(5.dp)
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

        if(dashboardViewModel.userCaptures.isNotEmpty()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp)
            ) {
                CapturesList(captures = dashboardViewModel.userCaptures)

            }
        }
        else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp)
            ) {
                Text(text = "Nothing here yet!")
            }
        }
    }

}

@Composable
fun CapturesList(captures: List<CaptureEntity>) {

    captures.forEach{
            Text(text =" ${ it.headForward }")

    }

}










