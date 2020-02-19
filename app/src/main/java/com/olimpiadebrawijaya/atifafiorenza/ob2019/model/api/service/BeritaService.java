package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;


import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BeritaService {

    //ob 2019
    @GET("DataBerita/getDataBeritaAll")
    Call<News.NewsResponse> getAllNews();

}
