import model.Role
import model.TemperatureMeasurement
import model.User
import java.util.*

object DummyData {
    val doctor1 = User(
        id = UUID.randomUUID(),
        login = "testDoctor",
        password = "123",
        name = "Zbigniew",
        surname = "Religa",
        role = Role.DOCTOR,
    )
    val user1 = User(
        id = UUID.randomUUID(),
        login = "testPatient",
        password = "123",
        name = "Jan",
        surname = "Kowalski",
        role = Role.PATIENT
    )
    val temperatureMeasurements1 = listOf(
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 123456789,
            value = 36.6,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 127056789,
            value = 37.1,
            userId = user1.id
        ),
        // TODO dodać poprawne czasy
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 130656789,
            value = 36.8,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 134256789,
            value = 39.2,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 137856789,
            value = 39.5,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 141456789,
            value = 39.8,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 145056789,
            value = 39.7,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 148656789,
            value = 37.5,
            userId = user1.id
        ),
        TemperatureMeasurement(
            id = UUID.randomUUID(),
            dateMs = 152256789,
            value = 36.7,
            userId = user1.id
        ),
    )
}