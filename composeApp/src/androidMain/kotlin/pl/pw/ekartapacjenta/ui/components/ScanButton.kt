package pl.pw.ekartapacjenta.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.pw.ekartapacjenta.R

@Composable
fun ScanButton(
    modifier: Modifier = Modifier,
    showScanView: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(32.dp)
    ) {
        Button(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            onClick = onClick
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