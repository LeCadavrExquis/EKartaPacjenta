package pl.pw.ekartapacjenta.ui

import android.media.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.User
import pl.pw.ekartapacjenta.ui.components.CameraPreview
import pl.pw.ekartapacjenta.ui.components.Header
import pl.pw.ekartapacjenta.ui.components.ScanButton

@Composable
fun DoctorHomeView(
    user: User,
    onScanImage: (Image) -> Unit,
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
                modifier = Modifier.size(300.dp),
                onImage = onScanImage,
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