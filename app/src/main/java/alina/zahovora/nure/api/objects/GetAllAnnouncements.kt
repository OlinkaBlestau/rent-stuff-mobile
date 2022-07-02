package alina.zahovora.nure.api.objects

import alina.zahovora.nure.api.RetrofitClient
import alina.zahovora.nure.api.interfaces.GetAllAnnouncements

object GetAllAnnouncements {
    private val GET_URL = "http://15.188.224.152/api/thing/"

    val retrofitService: GetAllAnnouncements
        get() = RetrofitClient.getClient(GET_URL).create(GetAllAnnouncements::class.java)
}