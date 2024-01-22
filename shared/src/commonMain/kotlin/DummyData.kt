import model.Role
import model.TemperatureMeasurement
import model.User
import java.util.*

object DummyData {
    val doctor1 = User(
        id = UUID.fromString("3df518b3-1708-45f0-983d-b3d32a168498"),
        login = "testDoctor",
        password = "123",
        name = "Zbigniew",
        surname = "Religa",
        role = Role.DOCTOR,
    )
    val user1 = User(
        id = UUID.fromString("f821acea-879d-4de7-af35-8f87f75c640c"),
        login = "testPatient",
        password = "123",
        name = "Jan",
        surname = "Kowalski",
        role = Role.PATIENT
    )
    val bed1 = model.Bed(
        id = 12344321,
        userId = user1.id,
        doctorId = doctor1.id
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