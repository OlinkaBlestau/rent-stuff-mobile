package alina.zahovora.nure

import alina.zahovora.nure.adapter.ViewPagerAdapter
import alina.zahovora.nure.api.objects.GetUserById
import alina.zahovora.nure.data.Shop
import alina.zahovora.nure.data.User
import alina.zahovora.nure.fragment.admin_tabbed.AdminProfileFragment
import alina.zahovora.nure.fragment.admin_tabbed.AdminShopAnnouncementsFragment
import alina.zahovora.nure.fragment.admin_tabbed.AdminViewInfoShopFragment
import alina.zahovora.nure.fragment.tabbed.ShopAnnouncementsFragment
import alina.zahovora.nure.fragment.tabbed.ViewInfoShopFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class AdminTabbedMenuActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_tabbed_menu)

        val apiService = GetUserById.retrofitService
        println(MainActivity.getUserId())
        apiService.getUserById(MainActivity.getUserId()!!).enqueue(object : Callback<User> {
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

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    pager = findViewById(R.id.viewPager)
                    tab = findViewById(R.id.tabs)

                    val adapter = ViewPagerAdapter(supportFragmentManager)

                    val args = Bundle()
                    args.putInt("shopId", response.body()!!.shop.id)

                    val viewInfoShopFragment = AdminViewInfoShopFragment()
                    val shopAnnouncementsFragment = AdminShopAnnouncementsFragment()

                    viewInfoShopFragment.arguments = args
                    shopAnnouncementsFragment.arguments = args

                    adapter.addFragment(viewInfoShopFragment, "Shops info")
                    adapter.addFragment(shopAnnouncementsFragment, "Announcements")
                    adapter.addFragment(AdminProfileFragment(), "Profile")

                    pager.adapter = adapter
                    tab.setupWithViewPager(pager)
                }

            }
        })
    }
}