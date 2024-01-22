package pl.pw.ekartapacjenta

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.dm7.barcodescanner.zxing.ZXingScannerView
import model.Role
import pl.pw.ekartapacjenta.ui.DoctorView
import pl.pw.ekartapacjenta.ui.PatientView
import pl.pw.ekartapacjenta.ui.LoginView

@Composable
fun App(
    scannerView: ZXingScannerView,
    mainViewModel: MainViewModel = viewModel()
) {
    val user by mainViewModel.user.collectAsState()
    val patient by mainViewModel.patient.collectAsState()
    val error by mainViewModel.error.collectAsState()
    val isLoading by mainViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.initUserView()
    }
    MaterialTheme {
        if (user != null) {
            if (user!!.role == Role.DOCTOR) {
                DoctorView(
                    user!!,
                    patient,
                    mainViewModel::onBedScan,
                    scannerView
                )
            } else {
                PatientView(user = user!!)
            }
        } else {
            LoginView(mainViewModel::logInUser, error)
        }
        if (isLoading) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(text = "Proszę czekać...")
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .width(150.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            )
        }
    }
}