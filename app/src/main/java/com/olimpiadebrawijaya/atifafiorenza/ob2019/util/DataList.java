package com.olimpiadebrawijaya.atifafiorenza.ob2019.util;

import android.content.Context;
import android.support.annotation.Nullable;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.CabangOlahraga;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.view.Fakultas;

import java.util.ArrayList;

public class DataList {

    public static Context context;

    public static final CabangOlahraga[] cabangOlahragaList = {
            new CabangOlahraga(1, R.mipmap.ic_cabor_atletik, R.string.caborAtletik), // fix
            new CabangOlahraga(2, R.mipmap.ic_cabor_badminton, R.string.caborBadminton), // fix
            //new CabangOlahraga(3, R.mipmap.ic_cabor_basket, R.string.caborBasket), // fix
            new CabangOlahraga(4, R.mipmap.ic_cabor_bridge, R.string.caborBridge), // fix

            new CabangOlahraga(5, R.mipmap.ic_cabor_catur, R.string.caborCatur), // fix
            new CabangOlahraga(6, R.mipmap.ic_cabor_dangdut, R.string.caborMenyanyiDangdut), // fix
            new CabangOlahraga(7, R.mipmap.ic_cabor_debat_indo, R.string.caborDebatIndonesia), // fix
            new CabangOlahraga(8, R.mipmap.ic_cabor_debat_inggris, R.string.caborDebatInggris), // fix
            new CabangOlahraga(9, R.mipmap.ic_cabor_fotografi, R.string.caborFotografi), // fix
            //new CabangOlahraga(10, R.mipmap.ic_cabor_futsal, R.string.caborFutsal), // gk ada di list

            new CabangOlahraga(11, R.mipmap.ic_cabor_karate, R.string.caborKarate), // gk ada di list
            new CabangOlahraga(12, R.mipmap.ic_cabor_kempo, R.string.caborKempo), // fix
            new CabangOlahraga(13, R.mipmap.ic_cabor_komik, R.string.caborKomik), // fix

            new CabangOlahraga(14, R.mipmap.ic_cabor_ml, R.string.caborMobileLegend), // fix
            new CabangOlahraga(15, R.mipmap.ic_cabor_padus, R.string.caborPaduanSuara), // fix

            new CabangOlahraga(16, R.mipmap.ic_cabor_panahan, R.string.caborPanahan), // fix
            new CabangOlahraga(17, R.mipmap.ic_cabor_pecak_silat, R.string.caborPencakSilat), // fix
            new CabangOlahraga(18, R.mipmap.ic_cabor_pop,R.string.caborMenyanyiPop),

            new CabangOlahraga(19, R.mipmap.ic_cabor_poster, R.string.caborDesainPoster), // fix
            new CabangOlahraga(20, R.mipmap.ic_cabor_pubg, R.string.caborPUBG), // fix

            new CabangOlahraga(21, R.mipmap.ic_cabor_puisi, R.string.caborPuisi), // fix

            new CabangOlahraga(22, R.mipmap.ic_cabor_renang, R.string.caborRenang), // fix
            new CabangOlahraga(23, R.mipmap.ic_cabor_sepakbola, R.string.caborSepakBola), // fix
            new CabangOlahraga(24, R.mipmap.ic_cabor_seriosa, R.string.caborMenyanyiSeriosa), // fix
            new CabangOlahraga(25, R.mipmap.ic_cabor_sinema, R.string.caborSinematografi), // gk ada di list
            new CabangOlahraga(26, R.mipmap.ic_cabor_taekwondo, R.string.caborTaekwondo), // fix

            new CabangOlahraga(27, R.mipmap.ic_cabor_tenis_lapangan, R.string.caborTennisLapangan), // fix
            new CabangOlahraga(28, R.mipmap.ic_cabor_tenis_meja, R.string.caborTennisMeja), // gk ada di list
            new CabangOlahraga(29, R.mipmap.ic_cabor_vocal_grup, R.string.caborPaduanSuara), //
            new CabangOlahraga(30, R.mipmap.ic_cabor_band, R.string.caborFestivalBand), // fix
            //new CabangOlahraga(17, R.mipmap.ic_cabor_voli, R.string.caborVoli), // fix
    };

