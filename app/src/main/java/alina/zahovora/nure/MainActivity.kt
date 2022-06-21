package alina.zahovora.nure

import alina.zahovora.nure.api.objects.Login
import alina.zahovora.nure.data.UserLoginForm
import alina.zahovora.nure.data.UserLoginResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        private var authToken: String? = null

        fun setAuthToken(value: String){
            authToken = value
        }
        fun getAuthToken(): String? = this.authToken
    }

    fun login (view: View){

        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)

        val user = UserLoginForm(
            email.text.toString(),
            password.text.toString()
        )
        //val intent = Intent(this, PlacesListActivity::class.java)
        val apiService = Login.retrofitService

        apiService.login(user).enqueue(object : Callback<UserLoginResponse> {
            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<UserLoginResponse>,
                response: Response<UserLoginResponse>
            ) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    setAuthToken("Bearer " + response.body()?.token)
                //    startActivity(intent)

                    println(response.body())
                }
            }
        })

    }
}