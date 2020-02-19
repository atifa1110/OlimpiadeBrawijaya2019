package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.HasilPertandingan;
        import io.reactivex.Observable;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

public interface HasilService {
    @GET("DataLomba/getKategori")
    Observable<HasilPertandingan.KategoriResponse> getKtegoriCabor(@Query("cabor") String namaCabor);

    @GET("DataLomba/getHasilPertandingan")
    Observable<HasilPertandingan.HasilResponse> getHasilPertandingan(@Query("cabor") String namaCabor,
                                                                     @Query("kategori") String kategori);

}
