package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.favorit


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence.JadwalFavorit
import com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence.JadwalFavoritDao
import com.olimpiadebrawijaya.atifafiorenza.ob2019.persistence.OBDatabase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import java.util.*
import kotlinx.android.synthetic.main.fragment_favorit.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritFragment : Fragment() , JadwalFavoritAdapter.onAdapterClickListener{

    lateinit var mAdapter: JadwalFavoritAdapter

    private var mJadwalList: ArrayList<JadwalFavorit>? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var jadwalFavoritDao : JadwalFavoritDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        compositeDisposable = CompositeDisposable()

        val obDatabase = OBDatabase.getInstance(context!!)
        jadwalFavoritDao = obDatabase.jadwalFavoritDao()

        mJadwalList = ArrayList()

        setupRecyclerView()
        loadJadwalFavorite()
    }

    private fun loadJadwalFavorite() {
        val disposable = jadwalFavoritDao!!.allJadwalFavorit
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ jadwalFavorit -> onGetJadwalFavoriteSucsess(jadwalFavorit) },
                { throwable ->
                    Toast.makeText(context, throwable.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                })

        compositeDisposable!!.add(disposable)
    }

    private fun onGetJadwalFavoriteSucsess(jadwalFavorit: List<JadwalFavorit>?) {
        mJadwalList?.clear()
        if(jadwalFavorit!!.isNotEmpty()){
            mJadwalList?.addAll(jadwalFavorit)
        }else{
            tv_tidak_ada_data_today_fragment.visibility = View.VISIBLE
        }
        mAdapter.notifyDataSetChanged()
    }


    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager
        mAdapter = JadwalFavoritAdapter(mJadwalList, context, this)
        recyclerView.adapter = mAdapter
    }


    override fun onFavoriteClicked(view: View, position: Int) {
        if (mJadwalList?.get(position)?.favorited == 1) {
            mJadwalList?.get(position)?.favorited = 0
            removeJadwalFromFavorite(mJadwalList?.get(position)!!)
        } else {
            mJadwalList?.get(position)?.favorited = 1
        }
    }

    private fun removeJadwalFromFavorite(jadwal: JadwalFavorit) {
        val disposable = Completable.fromAction {
            jadwalFavoritDao!!.deleteJadwalFavoritById(jadwal.jadwalId)
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    loadJadwalFavorite()
                    Toast.makeText(context, "ActualJadwal dihapus dari favorit",
                        Toast.LENGTH_SHORT)
                        .show()
                },
                { throwable ->
                    Toast.makeText(context, throwable.message.toString(),
                        Toast.LENGTH_SHORT)
                        .show()
                })
        compositeDisposable!!.add(disposable)
    }

    override fun onDestroy() {
        compositeDisposable!!.clear()
        super.onDestroy()
    }

}
