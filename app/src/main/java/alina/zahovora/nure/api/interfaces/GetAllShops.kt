package alina.zahovora.nure.api.interfaces

import alina.zahovora.nure.data.Shop
import alina.zahovora.nure.data.UserLoginForm
import alina.zahovora.nure.data.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GetAllShops {
    @GET("http://15.188.224.152/api/shop/")
    fun getAllShops(): Call<ArrayList<Shop>>;
}