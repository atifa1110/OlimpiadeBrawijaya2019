package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Jadwal;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JadwalService {
    @GET("DataLomba/getJadwalByWaktu")
    Observable<Jadwal.JadwalResponse> getJadwalByWaktu(@Query("waktu_cabor") String waktuCabor);

    @GET("DataLomba/getJadwalByFakultas")
    Observable<Jadwal.JadwalResponse> getJadwalByFakultas(@Query("fakultas") String fakultas);


}
