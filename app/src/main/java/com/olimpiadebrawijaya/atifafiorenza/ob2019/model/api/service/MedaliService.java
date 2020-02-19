package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Klasemen;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MedaliService {

    @GET("DataKlasemen/getKlasemenAll")
    Observable<Klasemen.KlasemenResponse> getListMedali();

    @GET("DataKlasemen/getKlasemenByFakultas")
    Call<Klasemen.KlasemenResponse2> getListMedalibyFakultas(@Query("fakultas") String idFakultas);

}
