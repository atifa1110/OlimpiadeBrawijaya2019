package com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.CabangOlahraga;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.ui.nav_menu.hasil_pertandingan.adapter.CaborAdapter;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.util.ItemOffsetDecoration;

import java.util.ArrayList;
import java.util.List;

public class CaborFragment extends Fragment implements CaborAdapter.ClickListener {

    public static CaborFragment newInstance() {

        Bundle args = new Bundle();
        CaborFragment fragment = new CaborFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerView recyclerView;
    public List<CabangOlahraga> mCaborList;
    public CaborAdapter adapter;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cabor, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.cabor_recyclerView);

        prepareCaborData();

        adapter = new CaborAdapter(mCaborList);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.itemOffset));
        recyclerView.setAdapter(adapter);
    }

    private void prepareCaborData() {
        mCaborList = new ArrayList<>();

        mCaborList.add(new CabangOlahraga(1, R.mipmap.ic_cabor_atletik, R.string.caborAtletik, getResources().getString(R.string.caborAtletik), false));
        mCaborList.add(new CabangOlahraga(2, R.mipmap.ic_cabor_badminton, R.string.caborBadminton, getResources().getString(R.string.caborBadminton), false));
        //mCaborList.add(new CabangOlahraga(3, R.mipmap.ic_cabor_basket, R.string.caborBasket, getResources().getString(R.string.caborBasket), false));
        mCaborList.add(new CabangOlahraga(4, R.mipmap.ic_cabor_bridge, R.string.caborBridge, getResources().getString(R.string.caborBridge), false));
        mCaborList.add(new CabangOlahraga(5, R.mipmap.ic_cabor_catur, R.string.caborCatur, getResources().getString(R.string.caborCatur), false));

        mCaborList.add(new CabangOlahraga(6, R.mipmap.ic_cabor_dangdut, R.string.caborMenyanyiDangdut, getResources().getString(R.string.caborMenyanyiDangdut), false));
        mCaborList.add(new CabangOlahraga(7, R.mipmap.ic_cabor_debat_indo, R.string.caborDebatIndonesia, getResources().getString(R.string.caborDebatIndonesia), false));
        mCaborList.add(new CabangOlahraga(8, R.mipmap.ic_cabor_debat_inggris, R.string.caborDebatInggris, getResources().getString(R.string.caborDebatInggris), false));
        mCaborList.add(new CabangOlahraga(9, R.mipmap.ic_cabor_fotografi, R.string.caborFotografi, getResources().getString(R.string.caborFotografi), false));
        //mCaborList.add(new CabangOlahraga(10, R.mipmap.ic_cabor_futsal, R.string.caborFutsal, getResources().getString(R.string.caborFutsal), false));

        mCaborList.add(new CabangOlahraga(11, R.mipmap.ic_cabor_karate, R.string.caborKarate, getResources().getString(R.string.caborKarate), false));
        mCaborList.add(new CabangOlahraga(12, R.mipmap.ic_cabor_kempo, R.string.caborKempo, getResources().getString(R.string.caborKempo), false));
        mCaborList.add(new CabangOlahraga(13, R.mipmap.ic_cabor_komik, R.string.caborKomik, getResources().getString(R.string.caborKomik), false));
        mCaborList.add(new CabangOlahraga(14, R.mipmap.ic_cabor_ml, R.string.caborMobileLegend, getResources().getString(R.string.caborMobileLegend), false));
        mCaborList.add(new CabangOlahraga(15, R.mipmap.ic_cabor_padus, R.string.caborPaduanSuara, getResources().getString(R.string.caborPaduanSuara), false));

        mCaborList.add(new CabangOlahraga(16, R.mipmap.ic_cabor_panahan, R.string.caborPanahan, getResources().getString(R.string.caborPanahan), false));
        mCaborList.add(new CabangOlahraga(17, R.mipmap.ic_cabor_pecak_silat, R.string.caborPencakSilat, getResources().getString(R.string.caborPencakSilat), false));

        mCaborList.add(new CabangOlahraga(18, R.mipmap.ic_cabor_pop, R.string.caborMenyanyiPop, getResources().getString(R.string.caborMenyanyiPop), false));
        mCaborList.add(new CabangOlahraga(19, R.mipmap.ic_cabor_poster, R.string.caborDesainPoster, getResources().getString(R.string.caborDesainPoster), false));
        mCaborList.add(new CabangOlahraga(20, R.mipmap.ic_cabor_pubg, R.string.caborPUBG, getResources().getString(R.string.caborPUBG), false));
        mCaborList.add(new CabangOlahraga(21, R.mipmap.ic_cabor_puisi, R.string.caborPuisi, getResources().getString(R.string.caborPuisi), false));
        mCaborList.add(new CabangOlahraga(22, R.mipmap.ic_cabor_renang, R.string.caborRenang, getResources().getString(R.string.caborRenang), false));

        mCaborList.add(new CabangOlahraga(23, R.mipmap.ic_cabor_sepakbola, R.string.caborSepakBola, getResources().getString(R.string.caborSepakBola), false));
        mCaborList.add(new CabangOlahraga(24, R.mipmap.ic_cabor_seriosa, R.string.caborMenyanyiSeriosa, getResources().getString(R.string.caborMenyanyiSeriosa), false));
        mCaborList.add(new CabangOlahraga(25, R.mipmap.ic_cabor_sinema, R.string.caborSinematografi, getResources().getString(R.string.caborSinematografi), false));

        mCaborList.add(new CabangOlahraga(26, R.mipmap.ic_cabor_taekwondo, R.string.caborTaekwondo, getResources().getString(R.string.caborTaekwondo), false));
        mCaborList.add(new CabangOlahraga(27, R.mipmap.ic_cabor_tenis_lapangan, R.string.caborTennisLapangan, getResources().getString(R.string.caborTennisLapangan), false));
        mCaborList.add(new CabangOlahraga(28, R.mipmap.ic_cabor_tenis_meja, R.string.caborTennisMeja, getResources().getString(R.string.caborTennisMeja), false));
        mCaborList.add(new CabangOlahraga(29, R.mipmap.ic_cabor_vocal_grup, R.string.caborVocalGrup, getResources().getString(R.string.caborVocalGrup), false));
        mCaborList.add(new CabangOlahraga(30, R.mipmap.ic_cabor_band, R.string.caborFestivalBand, getResources().getString(R.string.caborFestivalBand), false));

    }


    @Override
    public void itemClicked(View view, int position) {
        CabangOlahraga cabor = mCaborList.get(position);
        Intent intent = new Intent(getContext(), KategoriActivity.class);
        intent.putExtra("caborId", cabor.getId());
        intent.putExtra("caborName", cabor.getCaborName());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.notifyDataSetChanged();
    }

}
