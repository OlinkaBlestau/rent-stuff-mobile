package alina.zahovora.nure

import alina.zahovora.nure.api.objects.GetAnnouncementsById
import alina.zahovora.nure.api.objects.GetAnnouncementsByShopId
import alina.zahovora.nure.data.Announcement
import alina.zahovora.nure.data.Shop
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FullInfoAnnouncementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_info_announcement)

        val announcementId: Int = intent.getIntExtra("announcementId", 0)
        getAnnouncement(this, announcementId)
    }

    private fun getAnnouncement(view: FullInfoAnnouncementActivity, announcementId: Int) {
        val apiService = GetAnnouncementsById.retrofitService
        apiService.getAnnouncementById(announcementId).enqueue(object : Callback<Announcement> {
            override fun onFailure(call: Call<Announcement>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<Announcement>,
                response: Response<Announcement>
            ) {
                println(response.code())
                println(response.body())

                val name = view.findViewById<TextView>(R.id.textViewName)
                val price = view.findViewById<TextView>(R.id.textViewPrice)
                val category = view.findViewById<TextView>(R.id.textViewCategory)
                val description = view.findViewById<TextView>(R.id.textViewDescription)
                val image = view.findViewById<ImageView>(R.id.IVPreviewImage)

                name.text = response.body()?.name
                price.text = response.body()?.price.toString()
                category.text = when(response.body()?.category) {
                    null -> response.body()?.category_id.toString()
                    else -> response.body()?.category!!.name
                }
                description.text = response.body()?.description

                Glide.with(view)
                    .load("http://15.188.224.152/storage/images/" + response.body()?.photo)
                    .into(image)

            }
        })
    }
}