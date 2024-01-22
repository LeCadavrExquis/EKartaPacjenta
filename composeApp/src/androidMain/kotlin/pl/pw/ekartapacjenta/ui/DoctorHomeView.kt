package pl.pw.ekartapacjenta.ui

import DummyData
import android.media.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.dm7.barcodescanner.zxing.ZXingScannerView
import model.User
import pl.pw.ekartapacjenta.ui.components.CameraPreview
import pl.pw.ekartapacjenta.ui.components.Header
import pl.pw.ekartapacjenta.ui.components.ScanButton

@Composable
fun DoctorHomeView(
    user: User,
    onScan: (String) -> Unit,
    scannerView: ZXingScannerView,
) {
    var showScanView by remember { mutableStateOf(false) }
    var tmpUser by remember { mutableStateOf<User?>(null) }

    if (tmpUser == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (!showScanView) {
                Column(
                    modifier = Modifier.fillMaxSize(),
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
                }
            } else {
                CameraPreview(
                    scannerView = scannerView,
                    onScan = {
                        onScan(it)
                    }
                )
            }
            ScanButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp),
                showScanView = showScanView,
                onClick = {
                    showScanView = !showScanView
                }
            )
        }
    } else {
        HomeView(user = tmpUser!!)
    }

}