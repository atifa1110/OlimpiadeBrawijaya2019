package com.olimpiadebrawijaya.atifafiorenza.ob2019.util;

import android.content.Context;
import com.olimpiadebrawijaya.atifafiorenza.ob2019.R;

public class FakultasConverter {

    public static String getSingkatanFakultas(String namaFakultas, Context context) {
        if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFH))) {
            return "FH";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFEB))) {
            return "FEB";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFIA))) {
            return "FIA";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFP))) {
            return "FP";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFapet))) {
            return "FAPET";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFT))) {
            return "FT";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFK))) {
            return "FK";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFPIK))) {
            return "FPIK";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFMIPA))) {
            return "FMIPA";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFTP))) {
            return "FTP";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFISIP))) {
            return "FISIP";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFIB))) {
            return "FIB";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFKH))) {
            return "FKH";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFKG))) {
            return "FKG";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasFILKOM))) {
            return "FILKOM";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.fakultasVokasi))) {
            return "VOKASI";
        } else if (namaFakultas.equals(context.getResources().getString(R.string.UBKampus3))) {
            return "UB KAMPUS 3";
        } else if (namaFakultas.equals("UB Kediri")) {
            return "UB KAMPUS 3";
        }
        return namaFakultas;
    }

    public static int getFakultasDrawable(String namaFakultas) {
        if (namaFakultas.equals("FH")) {
            return R.mipmap.ic_fh;
        } else if (namaFakultas.equals("FEB")) {
            return R.mipmap.ic_feb;
        } else if (namaFakultas.equals("FIA")) {
            return R.mipmap.ic_fia;
        } else if (namaFakultas.equals("FP")) {
            return R.mipmap.ic_fp;
        } else if (namaFakultas.equals("FAPET")) {
            return R.mipmap.ic_fapet;
        } else if (namaFakultas.equals("FT")) {
            return R.mipmap.ic_ft;
        } else if (namaFakultas.equals("FK")) {
            return R.mipmap.ic_fk;
        } else if (namaFakultas.equals("FPIK")) {
            return R.mipmap.ic_fpik;
        } else if (namaFakultas.equals("FMIPA")) {
            return R.mipmap.ic_fmipa;
        } else if (namaFakultas.equals("FTP")) {
            return R.mipmap.ic_ftp;
        } else if (namaFakultas.equals("FISIP")) {
            return R.mipmap.ic_fisip;
        } else if (namaFakultas.equals("FIB")) {
            return R.mipmap.ic_fib;
        } else if (namaFakultas.equals("FKH")) {
            return R.mipmap.ic_fkh;
        } else if (namaFakultas.equals("FKG")) {
            return R.mipmap.ic_fkg;
        } else if (namaFakultas.equals("FILKOM")) {
            return R.mipmap.ic_filkom;
        } else if (namaFakultas.equals("VOKASI")) {
            return R.mipmap.ic_vokasi;
        } else if (namaFakultas.equals("UB Kampus 3")) {
            return R.mipmap.ic_ub_kediri;
        } else if (namaFakultas.equals("UB Kediri")) {
            return R.mipmap.ic_ub_kediri;
        } else {
            return 0;
        }
    }
}
