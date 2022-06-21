package alina.zahovora.nure.api.interfaces

import alina.zahovora.nure.data.UserLoginForm
import alina.zahovora.nure.data.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("https://192.168.1.28/api/login/")
    fun login(@Body requestBody: UserLoginForm): Call<UserLoginResponse>;
}