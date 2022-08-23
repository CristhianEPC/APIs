package com.cristhianpallmay.apis.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cristhianpallmay.apis.R;
import com.cristhianpallmay.apis.io.DiagnosticVetApiAdapter;
import com.cristhianpallmay.apis.io.DiagnosticVetApiService;
import com.cristhianpallmay.apis.io.response.DiseasesResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayAdapter arrayAdapter;
    ArrayList<String> nombres = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=findViewById(R.id.listaMarca);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,nombres);
        list.setAdapter(arrayAdapter);
        getAlbums();
    }

    private void getAlbums(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://parallelum.com.br/fipe/api/v1/carros/marcas")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DiagnosticVetApiService diagnosticVetApiService = retrofit.create(DiagnosticVetApiService.class);
        Call<List<DiseasesResponse>> call = diagnosticVetApiService.getDiseases();
        call.enqueue(new Callback<List<DiseasesResponse>>() {
            @Override
            public void onResponse(Call<List<DiseasesResponse>> call, Response<List<DiseasesResponse>> response) {
                for (DiseasesResponse diseasesResponse: response.body()){
                    nombres.add(diseasesResponse.getNome());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<DiseasesResponse>> call, Throwable t) {

            }
        });
    }
}