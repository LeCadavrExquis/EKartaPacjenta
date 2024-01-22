package pl.pw.ekartapacjenta

import android.app.Application
import android.content.Intent
import android.media.Image
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.dm7.barcodescanner.zxing.ZXingScannerView
import model.EKGMeasurement
import model.MorfMeasurement
import model.TemperatureMeasurement
import model.User
import pl.pw.ekartapacjenta.logic.EncryptedSharedPref
import pl.pw.ekartapacjenta.logic.NetworkManager
import java.util.*

class MainViewModel(
    application: Application,
) : AndroidViewModel(application) {
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
                    getStudyResults(userId!!)
                } catch (e: Exception) {
                    sharedPreferences.saveJWToken(null)
                    sharedPreferences.saveUserId(null)
                }
            }
        }
    }

    fun onBedScan(bedId: String) {
        viewModelScope.launch {
            val bed = networkManager.getBedFromId(bedId)

            getStudyResults(bed.userId.toString())
        }
    }

    fun handleNfcIntent(intent: Intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                val payload = messages[0].records[0].payload

                val nfcDataString = payload.toMutableList().toByteArray().toString(Charsets.UTF_8).drop(3)

                onBedScan(nfcDataString)
            }
        }
    }

    private suspend fun getStudyResults(userId: String) {
        val studyResults = networkManager.getPatientData(userId)

        _user.update { old -> studyResults.user }
        _temperatureResults.update { old -> studyResults.temperatureMeasurements ?: old }
        _ekgResults.update { old -> studyResults.ekgMeasurements ?: old }
        _morfResults.update { old -> studyResults.morfMeasurements ?: old }
    }
}