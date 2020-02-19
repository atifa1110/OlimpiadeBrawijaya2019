package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import kotlinx.android.synthetic.main.activity_detail_hasil.*

class DetailHasilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hasil)

        supportActionBar?.title = "Detail Hasil"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailHasil = intent?.extras?.getString("detailHasil")

        detail_hasil.text = detailHasil
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }
}
