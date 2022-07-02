package alina.zahovora.nure.api.objects

import alina.zahovora.nure.api.RetrofitClient
import alina.zahovora.nure.api.interfaces.GetUserById

object GetUserById {
    private val GET_URL = "http://15.188.224.152/api/user/{id}/"

    val retrofitService: GetUserById
        get() = RetrofitClient.getClient(GET_URL).create(GetUserById::class.java)
}