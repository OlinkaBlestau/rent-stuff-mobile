package alina.zahovora.nure.data
import java.io.Serializable
import java.sql.Date

data class Announcement(
    var id: Int,
    var name: String,
    var price: Double,
    var description: String,
    var photo: String,
    var shop_id: Int,
    var category_id: Int,
    var created_at: Date,
    var category: Category?,
    var shop: Shop?,
): Serializable