package pl.pw.ekartapacjenta

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import model.Role
import pl.pw.ekartapacjenta.ui.DoctorHomeView
import pl.pw.ekartapacjenta.ui.HomeView
import pl.pw.ekartapacjenta.ui.LoginView

@Composable
fun App(mainViewModel: MainViewModel = viewModel()) {
    val user by mainViewModel.user.collectAsState()
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
            LoginView(mainViewModel::logInUser)
        }
    }
}