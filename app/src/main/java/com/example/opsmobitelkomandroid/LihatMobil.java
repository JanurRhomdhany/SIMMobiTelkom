package com.example.opsmobitelkomandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.ModelResponse.FetchMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Mobil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatMobil extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Mobil> mobilList;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_mobil);

        recyclerView = findViewById(R.id.recycle_lihat_mobil);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmData);
        retrieveData();


    }

    public void retrieveData(){
        Call<FetchMobilResponse> call = RetrofitClient.getInstance().getApi().fetchMobil();

        call.enqueue(new Callback<FetchMobilResponse>() {
            @Override
            public void onResponse(Call<FetchMobilResponse> call, Response<FetchMobilResponse> response) {


                String pesan = response.body().getMessage();

                Toast.makeText(LihatMobil.this, "Pesan: " + pesan, Toast.LENGTH_SHORT).show();

                mobilList = response.body().getMobilList();
                adData = new MobilAdapter(LihatMobil.this, mobilList);
                recyclerView.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FetchMobilResponse> call, Throwable t) {
                    Toast.makeText(LihatMobil.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}