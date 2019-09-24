package com.example.test_task;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetData {

    private static final String TAG = "GetData";
    private static final String ERROR = "Ошибка ";
    private static final String BASE_URL = "https://demo0040494.mockable.io/";
    private Retrofit retrofit;
    private MyApi myApi;

    private TestTaskCallback callback;


    public GetData(TestTaskCallback callback) {
        this.callback = callback;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(MyApi.class);
    }

    public void gettingListA (){
        myApi.getListA().enqueue(new Callback<List<ResponseA>>() {

            @Override
            public void onResponse(Call<List<ResponseA>> call, Response<List<ResponseA>> response) {
                if (response.isSuccessful()) {
                    callback.onGetListACallback(response.body());
                }
                else Log.e(TAG, ERROR + response.code());
            }

            @Override
            public void onFailure(Call<List<ResponseA>> call, Throwable t) {
                Log.e(TAG, ERROR + t);
            }
        });

    }

    public void gettingitemB (int id){
        myApi.getListB (id).enqueue(new Callback<ResponseB>() {
            @Override
            public void onResponse(Call<ResponseB> call, Response<ResponseB> response) {
                if (response.isSuccessful()) {
                    callback.onGetItemBCallback(response.body());
                }
                else Log.e(TAG, ERROR + response.code());
            }
            @Override
            public void onFailure(Call<ResponseB> call, Throwable t) {
                Log.e(TAG, ERROR + t);
            }
        });
    }

}
