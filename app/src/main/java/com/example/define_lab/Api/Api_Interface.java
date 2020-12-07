package com.example.define_lab.Api;

import com.example.define_lab.Model.Message_Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Interface {

    @GET("search")
    Call<Message_Model> getmatches(
            @Header("Accept") String header,
            @Query("ll") String ll,
            @Query("oauth_token") String token,
            @Query("v") String v

    );
}
