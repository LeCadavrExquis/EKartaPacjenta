package pl.pw.ekartapacjenta.ui

import DummyData
import DummyDataEKG
import DummyDataMorf
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.User
import pl.pw.ekartapacjenta.ui.components.Header
import pl.pw.ekartapacjenta.ui.components.ResultsView

@Composable
fun PatientView(
    user: User,
    showHeader: Boolean = true
) {
    LazyColumn {
        if (showHeader) {
            item {
                Header(user.role)
            }
        }
        item {
            Card {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Account Icon",
                            modifier = Modifier.size(64.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = user.name,
                            fontSize = 24.sp
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = user.surname,
                            fontSize = 48.sp
                        )
                    }
                }
            }
        }
        item {
            ResultsView(
                DummyData.temperatureMeasurements1,
                DummyDataEKG.ekgMeasurement1,
                DummyDataMorf.morfMeasurements1
            )
        }
    }
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(360.dp),
//        ) {
//            Header(user.role)
//        }
//        Card {
//            Column {
//                Text(
//                    modifier = Modifier
//                        .padding(4.dp),
//                    text = "imię: ${user.name}",
//                    fontSize = 16.sp
//                )
//                Text(
//                    modifier = Modifier.padding(4.dp),
//                    text = "nazwisko: ${user.surname}",
//                )
//                Text(
//                    modifier = Modifier.padding(4.dp),
//                    text = "rola: ${user.role}",
//                )
//            }
//        }
//
//    }
}

@Preview
@Composable
fun HomeViewPreview() {
    PatientView(
        user = DummyData.doctor1
    )
}