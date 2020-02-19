package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.ketentuan;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import kotlinx.android.synthetic.main.content_berita_detail.*

class KetentuanFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ketentuan, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val syaratPenonton = arrayListOf(KetentuanContent(resources.getString(R.string.syaratPenonton1)),
            KetentuanContent(resources.getString(R.string.syaratPenonton2)),
            KetentuanContent(resources.getString(R.string.syaratPenonton3)),
            KetentuanContent(resources.getString(R.string.syaratPenonton4)),
            KetentuanContent(resources.getString(R.string.syaratPenonton5)),
            KetentuanContent(resources.getString(R.string.syaratPenonton6)),
            KetentuanContent(resources.getString(R.string.syaratPenonton7)),
            KetentuanContent(resources.getString(R.string.syaratPenonton8)),
            KetentuanContent(resources.getString(R.string.syaratPenonton9)),
            KetentuanContent(resources.getString(R.string.syaratPenonton10)),
            KetentuanContent(resources.getString(R.string.syaratPenonton11)),
            KetentuanContent(resources.getString(R.string.syaratPenonton12)))

        val peraturanTambahan = arrayListOf(KetentuanContent(resources.getString(R.string.peraturanTambahan1)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan2)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan3)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan4)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan5)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan6)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan7)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan8)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan9)),
            KetentuanContent(resources.getString(R.string.peraturanTambahan10))
        )

        val barangDilarang = arrayListOf(KetentuanContent(resources.getString(R.string.barangDilaranag1)),
            KetentuanContent(resources.getString(R.string.barangDilaranag2)),
            KetentuanContent(resources.getString(R.string.barangDilaranag3)),
            KetentuanContent(resources.getString(R.string.barangDilaranag4)),
            KetentuanContent(resources.getString(R.string.barangDilaranag5)),
            KetentuanContent(resources.getString(R.string.barangDilaranag6)),
            KetentuanContent(resources.getString(R.string.barangDilaranag7)),
            KetentuanContent(resources.getString(R.string.barangDilaranag8)),
            KetentuanContent(resources.getString(R.string.barangDilaranag9)),
            KetentuanContent(resources.getString(R.string.barangDilaranag10)),
            KetentuanContent(resources.getString(R.string.barangDilaranag11)),
            KetentuanContent(resources.getString(R.string.barangDilaranag12)),
            KetentuanContent(resources.getString(R.string.barangDilaranag13)),
            KetentuanContent(resources.getString(R.string.barangDilaranag14)))

        val genres = arrayListOf(Ketentuan("Syarat Penonoton", syaratPenonton),
            Ketentuan("Peraturan Tambahan", peraturanTambahan),
            Ketentuan("Barang yang Dilarang", barangDilarang))

        val layoutManager = LinearLayoutManager(context)

        //instantiate your adapter with the list of genres
        val adapter = KetentuanAdapter(genres)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
    }
}
