package pl.pw.ekartapacjenta

import android.app.Application
import android.media.Image
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.EKGMeasurement
import model.MorfMeasurement
import model.TemperatureMeasurement
import model.User
import pl.pw.ekartapacjenta.logic.EncryptedSharedPref
import pl.pw.ekartapacjenta.logic.NetworkManager
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = EncryptedSharedPref(application)
    private val networkManager = NetworkManager(sharedPreferences.getJWToken())
    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)
    private var _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private var _temperatureResults: MutableStateFlow<List<TemperatureMeasurement>> = MutableStateFlow(listOf())
    private var _ekgResults: MutableStateFlow<List<EKGMeasurement>> = MutableStateFlow(listOf())
    private var _morfResults: MutableStateFlow<MorfMeasurement?> = MutableStateFlow(null)

    val user: StateFlow<User?>
        get() = _user
    val error: StateFlow<Boolean>
        get() = _error

    fun logInUser(login: String, password: String) {
        var id: UUID
        var token: String
        this.viewModelScope.launch {
            try {
                val response = networkManager.validateLogin(login, password)
                id = response.id
                token = response.token
                sharedPreferences.saveUserId(id.toString())
                sharedPreferences.saveJWToken(token)
                networkManager.updateToken(token)

                val studyResults = networkManager.getPatientData(id.toString())

                _user.update { old -> studyResults.user }
                _temperatureResults.update { old -> studyResults.temperatureMeasurements ?: old }
                _ekgResults.update { old -> studyResults.ekgMeasurements ?: old }
                _morfResults.update { old -> studyResults.morfMeasurements ?: old }

            } catch (e: Exception) {
                _error.update { true }
            }
        }
    }

    fun initUserView() {
        val token = sharedPreferences.getJWToken()
        val userId = sharedPreferences.getUserId()
        if (token != null) {
            this.viewModelScope.launch {
                try {
                    val studyResults = networkManager.getPatientData(userId!!)

                    _user.update { old -> studyResults.user }
                    _temperatureResults.update { old -> studyResults.temperatureMeasurements ?: old }
                    _ekgResults.update { old -> studyResults.ekgMeasurements ?: old }
                    _morfResults.update { old -> studyResults.morfMeasurements ?: old }

                } catch (e: Exception) {
                    // ignore
                }
            }
        }
    }

    // TODO
    fun onScanImage(image: Image) {
    }
}