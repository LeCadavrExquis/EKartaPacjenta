package pl.pw.ekartapacjenta.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.User

@Composable
fun HomeView(user: User) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
}