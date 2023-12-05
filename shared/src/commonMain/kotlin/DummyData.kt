import model.Role
import model.User
import java.util.*

object DummyData {
    val doctor1 = User(
        id = UUID.randomUUID(),
        login = "testDoctor",
        name = "Zbigniew",
        surname = "Religa",
        role = Role.DOCTOR
    )
    val user1 = User(
        id = UUID.randomUUID(),
        login = "testPatient",
        name = "Jan",
        surname = "Kowalski",
        role = Role.PATIENT
    )
}