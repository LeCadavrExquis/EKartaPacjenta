package pl.pw.ekartapacjenta.ui

import DummyData
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.dm7.barcodescanner.zxing.ZXingScannerView
import model.Role
import model.User
import pl.pw.ekartapacjenta.ui.components.CameraPreview
import pl.pw.ekartapacjenta.ui.components.Header
import pl.pw.ekartapacjenta.ui.components.ScanButton
import java.util.UUID

@Composable
fun DoctorView(
    user: User,
    patient: User?,
    onScan: (String) -> Unit,
    scannerView: ZXingScannerView,
) {
    var showScanView by remember { mutableStateOf(false) }

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
                        .height(240.dp),
                ) {
                    Header(user.role)
                }
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
                if (patient != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        text = "Podgląd karty pacjenta",
                        fontSize = 36.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PatientView(user = patient, false)
                }
            }
        } else {
            CameraPreview(
                scannerView = scannerView,
                onScan = {
                    onScan(it)
                    showScanView = !showScanView
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
}

@Preview
@Composable
fun DoctorViewPreview() {
    DoctorView(
        user = DummyData.doctor1,
        patient = DummyData.user1,
        onScan = {},
        scannerView = ZXingScannerView(null)
    )
}