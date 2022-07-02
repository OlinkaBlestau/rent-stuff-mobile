package alina.zahovora.nure

import alina.zahovora.nure.fragment.AnnouncementFragment
import alina.zahovora.nure.fragment.MapFragment
import alina.zahovora.nure.fragment.ProfileFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        setContentView(R.layout.activity_bottom_navigation)
        loadFragment(AnnouncementFragment())

        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.map -> {
                    loadFragment(MapFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.announcement -> {
                    loadFragment(AnnouncementFragment())
                    return@setOnNavigationItemReselectedListener
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}