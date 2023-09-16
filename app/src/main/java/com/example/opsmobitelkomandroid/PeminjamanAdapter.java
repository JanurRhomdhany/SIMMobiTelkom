package com.example.opsmobitelkomandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opsmobitelkomandroid.ModelResponse.FetchStatusPengembalianMobilResponse;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import com.example.opsmobitelkomandroid.ModelResponse.Pengembalian;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeminjamanAdapter extends RecyclerView.Adapter<PeminjamanAdapter.ViewHolder>{

    private static Context context;
    private List<Peminjaman> peminjamanList;
    private static List<Pengembalian> pengembalianList;
    Button btnAmbil;
    static SharedPrefManager sharedPrefManager;

    //BATAS JAM PENGEMBALIAN
    private final int startHour = 8;
    private final int endHour = 18;

    public PeminjamanAdapter(Context context, List<Peminjaman> peminjamanList) {
        this.context = context;
        this.peminjamanList = peminjamanList;
    }

    public List<Peminjaman> getPeminjamanList() {
        return peminjamanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.status_peminjaman_item, parent, false);
        return new PeminjamanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SharedPrefManager[] sharedPrefManager = new SharedPrefManager[1];
        sharedPrefManager[0] = new SharedPrefManager(context.getApplicationContext());

        Peminjaman fetchStatusPeminjaman = peminjamanList.get(position);

        //Waktu
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

        boolean isBetweenTime = (currentHour > startHour ||
                (currentHour == startHour && currentMinute >= startMinute)) &&
                (currentHour < endHour ||
                        (currentHour == endHour && currentMinute < endMinute));

        boolean afterEndTime = (currentHour > endHour || (currentHour == endHour && currentMinute >= endMinute));

        holder.id_peminjaman.setText(fetchStatusPeminjaman.getId_peminjaman());
        holder.idSales.setText(fetchStatusPeminjaman.getIdSales());
        holder.plat_mobil.setText(fetchStatusPeminjaman.getPlat_mobil());
        holder.jam_pinjam.setText(fetchStatusPeminjaman.getJam_peminjaman());
        holder.nama_lokasi.setText(fetchStatusPeminjaman.getNama_lokasi());
        holder.nama_agency.setText(fetchStatusPeminjaman.getNama_agency());
        holder.nama_sales.setText(fetchStatusPeminjaman.getNama_sales());
        holder.tanggal.setText(fetchStatusPeminjaman.getTanggal());
        holder.status_peminjaman.setText(fetchStatusPeminjaman.getStatus_peminjaman());
        holder.keterangan.setText(fetchStatusPeminjaman.getKeterangan());

        holder.idMobil.setText(fetchStatusPeminjaman.getIdMobil());
        holder.idLokasi.setText(fetchStatusPeminjaman.getIdLokasi());
        holder.idAgency.setText(fetchStatusPeminjaman.getIdAgency());

        if (holder.status_peminjaman.getText().equals("Diterima") && (isBetweenTime || afterEndTime)){
            holder.status_peminjaman.setBackgroundResource(R.drawable.badge_success);
            holder.btnKembalikanMobil.setBackgroundResource(R.drawable.custom_button_enabled);
            holder.btnKembalikanMobil.setEnabled(true);
        } else if (holder.status_peminjaman.getText().equals("Request") && (isBetweenTime || afterEndTime)) {
            holder.btnKembalikanMobil.setBackgroundResource(R.drawable.custom_button_disabled);
            holder.btnKembalikanMobil.setEnabled(false);
            holder.btnKembalikanMobil.setClickable(false);
            holder.status_peminjaman.setBackgroundResource(R.drawable.badge_warning);
        }
        else if (holder.status_peminjaman.getText().equals("Ditolak") && (isBetweenTime || afterEndTime)){
            holder.status_peminjaman.setBackgroundResource(R.drawable.badge_danger);
            holder.btnKembalikanMobil.setBackgroundResource(R.drawable.custom_button_disabled);
            holder.btnKembalikanMobil.setEnabled(false);
            holder.btnKembalikanMobil.setClickable(false);
        }

        View view2 = LayoutInflater.from(context).inflate(R.layout.jadwal_item, null);
        btnAmbil = view2.findViewById(R.id.btn_ambilMobil);

        holder.btnKembalikanMobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, PengembalianMobil.class);
                i.putExtra("idMobilPinjam", holder.idMobil.getText().toString());
                i.putExtra("idLokasiPinjam", holder.idLokasi.getText().toString());
                context.startActivity(i);

            }

        });

    }



    @Override
    public int getItemCount() {
        if (peminjamanList == null) return 0;
        return peminjamanList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id_peminjaman;
        public TextView tanggal;
        public TextView plat_mobil;
        public TextView status_peminjaman;
        public TextView nama_sales;
        public TextView nama_agency;
        public TextView nama_lokasi;
        public TextView jam_pinjam;
        public TextView keterangan;
        public TextView idMobil;
        public TextView idLokasi;
        public TextView idAgency;
        public TextView idSales;
        public TextView bukti_kegiatan;
        public Button btnKembalikanMobil;
        public Button btnPilihGambar;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            sharedPrefManager = new SharedPrefManager(context.getApplicationContext());


            String idSalesLogin = sharedPrefManager.getLogin().getIdSales();

            id_peminjaman = itemView.findViewById(R.id.tv_id_peminjaman);
            tanggal = itemView.findViewById(R.id.tv_tanggal);
            plat_mobil = itemView.findViewById(R.id.tv_plat_mobil);
            status_peminjaman = itemView.findViewById(R.id.tv_status_peminjaman);
            nama_sales = itemView.findViewById(R.id.tv_nama_sales);
            nama_agency = itemView.findViewById(R.id.tv_nama_agency);
            nama_lokasi = itemView.findViewById(R.id.tv_nama_lokasi);
            jam_pinjam = itemView.findViewById(R.id.tv_jam_pinjam);
            keterangan = itemView.findViewById(R.id.tv_ketPeminjaman);

            idLokasi = itemView.findViewById(R.id.tv_idLokasiPeminjaman);
            idAgency = itemView.findViewById(R.id.tv_idAgencyPeminjaman);
            idMobil = itemView.findViewById(R.id.tv_idMobilPeminjaman);

            idSales = itemView.findViewById(R.id.tv_idSales);
            btnPilihGambar = itemView.findViewById(R.id.btn_pilihGambar);
            bukti_kegiatan = itemView.findViewById(R.id.tv_buktiKegiatanPeminjaman);

            btnKembalikanMobil = itemView.findViewById(R.id.btn_kembaliMobil);

            Calendar c = Calendar.getInstance();
            Date d = Calendar.getInstance().getTime();
            // Create a SimpleDateFormat object to format the time
            SimpleDateFormat dateFormatJam = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            //simpledateformattanggal
            SimpleDateFormat dateFormatTanggal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

            String currentDate = dateFormatTanggal.format(d);

            fetchDataPengembalianMobil(idSalesLogin, currentDate);

        }

        private boolean fetchDataPengembalianMobil(String idSalesLogin, String tanggalKembali) {
            Call<FetchStatusPengembalianMobilResponse> call = RetrofitClient.getInstance().getApi().fetchPengembalian(idSalesLogin, tanggalKembali);

            call.enqueue(new Callback<FetchStatusPengembalianMobilResponse>() {
                @Override
                public void onResponse(Call<FetchStatusPengembalianMobilResponse> call, Response<FetchStatusPengembalianMobilResponse> response) {

                    if (response.isSuccessful()){

                        pengembalianList = response.body().getPengembalianList();
                        if (pengembalianList == null){
                            btnKembalikanMobil.setBackgroundResource(R.drawable.custom_button_enabled);
                            btnKembalikanMobil.setEnabled(true);
                        }else {
                            btnKembalikanMobil.setBackgroundResource(R.drawable.custom_button_disabled);
                            btnKembalikanMobil.setEnabled(false);
                            btnKembalikanMobil.setClickable(false);
                        }

                    }else {

                    }
                }

                @Override
                public void onFailure(Call<FetchStatusPengembalianMobilResponse> call, Throwable t) {
                    Toast.makeText(context, "Gagal", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
    }




}
