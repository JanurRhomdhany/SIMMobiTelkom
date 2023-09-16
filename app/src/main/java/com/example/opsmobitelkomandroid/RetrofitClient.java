package com.example.opsmobitelkomandroid;

import com.example.opsmobitelkomandroid.ModelResponse.FetchJadwalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "https://simmobitelkommataram.my.id/api/"; //Online
//    private static String BASE_URL = "http://192.168.2.139/opsmobi_telkom/api/"; //LocalHost
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;



    public RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){

        if (retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }


}
