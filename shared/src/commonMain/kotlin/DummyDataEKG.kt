import model.EKGMeasurement
import java.util.*
import kotlin.random.Random

fun generateEKGData(numSamples : Int): List<EKGMeasurement> {
    val random = Random(System.currentTimeMillis())
    val EKGDataList = mutableListOf<EKGMeasurement>()

    for(i in 0 until numSamples){
        val timeMs = i * 10L //zakładamy, że 1 próbka będzie trwać 10 ms
        val voltage = random.nextDouble(-1.0, 1.0)  //zakres napięć
        val ekgData = toEKGMeasurement(timeMs, voltage)
        EKGDataList.add(ekgData)
    }
    return EKGDataList.toList()
}

fun toEKGMeasurement(
    timeMS: Long,
    voltage: Double
) : EKGMeasurement {
    return EKGMeasurement(
        UUID.randomUUID(),
        dateMs = timeMS,
        value = voltage,
        DummyData.user1.id
    )
}

val numSamples = 100

object DummyDataEKG {
    val ekgMeasurement1 = generateEKGData(numSamples)
}



