package com.atitto.nearbyplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.atitto.nearbyplaces.map.MapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val tr = supportFragmentManager.beginTransaction()
            tr.replace(R.id.vParent, MapFragment.newInstance())
            tr.commit()
        }
    }
}
