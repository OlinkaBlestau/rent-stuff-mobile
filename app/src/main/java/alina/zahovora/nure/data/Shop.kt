package alina.zahovora.nure.data
import java.io.Serializable

data class Shop(
    var id: Int,
    var name: String,
    var email: String,
    var phone: String,
    var address: String,
    var longitude: Double,
    var latitude: Double,
    var description: String,
    var user_id: Int,
    var thing: ArrayList<Announcement>?,
): Serializable