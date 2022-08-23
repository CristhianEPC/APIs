package com.cristhianpallmay.apis.io;

import com.cristhianpallmay.apis.io.response.DiseasesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DiagnosticVetApiService {
    @GET("diseases")
    Call<List<DiseasesResponse>> getDiseases();

    @FormUrlEncoded
    @POST("upload/photo")
    Call<DiseasesResponse> postPhoto(
            @Field("imagen") String base64,
            @Field("extension") String extesion,
            @Field("user_id") String user_id
    );

    @GET("login")
    Call<DiseasesResponse> getLogin(
            @Query("username") String username,
            @Query("password") String password
    );

    @FormUrlEncoded
    @POST("product")
    Call<DiseasesResponse> postNewProduct(
            @Field("code") String code,
            @Field("name") String name,
            @Field("description") String description
    );
}
