package alina.zahovora.nure

import alina.zahovora.nure.adapter.ViewPagerAdapter
import alina.zahovora.nure.data.Shop
import alina.zahovora.nure.fragment.ShopAnnouncementsFragment
import alina.zahovora.nure.fragment.ViewInfoShopFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TabbedMenuActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed_menu)

        val shop: Shop = intent.getSerializableExtra("shop") as Shop
        // set the references of the declared objects above
        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val args = Bundle()
        args.putInt("shopId", shop.id)

        val viewInfoShopFragment = ViewInfoShopFragment()
        val shopAnnouncementsFragment = ShopAnnouncementsFragment()

        viewInfoShopFragment.arguments = args
        shopAnnouncementsFragment.arguments = args

        adapter.addFragment(viewInfoShopFragment, "Shops info")
        adapter.addFragment(shopAnnouncementsFragment, "Announcements")

        pager.adapter = adapter
        tab.setupWithViewPager(pager)
    }
}