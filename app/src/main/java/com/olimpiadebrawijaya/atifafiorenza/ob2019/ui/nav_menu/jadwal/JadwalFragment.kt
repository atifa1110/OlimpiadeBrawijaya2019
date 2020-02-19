package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.jadwal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.adapter.JadwalAdapter
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Jadwal
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.ActualJadwal
import com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence.JadwalFavorit
import com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence.JadwalFavoritDao
import com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence.OBDatabase
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.DataList
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.FakultasConverter
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_jadwal.*
import java.text.SimpleDateFormat
import java.util.*


class JadwalFragment : Fragment(), JadwalAdapter.onAdapterClickListener ,
    ViewPager.OnPageChangeListener{

    var dateList: ArrayList<String> = arrayListOf()
    var formattedDateList: ArrayList<String> = arrayListOf()
    lateinit var mAdapter: JadwalAdapter
    var mJadwalList: ArrayList<ActualJadwal> = arrayListOf()

    private var compositeDisposable: CompositeDisposable? = null
    private var jadwalFavoritDao: JadwalFavoritDao? = null

    var dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    lateinit var formattedDate: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDate()

        viewPager.adapter = DateViewPagerAdapter(context, formattedDateList)
        viewPagerArrowIndicator.setArrowIndicatorRes(R.mipmap.arrow_left,
            R.mipmap.arrow_right)
        viewPagerArrowIndicator.bind(viewPager)
        viewPager.addOnPageChangeListener(this)

        val currentDate = Calendar.getInstance().time
        formattedDate = dateFormatter.format(currentDate)

        var position = 0

        for (i in 0 until dateList.size) {
            if (dateList[i] == formattedDate) {
                position = i
                break
            }
        }

        viewPager.setCurrentItem(position, false)

        mJadwalList = arrayListOf()

        compositeDisposable = CompositeDisposable()

        val obDatabase = OBDatabase.getInstance(context!!)
        jadwalFavoritDao = obDatabase.jadwalFavoritDao()

        setupRecyclerView()
        fetchData(formattedDate)

    }

    private fun setupDate() {
        for (i in 16..30) {
            dateList.add("2019-09-$i")
        }
        for (i in 1..13) {
            dateList.add("2019-10-$i")
        }

        for (i in 16..30) {
            formattedDateList.add("$i September 2019")
        }
        for (i in 1..13) {
            formattedDateList.add("$i Oktober 2019")
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false)
        mAdapter = JadwalAdapter(mJadwalList, context, this)
        recyclerView.adapter = mAdapter
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    //date viewpager page change
    override fun onPageSelected(position: Int) {
        fetchData(dateList[position])
    }

    private fun fetchData(formattedDate: String) {
        progressBar.visibility = View.VISIBLE
        tv_tidak_ada_data_today_fragment!!.visibility = View.GONE
        compositeDisposable?.add(
            RestClient.jadwalService.getJadwalByWaktu(formattedDate)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        progressBar.visibility = View.GONE
                        val responseList = result.data

                        if (responseList != null) {
                            tv_tidak_ada_data_today_fragment!!.visibility = View.GONE
                            mJadwalList.clear()
                            for (data in responseList) {
                                convertJadwal(data)
                            }
                            mAdapter.notifyDataSetChanged()
                            loadJadwalFavorite()
                        } else {
                            mJadwalList.clear()
                            mAdapter.notifyDataSetChanged()
                            tv_tidak_ada_data_today_fragment!!.visibility = View.VISIBLE
                        }

                    },
                    { error ->
                        progressBar.visibility = View.GONE
                        tv_tidak_ada_data_today_fragment!!.visibility = View.VISIBLE
                        Toast.makeText(context, "Terjadi kesalahan jaringan",
                            Toast.LENGTH_SHORT).show()

                    })
        )


    }

    private fun convertJadwal(data: Jadwal.JadwalData) {
        val jadwal = ActualJadwal()

        jadwal.idJadwal = data.ID_JADWAL.toInt()
        jadwal.namaJadwal = data.NAMA_JADWAL
        val cabor = DataList.getCabangOlahragaObject(data.NAMA_CABOR)
        jadwal.caborRes = cabor?.caborIconRes
        jadwal.caborName = data.NAMA_CABOR
        if (data.KATEGORI_CABOR.isBlank()) {
            jadwal.caborKet = "Babak Penyisihan"
        } else {
            jadwal.caborKet = data.KATEGORI_CABOR
        }
        jadwal.caborDate = data.WAKTU
        jadwal.caborPlace = data.VENUE

        val jenisPertandingan = data.JENIS_PERTANDINGAN

        if (jenisPertandingan.equals("Versus")) {
            jadwal.team1Name = data.NAMA_FAKULTAS
            jadwal.team1Res = FakultasConverter.getFakultasDrawable(data.NAMA_FAKULTAS)
            jadwal.team2Name = data.NAMA_FAKULTAS2
            jadwal.team2Res = FakultasConverter.getFakultasDrawable(data.NAMA_FAKULTAS2)
            if(data.SKOR.isBlank() && data.SKOR.isBlank()){
                jadwal.team1Score = "0"
                jadwal.team2Score = "0"
            }else{
                jadwal.team1Score = data.SKOR
                jadwal.team2Score = data.SKOR2
            }

            jadwal.isVersus = true
        } else {
            jadwal.isVersus = false
        }

        jadwal.isFavorit = false

        mJadwalList.add(jadwal)
    }

    private fun loadJadwalFavorite() {
        val disposable = jadwalFavoritDao!!.allJadwalFavorit
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ listJadwalFavorit -> onGetJadwalFavoritSuccess(listJadwalFavorit) },
                { throwable ->
                    Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT)
                        .show()
                })

        compositeDisposable!!.add(disposable)
    }

    private fun onGetJadwalFavoritSuccess(listJadwalFavorit: List<JadwalFavorit>?) {
        for (jadwal in mJadwalList) {
            for (jadwalFavorit in listJadwalFavorit!!) {
                if (jadwal.idJadwal == jadwalFavorit.jadwalId) {
                    jadwal.isFavorit = true
                }
            }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onFavoriteClicked(view: View, position: Int) {

        if (mJadwalList!![position].isFavorit) {
            mJadwalList!![position].isFavorit = false
            removeJadwalFromFavorite(mJadwalList!![position], position)
        } else {
            mJadwalList!![position].isFavorit = true
            addJadwalToFavorit(mJadwalList!![position], position)
        }


    }

    private fun removeJadwalFromFavorite(jadwalResponse: ActualJadwal, position: Int) {
        val disposable = Completable.fromAction {
            jadwalFavoritDao!!.deleteJadwalFavoritById(jadwalResponse.idJadwal)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Toast.makeText(context, "Jadwal dihapus dari favorit",
                        Toast.LENGTH_SHORT)
                        .show()
                },
                { throwable ->
                    Toast.makeText(context, "Error remove faovorit",
                        Toast.LENGTH_SHORT)
                        .show()
                })
        compositeDisposable!!.add(disposable)
    }


    private fun addJadwalToFavorit(jadwal: ActualJadwal, position: Int) {
        compositeDisposable!!.add(
            Completable.fromAction {
                val jadwalfavorit = JadwalFavorit()
                jadwalfavorit.jadwalId = jadwal.idJadwal
                jadwalfavorit.jadwalName = jadwal.namaJadwal
                jadwalfavorit.caborRes = jadwal.caborRes!!
                jadwalfavorit.caborName = jadwal.caborName
                jadwalfavorit.caborKet = jadwal.caborKet
                jadwalfavorit.caborDate = jadwal.caborDate
                jadwalfavorit.caborPlace = jadwal.caborPlace
                if (jadwal.isVersus) {
                    jadwalfavorit.team1Res = jadwal.team1Res
                    jadwalfavorit.team1Name = jadwal.team1Name
                    jadwalfavorit.team1Score = jadwal.team1Score.toInt()
                    jadwalfavorit.team2Res = jadwal.team2Res
                    jadwalfavorit.team2Name = jadwal.team2Name
                    jadwalfavorit.team2Score = jadwal.team2Score.toInt()
                    jadwalfavorit.versus = 1
                } else {
                    jadwalfavorit.versus = 0
                }
                jadwalfavorit.favorited = 1
                jadwalFavoritDao?.insertJadwalFavorit(jadwalfavorit)
            }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        Toast.makeText(context,
                            "Berhasil ditambahkan ke favorit",
                            Toast.LENGTH_SHORT)
                            .show()
                    },
                    { throwable ->
                        Toast.makeText(context,
                            throwable.message,
                            Toast.LENGTH_SHORT)
                            .show()
                    }))
    }

    override fun onDestroy() {
        compositeDisposable!!.clear()
        super.onDestroy()
    }
}
