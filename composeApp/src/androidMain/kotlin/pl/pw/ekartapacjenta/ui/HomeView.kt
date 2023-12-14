package pl.pw.ekartapacjenta.ui

import android.Manifest
import android.media.Image
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import model.User
import pl.pw.ekartapacjenta.R
import pl.pw.ekartapacjenta.ui.components.CameraPreview
import java.security.Permission

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeView(
    user: User,
    onScanImage: (Image) -> Unit,
) {
    var showScanView by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (!showScanView) {
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
        } else {
            CameraPreview(
                modifier = Modifier.size(300.dp),
                onImage = onScanImage,
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
        ) {
            Button(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape),
                onClick = { showScanView = !showScanView }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(
                        if (!showScanView) {
                            R.drawable.scan_icon
                        } else {
                            R.drawable.back_arrow_icon
                        }
                    ),
                    contentDescription = null
                )
            }
        }
    }
}