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
    val calenedar = Calendar.getInstance()
    val temperatureTimeData = temperatureResults.map { result ->
        calenedar.time = Date(result.dateMs)
        calenedar.get(Calendar.HOUR_OF_DAY).toFloat()
    }
    val temperatureValueData = temperatureResults.map { result ->
        result.measuredValue.toFloat()
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
        }
        item {
            //TODO dodać ekran wyników badań ekg
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