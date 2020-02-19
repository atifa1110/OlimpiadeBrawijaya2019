package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.beranda

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.ketentuan.KetentuanFragment
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.berita.BeritaFragment
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.klasmen.KlasmenFragment
import kotlinx.android.synthetic.main.fragment_beranda.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BerandaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FragmentPagerItemAdapter(
            childFragmentManager, FragmentPagerItems.with(context)
                .add(R.string.viewPagerToday, TodayFragment::class.java)
                .add(R.string.navDrawerNews, BeritaFragment::class.java)
                .add(R.string.navDrawerKlasemen, KlasmenFragment::class.java)
                .add(R.string.ketentuan, KetentuanFragment::class.java)
                .create())


        jadwal_viewPager.adapter = adapter

        tabLayout.setViewPager(jadwal_viewPager)
        tabLayout.setOnTouchListener { v, event -> true }

    }

}
