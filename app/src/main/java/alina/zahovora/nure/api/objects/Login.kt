package alina.zahovora.nure.api.objects

import alina.zahovora.nure.api.RetrofitClient
import alina.zahovora.nure.api.interfaces.Login

object Login {
    private val LOGIN_URL = "https://192.168.1.28/api/login/"

    val retrofitService: Login
        get () = RetrofitClient.getClient(LOGIN_URL).create(Login::class.java)
}