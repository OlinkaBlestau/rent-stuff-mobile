package alina.zahovora.nure.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.R
import alina.zahovora.nure.adapter.AnnouncementsListAdapter
import alina.zahovora.nure.api.objects.GetAllShops
import alina.zahovora.nure.api.objects.GetAnnouncementsByShopId
import alina.zahovora.nure.data.Shop
import android.widget.ListView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
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
        val view = inflater.inflate(R.layout.fragment_announcements_list, container, false)
        getAnnouncements(view, shopId!!)
        return view
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

                val listView = view.findViewById<ListView>(R.id.announcements_list)
                val adapter = AnnouncementsListAdapter(view.context, response.body()!!.thing!!)
                listView.adapter = adapter
            }
        })
    }
}