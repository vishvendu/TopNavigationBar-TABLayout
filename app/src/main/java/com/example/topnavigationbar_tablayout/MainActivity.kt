package com.example.topnavigationbar_tablayout

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import android.graphics.Typeface
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() , MovieFragment.OnFragmentInteractionListener,
    TVSHOWFragment.OnFragmentInteractionListener,
    PeopleFragment.OnFragmentInteractionListener,
    SearchFragment.OnFragmentInteractionListener,
    ProfileFragment.OnFragmentInteractionListener{


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var viewPager : ViewPager? = null
    var tabLayout : TabLayout? = null
    var toolbar : Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar?.setTitleTextAppearance(this,R.style.TitleBarAppearance)

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.viewPager)


        val viewAdapter = ViewPagerAdapter(supportFragmentManager)
        viewAdapter.addFragment(MovieFragment(), "Movies")
        viewAdapter.addFragment(TVSHOWFragment(), "TV Show")
        viewAdapter.addFragment(PeopleFragment(), "People")
        viewAdapter.addFragment(SearchFragment(), "Search")
        viewAdapter.addFragment(ProfileFragment(), "Profile")

        viewPager?.adapter = viewAdapter
        tabLayout?.setupWithViewPager(viewPager)



        for (i in 0 until tabLayout?.tabCount!!) {

            val tab = tabLayout?.getTabAt(i)
            if (tab != null) {

                val tabTextView = TextView(applicationContext)
                tab.customView = tabTextView

                tabTextView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT

                tabTextView.text = tab.text
                tabTextView.setTextColor(ContextCompat.getColor(applicationContext, R.color.offwhite));
                var typeFace: Typeface? = ResourcesCompat.getFont(applicationContext, R.font.moviedragonregular)
                tabTextView.setTextSize(20F)
                tabTextView.scaleX = 1F
                tabTextView.scaleY = 1F
                tabTextView.typeface = typeFace

                // First tab is the selected tab, so if i==0 then set BOLD typeface
                if (i == 0) {
                    tabTextView.setTextSize(20F)
                    tabTextView.scaleX = 1.4F
                    tabTextView.scaleY = 1.4F
                    var typeFace: Typeface? = ResourcesCompat.getFont(applicationContext, R.font.moviedragonbold)
                    tabTextView.setTextColor(ContextCompat.getColor(applicationContext, R.color.myfavcolor));

                    tabTextView.typeface = typeFace
                    //tabTextView.setFontVariationSettings(R.style.MyTabLayoutTextAppearance)
                }

            }

        }

        initTabLayout(tabLayout!!)

    }

    fun initTabLayout(tabLayout: TabLayout) {

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {


            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val text = tab?.getCustomView() as TextView
                text.setTextSize(20F)
                text.scaleX = 1F
                text.scaleY = 1F
                var typeFace: Typeface? = ResourcesCompat.getFont(applicationContext, R.font.moviedragonregular)
                text.setTextColor(ContextCompat.getColor(applicationContext, R.color.offwhite));
                text.typeface = typeFace
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPager?.currentItem = tab.getPosition()
                }
                val text = tab?.getCustomView() as TextView
                text.setTextSize(20F)
                text.scaleX = 1.4F
                text.scaleY = 1.4F
                var typeFace: Typeface? = ResourcesCompat.getFont(applicationContext, R.font.moviedragonbold)
                text.setTextColor(ContextCompat.getColor(applicationContext, R.color.myfavcolor));
                text.typeface = typeFace


            }

        })



    }
}
