package com.example.opsmobitelkomandroid.statusfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPeminjamanMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPengembalianMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import com.example.opsmobitelkomandroid.ModelResponse.Pengembalian;
import com.example.opsmobitelkomandroid.PeminjamanAdapter;
import com.example.opsmobitelkomandroid.PengembalianAdapter;
import com.example.opsmobitelkomandroid.R;
import com.example.opsmobitelkomandroid.RetrofitClient;
import com.example.opsmobitelkomandroid.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatusPengembalianFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Pengembalian> pengembalianList;

    SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_status_pengembalian, container, false);

        recyclerView = view.findViewById(R.id.recycle_status_pengembalian);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        sharedPrefManager = new SharedPrefManager(getContext());
        String idSales = sharedPrefManager.getLogin().getIdSales();

        Calendar c = Calendar.getInstance();
        Date d = Calendar.getInstance().getTime();
        // Create a SimpleDateFormat object to format the time
        SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        //simpledateformattanggal
        SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //get current date
        String currentDate = dateFormatTanggal.format(d);

//        et_tanggalPinjam = findViewById(R.id.et_tanggalPinjam);
//        et_tanggalPinjam.setText(currentDate);
        // Get the current time
        String currentTime = dateFormatJam.format(d);
//        et_jamPinjam = findViewById(R.id.et_jamPinjam);
//        et_jamPinjam.setText(currentTime);

        fetchDataPengembalianMobil(idSales, currentDate);



        return view;
    }

    private void fetchDataPengembalianMobil(String idSales, String tanggalKembali) {
        Call<FetchStatusPengembalianMobilResponse> call = RetrofitClient.getInstance().getApi().fetchPengembalian(idSales, tanggalKembali);

        call.enqueue(new Callback<FetchStatusPengembalianMobilResponse>() {
            @Override
            public void onResponse(Call<FetchStatusPengembalianMobilResponse> call, Response<FetchStatusPengembalianMobilResponse> response) {


//                String pesan = response.body().getMessage();
//
//                Toast.makeText(getActivity(), "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    pengembalianList = response.body().getPengembalianList();
                    recyclerView.setAdapter(new PengembalianAdapter(getActivity(), pengembalianList));
                }else {
                    Toast.makeText(getActivity(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }

//                adData = new PeminjamanAdapter(getActivity(), peminjamanList);
//                recyclerView.setAdapter(adData);
//                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FetchStatusPengembalianMobilResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}