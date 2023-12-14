package pl.pw.ekartapacjenta

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import pl.pw.ekartapacjenta.ui.HomeView
import pl.pw.ekartapacjenta.ui.LoginView

@Composable
fun App(mainViewModel: MainViewModel = viewModel()) {
    val user by mainViewModel.user.collectAsState()
    MaterialTheme {
        if (user != null) {
            HomeView(
                user!!,
                mainViewModel::onScanImage
            )
        } else {
            LoginView(mainViewModel::logInUser)
        }
    }
}