package pl.pw.ekartapacjenta.ui

import android.Manifest
import android.media.Image
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch
import model.Role
import model.User
import pl.pw.ekartapacjenta.R
import pl.pw.ekartapacjenta.ui.components.CameraPreview
import pl.pw.ekartapacjenta.ui.components.Header
import java.security.Permission
import java.util.UUID

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeView(
    user: User,
    onScanImage: (Image) -> Unit,
) {
    var showScanView by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(user.role)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
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
        if (!showScanView) {

        } else {
            CameraPreview(
                modifier = Modifier.size(300.dp),
                onImage = onScanImage,
            )
        }
//        Box(
//            modifier = Modifier
//                .padding(32.dp)
//        ) {
//            Button(
//                modifier = Modifier
//                    .size(72.dp)
//                    .clip(CircleShape),
//                onClick = { showScanView = !showScanView }
//            ) {
//                Icon(
//                    modifier = Modifier.size(36.dp),
//                    painter = painterResource(
//                        if (!showScanView) {
//                            R.drawable.scan_icon
//                        } else {
//                            R.drawable.back_arrow_icon
//                        }
//                    ),
//                    contentDescription = null
//                )
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    HomeView(
        user = User(
            id = UUID.randomUUID(),
            login = "jan.kowalski",
            name = "Jan",
            surname = "Kowalski",
            role = Role.PATIENT,
        ),
        onScanImage = {},
    )
}