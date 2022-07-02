package alina.zahovora.nure.api.interfaces

import alina.zahovora.nure.data.Shop
import alina.zahovora.nure.data.User
import alina.zahovora.nure.data.UserLoginForm
import alina.zahovora.nure.data.UserLoginResponse
import retrofit2.Call
import retrofit2.http.*

interface GetUserById {
    @GET("http://15.188.224.152/api/user/{id}/")
    fun getUserById(@Path("id") int: Int): Call<User>
}