    public static final Fakultas[] fakultasList = {
            new Fakultas(1, R.mipmap.ic_fh, R.string.fakultasFH),
            new Fakultas(2, R.mipmap.ic_feb, R.string.fakultasFEB),
            new Fakultas(3, R.mipmap.ic_fia, R.string.fakultasFIA),
            new Fakultas(4, R.mipmap.ic_fp, R.string.fakultasFP), // SOMETHING
            new Fakultas(5, R.mipmap.ic_fapet, R.string.fakultasFapet),
            new Fakultas(6, R.mipmap.ic_ft, R.string.fakultasFT),
            new Fakultas(7, R.mipmap.ic_fk, R.string.fakultasFK),
            new Fakultas(8, R.mipmap.ic_fpik, R.string.fakultasFPIK),
            new Fakultas(9, R.mipmap.ic_fmipa, R.string.fakultasFMIPA),
            new Fakultas(10, R.mipmap.ic_ftp, R.string.fakultasFTP),
            new Fakultas(11, R.mipmap.ic_fisip, R.string.fakultasFISIP),
            new Fakultas(12, R.mipmap.ic_fib, R.string.fakultasFIB),
            new Fakultas(13, R.mipmap.ic_fkh, R.string.fakultasFKH),
            new Fakultas(14, R.mipmap.ic_fkg, R.string.fakultasFKG),
            new Fakultas(15, R.mipmap.ic_filkom, R.string.fakultasFILKOM),
            new Fakultas(16, R.mipmap.ic_vokasi, R.string.fakultasVokasi),
            new Fakultas(17, R.mipmap.ic_ub_kediri, R.string.UBKampus3)
    };

    /**
     * modified by arifinfirdaus
     */
    public static ArrayList<Fakultas> getFakultasArrayList() {
        ArrayList<Fakultas> fakultasArrayList = new ArrayList<>();
        fakultasArrayList.add(new Fakultas(1, R.mipmap.ic_fh, R.string.fakultasFH));
        fakultasArrayList.add(new Fakultas(2, R.mipmap.ic_feb, R.string.fakultasFEB));
        fakultasArrayList.add(new Fakultas(3, R.mipmap.ic_fia, R.string.fakultasFIA));
        fakultasArrayList.add(new Fakultas(4, R.mipmap.ic_fp, R.string.fakultasFP));
        fakultasArrayList.add(new Fakultas(5, R.mipmap.ic_fapet, R.string.fakultasFapet));
        fakultasArrayList.add(new Fakultas(6, R.mipmap.ic_ft, R.string.fakultasFT));
        fakultasArrayList.add(new Fakultas(7, R.mipmap.ic_fk, R.string.fakultasFK));
        fakultasArrayList.add(new Fakultas(8, R.mipmap.ic_fpik, R.string.fakultasFPIK));
        fakultasArrayList.add(new Fakultas(9, R.mipmap.ic_fmipa, R.string.fakultasFMIPA));
        fakultasArrayList.add(new Fakultas(10, R.mipmap.ic_ftp, R.string.fakultasFTP));
        fakultasArrayList.add(new Fakultas(11, R.mipmap.ic_fisip, R.string.fakultasFISIP));
        fakultasArrayList.add(new Fakultas(12, R.mipmap.ic_fib, R.string.fakultasFIB));
        fakultasArrayList.add(new Fakultas(13, R.mipmap.ic_fkh, R.string.fakultasFKH));
        fakultasArrayList.add(new Fakultas(14, R.mipmap.ic_fkg, R.string.fakultasFKG));
        fakultasArrayList.add(new Fakultas(15, R.mipmap.ic_filkom, R.string.fakultasFILKOM));
        fakultasArrayList.add(new Fakultas(16, R.mipmap.ic_vokasi, R.string.fakultasVokasi));
        fakultasArrayList.add(new Fakultas(17, R.mipmap.ic_ub_kediri, R.string.UBKampus3));
        return fakultasArrayList;
    }


    public static void initialize(Context mContext) {
        for (CabangOlahraga cabangOlahraga : cabangOlahragaList) {
            cabangOlahraga.setCaborName(mContext.getResources().getString(cabangOlahraga.getCaborNameRes()));
            cabangOlahraga.setCaborIconRes(cabangOlahraga.getCaborIconRes());
        }

        for (Fakultas fakultas : fakultasList) {
            fakultas.setFakultasName(mContext.getResources().getString(fakultas.getFakultasNameRes()));
        }

    }

    @Nullable
    public static CabangOlahraga getCabangOlahragaObject(String namaCabor) {
        for (CabangOlahraga current : cabangOlahragaList) {
            if (current.getCaborName().equalsIgnoreCase(namaCabor)) return current;
        }
        return null;
    }

    @Nullable
    public static Fakultas getFakultasObject(String namaFakultas) {
        for (Fakultas current : fakultasList) {
            if (current.getFakultasName().equalsIgnoreCase(namaFakultas)) return current;
        }
        return null;
    }
}
