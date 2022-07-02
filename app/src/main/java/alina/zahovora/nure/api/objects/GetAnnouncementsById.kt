package alina.zahovora.nure.api.objects

import alina.zahovora.nure.api.RetrofitClient
import alina.zahovora.nure.api.interfaces.GetAnnouncementsById

object GetAnnouncementsById {
    private val GET_URL = "http://15.188.224.152/api/thing/{id}/"

    val retrofitService: GetAnnouncementsById
        get() = RetrofitClient.getClient(GET_URL).create(GetAnnouncementsById::class.java)
}