package pl.pw.ekartapacjenta

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import model.Role
import pl.pw.ekartapacjenta.ui.DoctorHomeView
import pl.pw.ekartapacjenta.ui.HomeView
import pl.pw.ekartapacjenta.ui.LoginView

@Composable
fun App(mainViewModel: MainViewModel = viewModel()) {
    val user by mainViewModel.user.collectAsState()
    val error by mainViewModel.error.collectAsState()
    MaterialTheme {
        if (user != null) {
            if (user!!.role == Role.DOCTOR) {
                DoctorHomeView(
                    user!!,
                    mainViewModel::onScanImage
                )
            } else {
                HomeView(user = user!!)
            }
        } else {
            LoginView(mainViewModel::logInUser, error)
        }
    }
}