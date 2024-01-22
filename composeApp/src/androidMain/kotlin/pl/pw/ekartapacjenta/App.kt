package pl.pw.ekartapacjenta

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import me.dm7.barcodescanner.zxing.ZXingScannerView
import model.Role
import pl.pw.ekartapacjenta.ui.DoctorHomeView
import pl.pw.ekartapacjenta.ui.HomeView
import pl.pw.ekartapacjenta.ui.LoginView

@Composable
fun App(
    scannerView: ZXingScannerView,
    mainViewModel: MainViewModel = viewModel()
) {
    val user by mainViewModel.user.collectAsState()
    val error by mainViewModel.error.collectAsState()
    LaunchedEffect(Unit) {
        mainViewModel.initUserView()
    }
    MaterialTheme {
        if (user != null) {
            if (user!!.role == Role.DOCTOR) {
                DoctorHomeView(
                    user!!,
                    mainViewModel::onBedScan,
                    scannerView
                )
            } else {
                HomeView(user = user!!)
            }
        } else {
            LoginView(mainViewModel::logInUser, error)
        }
    }
}