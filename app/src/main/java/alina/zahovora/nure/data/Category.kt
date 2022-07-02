package alina.zahovora.nure.data
import java.io.Serializable
import java.sql.Date

data class Category(
    var id: Int,
    var name: String,
    var created_at: Date,
): Serializable