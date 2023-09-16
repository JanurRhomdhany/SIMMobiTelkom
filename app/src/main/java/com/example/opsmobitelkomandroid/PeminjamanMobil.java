package com.example.opsmobitelkomandroid;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import com.example.opsmobitelkomandroid.ModelResponse.FetchJadwalResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Jadwal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeminjamanMobil extends AppCompatActivity {

    List<Jadwal> jadwalList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    JadwalAdapter jadwalAdapter;

    SharedPrefManager sharedPrefManager;


    public PeminjamanMobil() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman_mobil);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        Calendar c = Calendar.getInstance();
        Date d = Calendar.getInstance().getTime();
        //simpledateformattanggal
        SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //get current date
        String currentDate = dateFormatTanggal.format(d);

        fetchDataJadwalTanggal(currentDate);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

    }

    private void fetchDataJadwalTanggal(String tanggal) {

        Call<FetchJadwalResponse> fetchJadwalResponseCall = RetrofitClient.getInstance().getApi().fetchJadwalTanggal(tanggal);

        fetchJadwalResponseCall.enqueue(new Callback<FetchJadwalResponse>() {
            @Override
            public void onResponse(Call<FetchJadwalResponse> call, Response<FetchJadwalResponse> response) {
                if (response.isSuccessful()){
                    jadwalList = response.body().getJadwalList();
                    jadwalAdapter = new JadwalAdapter((Context) PeminjamanMobil.this, (List<Jadwal>) jadwalList);
                    recyclerView.setAdapter(jadwalAdapter);
                    jadwalAdapter.notifyDataSetChanged();

                }else {
                    Toast.makeText(PeminjamanMobil.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchJadwalResponse> call, Throwable t) {
                Toast.makeText(PeminjamanMobil.this, "Error on: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}