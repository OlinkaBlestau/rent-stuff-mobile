package alina.zahovora.nure.api.interfaces

import alina.zahovora.nure.data.Announcement
import alina.zahovora.nure.data.Shop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAllAnnouncements {
    @GET("http://15.188.224.152/api/thing/")
    fun getAllAnnouncements(): Call<ArrayList<Announcement>>
}