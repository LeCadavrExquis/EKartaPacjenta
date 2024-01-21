package pl.pw.ekartapacjenta.ui.components

import DummyData
import DummyDataEKG
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ekgDummyData
import model.EKGMeasurement
import model.MorfMeasurement
import model.TemperatureMeasurement
import java.sql.Time
import java.util.Calendar
import java.util.Date

@Composable
fun ResultsView(
    temperatureResults: List<TemperatureMeasurement>,
    EKGResults: List<EKGMeasurement>,
    morfResults: List<MorfMeasurement>
) {
    val calendar = Calendar.getInstance()
    val temperatureTimeData = temperatureResults.map { result ->
        calendar.time = Date(result.dateMs)
        calendar.get(Calendar.HOUR_OF_DAY).toFloat()
    }
    val temperatureValueData = temperatureResults.map { result ->
        result.value.toFloat()
    }

    class MainActivity : ComponentActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                EKGScreen(ekaData = ekgDummyData.ekgMeasurement1)
            }
        }
    }

    @Composable
    fun EKGChart(ekgData: List<DummyDataEKG>){
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.surface),
            onDraw = {
                drawLineChart(ekgData, this)
            }
        )
    }

    @Composable
    fun EKGScreen(ekgData: List<DummyDataEKG>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text("Pomiar EKG")},
                    backgroundColor = Color.Gray
                )
            },
            content = {
                Column (
                    Divider(
                        modifier = Modifier
                          .fillMaxSize()
                          .background(MaterialTheme.colorScheme.background)
                          .padding(16.dp)
                     ){
                        EKGChart(ekgData = ekgData)
                     }
                )
            }
        )
    }



    @Composable
    fun CanvaScope.drawLineChart(data: List<DummyDataEKG>, canvas: CanvasDrawScope){
        val maxValue = data.maxOf {it.voltage}
        val minValue = data.minOf {it.voltage}

        val xStep = size.width / data.size.toFloat()
        val yStep = size.height / (maxValue - minValue)

        val path = Patch()

        data.forEachIndexed { index, point ->
            val x = intex * xStep
            val y = yStep * (point.voltage - minValue)
            if (index == 0){
                path.moveTo(x, size.height - y)
            }else {
                path.lineTo(x, size.height - y)
            }
        }

        canvas.drawPath(
            path = path,
            color = color.Red,
            style = Stroke(width = 2f)
        )

        }
    }


    val htValueData = morfResults.map { it.htValue.toFloat() }
    val hbValueData = morfResults.map { it.hbValue.toFloat() }
    val mcvValueData = morfResults.map { it.mcvValue.toFloat() }
    val mchcValueData = morfResults.map { it.mchcValue.toFloat() }


    LazyColumn {
        item {
            Card {
                Column {
                    Text("Pomiar temperatury")
                    Divider(modifier = Modifier.padding(4.dp))
                    ChartDisplay(
                        yData = temperatureTimeData,
                        xData = temperatureValueData
                    )
                }
            }
        }
        item {
            //TODO dodać ekran wyników badań krwi
            Card {
                Column {
                    Text("Morfologia")
                    Divider(modifier = Modifier.padding(4.dp))
                    Text("Ht: $htValueData")
                    Text("Hb: $hbValueData")
                    Text("MCV: $mcvValueData")
                    Text("MCHC: $mchcValueData")
                }
            }
        }
        item {
            //TODO dodać ekran wyników badań ekg
            Card {
                Column {
                    Text("Pomiar EKG")
                    Divider(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp)
                    )

                }
            }

        }
    }
}

@Preview
@Composable
fun ResultsViewPreview() {
    ResultsView(
        temperatureResults = DummyData.temperatureMeasurements1
    )
}