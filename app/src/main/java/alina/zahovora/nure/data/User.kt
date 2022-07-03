package alina.zahovora.nure.data
import java.io.Serializable

data class User(
    var name: String,
    var surname: String,
    var email: String,
    var phone: String,
    var password: String,
    var photo: String,
    var shop: Shop,
): Serializable