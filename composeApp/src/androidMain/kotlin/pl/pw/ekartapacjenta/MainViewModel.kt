package pl.pw.ekartapacjenta

import DummyData
import android.app.Application
import android.media.Image
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import model.User
import pl.pw.ekartapacjenta.logic.EncryptedSharedPref

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = EncryptedSharedPref(application)
    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)

    val user: StateFlow<User?>
        get() = _user

    // TODO
    fun logInUser(login: String, password: String): Boolean {
        if (password == "123") {
            _user.update { old -> DummyData.user1 }
        }
        return password == "123"
    }

    // TODO
    fun onScanImage(image: Image) {

    }
}