package alina.zahovora.nure.fragment.admin_tabbed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.R
import alina.zahovora.nure.api.objects.GetAnnouncementsByShopId
import alina.zahovora.nure.data.Shop
import alina.zahovora.nure.fragment.tabbed.ViewInfoShopFragment
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SHOP_ID = "shopId"
/**
 * A simple [Fragment] subclass.
 * Use the [AdminViewInfoShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AdminViewInfoShopFragment : Fragment() {
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
        val view =  inflater.inflate(R.layout.fragment_admin_view_info_shop, container, false)

        getShop(view, shopId!!)

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(shopId: Int) =
            ViewInfoShopFragment().apply {
                arguments = Bundle().apply {
                    putInt(SHOP_ID, shopId)
                }
            }
    }

    private fun getShop(view: View, shopId: Int) {
        val apiService = GetAnnouncementsByShopId.retrofitService
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

                val shopName = view.findViewById<TextView>(R.id.textViewName)
                val shopAddress = view.findViewById<TextView>(R.id.textViewAddress)
                val shopPhone = view.findViewById<TextView>(R.id.textViewPhone)
                val shopEmail = view.findViewById<TextView>(R.id.textViewEmail)
                val shopDescription = view.findViewById<TextView>(R.id.textViewDescription)
                val shopLongitude = view.findViewById<TextView>(R.id.textViewLongitude)
                val shopLatitude = view.findViewById<TextView>(R.id.textViewLatitude)

                shopName.text = response.body()?.name
                shopAddress.text = response.body()?.address
                shopPhone.text = response.body()?.phone
                shopEmail.text = response.body()?.email
                shopDescription.text = response.body()?.description
                shopLongitude.text = response.body()?.longitude.toString()
                shopLatitude.text = response.body()?.latitude.toString()
            }
        })
    }
}