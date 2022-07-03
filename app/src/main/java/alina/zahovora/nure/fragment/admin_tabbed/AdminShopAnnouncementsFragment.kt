package alina.zahovora.nure.fragment.admin_tabbed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.R
import alina.zahovora.nure.adapter.AdminAnnouncementsListAdapter
import alina.zahovora.nure.api.objects.GetAnnouncementsByShopId
import alina.zahovora.nure.data.Announcement
import alina.zahovora.nure.data.Shop
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SHOP_ID = "shopId"

class AdminShopAnnouncementsFragment : Fragment() {
    private var shopId: Int? = null

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
        val view = inflater.inflate(R.layout.fragment_admin_announcements_list, container, false)

        getAnnouncements(view, shopId!!)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(shopId: Int) =
            AdminShopAnnouncementsFragment().apply {
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

                val announcements: ArrayList<Announcement> = response.body()!!.thing!!

                val listView = view.findViewById<ListView>(R.id.admin_announcements_list)
                val adapter = AdminAnnouncementsListAdapter(view.context, announcements)
                listView.adapter = adapter
            }
        })
    }
}