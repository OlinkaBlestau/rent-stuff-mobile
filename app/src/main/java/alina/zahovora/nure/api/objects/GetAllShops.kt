package alina.zahovora.nure.api.objects

import alina.zahovora.nure.api.RetrofitClient
import alina.zahovora.nure.api.interfaces.GetAllShops

object GetAllShops {
    private val LOGIN_URL = "http://15.188.224.152/api/shop/"

    val retrofitService: GetAllShops
        get () = RetrofitClient.getClient(LOGIN_URL).create(GetAllShops::class.java)
}