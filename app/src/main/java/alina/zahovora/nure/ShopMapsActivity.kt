package alina.zahovora.nure

import alina.zahovora.nure.api.objects.GetAllShops
import alina.zahovora.nure.data.Shop
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import alina.zahovora.nure.databinding.ActivityShopMapsBinding
import android.content.Intent
import com.google.android.gms.maps.model.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityShopMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShopMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        var shelters = arrayListOf<Shop>();
        val context = this;

        val apiService = GetAllShops.retrofitService;
        apiService.getAllShops().enqueue(object : Callback<ArrayList<Shop>> {
            override fun onFailure(call: Call<ArrayList<Shop>>, t: Throwable) {}

            override fun onResponse(
                call: Call<ArrayList<Shop>>,
                response: Response<ArrayList<Shop>>
            ) {
                shelters = response.body()!!

                var markers = arrayListOf<Marker?>();

                for (shelter in shelters) {
                    val marker = mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(shelter.latitude, shelter.longitude))
                            .title(shelter.name)
                            .snippet(shelter.address)
                    )
                    if (marker != null) {
                        marker.tag = shelter
                    };
                }

                val homeLatLng = LatLng(49.993384110300404, 36.29336276457478)
                val zoomLevel = 13f
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context))

                mMap.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener { marker ->
                    val shelter = Intent(context, BottomNavigationActivity::class.java)
                    shelter.putExtra("shelter", marker.tag as Shop);
                    startActivity(shelter)
                })

            }
        })
    }
}