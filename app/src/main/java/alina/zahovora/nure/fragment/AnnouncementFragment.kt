package alina.zahovora.nure.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.R
import alina.zahovora.nure.adapter.AnnouncementsListAdapter
import alina.zahovora.nure.api.objects.GetAllAnnouncements
import alina.zahovora.nure.data.Announcement
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnouncementFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_announcements_list, container, false)
        getAnnouncements(view)
        return view
    }

    private fun getAnnouncements(view: View) {
        val apiService = GetAllAnnouncements.retrofitService;
        apiService.getAllAnnouncements().enqueue(object : Callback<ArrayList<Announcement>> {
            override fun onFailure(call: Call<ArrayList<Announcement>>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<ArrayList<Announcement>>,
                response: Response<ArrayList<Announcement>>
            ) {
                println(response.code())
                println(response.body())

                val listView = view.findViewById<ListView>(R.id.announcements_list)
                val adapter = AnnouncementsListAdapter(view.context, response.body()!!)
                listView.adapter = adapter
            }
        })
    }
}