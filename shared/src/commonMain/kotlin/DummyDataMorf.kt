import java.util*
import java.util.UUID
import model.Role
import model.MorfMeasurement
import model.User
import java.time.LocalTime
import kotlin.random.Random
//data class DummyDataMorf()

//losowanie godziny
val random = Random(System.currentTimeMillis())
val randomHour = random.nextInt(24)
val randomMinute = random.nextInt(60)

object DummyDataMorf {
    val doctor1 = User(
        id = UUID.randomUUID(),
        login = "testDoctor",
        name = "Zbigniew",
        surname = "Religa",
        role = Role.DOCTOR
    )
    val user1 = User(
        id = UUID.randomUUID(),
        login = "testPacjent",
        name = "Jan",
        surname = "Kowalski",
        role = Role.PATIENT
    )
    val morfMeasurements1 = listOf(
        MorfMeasurement(
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
            userId = user1.id
        ),
    )
}
