package pl.pw.ekartapacjenta.ui

import DummyData
import DummyDataEKG
import DummyDataMorf
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.User
import pl.pw.ekartapacjenta.ui.components.Header
import pl.pw.ekartapacjenta.ui.components.ResultsView

@Composable
fun HomeView(
    user: User,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
        ) {
            Header(user.role)
        }
        Card {
            Column {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "imię: ${user.name}",
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "nazwisko: ${user.surname}",
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "rola: ${user.role}",
                )
            }
        }
        ResultsView(
            DummyData.temperatureMeasurements1,
            DummyDataEKG.ekgMeasurement1,
            DummyDataMorf.morfMeasurements1
        )
    }
}