package pl.pw.ekartapacjenta.ui.components

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*

@Composable
fun ChartDisplay(
    modifier: Modifier = Modifier,
    xData: List<Float>,
    yData: List<Float>,
) {
    val line1 = (1..100).zip(50..100).map {
        Entry(it.second.toFloat(), it.first.toFloat())
    }
    val line1Label = "1 label"
    val points = (50..100).zip(10..1000).map {
        Entry(it.second.toFloat(), it.first.toFloat())
    }
    val pointsLabel = "2 label"

    AndroidView(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth(),
        factory = { context ->
            val chart = CombinedChart(context)

            chart.description.isEnabled = false
            chart.setBackgroundColor(Color.WHITE)
            chart.setDrawGridBackground(false)
            chart.setDrawBarShadow(false)
            chart.isHighlightFullBarEnabled = false
            chart.drawOrder = arrayOf(
                DrawOrder.LINE, DrawOrder.SCATTER
            )

            val lineData = LineData(
                LineDataSet(line1, line1Label).apply {
                    color = Color.rgb(100, 238, 170)
                    setDrawCircles(false)
                },
            )
            val pointsData = ScatterData(
                ScatterDataSet(points, pointsLabel).apply {
                    color = Color.rgb(200, 4, 0)
                    setScatterShape(ScatterChart.ScatterShape.SQUARE)
                }
            )
            val data = CombinedData()
            data.setData(lineData)
            data.setData(pointsData)

            chart.data = data

            val xAxis: XAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            chart.invalidate()
            chart
        }
    )
}

@Preview
@Composable
fun ChartDisplayPreview() {
    ChartDisplay(
        xData = listOf(1f, 2f, 3f, 4f, 5f),
        yData = listOf(1f, 2f, 3f, 4f, 5f),
    )
}