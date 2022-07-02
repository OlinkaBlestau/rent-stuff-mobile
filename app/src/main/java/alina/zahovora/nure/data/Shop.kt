package alina.zahovora.nure.data
import java.io.Serializable

data class Shop(
    var name: String,
    var email: String,
    var phone: String,
    var address: String,
    var longitude: Double,
    var latitude: Double,
    var descsription: String,
    var user_id:Int,
): Serializable