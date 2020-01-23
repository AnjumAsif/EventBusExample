package com.asif.eventbusexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var bus: EventBus = EventBus.getDefault()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment()
        buttonPost1.setOnClickListener(this)
        buttonPost2.setOnClickListener(this)
    }

    private fun loadFragment() {

        val transaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layoutContainer, FirstFragment())
        transaction.commit()
    }

    override fun onStop() {
        bus.unregister(this)
        super.onStop()
    }

    override fun onStart() {
        bus.register(this)
        super.onStart()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonPost1 -> {
                bus.post("Data come from fragment1")
            }
            R.id.buttonPost2 -> {
                bus.post("Data come from fragment2")
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getResult(msg: String) {

    }
}
