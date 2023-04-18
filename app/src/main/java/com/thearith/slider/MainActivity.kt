package com.thearith.slider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val adapter = ImageSliderAdapter(viewModel.list)

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.setIndex(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


        viewModel.index.observe(this) {
            viewPager.currentItem = it
        }
    }
}