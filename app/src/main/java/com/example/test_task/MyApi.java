package com.example.test_task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApi {

    @GET("api/v1/trending")
    Call<List<ResponseA>> getListA ();

    @GET("api/v1/object/{id}")
    Call<ResponseB> getListB(@Path("id") int id);
}
