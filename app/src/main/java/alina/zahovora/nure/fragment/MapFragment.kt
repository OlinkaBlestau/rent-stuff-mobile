package alina.zahovora.nure.fragment

import alina.zahovora.nure.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alina.zahovora.nure.api.objects.GetAllShops
import alina.zahovora.nure.data.Shop
import android.content.Intent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        var shops = arrayListOf<Shop>();

        val apiService = GetAllShops.retrofitService;
        apiService.getAllShops().enqueue(object : Callback<ArrayList<Shop>> {
            override fun onFailure(call: Call<ArrayList<Shop>>, t: Throwable) {}

            override fun onResponse(
                call: Call<ArrayList<Shop>>,
                response: Response<ArrayList<Shop>>
            ) {
                shops = response.body()!!

                var markers = arrayListOf<Marker?>();

                for (shop in shops) {
                    val marker = googleMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(shop.latitude, shop.longitude))
                            .title(shop.name)
                            .snippet(shop.address)
                    )
                    if (marker != null) {
                        marker.tag = shop
                    };
                }

                val homeLatLng = LatLng(49.993384110300404, 36.29336276457478)
                val zoomLevel = 13f
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                googleMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context!!))

                googleMap.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener { marker ->
                    val shop = Intent(context, TabbedMenuActivity::class.java)
                    shop.putExtra("shop", marker.tag as Shop);
                    startActivity(shop)
                })

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}