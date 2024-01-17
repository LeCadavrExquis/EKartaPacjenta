package pl.pw.ekartapacjenta.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import model.Role
import pl.pw.ekartapacjenta.R

@Composable
fun Header(
    role: Role = Role.PATIENT,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            fontSize = 36.sp,
            text = "E-Karta"
        )
        Icon(
            modifier = Modifier.scale(0.9f),
            painter = painterResource(id = R.drawable.logo_icon),
            contentDescription = null
        )
        Text(
            fontSize = 36.sp,
            text = if (role == Role.DOCTOR) "Lekarza" else "Pacjenta"
        )
    }
}