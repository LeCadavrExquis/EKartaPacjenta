package pl.pw.ekartapacjenta.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginView(
    logIn: (login: String, pass: String) -> Boolean
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                OutlinedTextField(
                    label = { Text("Login") },
                    value = login,
                    onValueChange = { newValue ->
                        login = newValue
                    },
                    isError = error
                )
                OutlinedTextField(
                    label = { Text("Hasło") },
                    value = password,
                    onValueChange = { newValue ->
                        password = newValue
                    },
                    isError = error
                )
                Button(
                    onClick = {
                        val loggedInSuccess = logIn(login, password)

                        error = !loggedInSuccess
                    }
                ) { Text("Zaloguj") }
            }
        }
    }
}

@Preview
@Composable
fun LoginViewPreview() {
    LoginView { _, _ -> true }
}