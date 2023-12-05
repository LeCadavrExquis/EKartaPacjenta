package pl.pw.ekartapacjenta

import DummyData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import model.User
import java.util.*

class MainViewModel: ViewModel() {
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
}