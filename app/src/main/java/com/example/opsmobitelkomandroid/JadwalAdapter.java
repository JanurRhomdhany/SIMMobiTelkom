package com.example.opsmobitelkomandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opsmobitelkomandroid.Activities.HomeActivity;
import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPeminjamanMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Jadwal;
import com.example.opsmobitelkomandroid.ModelResponse.KodepinjamResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import com.example.opsmobitelkomandroid.ModelResponse.PeminjamanResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder>{

    Context context;
    List<Jadwal> jadwalList;

    List<Peminjaman> peminjamanList;
    SharedPrefManager sharedPrefManager;

    Peminjaman peminjaman;
    Jadwal jadwal;


    private String inputDataIdPeminjaman, inputDataTanggalPinjam, inputDataIdSales, inputDataIdMobil, inputDataIdLokasi, inputDataJamPeminjaman, inputDataStatusPeminjaman, inputDataKetPeminjaman;

    //BATAS JAM PEMINJAMAN
    private final int startHour = 6;
    private final int endHour = 18;
    public JadwalAdapter(Context context, List<Jadwal> jadwalList) {
        this.context = context;
        this.jadwalList = jadwalList;
    }

    @NonNull
    @Override
    public JadwalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jadwal_item, parent, false);
        View view = LayoutInflater.from(context).inflate(R.layout.jadwal_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull JadwalAdapter.ViewHolder holder, int position) {
        holder.tanggalJadwal.setText(jadwalList.get(position).getTanggal());
        holder.platMobil.setText(jadwalList.get(position).getPlatMobil());
        holder.namaAgency.setText(jadwalList.get(position).getNamaAgency());
        holder.namaLokasi.setText(jadwalList.get(position).getNamaLokasi());
        holder.idMobil.setText(jadwalList.get(position).getIdMobil());
        holder.idLokasi.setText(jadwalList.get(position).getIdLokasi());
        holder.idAgency.setText(jadwalList.get(position).getIdAgency());


        sharedPrefManager = new SharedPrefManager(context.getApplicationContext());


        String agencySalesLogin = sharedPrefManager.getLogin().getNamaAgency();
        String idSalesLogin = sharedPrefManager.getLogin().getIdSales();

        int currentHour;
        int desiredHour;
        int currentMinute;
        int desiredMinute;
        int currentSecond;
        int desiredSecond;
        int currentEarlyHour;
        int desiredEarlyHour;


        //waktu request
        Calendar c = Calendar.getInstance();
        Date d = Calendar.getInstance().getTime();
        // Create a SimpleDateFormat object to format the time
        SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

        //simpledateformattanggal
        SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        //get current date
        String currentDate = dateFormatTanggal.format(d);

        // Get the current time
        String currentTime = dateFormatJam.format(d);

        currentHour = dateFormatJam.getCalendar().get(Calendar.HOUR_OF_DAY);
        currentMinute = dateFormatJam.getCalendar().get(Calendar.MINUTE);
        currentSecond = dateFormatJam.getCalendar().get(Calendar.SECOND);

        int startMinute = 0;
        int startSecond = 0;


        int endMinute = 0;
        int endSecond = 0;

        boolean isBetweenTime = (currentHour > startHour || (currentHour == startHour && currentMinute > startMinute)) &&
                (currentHour < endHour || (currentHour == endHour && currentMinute <= endMinute));

        boolean afterEndTime = (currentHour > endHour || (currentHour == endHour && currentMinute > endMinute));

        String namaAgencyJadwal = holder.namaAgency.getText().toString();
        String tanggalJadwalMobil = holder.tanggalJadwal.getText().toString();
        String getidMobil = holder.idMobil.getText().toString();
        TextView dataIdMobil = holder.itemView.findViewById(R.id.tv_dataIdMobil);
        holder.fetchDataPeminjamanMobil(idSalesLogin, tanggalJadwalMobil);
        holder.fetchDataPeminjamanMobilBased(getidMobil, currentDate);

        if (namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil) && isBetweenTime){
            holder.btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
            holder.btn_ambilMobil.setEnabled(true);
        }
        else if (afterEndTime && namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil)) {
            holder.btn_ambilMobil.setEnabled(false);
            holder.btn_ambilMobil.setClickable(false);
        }

        else if (afterEndTime && !namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil) && holder.fetchDataPeminjamanMobilBased(getidMobil, currentDate)) {
            holder.btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
            holder.btn_ambilMobil.setEnabled(true);
        }
        else {
            holder.btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_disabled);
            holder.btn_ambilMobil.setEnabled(false);
            holder.btn_ambilMobil.setClickable(false);
        }


        holder.btn_ambilMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Data Peminjaman");

                View view = LayoutInflater.from(context).inflate(R.layout.data_peminjaman_layout_dialog, null);

                Calendar c = Calendar.getInstance();
                Date d = Calendar.getInstance().getTime();
                // Create a SimpleDateFormat object to format the time
                SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                //simpledateformattanggal
                SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                //get current date
                String currentDate = dateFormatTanggal.format(d);

                String tanggalPinjam = holder.tanggalJadwal.getText().toString();
                TextView dataTanggalPinjam = view.findViewById(R.id.tv_dataTanggal);
                dataTanggalPinjam.setText(tanggalPinjam);

                //get current time
                String currentTime = dateFormatJam.format(d);
                TextView dataJamPinjam = view.findViewById(R.id.tv_dataJamPinjam);
                dataJamPinjam.setText(currentTime);

                //get ID SALES
                String id_sales = sharedPrefManager.getLogin().getIdSales();
                TextView dataIdSales = view.findViewById(R.id.tv_dataIdSales);
                dataIdSales.setText(id_sales);

                //get nama sales
                String namaSales = sharedPrefManager.getLogin().getNamaSales();
                TextView dataNamaSales = view.findViewById(R.id.data_namaSales);
                dataNamaSales.setText(namaSales);

                //get plat mobil
                String platMobil = holder.platMobil.getText().toString();
                TextView dataPlatMobil = view.findViewById(R.id.data_platMobil);
                dataPlatMobil.setText(platMobil);

                //get ID Mobil
                String getidMobil = holder.idMobil.getText().toString();
                TextView dataIdMobil = view.findViewById(R.id.tv_dataIdMobil);
                dataIdMobil.setText(getidMobil);


                //get nama lokasi
                String namaLokasi = holder.namaLokasi.getText().toString();
                TextView dataNamaLokasi = view.findViewById(R.id.data_namaLokasi);
                dataNamaLokasi.setText(namaLokasi);

                //get ID lokasi
                String getidLokasi = holder.idLokasi.getText().toString();
                TextView dataIdLokasi = view.findViewById(R.id.tv_dataIdLokasi);
                dataIdLokasi.setText(getidLokasi);

                //get namaAgency
                String namaAgency = sharedPrefManager.getLogin().getNamaAgency();
                TextView dataNamaAgency = view.findViewById(R.id.data_namaAgency);
                dataNamaAgency.setText(namaAgency);

                //get ID Agency
                String getidAgency = sharedPrefManager.getLogin().getIdAgency();
                TextView dataIdAgency = view.findViewById(R.id.tv_dataIdAgency);
                dataIdAgency.setText(getidAgency);

                TextView dataIdPinjam = view.findViewById(R.id.tv_dataIdPeminjaman);

                //if status pinjam request
                TextView dataStatusPinjam = view.findViewById(R.id.tv_dataStatusPinjam);
                TextView dataKetPinjam = view.findViewById(R.id.tv_dataKetPinjam);
                if (currentHour <= endHour) {
                    dataStatusPinjam.setText("Diterima");
                    dataKetPinjam.setText("Mobil Jalan");
                } else {
                    dataStatusPinjam.setText("Request");
                    dataKetPinjam.setText("Mobil Parkir");
                }

                //Get Auto ID Peminjaman
                Call<KodepinjamResponse> kodepinjamResponseCall = RetrofitClient.getInstance().getApi().getLatestData();

                kodepinjamResponseCall.enqueue(new Callback<KodepinjamResponse>() {
                    @Override
                    public void onResponse(Call<KodepinjamResponse> call, Response<KodepinjamResponse> response) {
                        if (response.isSuccessful()) {

                            KodepinjamResponse kodepinjamResponse = response.body();
                            if (kodepinjamResponse != null) {
                                String latestId = kodepinjamResponse.getLatestId();
                                if (latestId != null) {
                                    String autoId = generateAutoCode(latestId);
                                    TextView dataIdPinjam = view.findViewById(R.id.tv_dataIdPeminjaman);
                                    dataIdPinjam.setText(autoId);
                                } else {
                                    TextView dataIdPinjam = view.findViewById(R.id.tv_dataIdPeminjaman);
                                    dataIdPinjam.setText("PJ001");
                                }


                            }
                        }
                    }

                    private String generateAutoCode(String latestId) {

                        int numericPart = Integer.parseInt(latestId.substring(2));
                        numericPart++;

                        String newCode = "PJ" + String.format("%03d", numericPart);

                        return newCode;
                    }

                    @Override
                    public void onFailure(Call<KodepinjamResponse> call, Throwable t) {
                        Toast.makeText(context, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setView(view);


                builder.setPositiveButton("Ambil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        prosesAmbilMobil();

                    }

                    private void prosesAmbilMobil() {
                        inputDataIdPeminjaman = dataIdPinjam.getText().toString();
                        inputDataTanggalPinjam = dataTanggalPinjam.getText().toString();
                        inputDataIdSales = dataIdSales.getText().toString();
                        inputDataIdMobil = dataIdMobil.getText().toString();
                        inputDataIdLokasi = dataIdLokasi.getText().toString();
                        inputDataJamPeminjaman = dataJamPinjam.getText().toString();
                        inputDataStatusPeminjaman = dataStatusPinjam.getText().toString();
                        inputDataKetPeminjaman = dataKetPinjam.getText().toString();

                        Call<PeminjamanResponse> call = RetrofitClient.getInstance().getApi().InputDataPeminjaman(
                                inputDataIdPeminjaman,
                                inputDataTanggalPinjam,
                                inputDataIdSales,
                                inputDataIdMobil,
                                inputDataIdLokasi,
                                inputDataJamPeminjaman,
                                inputDataStatusPeminjaman,
                                inputDataKetPeminjaman);

                        call.enqueue(new Callback<PeminjamanResponse>() {
                            @Override
                            public void onResponse(Call<PeminjamanResponse> call, Response<PeminjamanResponse> response) {

                                Intent intent = new Intent(context, HomeActivity.class);
                                context.startActivity(intent);
                                Toast.makeText(context, "Berhasil Ambil Mobil", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<PeminjamanResponse> call, Throwable t) {
                                Toast.makeText(context, "Gagal, Coba Beberapa Saat Lagi", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("Batal", null);

                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {

        if (jadwalList == null) return 0;
        return jadwalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView platMobil, namaAgency, namaLokasi, tanggalJadwal, idLokasi, idAgency, idMobil;
        Button btn_ambilMobil;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            sharedPrefManager = new SharedPrefManager(context.getApplicationContext());


            String idSalesLogin = sharedPrefManager.getLogin().getIdSales();


            tanggalJadwal = itemView.findViewById(R.id.tv_tanggalJadwal);
            platMobil = itemView.findViewById(R.id.tv_platMobil);
            namaAgency = itemView.findViewById(R.id.tv_namaAgency);
            namaLokasi = itemView.findViewById(R.id.tv_namaLokasi);
            idLokasi = itemView.findViewById(R.id.tv_idLokasi);
            idAgency = itemView.findViewById(R.id.tv_idAgency);
            idMobil = itemView.findViewById(R.id.tv_idMobil);

            btn_ambilMobil = itemView.findViewById(R.id.btn_ambilMobil);
            Calendar c = Calendar.getInstance();
            Date d = Calendar.getInstance().getTime();
            // Create a SimpleDateFormat object to format the time
            SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            //simpledateformattanggal
            SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            //get current date
            String currentDate = dateFormatTanggal.format(d);
//            fetchDataPeminjamanMobil(idSalesLogin, currentDate);

            String getidMobil = idMobil.getText().toString();
            TextView dataIdMobil = itemView.findViewById(R.id.tv_dataIdMobil);
//            fetchDataPeminjamanMobilBased(getidMobil, currentDate);

        }

        private boolean fetchDataPeminjamanMobilBased(String idMobil, String tanggal){
            Call<FetchStatusPeminjamanMobilResponse> call2 = RetrofitClient.getInstance().getApi().fetchMobilPeminjaman(idMobil, tanggal);

            call2.enqueue(new Callback<FetchStatusPeminjamanMobilResponse>() {
                @Override
                public void onResponse(Call<FetchStatusPeminjamanMobilResponse> call, Response<FetchStatusPeminjamanMobilResponse> response) {
                    sharedPrefManager = new SharedPrefManager(context.getApplicationContext());

                    String agencySalesLogin = sharedPrefManager.getLogin().getNamaAgency();
                    String idSalesLogin = sharedPrefManager.getLogin().getIdSales();

                    int currentHour;
                    int desiredHour;
                    int currentMinute;
                    int desiredMinute;
                    int currentSecond;
                    int desiredSecond;
                    int currentEarlyHour;
                    int desiredEarlyHour;
                    //waktu request
                    Calendar c = Calendar.getInstance();
                    Date d = Calendar.getInstance().getTime();
                    // Create a SimpleDateFormat object to format the time
                    SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                    //simpledateformattanggal
                    SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                    //get current date
                    String currentDate = dateFormatTanggal.format(d);

                    // Get the current time
                    String currentTime = dateFormatJam.format(d);

                    currentHour = dateFormatJam.getCalendar().get(Calendar.HOUR_OF_DAY);
                    currentMinute = dateFormatJam.getCalendar().get(Calendar.MINUTE);
                    currentSecond = dateFormatJam.getCalendar().get(Calendar.SECOND);

                    int startMinute = 0;
                    int startSecond = 0;

                    int endMinute = 0;
                    int endSecond = 0;

                    boolean isBetweenTime = (currentHour > startHour || (currentHour == startHour && currentMinute > startMinute)) &&
                            (currentHour < endHour || (currentHour == endHour && currentMinute <= endMinute));

                    boolean afterEndTime = (currentHour > endHour || (currentHour == endHour && currentMinute > endMinute));

                    String tanggal = currentDate;
                    String namaAgencyJadwal = namaAgency.getText().toString();
                    String tanggalJadwalMobil = tanggalJadwal.getText().toString();


                    String statusPinjam = null;
                    if (response.isSuccessful()){
                        peminjamanList = response.body().getPeminjamanList();
                        for (Peminjaman p : peminjamanList){
                            statusPinjam = p.getStatus_peminjaman();
                        }

                        if (peminjamanList == null && namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil) && isBetweenTime){
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
                            btn_ambilMobil.setEnabled(true);
                        }
                        else if (afterEndTime && peminjamanList == null && !namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil)) {
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
                            btn_ambilMobil.setEnabled(true);
                        }
                        else if (afterEndTime && peminjamanList != null && statusPinjam.equals("Ditolak") && !namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil)) {
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
                            btn_ambilMobil.setEnabled(true);
                        }

                        else {
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_disabled);
                            btn_ambilMobil.setEnabled(false);
                            btn_ambilMobil.setClickable(false);
                        }
                    }
                }

                @Override
                public void onFailure(Call<FetchStatusPeminjamanMobilResponse> call, Throwable t) {

                }
            });


            return true;
        }

        private boolean fetchDataPeminjamanMobil(String idSalesLogin, String tanggalPinjam) {

            Call<FetchStatusPeminjamanMobilResponse> call = RetrofitClient.getInstance().getApi().fetchPeminjaman(idSalesLogin, tanggalPinjam);

            call.enqueue(new Callback<FetchStatusPeminjamanMobilResponse>() {
                @Override
                public void onResponse(Call<FetchStatusPeminjamanMobilResponse> call, Response<FetchStatusPeminjamanMobilResponse> response) {
                    sharedPrefManager = new SharedPrefManager(context.getApplicationContext());


                    String agencySalesLogin = sharedPrefManager.getLogin().getNamaAgency();
                    String idSalesLogin = sharedPrefManager.getLogin().getIdSales();

                    int currentHour;
                    int desiredHour;
                    int currentMinute;
                    int desiredMinute;
                    int currentSecond;
                    int desiredSecond;
                    int currentEarlyHour;
                    int desiredEarlyHour;
                    //{
                    //waktu request
                    Calendar c = Calendar.getInstance();
                    Date d = Calendar.getInstance().getTime();
                    // Create a SimpleDateFormat object to format the time
                    SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

                    //simpledateformattanggal
                    SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

                    //get current date
                    String currentDate = dateFormatTanggal.format(d);

                    // Get the current time
                    String currentTime = dateFormatJam.format(d);

                    currentHour = dateFormatJam.getCalendar().get(Calendar.HOUR_OF_DAY);
                    currentMinute = dateFormatJam.getCalendar().get(Calendar.MINUTE);
                    currentSecond = dateFormatJam.getCalendar().get(Calendar.SECOND);

                    int startMinute = 0;
                    int startSecond = 0;

                    int endMinute = 0;
                    int endSecond = 0;

                    boolean isBetweenTime = (currentHour > startHour || (currentHour == startHour && currentMinute > startMinute)) &&
                            (currentHour < endHour || (currentHour == endHour && currentMinute <= endMinute));

                    boolean afterEndTime = (currentHour > endHour || (currentHour == endHour && currentMinute > endMinute));

                    String tanggal = currentDate;
                    String namaAgencyJadwal = namaAgency.getText().toString();
                    String tanggalJadwalMobil = tanggalJadwal.getText().toString();

                    if (response.isSuccessful()) {
                        peminjamanList = response.body().getPeminjamanList();

                        if (peminjamanList == null && namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil) && isBetweenTime){
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
                            btn_ambilMobil.setEnabled(true);
                        }
                        else if (afterEndTime && peminjamanList == null && !namaAgencyJadwal.equals(agencySalesLogin) && currentDate.equals(tanggalJadwalMobil)) {
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_enabled);
                            btn_ambilMobil.setEnabled(true);
                        }
                        else {
                            btn_ambilMobil.setBackgroundResource(R.drawable.custom_button_disabled);
                            btn_ambilMobil.setEnabled(false);
                            btn_ambilMobil.setClickable(false);
                        }

                    } else {

                    }

                }

                @Override
                public void onFailure(Call<FetchStatusPeminjamanMobilResponse> call, Throwable t) {

                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                }
            });
                return true;
        }
    }
}
