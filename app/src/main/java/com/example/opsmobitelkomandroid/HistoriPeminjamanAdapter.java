package com.example.opsmobitelkomandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.opsmobitelkomandroid.ModelResponse.Peminjaman;
import java.util.List;

public class HistoriPeminjamanAdapter extends RecyclerView.Adapter<HistoriPeminjamanAdapter.ViewHolder> {

    Context context;

    List<Peminjaman> peminjamanList;


    public HistoriPeminjamanAdapter(Context context, List<Peminjaman> peminjamanList) {
        this.context = context;
        this.peminjamanList = peminjamanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_peminjaman_item, parent, false);
        return new HistoriPeminjamanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tanggal.setText(peminjamanList.get(position).getTanggal());
        holder.plat_mobil.setText(peminjamanList.get(position).getPlat_mobil());
        holder.nama_sales.setText(peminjamanList.get(position).getNama_sales());
        holder.nama_agency.setText(peminjamanList.get(position).getNama_agency());
        holder.nama_lokasi.setText(peminjamanList.get(position).getNama_lokasi());
        holder.jam_pinjam.setText(peminjamanList.get(position).getJam_peminjaman());
    }


    @Override
    public int getItemCount() {
        if (peminjamanList == null) return 0;
        return peminjamanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tanggal;
        public TextView plat_mobil;
        public TextView nama_sales;
        public TextView nama_agency;
        public TextView nama_lokasi;
        public TextView jam_pinjam;
        public TextView idSales;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            id_peminjaman = itemView.findViewById(R.id.tv_id_peminjamanHistory);
            tanggal = itemView.findViewById(R.id.tv_tanggalHistory);
            plat_mobil = itemView.findViewById(R.id.tv_plat_mobilHistory);
            nama_sales = itemView.findViewById(R.id.tv_nama_salesHistory);
            nama_agency = itemView.findViewById(R.id.tv_nama_agencyHistory);
            nama_lokasi = itemView.findViewById(R.id.tv_nama_lokasiHistory);
            jam_pinjam = itemView.findViewById(R.id.tv_jam_pinjamHistory);

        }
    }
}
