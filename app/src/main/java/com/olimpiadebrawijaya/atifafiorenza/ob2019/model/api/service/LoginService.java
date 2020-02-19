package com.olimpiadebrawijaya.atifafiorenza.ob2019.model.api.service;

import com.olimpiadebrawijaya.atifafiorenza.ob2019.model.json.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {
    @GET("login/loginApps")
    Call<User.LoginResponse> login(@Query("nim") String nim,
                                   @Query("password") String password);

}
