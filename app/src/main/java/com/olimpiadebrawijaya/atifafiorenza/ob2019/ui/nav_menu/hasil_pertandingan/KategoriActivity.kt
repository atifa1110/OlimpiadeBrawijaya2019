package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.HasilPertandingan
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.adapter.KategoriCaborAdapter
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.DataList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_kategori.*
import java.util.ArrayList

class KategoriActivity : AppCompatActivity() {
    private lateinit var caborName : String
    private lateinit var adapter: KategoriCaborAdapter
    private lateinit var hasilPertandinganList: ArrayList<HasilPertandingan.KategoriData>

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        caborName = intent.extras.getString("caborName")

        supportActionBar?.title = caborName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        result_text.visibility = View.GONE

        hasilPertandinganList = arrayListOf()

        setupRecyclerView()
        fetchData(caborName)

    }

    private fun fetchData(caborName: String) {
        progressBar.visibility = View.VISIBLE
        compositeDisposable.add(
            RestClient.hasilService.getKtegoriCabor(caborName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            result ->
                        progressBar.visibility = View.GONE
                        val responseList = result.data

                        if(responseList != null){
                            result_text.visibility = View.GONE
                            hasilPertandinganList.clear()
                            for(data in responseList){
                                hasilPertandinganList.add(data)
                            }
                            adapter.notifyDataSetChanged()
                        }else{
                            result_text.visibility = View.VISIBLE
                        }


                    },
                    {
                        progressBar.visibility = View.GONE
                        result_text.visibility = View.VISIBLE
                        Toast.makeText(
                            DataList.context, "Terjadi kesalahan jaringan",
                            Toast.LENGTH_SHORT).show()
                    })
        )
    }

    private fun setupRecyclerView() {
        result_recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = KategoriCaborAdapter(this, hasilPertandinganList)
        result_recyclerView.adapter = adapter
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