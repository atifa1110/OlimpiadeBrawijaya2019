package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.Enroll;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EnrollService {

    @GET("DataPanitia/enrollKey")
    Call<Enroll.EnrollResponse> sendEnrollKey(@Query("nim") String nim,
                                              @Query("nama") String nama,
                                              @Query("key") String key);
}