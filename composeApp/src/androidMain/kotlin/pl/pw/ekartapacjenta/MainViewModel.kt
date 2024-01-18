package pl.pw.ekartapacjenta

import DummyData
import android.app.Application
import android.media.Image
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import model.TemperatureMeasurement
import model.User
import pl.pw.ekartapacjenta.logic.EncryptedSharedPref
import pl.pw.ekartapacjenta.logic.NetworkManager
import java.util.UUID

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = EncryptedSharedPref(application)
    private val networkManager = NetworkManager()
    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)
    private var _temperatureResults: MutableStateFlow<List<TemperatureMeasurement>> = MutableStateFlow(listOf())

    val user: StateFlow<User?>
        get() = _user

    fun logInUser(login: String, password: String): Boolean {
        val id: UUID
        val token: String
        try {
            val response = networkManager.validateLogin(login, password)
            id = response.id
            token = response.token
        } catch (e: Exception) {
            return false
        }

        sharedPreferences.saveJWToken(token)

        val studyResults = networkManager.getPatientData(id)

        _temperatureResults.update { old -> studyResults }

        _user.update { old -> DummyData.user1 }

        return true
    }

    // TODO
    fun onScanImage(image: Image) {
    }
}