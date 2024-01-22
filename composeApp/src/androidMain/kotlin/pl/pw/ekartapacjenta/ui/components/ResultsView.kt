package pl.pw.ekartapacjenta.ui.components

import DummyData
import DummyDataEKG
import DummyDataMorf
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.EKGMeasurement
import model.MorfMeasurement
import model.TemperatureMeasurement
import java.util.*

@Composable
fun ResultsView(
    temperatureResults: List<TemperatureMeasurement>,
    EKGResults: List<EKGMeasurement>,
    morfResults: MorfMeasurement
) {
    Column(
        modifier = Modifier.padding(4.dp),
    ) {
        Text(text = "Wyniki badań", fontSize = 20.sp)
        Row(modifier = Modifier.padding(4.dp)) {
            val htValueData = morfResults.htValue
            val htUnitData = morfResults.htUnit
            val hbValueData = morfResults.hbValue
            val hbUnitData = morfResults.hbUnit
            val mcvValueData = morfResults.mcvValue
            val mcvUnitData = morfResults.mcvUnit
            val mchcValueData = morfResults.mchcValue
            val mchcUnitData = morfResults.mchcUnit
            Card {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Morfologia")
                    Divider(modifier = Modifier.padding(4.dp))
                    Text("Ht: $htValueData $htUnitData")
                    Text("Hb: $hbValueData $hbUnitData")
                    Text("MCV: $mcvValueData $mcvUnitData")
                    Text("MCHC: $mchcValueData $mchcUnitData")
                }
            }
        }
        Row(modifier = Modifier.padding(4.dp)) {
            val calendar = Calendar.getInstance()
            val temperatureTimeData = temperatureResults.map { result ->
                calendar.time = Date(result.dateMs)
                calendar.get(Calendar.HOUR_OF_DAY).toFloat()
            }
            val temperatureValueData = temperatureResults.map { result ->
                result.value.toFloat()
            }
            Card {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Pomiar temperatury")
                    Divider(modifier = Modifier.padding(4.dp))
                    ChartDisplay(
                        yData = temperatureTimeData,
                        xData = temperatureValueData
                    )
                }
            }
        }
        Row(modifier = Modifier.padding(4.dp)) {
            val EKGValueData = EKGResults.map { result ->
                result.value.toFloat()
            }
            val EKGTimeData = EKGResults.map { result ->
                (result.dateMs - EKGResults[0].dateMs).toFloat()
            }
            Card {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Pomiar EKG")
                    Divider(modifier = Modifier.padding(4.dp))
                    ChartDisplay(
                        xData = EKGValueData,
                        yData = EKGTimeData
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
        temperatureResults = DummyData.temperatureMeasurements1,
        EKGResults = DummyDataEKG.ekgMeasurement1,
        morfResults = DummyDataMorf.morfMeasurements1
    )
}