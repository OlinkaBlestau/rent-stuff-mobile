package alina.zahovora.nure.data
import java.io.Serializable

data class UserLoginForm(
    var email: String,
    var password: String
): Serializable