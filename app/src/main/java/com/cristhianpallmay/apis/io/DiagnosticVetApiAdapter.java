package com.cristhianpallmay.apis.io;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiagnosticVetApiAdapter {
    private static DiagnosticVetApiAdapter API_SERVICE;

    public static DiagnosticVetApiAdapter getApiService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        String baseUrl="https://parallelum.com.br/fipe/api/v1/carros/marcas";

        if (API_SERVICE == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            API_SERVICE = retrofit.create((DiagnosticVetApiAdapter.class));
        }

        return API_SERVICE;
    }
}
