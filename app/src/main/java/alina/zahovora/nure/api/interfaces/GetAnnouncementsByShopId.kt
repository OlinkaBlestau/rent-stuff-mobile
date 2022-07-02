package alina.zahovora.nure.api.interfaces

import alina.zahovora.nure.data.Shop
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAnnouncementsByShopId {
    @GET("http://15.188.224.152/api/shop/{id}/")
    fun getAnnouncementByShopId(@Path("id") shopId: Int): Call<Shop>
}