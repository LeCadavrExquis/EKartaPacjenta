package pl.pw.ekartapacjenta.ui.components

import DummyData
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import model.TemperatureMeasurement
import java.util.Calendar
import java.util.Date

@Composable
fun ResultsView(
    temperatureResults: List<TemperatureMeasurement>
) {
    val calendar = Calendar.getInstance()
    val temperatureTimeData = temperatureResults.map { result ->
        calendar.time = Date(result.dateMs)
        calendar.get(Calendar.HOUR_OF_DAY).toFloat()
    }
    val temperatureValueData = temperatureResults.map { result ->
        result.measuredValue.toFloat()
    }
    val EKGTimeData = EKGResults.map { result ->
        calendar.time = Time(result.dateMs)
        calendar.get(Calendar.MILLISECOND).toFloat()
    }

    val EKGValueData = EKG.map { result ->
        result.arrayValue.toFloat()
    }



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
            Card{
                Column{
                    Text("Morfologia")
                    Divider(modifier = Modifier.padding(4.dp))
                    val htValue : Float
                    val hbValue : Float
                    val mcvValue : Float
                    val mchcValue : Float

                }
            }
        }
        item {
            //TODO dodać ekran wyników badań ekg
            Card{
                Column{
                    Text("Pomiar EKG")
                    Divider(modifier = Modifier.padding(4.dp))
                    ChartDisplay(
                        xData = EKGTimeData,
                        yData = EKGValueData
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