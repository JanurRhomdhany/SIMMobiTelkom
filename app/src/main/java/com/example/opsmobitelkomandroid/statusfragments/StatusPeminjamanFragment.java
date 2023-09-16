package com.example.opsmobitelkomandroid.statusfragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsmobitelkomandroid.JadwalAdapter;
import com.example.opsmobitelkomandroid.LihatMobil;
import com.example.opsmobitelkomandroid.MobilAdapter;
import com.example.opsmobitelkomandroid.ModelResponse.FetchMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPeminjamanMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.KodekembaliResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Mobil;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import com.example.opsmobitelkomandroid.ModelResponse.Pengembalian;
import com.example.opsmobitelkomandroid.PeminjamanAdapter;
import com.example.opsmobitelkomandroid.PengembalianMobil;
import com.example.opsmobitelkomandroid.R;
import com.example.opsmobitelkomandroid.RealPathUtil;
import com.example.opsmobitelkomandroid.RetrofitClient;
import com.example.opsmobitelkomandroid.SharedPrefManager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatusPeminjamanFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Peminjaman> peminjamanList;

    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;

    SharedPrefManager sharedPrefManager;

    public String path;
    public ImageView ivGambar;

    private ActivityResultLauncher<String> galleryLauncher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_status_peminjaman, container, false);

        recyclerView = view.findViewById(R.id.recycle_status_peminjaman);
        lmData = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lmData);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        lmData = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

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
        String cobaTanggal = "2023-08-03";
        fetchDataPeminjamanMobil(idSales, currentDate);



        return view;
    }

    private void fetchDataPeminjamanMobil(String idSales, String tanggalPinjam) {
        Call<FetchStatusPeminjamanMobilResponse> call = RetrofitClient.getInstance().getApi().fetchPeminjaman(idSales, tanggalPinjam);

        call.enqueue(new Callback<FetchStatusPeminjamanMobilResponse>() {
            @Override
            public void onResponse(Call<FetchStatusPeminjamanMobilResponse> call, Response<FetchStatusPeminjamanMobilResponse> response) {


//                String pesan = response.body().getMessage();
//
//                Toast.makeText(getActivity(), "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    peminjamanList = response.body().getPeminjamanList();
                    recyclerView.setAdapter(new PeminjamanAdapter(getActivity(), peminjamanList));
                } else {
                    Toast.makeText(getActivity(), "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                }

//                adData = new PeminjamanAdapter(getActivity(), peminjamanList);
//                recyclerView.setAdapter(adData);
//                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FetchStatusPeminjamanMobilResponse> call, Throwable t) {

                Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void retrieveData(){
//        Call<FetchStatusPeminjamanMobilResponse> call = RetrofitClient.getInstance().getApi().fetchPeminjaman();
//
//        call.enqueue(new Callback<FetchStatusPeminjamanMobilResponse>() {
//            @Override
//            public void onResponse(Call<FetchStatusPeminjamanMobilResponse> call, Response<FetchStatusPeminjamanMobilResponse> response) {
//
//                if (response.isSuccessful()){
//                    peminjamanList = response.body().getPeminjamanList();
//                }else {
//                    recyclerView.setAdapter(adData);
//                    adData.notifyDataSetChanged();
//                    String pesan = response.body().getMessage();
//                    Toast.makeText(getContext(), "Pesan: " + pesan , Toast.LENGTH_SHORT).show();
//                }
//
////                String pesan = response.body().getMessage();
////
////                Toast.makeText(getContext(), "Pesan: " + pesan, Toast.LENGTH_SHORT).show();
////
////                peminjamanList = response.body().getPeminjamanList();
////                recyclerView.setAdapter(new PeminjamanAdapter(getActivity(), peminjamanList));
////                adData = new PeminjamanAdapter(getActivity(), peminjamanList);
////                recyclerView.setAdapter(adData);
////                adData.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<FetchStatusPeminjamanMobilResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//    public class PeminjamanAdapter extends RecyclerView.Adapter<com.example.opsmobitelkomandroid.PeminjamanAdapter.ViewHolder>{
//
//
//
//        private Context context;
//        private List<Peminjaman> peminjamanList;
//
//        private String path;
//        private ImageView ivGambar;
//
//        StatusPeminjamanFragment statusPeminjamanFragment;
//
//        SharedPrefManager sharedPrefManager;
//
//        public PeminjamanAdapter(Context context, List<Peminjaman> peminjamanList) {
//            this.context = context;
//            this.peminjamanList = peminjamanList;
//        }
//
//        @NonNull
//        @Override
//        public com.example.opsmobitelkomandroid.PeminjamanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//            View view = LayoutInflater.from(context).inflate(R.layout.status_peminjaman_item, parent, false);
//            return new com.example.opsmobitelkomandroid.PeminjamanAdapter.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull com.example.opsmobitelkomandroid.PeminjamanAdapter.ViewHolder holder, int position) {
//
//            final SharedPrefManager[] sharedPrefManager = new SharedPrefManager[1];
//            sharedPrefManager[0] = new SharedPrefManager(context.getApplicationContext());
//
//            Peminjaman fetchStatusPeminjaman = peminjamanList.get(position);
//
//
//            //Waktu
//            int currentHour;
//            int desiredHour;
//            int currentMinute;
//            int desiredMinute;
//            int currentSecond;
//            int desiredSecond;
//            int currentEarlyHour;
//            int desiredEarlyHour;
//            //{
//            //waktu request
//            Calendar c = Calendar.getInstance();
//            Date d = Calendar.getInstance().getTime();
//            // Create a SimpleDateFormat object to format the time
//            SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//
//            //simpledateformattanggal
//            SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//
//            //get current date
//            String currentDate = dateFormatTanggal.format(d);
//
//            // Get the current time
//            String currentTime = dateFormatJam.format(d);
//
//            currentHour = dateFormatJam.getCalendar().get(Calendar.HOUR_OF_DAY);
//            currentMinute = dateFormatJam.getCalendar().get(Calendar.MINUTE);
//            currentSecond = dateFormatJam.getCalendar().get(Calendar.SECOND);
//
//
//            int startHour = 10;
//            int startMinute = 0;
//            int startSecond = 0;
//
//            int endHour = 18;
//            int endMinute = 0;
//            int endSecond = 0;
//
//            boolean isBetweenTime = (currentHour > startHour ||
//                    (currentHour == startHour && currentMinute >= startMinute)) &&
//                    (currentHour < endHour ||
//                            (currentHour == endHour && currentMinute < endMinute));
//
//            boolean afterEndTime = (currentHour > endHour || (currentHour == endHour && currentMinute >= endMinute));
//
//            holder.id_peminjaman.setText(fetchStatusPeminjaman.getId_peminjaman());
//            holder.plat_mobil.setText(fetchStatusPeminjaman.getPlat_mobil());
//            holder.jam_pinjam.setText(fetchStatusPeminjaman.getJam_peminjaman());
//            holder.nama_lokasi.setText(fetchStatusPeminjaman.getNama_lokasi());
//            holder.nama_agency.setText(fetchStatusPeminjaman.getNama_agency());
//            holder.nama_sales.setText(fetchStatusPeminjaman.getNama_sales());
//            holder.tanggal.setText(fetchStatusPeminjaman.getTanggal());
//            holder.status_peminjaman.setText(fetchStatusPeminjaman.getStatus_peminjaman());
//            holder.keterangan.setText(fetchStatusPeminjaman.getKeterangan());
//
//            if (holder.status_peminjaman.getText().equals("Diterima")){
//                holder.status_peminjaman.setBackgroundColor(Color.parseColor("#198754"));
//            } else if (holder.status_peminjaman.getText().equals("Request")) {
//                holder.status_peminjaman.setBackgroundColor(Color.parseColor("#E9D502"));
//            }
//            else {
//                holder.status_peminjaman.setBackgroundColor(Color.parseColor("#FF0000"));
//            }
//
//            if (isBetweenTime){
//                holder.btnKembalikanMobil.setBackgroundColor(Color.parseColor("#c80a0a"));
//                holder.btnKembalikanMobil.setEnabled(true);
//            } else {
//                holder.btnKembalikanMobil.setBackgroundColor(Color.parseColor("#D3D3D3"));
//                holder.btnKembalikanMobil.setEnabled(false);
//                holder.btnKembalikanMobil.setClickable(false);
//            }
//
//
//
//            holder.btnKembalikanMobil.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = new Intent(context, PengembalianMobil.class);
//                    context.startActivity(i);
////                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
////                    builder.setTitle("Data Pengembalian");
////
////                    View view = LayoutInflater.from(context).inflate(R.layout.data_pengembalian_layout_dialog, null);
////                    ivGambar = view.findViewById(R.id.iv_dataBuktiKegiatan);
////
////                    holder.btnPilihGambar = view.findViewById(R.id.btn_pilihGambar);
////
////                    holder.btnPilihGambar.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////                            pilihGambar();
////                        }
////                        public void pilihGambar() {
////                            galleryLauncher.launch("image/*");
//////                            if (ContextCompat.checkSelfPermission(getContext(),
//////                                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//////                                Intent gallery = new Intent();
//////                                gallery.setType("image/*");
//////                                gallery.setAction(Intent.ACTION_GET_CONTENT);
//////                                startActivityForResult(gallery, 10);
//////                            } else{
//////                                ActivityCompat.requestPermissions(getActivity(),
//////                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//////                            }
////
////                        }
////
////
////
////                    });
////
////
////                    //get ID Sales
////                    sharedPrefManager[0] = new SharedPrefManager(context.getApplicationContext());
////                    String id_sales = sharedPrefManager[0].getLogin().getIdSales();
////                    TextView dataIdSales = view.findViewById(R.id.tv_dataIdSales);
////                    dataIdSales.setText(id_sales);
////
////                    //get Tanggal Pengembalian
////                    String tanggalPinjam = holder.tanggal.getText().toString();
////                    TextView dataTanggalPinjam = view.findViewById(R.id.tv_dataTanggal);
////                    dataTanggalPinjam.setText(tanggalPinjam);
////
////                    //get ID Mobil
////                    String idMobil = holder.idMobil.getText().toString();
////                    TextView dataIdMobil = view.findViewById(R.id.tv_dataIdMobil);
////                    dataIdMobil.setText(idMobil);
////
////                    //get Text Bukti Kegiatan
//////                    String buktiKegiatan = holder.bukti_kegiatan.getText().toString();
//////                    TextView dataBuktiKegiatan = view.findViewById(R.id.tv_buktiKegiatan);
//////                    dataBuktiKegiatan.setText(buktiKegiatan);
////
////                    //get ID Lokasi
////                    String idLokasi = holder.idLokasi.getText().toString();
////                    TextView dataIdLokasi = view.findViewById(R.id.tv_dataIdLokasi);
////                    dataIdLokasi.setText(idLokasi);
////
////                    //get ket Kegiatan
////                    String ketKegiatan = holder.keterangan.getText().toString();
////                    EditText dataKetKegiatan = view.findViewById(R.id.tv_ketKegiatanPengembalian);
////                    dataKetKegiatan.setText(ketKegiatan);
////
////                    //get jam pengembalian
////                    String currentTime = dateFormatJam.format(d);
////                    TextView dataJamPengembalian = view.findViewById(R.id.tv_dataJamPengembalian);
////                    dataJamPengembalian.setText(currentTime);
////
////                    //if status pengembalian
////                    TextView dataStatusPengembalian = view.findViewById(R.id.tv_dataStatusPengembalian);
////                    TextView dataKetStatusPengembalian = view.findViewById(R.id.tv_dataKetStatusPengembalian);
////                    if (currentHour <= endHour){
////                        dataStatusPengembalian.setText("Diterima");
////                        dataKetStatusPengembalian.setText("Mobil Parkir");
////                    } else {
////                        dataStatusPengembalian.setText("Request");
////                        dataKetStatusPengembalian.setText("Mobil Jalan");
////                    }
////
////
////                    //Auto ID Pengembalian
////                    Call<KodekembaliResponse> callKodeKembali = RetrofitClient.getInstance().getApi().getLatestDataPengembalian();
////
////                    callKodeKembali.enqueue(new Callback<KodekembaliResponse>() {
////                        @Override
////                        public void onResponse(Call<KodekembaliResponse> call, Response<KodekembaliResponse> response) {
////
////                            if (response.isSuccessful()){
////                                KodekembaliResponse kodekembaliResponse = response.body();
////
////                                if (kodekembaliResponse != null){
////                                    String latestId = kodekembaliResponse.getLatestIdPengembalian();
////                                    if (latestId != null){
////                                        String autoId = generateAutoCode(latestId);
////                                        TextView dataIdPengembalian = view.findViewById(R.id.tv_dataIdPengembalian);
////                                        dataIdPengembalian.setText(autoId);
////
////                                    }else {
////                                        TextView dataIdPengembalian = view.findViewById(R.id.tv_dataIdPengembalian);
////                                        dataIdPengembalian.setText("PB001");
////                                    }
////                                }
////                            }
////                        }
////
////                        private String generateAutoCode(String latestId) {
////
////                            int numericPart = Integer.parseInt(latestId.substring(2));
////                            numericPart++;
////
////                            String newCode = "PB" + String.format("%03d", numericPart);
////
////                            return newCode;
////                        }
////
////                        @Override
////                        public void onFailure(Call<KodekembaliResponse> call, Throwable t) {
////                            Toast.makeText(context, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
////                        }
////                    });
////
////                    builder.setView(view);
////
////                    builder.setPositiveButton("Ambil", new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////
////                            Toast.makeText(context, "Berhasil Kembalikan Mobil", Toast.LENGTH_SHORT).show();
////
////                        }
////                    });
////
////                    builder.setNegativeButton("Batal", null);
////
////                    builder.create().show();
//
//                }
//
//            });
//
//        }
//
//
//
//
//        @Override
//        public int getItemCount() {
//            return peminjamanList.size();
//        }
//
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//
//            TextView bukti_kegiatan, id_peminjaman, tanggal, plat_mobil, status_peminjaman, nama_sales, nama_agency, nama_lokasi, jam_pinjam, keterangan, idMobil, idLokasi, idAgency, idSales;
//            Button btnKembalikanMobil, btnPilihGambar;
//            public ViewHolder(@NonNull View itemView){
//                super(itemView);
//
//                id_peminjaman = itemView.findViewById(R.id.tv_id_peminjaman);
//                tanggal = itemView.findViewById(R.id.tv_tanggal);
//                plat_mobil = itemView.findViewById(R.id.tv_plat_mobil);
//                status_peminjaman = itemView.findViewById(R.id.tv_status_peminjaman);
//                nama_sales = itemView.findViewById(R.id.tv_nama_sales);
//                nama_agency = itemView.findViewById(R.id.tv_nama_agency);
//                nama_lokasi = itemView.findViewById(R.id.tv_nama_lokasi);
//                jam_pinjam = itemView.findViewById(R.id.tv_jam_pinjam);
//                keterangan = itemView.findViewById(R.id.tv_keterangan);
//                idLokasi = itemView.findViewById(R.id.tv_idLokasi);
//                idAgency = itemView.findViewById(R.id.tv_idAgency);
//                idMobil = itemView.findViewById(R.id.tv_idMobil);
//                idSales = itemView.findViewById(R.id.tv_idSales);
//                btnPilihGambar = itemView.findViewById(R.id.btn_pilihGambar);
//
//
//
//                btnKembalikanMobil = itemView.findViewById(R.id.btn_kembaliMobil);
//
//
//
//
//            }
//        }
//
//
//
//
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 10 && resultCode == Activity.RESULT_OK){
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.data_pengembalian_layout_dialog, null);
//            //get foto bukti kegiatan
//            ivGambar = view.findViewById(R.id.iv_dataBuktiKegiatan);
//
//            Uri uri = data.getData();
//            Context context = getContext();
//            path = RealPathUtil.getRealPath(context, uri);
//            Bitmap bitmap = BitmapFactory.decodeFile(path);
//
//            ivGambar.setImageBitmap(bitmap);
//
//        }
    }
