package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service.*;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bradhawk on 9/30/2016.
 */

public class RestClient {

    private static Retrofit retrofit;

    public static BeritaService beritaService;
    public static MedaliService medaliService;
    public static JadwalService jadwalService;
    public static KomentarService komentarService;
    public static LoginService loginService;
    public static HasilService hasilService;
    //public static EnrollService enrollService;

    public static void initialize() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://orsim.ub.ac.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();

        beritaService = retrofit.create(BeritaService.class);
        medaliService = retrofit.create(MedaliService.class);
        jadwalService = retrofit.create(JadwalService.class);
        komentarService = retrofit.create(KomentarService.class);
        loginService = retrofit.create(LoginService.class);
        hasilService = retrofit.create(HasilService.class);
        //enrollService = retrofit.create(EnrollService.class);
    }

}
