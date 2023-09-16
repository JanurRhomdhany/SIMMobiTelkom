package com.example.opsmobitelkomandroid.NavFragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.opsmobitelkomandroid.HistoriPeminjamanAdapter;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPeminjamanMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import com.example.opsmobitelkomandroid.R;
import com.example.opsmobitelkomandroid.RetrofitClient;
import com.example.opsmobitelkomandroid.SharedPrefManager;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    List<Peminjaman> peminjamanList;
    SharedPrefManager sharedPrefManager;
    HistoriPeminjamanAdapter historiPeminjamanAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        sharedPrefManager = new SharedPrefManager(getContext());
        String idSales = sharedPrefManager.getLogin().getIdSales();

        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fetchDataPeminjamanidSales(idSales);
        return view;

    }


    private void fetchDataPeminjamanidSales(String idSales){

        Call<FetchStatusPeminjamanMobilResponse> fetchStatusPeminjamanMobilResponseCall = RetrofitClient.getInstance().getApi().fetchPeminjamanIdSales(idSales);

        fetchStatusPeminjamanMobilResponseCall.enqueue(new Callback<FetchStatusPeminjamanMobilResponse>() {
            @Override
            public void onResponse(Call<FetchStatusPeminjamanMobilResponse> call, Response<FetchStatusPeminjamanMobilResponse> response) {

                if (response.isSuccessful()){
                    peminjamanList = response.body().getPeminjamanList();
                    historiPeminjamanAdapter = new HistoriPeminjamanAdapter(getContext(), peminjamanList);
                    recyclerView.setAdapter(historiPeminjamanAdapter);
                    historiPeminjamanAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchStatusPeminjamanMobilResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}