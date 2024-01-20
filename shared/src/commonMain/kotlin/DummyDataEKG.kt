import model.Role
import model.User
import java.util.*
import kotlin.random.Random
data class DummyDataEKG (val timeMS: Long, val voltage: Double)

fun generateEKGData(numSamples : Int): List<DummyDataEKG> {
    val random = Random(System.currentTimeMillis())
    val EKGDataList = mutableListOf<DummyDataEKG>()

    for(i in 0 until numSamples){
        val timeMs = i * 10L //zakładamy, że 1 próbka będzie trwać 10 ms
        val voltage = random.nextDouble(-1.0, 1.0)  //zakres napięć
        val ekgData = DummyDataEKG(timeMs, voltage)
        EKGDataList.add(ekgData)
    }
    return EKGDataList
}

val numSamples = 500

object ekgDummyData {
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
    val ekgMeasurement1 = generateEKGData(numSamples)
}



