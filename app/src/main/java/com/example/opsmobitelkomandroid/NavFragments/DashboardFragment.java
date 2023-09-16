package com.example.opsmobitelkomandroid.NavFragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.opsmobitelkomandroid.ModelResponse.LoginResponse;
import com.example.opsmobitelkomandroid.PeminjamanMobil;
import com.example.opsmobitelkomandroid.R;
import com.example.opsmobitelkomandroid.SharedPrefManager;
import com.example.opsmobitelkomandroid.StatusTransaksiMobil;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    TextView idSales, namaSales, namaAgency, idAgency;
    ImageView pinjamMobil, kembaliMobil, statusMobil, lihatMobil;

    LoginResponse loginResponse;
    SharedPrefManager sharedPrefManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //defining id
        pinjamMobil = view.findViewById(R.id.iv_pinjam_mobil);
//        kembaliMobil = view.findViewById(R.id.iv_kembali_mobil);
        statusMobil = view.findViewById(R.id.iv_status);
//        lihatMobil = view.findViewById(R.id.iv_mobil);

        idSales = view.findViewById(R.id.tv_idSales);
        namaSales = view.findViewById(R.id.tv_namaSales);
        namaAgency = view.findViewById(R.id.tv_agencySales);
//        idAgency = view.findViewById(R.id.tv_idAgencySales);

        sharedPrefManager = new SharedPrefManager(getActivity());

        String nama_sales = sharedPrefManager.getLogin().getNamaSales();
        String sales = "Selamat Datang, " + "<b>" + nama_sales + "</b>";
        namaSales.setText(Html.fromHtml(sales));

        String id_sales = sharedPrefManager.getLogin().getIdSales();
        idSales.setText(id_sales);

        String nama_agency = sharedPrefManager.getLogin().getNamaAgency();
        namaAgency.setText(nama_agency);

//        String id_agency = "ID Agency: " + sharedPrefManager.getLogin().getIdAgency();
//        idAgency.setText(id_agency);


        //add click listener
        pinjamMobil.setOnClickListener(this);
//        kembaliMobil.setOnClickListener(this);
        statusMobil.setOnClickListener(this);
//        lihatMobil.setOnClickListener(this);



//        sharedPrefManager = new SharedPrefManager(getActivity());


//        String userName = "Selamat datang di Aplikasi Mobi Telkom";
//        etusername.setText(userName);
        return view;


    }


    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.iv_pinjam_mobil: i = new Intent(v.getContext(), PeminjamanMobil.class); startActivity(i); break;
//            case R.id.iv_kembali_mobil: i = new Intent(v.getContext(), PengembalianMobil.class); startActivity(i); break;
            case R.id.iv_status: i = new Intent(v.getContext(), StatusTransaksiMobil.class); startActivity(i); break;
//            case R.id.iv_mobil: i = new Intent(v.getContext(), LihatMobil.class); startActivity(i); break;
            default: break;
        }

    }
}