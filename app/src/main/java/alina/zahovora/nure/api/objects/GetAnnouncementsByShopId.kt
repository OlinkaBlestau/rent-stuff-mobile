package alina.zahovora.nure.api.objects

import alina.zahovora.nure.api.RetrofitClient
import alina.zahovora.nure.api.interfaces.GetAnnouncementsByShopId

object GetAnnouncementsByShopId {
    private val GET_URL = "http://15.188.224.152/api/shop/{id}/"

    val retrofitService: GetAnnouncementsByShopId
        get() = RetrofitClient.getClient(GET_URL).create(GetAnnouncementsByShopId::class.java)
}