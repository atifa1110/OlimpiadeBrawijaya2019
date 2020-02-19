package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.HasilPertandingan
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.adapter.HasilPertandinganAdapter
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.DataList.context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_hasil_pertandingan.*
import kotlinx.android.synthetic.main.hasil_pertandingan_multiple.*
import java.util.ArrayList

class HasilPertandinganActivity : AppCompatActivity() {

    private lateinit var caborName : String
    private lateinit var kategoriCabor : String
    private lateinit var adapter: HasilPertandinganAdapter
    private lateinit var hasilPertandinganList: ArrayList<HasilPertandingan.HasilData>

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_pertandingan)

        caborName = intent.extras.getString("caborName")
        kategoriCabor = intent.extras.getString("kategoriCabor")

        val title = caborName.toLowerCase()
        val newTitle = title.substring(0, 1).toUpperCase() + title.substring(1)
        supportActionBar?.title = newTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        pertandingan_versus_rv.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        result_text.visibility = View.GONE

        pertandingan_versus_rv.visibility = View.INVISIBLE
        pertandingan_multiple.visibility = View.GONE

        hasilPertandinganList = arrayListOf()

        fetchData()

    }


    private fun fetchData() {
        progressBar.visibility = View.VISIBLE
        compositeDisposable.add(
            RestClient.hasilService.getHasilPertandingan(caborName, kategoriCabor)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            result ->
                        progressBar.visibility = View.GONE
                        val responseList = result.data

                        if(responseList != null){
                            result_text.visibility = View.GONE
                            showHasilPertandingan(responseList)
                        }else{
                            result_text.visibility = View.VISIBLE
                        }


                    },
                    {
                        progressBar.visibility = View.GONE
                        result_text.visibility = View.VISIBLE
                        Toast.makeText(context, "Terjadi kesalahan jaringan",
                            Toast.LENGTH_SHORT).show()

                    })
        )
    }



    private fun showHasilPertandingan(responseList: List<HasilPertandingan.HasilData>) {
        if(responseList[0].JENIS_PERTANDINGAN.equals("versus", true)){
            pertandingan_multiple.visibility = View.GONE
            pertandingan_versus_rv.visibility = View.VISIBLE
            hasilPertandinganList.clear()
            for(data in responseList){
                hasilPertandinganList.add(data)
            }
            adapter = HasilPertandinganAdapter(hasilPertandinganList, this)
            pertandingan_versus_rv.adapter = adapter

        }else{
            val hasil = responseList[0]
            pertandingan_multiple.visibility = View.VISIBLE
            pertandingan_versus_rv.visibility = View.INVISIBLE

            nama_fakultas.text = hasil.NAMA_FAKULTAS
            tv_skor.text = hasil.SKOR
            nama_fakultas2.text = hasil.NAMA_FAKULTAS2
            tv_skor2.text = hasil.SKOR2
            nama_fakultas3.text = hasil.NAMA_FAKULTAS3
            tv_skor3.text = hasil.SKOR3
            nama_fakultas4.text = hasil.NAMA_FAKULTAS4
            tv_skor4.text = hasil.SKOR4
            nama_fakultas5.text = hasil.NAMA_FAKULTAS5
            tv_skor5.text = hasil.SKOR5
            nama_fakultas6.text = hasil.NAMA_FAKULTAS6
            tv_skor6.text = hasil.SKOR6
            nama_fakultas7.text = hasil.NAMA_FAKULTAS7
            tv_skor7.text = hasil.SKOR7
            nama_fakultas8.text = hasil.NAMA_FAKULTAS8
            tv_skor8.text = hasil.SKOR8
            nama_fakultas9.text = hasil.NAMA_FAKULTAS9
            tv_skor9.text = hasil.SKOR9
            nama_fakultas10.text = hasil.NAMA_FAKULTAS10
            tv_skor10.text = hasil.SKOR10
            nama_fakultas11.text = hasil.NAMA_FAKULTAS11
            tv_skor11.text = hasil.SKOR11
            nama_fakultas12.text = hasil.NAMA_FAKULTAS12
            tv_skor12.text = hasil.SKOR12
            nama_fakultas13.text = hasil.NAMA_FAKULTAS13
            tv_skor13.text = hasil.SKOR13
            nama_fakultas14.text = hasil.NAMA_FAKULTAS14
            tv_skor14.text = hasil.SKOR14
            nama_fakultas15.text = hasil.NAMA_FAKULTAS15
            tv_skor15.text = hasil.SKOR15
            nama_fakultas16.text = hasil.NAMA_FAKULTAS16
            tv_skor16.text = hasil.SKOR16
            nama_fakultas17.text = hasil.NAMA_FAKULTAS17
            tv_skor17.text = hasil.SKOR17


        }

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

