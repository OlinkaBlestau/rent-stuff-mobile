package alina.zahovora.nure.fragment.tabbed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.R
import alina.zahovora.nure.adapter.AnnouncementsListAdapter
import alina.zahovora.nure.api.objects.GetAnnouncementsByShopId
import alina.zahovora.nure.data.Announcement
import alina.zahovora.nure.data.Shop
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Spinner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SHOP_ID = "shopId"

/**
 * A simple [Fragment] subclass.
 * Use the [ShopAnnouncementsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShopAnnouncementsFragment : Fragment() {
    private var shopId: Int? = null
    private var sort: Spinner? = null
    private var filter: Spinner? = null
    private var rootView: View? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            shopId = it.getInt(SHOP_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_announcements_list, container, false)

        sort = rootView?.findViewById(R.id.spinnerSorting)
        filter = rootView?.findViewById(R.id.spinnerFilter)

        sort?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getAnnouncements(rootView!!, shopId!!)
            }
        }

        filter?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                getAnnouncements(rootView!!, shopId!!)
            }
        }

        getAnnouncements(rootView!!, shopId!!)
        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(shopId: Int) =
            ShopAnnouncementsFragment().apply {
                arguments = Bundle().apply {
                    putInt(SHOP_ID, shopId)
                }
            }
    }

    private fun getAnnouncements(view: View, shopId: Int) {
        val apiService = GetAnnouncementsByShopId.retrofitService;
        apiService.getAnnouncementByShopId(shopId).enqueue(object : Callback<Shop> {
            override fun onFailure(call: Call<Shop>, t: Throwable) {
                println(t.message)
                println(t.stackTrace)
            }

            override fun onResponse(
                call: Call<Shop>,
                response: Response<Shop>
            ) {
                println(response.code())
                println(response.body())

                val sortItem = sort?.selectedItem.toString()
                val filterItem = filter?.selectedItem.toString()
                var announcements: ArrayList<Announcement> = response.body()!!.thing!!

                println(sortItem)
                println(filterItem)

                announcements = when (sortItem) {
                    "?????????????????? ???" -> {
                        announcements.sortedBy { it.price }.toCollection(ArrayList<Announcement>())
                    }
                    "?????????????????? ???" -> {
                        announcements.sortedByDescending { it.price }
                            .toCollection(ArrayList<Announcement>())
                    }
                    else -> {
                        announcements
                    }
                }

                announcements = when (filterItem) {
                    "?????????????? ?????? ????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("?????????????? ?????? ????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "???????? ???? ??????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("???????? ???? ??????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "??????????????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("??????????????????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "?????????????? ??????????????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("?????????????? ??????????????????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "?????????????????? ??????????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("?????????????????? ??????????????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "?????????? ???? ????????????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("?????????? ???? ????????????????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "???????????????????????? ?????? ??????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("???????????????????????? ?????? ??????????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "??????????????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("??????????????????????")
                        }.toCollection(ArrayList<Announcement>())
                    }
                    "???????? ???? ??????????????" -> {
                        announcements.filter { announcement ->
                            announcement.category.name.equals("???????? ???? ??????????????")
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