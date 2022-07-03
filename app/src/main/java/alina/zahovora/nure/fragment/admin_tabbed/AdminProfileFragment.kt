package alina.zahovora.nure.fragment.admin_tabbed

import alina.zahovora.nure.MainActivity.Companion.getUserId
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.R
import alina.zahovora.nure.api.objects.GetUserById
import alina.zahovora.nure.data.User
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class AdminProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val apiService = GetUserById.retrofitService
        println(getUserId())
        apiService.getUserById(getUserId()!!).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                println(response.body())
                println(response.code())

                val name = view.findViewById<TextView>(R.id.textViewName)
                val surname = view.findViewById<TextView>(R.id.textViewSurname)
                val phone = view.findViewById<TextView>(R.id.textViewPhone)
                val email = view.findViewById<TextView>(R.id.textViewEmail)
                val photo = view.findViewById<ImageView>(R.id.IVPreviewImage)

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    name.text = response.body()?.name
                    surname.text = response.body()?.surname
                    phone.text = response.body()?.phone
                    email.text = response.body()?.email

                    Glide.with(view.context)
                        .load("http://15.188.224.152/storage/images/" + response.body()?.photo)
                        .into(photo)
                }

            }
        })

        return view
    }
}