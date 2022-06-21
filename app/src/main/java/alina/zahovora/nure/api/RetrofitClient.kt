package alina.zahovora.nure.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object RetrofitClient {
    private var retrofit: Retrofit? = null
    val gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd")
        .create()

    fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getUnprotectedHttpClient())
                .build()
        }
        return retrofit!!
    }

    private fun getUnprotectedHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .hostnameVerifier { hostname, session ->
                val hv = HttpsURLConnection.getDefaultHostnameVerifier()
                true
            }
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build()

    }

}