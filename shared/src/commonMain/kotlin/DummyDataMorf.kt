import model.MorfMeasurement
import java.time.LocalTime
import java.util.*
import kotlin.random.Random

//data class DummyDataMorf()

//losowanie godziny
val random = Random(System.currentTimeMillis())
val randomHour = random.nextInt(24)
val randomMinute = random.nextInt(60)

object DummyDataMorf {
    val morfMeasurements1 = MorfMeasurement(
        id = UUID.randomUUID(),
        time = LocalTime.of(randomHour, randomMinute).toNanoOfDay(),
        htValue = 41.34,
        htUnit = "%",
        hbValue = 15.67,
        hbUnit = "g/dL",
        mcvValue = 82.00,
        mcvUnit = "fL",
        mchcValue = 33.50,
        mchcUnit = "%",
        userId = DummyData.user1.id
    )
}
