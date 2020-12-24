package com.example.pecode_test.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pecode_test.Connector
import com.example.pecode_test.notifications.NotificationHandler
import com.example.pecode_test.adapter.PagerAdapter
import com.example.pecode_test.R
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), Connector {
    private lateinit var viewpager: ViewPager
    private lateinit var adapter: PagerAdapter
    private val notificator: NotificationHandler by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager = findViewById(R.id.viewpager)
        setupViewPager()
        notificator.createNotificationChannel(this)
    }

    private fun setupViewPager() {

        adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(Home.newInstance(0))

        viewpager.adapter = adapter
    }


    override fun onNextBtnClick() {
        adapter.addFragment(Home.newInstance(viewpager.currentItem + 1))
        adapter.notifyDataSetChanged()
        viewpager.setCurrentItem(adapter.count - 1, true)
    }


    override fun onDeleteBtnClick() {
        if (viewpager.currentItem != 0) {
            adapter.deleteFragment(viewpager.currentItem)
            adapter.notifyDataSetChanged()
        }
    }

    override fun notificationButtonClick() {
        notificator.sendNotification(this, viewpager.currentItem)
    }
}
