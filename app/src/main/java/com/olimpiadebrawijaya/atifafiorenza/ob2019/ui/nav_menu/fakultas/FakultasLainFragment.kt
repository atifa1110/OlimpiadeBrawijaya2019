package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.fakultas


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.Fakultas
import java.util.ArrayList

class FakultasLainFragment : Fragment() , FakultasAdapter.ClickListener{

    lateinit var recyclerView: RecyclerView
    lateinit var fakultasList: MutableList<Fakultas>
    lateinit var adapter: FakultasAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fakultas_lain, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<View>(R.id.fakultasLain_recycler) as RecyclerView

        fakultasList = ArrayList()

        fakultasList.add(Fakultas(1, R.mipmap.ic_fh,
            R.string.fakultasFH
        ))
        fakultasList.add(Fakultas(2, R.mipmap.ic_feb,
            R.string.fakultasFEB
        ))
        fakultasList.add(Fakultas(3, R.mipmap.ic_fia,
            R.string.fakultasFIA
        ))
        fakultasList.add(Fakultas(4, R.mipmap.ic_fp,
            R.string.fakultasFP
        ))
        fakultasList.add(Fakultas(5, R.mipmap.ic_fapet,
            R.string.fakultasFapet
        ))
        fakultasList.add(Fakultas(6, R.mipmap.ic_ft,
            R.string.fakultasFT
        ))
        fakultasList.add(Fakultas(7, R.mipmap.ic_fk,
            R.string.fakultasFK
        ))
        fakultasList.add(Fakultas(8, R.mipmap.ic_fpik,
            R.string.fakultasFPIK
        ))
        fakultasList.add(Fakultas(9, R.mipmap.ic_fmipa,
            R.string.fakultasFMIPA
        ))
        fakultasList.add(Fakultas(10, R.mipmap.ic_ftp,
            R.string.fakultasFTP
        ))
        fakultasList.add(Fakultas(11, R.mipmap.ic_fisip,
            R.string.fakultasFISIP
        ))
        fakultasList.add(Fakultas(12, R.mipmap.ic_fib,
            R.string.fakultasFIB
        ))
        fakultasList.add(Fakultas(13, R.mipmap.ic_fkh,
            R.string.fakultasFKH
        ))
        fakultasList.add(Fakultas(14, R.mipmap.ic_fkg,
            R.string.fakultasFKG
        ))
        fakultasList.add(Fakultas(15, R.mipmap.ic_filkom,
            R.string.fakultasFILKOM
        ))
        fakultasList.add(Fakultas(16, R.mipmap.ic_vokasi,
            R.string.fakultasVokasi
        ))
        fakultasList.add(Fakultas(17, R.mipmap.ic_ub_kediri,
            R.string.UBKampus3
        ))

        adapter = FakultasAdapter(context, fakultasList)
        adapter.setClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

    }

    override fun itemClicked(view: View, position: Int) {
        val fakultas = fakultasList[position]
        val fragment = FakultasResultFragment.newInstance(fakultas.id, fakultas.fakultasNameRes)

        val ft = activity!!.supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        ft.replace(R.id.content_frame, fragment)
        ft.commit()
    }

}
