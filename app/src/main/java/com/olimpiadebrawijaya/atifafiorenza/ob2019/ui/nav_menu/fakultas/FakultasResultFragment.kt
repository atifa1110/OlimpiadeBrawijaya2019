package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.fakultas

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import kotlinx.android.synthetic.main.fragment_fakultas_result.*
import java.util.ArrayList


class FakultasResultFragment : Fragment() , SwipeRefreshLayout.OnRefreshListener , JadwalAdapter.onAdapterClickListener{

    private var mFakultasId: Int = 0
    private var mFakultasName: Int = 0
    private lateinit var singkatanFakultas : String

    private lateinit var adapter: JadwalAdapter
    private lateinit var mJadwalList: ArrayList<ActualJadwal>

    private var compositeDisposable: CompositeDisposable? = null
    private var jadwalFavoritDao: JadwalFavoritDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFakultasId = it.getInt(FAKULTAS)
            mFakultasName = it.getInt(FAKULTAS_NAME)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fakultas_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mFakultasId = arguments!!.getInt(FAKULTAS)
        mFakultasName = arguments!!.getInt(FAKULTAS_NAME)

        singkatanFakultas = FakultasConverter.getSingkatanFakultas(
            context!!.resources.getString(mFakultasName),
            context!!)
        (activity as AppCompatActivity).supportActionBar!!.title = singkatanFakultas

        compositeDisposable = CompositeDisposable()

        val obDatabase = OBDatabase.getInstance(context!!)
        jadwalFavoritDao = obDatabase.jadwalFavoritDao()

        result_text.visibility = View.GONE

        mJadwalList = arrayListOf()

        setupRecyclerView(result_recyclerView)
        fetchData()

    }

    // MARK: - SwipeRefreshLayout.OnRefreshListener
    override fun onRefresh() {
        fetchData()
    }


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = JadwalAdapter(mJadwalList, context, this)
        recyclerView.adapter = adapter
    }


    private fun fetchData() {
        progressBar.visibility = View.VISIBLE
        compositeDisposable?.add(
            RestClient.jadwalService.getJadwalByFakultas(singkatanFakultas)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            result ->
                        progressBar.visibility = View.GONE
                        val responseList = result.data

                        if(responseList != null){
                            result_text.visibility = View.GONE
                            mJadwalList.clear()
                            for(data in responseList){
                                convertJadwal(data)
                            }
                            adapter.notifyDataSetChanged()
                            loadJadwalFavorite()
                        }else{
                            result_text.visibility = View.VISIBLE
                        }

                        if (result_swipeRefresh.isRefreshing) {
                            result_swipeRefresh.isRefreshing = false
                        }

                    },
                    {
                        progressBar.visibility = View.GONE
                        result_text.visibility = View.VISIBLE
                        Toast.makeText(context, "Terjadi kesalahan jaringan",
                            Toast.LENGTH_SHORT).show()

                        if (result_swipeRefresh.isRefreshing) {
                            result_swipeRefresh.isRefreshing = false
                        }

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
        if(data.KATEGORI_CABOR.isBlank()){
            jadwal.caborKet = "Babak Penyisihan"
        }else{
            jadwal.caborKet = data.KATEGORI_CABOR
        }
        jadwal.caborDate = data.WAKTU
        jadwal.caborPlace = data.VENUE

        val jenisPertandingan = data.JENIS_PERTANDINGAN

        if(jenisPertandingan.equals("Versus")){
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
        }else{
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
        adapter.notifyDataSetChanged()
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

    companion object {

        val FAKULTAS = "FAKULTAS"
        val FAKULTAS_NAME = "FAKULTAS NAME"

        fun newInstance(fakultasId: Int, fakultasName: Int) : Fragment {

            val fragment = FakultasResultFragment()
            val args = Bundle()
            args.putInt(FAKULTAS, fakultasId)
            args.putInt(FAKULTAS_NAME, fakultasName)
            fragment.arguments = args
            return fragment
        }
    }
}
