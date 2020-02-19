package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.klasmen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.RestClient
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Klasemen
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_klasmen.*
import java.util.ArrayList

class KlasmenFragment : Fragment() {
    lateinit var mKlasmenAdapter: MedaliAdapter
    private lateinit var mKlasmenList: ArrayList<Klasemen.Medali>
    private var compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_klasmen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mKlasmenList = ArrayList()
        setupRecyclerView(klasmen_recyclerView)

        fetchData()
    }

    private fun fetchData() {
        compositeDisposable.add(
            RestClient.medaliService.listMedali
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        val responseList = result.data
                        if(responseList != null){
                            mKlasmenList.clear()
                            mKlasmenList.addAll(responseList)
                            mKlasmenAdapter.notifyDataSetChanged()
                        }
                    },
                    {error ->
                        Toast.makeText(context, ""+error.message,
                            Toast.LENGTH_SHORT).show()
                    })
        )

    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        mKlasmenAdapter = MedaliAdapter(mKlasmenList, context)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mKlasmenAdapter
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}