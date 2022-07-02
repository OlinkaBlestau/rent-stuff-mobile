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
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Spinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnouncementFragment : Fragment() {
    private var sort: Spinner? = null
    private var filter: Spinner? = null
    private var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_announcements_list, container, false)

        sort = rootView?.findViewById(R.id.spinnerSorting)
        filter = rootView?.findViewById(R.id.spinnerSpecies)

        sort?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getAnnouncements(rootView!!)
            }

        }

        filter?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getAnnouncements(rootView!!)
            }

        }

        getAnnouncements(rootView!!)
        return rootView
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

                val sortItem = sort?.selectedItem.toString()
                val filterItem = filter?.selectedItem.toString()
                var announcements: ArrayList<Announcement> = response.body()!!

                announcements = when (sortItem) {
                    "Вартність ↑" -> {
                        announcements.sortedBy { it.price }.toCollection(ArrayList<Announcement>())
                    }
                    "Вартність ↓" -> {
                        announcements.sortedByDescending { it.price }
                            .toCollection(ArrayList<Announcement>())
                    }
                    else -> {
                        announcements
                    }
                }

                announcements = when (filterItem) {
                    "Техніка для дома" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Техніка для дома")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Фото та відео" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Фото та відео")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Інструменти" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Інструменти")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Музичні інструменти" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Музичні інструменти")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Спортивні тренажери" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Спортивні тренажери")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Спорт та відпочинок" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Спорт та відпочинок")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Устаткування для заходів" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Устаткування для заходів")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Електроніка" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Електроніка")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "Ігри та консолі" -> {
                        announcements.filter { announcement ->
                            announcement.category?.name.equals("Ігри та консолі")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    else -> {
                        announcements
                    }
                }

                val listView = view.findViewById<ListView>(R.id.announcements_list)
                val adapter = AnnouncementsListAdapter(view.context, announcements)
                listView.adapter = adapter
            }
        })
    }
}