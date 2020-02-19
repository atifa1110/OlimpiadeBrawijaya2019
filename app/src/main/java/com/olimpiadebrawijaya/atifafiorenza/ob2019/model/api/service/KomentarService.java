package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Komentar;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KomentarService {
    @GET("DataBerita/getDataKomentar")
    Observable<Komentar.KomentarResponse> getKomentarBerita(@Query("id_berita") String id_berita);

    @GET("DataBerita/setDataKomentar")
    Observable<Komentar.SendKomentarResponse> setKomentarBerita(@Query("id_berita") String id_berita,
                                                                @Query("nama_pengguna") String nama_pengguna,
                                                                @Query("komentar_pengguna") String komentar_pengguna);

}
