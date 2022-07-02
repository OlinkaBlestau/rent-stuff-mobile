package alina.zahovora.nure.api.interfaces

import alina.zahovora.nure.data.Announcement
import alina.zahovora.nure.data.Shop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAnnouncementsById {
    @GET("http://15.188.224.152/api/thing/{id}/")
    fun getAnnouncementById(@Path("id") announcementId: Int): Call<Announcement>
}