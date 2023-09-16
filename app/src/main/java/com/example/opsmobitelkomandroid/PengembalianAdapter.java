package com.example.opsmobitelkomandroid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opsmobitelkomandroid.ModelResponse.Pengembalian;

import java.util.List;

public class PengembalianAdapter extends RecyclerView.Adapter<PengembalianAdapter.ViewHolder>{

    private Context context;
    private List<Pengembalian> pengembalianList;

    public PengembalianAdapter(Context context, List<Pengembalian> pengembalianList) {
        this.context = context;
        this.pengembalianList = pengembalianList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.status_pengembalian_item, parent, false);
        return new PengembalianAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pengembalian fetchPengembalian = pengembalianList.get(position);

        holder.id_pengembalian.setText(fetchPengembalian.getId_pengembalian());
        holder.ket_status.setText(fetchPengembalian.getKet_status());
        holder.nama_lokasi.setText(fetchPengembalian.getNama_lokasi());
        holder.nama_sales.setText(fetchPengembalian.getNama_sales());
        holder.nama_agency.setText(fetchPengembalian.getNama_agency());
        holder.plat_mobil.setText(fetchPengembalian.getPlat_mobil());
        holder.ket_kegiatan.setText(fetchPengembalian.getKet_kegiatan());
        holder.bukti_kegiatan.setText(fetchPengembalian.getBukti_kegiatan());
        holder.jam_pengembalian.setText(fetchPengembalian.getJam_pengembalian());
        holder.tanggal.setText(fetchPengembalian.getTanggal());

        holder.status_pengembalian.setText(fetchPengembalian.getStatus_pengembalian());

        if (holder.status_pengembalian.getText().equals("Berhasil")){
            holder.status_pengembalian.setBackgroundResource(R.drawable.badge_success);
        } else if (holder.status_pengembalian.getText().equals("Request")) {
            holder.status_pengembalian.setBackgroundResource(R.drawable.badge_warning);
        }
        else {
            holder.status_pengembalian.setBackgroundResource(R.drawable.badge_danger);
        }
    }

    @Override
    public int getItemCount() {
        if (pengembalianList == null) return 0;
        return pengembalianList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id_pengembalian, tanggal, plat_mobil, status_pengembalian, nama_sales, nama_agency, nama_lokasi, jam_pengembalian, ket_status, bukti_kegiatan, ket_kegiatan;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            id_pengembalian = itemView.findViewById(R.id.tv_id_pengembalian);
            tanggal = itemView.findViewById(R.id.tv_tanggalPengembalian);
            plat_mobil = itemView.findViewById(R.id.tv_plat_mobilPengembalian);
            status_pengembalian = itemView.findViewById(R.id.tv_status_pengembalianMobil);
            nama_sales = itemView.findViewById(R.id.tv_nama_salesPengembalian);
            nama_agency = itemView.findViewById(R.id.tv_nama_agencyPengembalian);
            nama_lokasi = itemView.findViewById(R.id.tv_nama_lokasiPengembalian);
            jam_pengembalian = itemView.findViewById(R.id.tv_jam_pengembalian);
            ket_status = itemView.findViewById(R.id.tv_keterangan_statusPengembalian);
            bukti_kegiatan = itemView.findViewById(R.id.tv_bukti_kegiatanPengembalian);
            ket_kegiatan = itemView.findViewById(R.id.tv_keterangan_kegiatanPengembalian);




        }
    }
}
