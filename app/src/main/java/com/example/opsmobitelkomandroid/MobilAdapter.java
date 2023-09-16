package com.example.opsmobitelkomandroid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opsmobitelkomandroid.ModelResponse.Mobil;

import java.util.List;

public class MobilAdapter extends RecyclerView.Adapter<MobilAdapter.ViewHolder> {

    public MobilAdapter(Context context, List<Mobil> mobilList) {
        this.context = context;
        this.mobilList = mobilList;
    }

    private Context context;
    private List<Mobil> mobilList;

    @NonNull
    @Override
    public MobilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lihat_mobil_item, parent, false);
        return new MobilAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MobilAdapter.ViewHolder holder, int position) {

        holder.id_mobil.setText(mobilList.get(position).getId_mobil());
        holder.plat_mobil.setText(mobilList.get(position).getPlat_mobil());
        holder.status_mobil.setText(mobilList.get(position).getStatus_mobil());

        if (holder.status_mobil.getText().equals("Tersedia")){
            holder.status_mobil.setBackgroundColor(Color.parseColor("#198754"));
        } else {
            holder.status_mobil.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    @Override
    public int getItemCount() {

        return mobilList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id_mobil, plat_mobil, status_mobil;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            id_mobil = itemView.findViewById(R.id.tv_id_mobil);
            plat_mobil = itemView.findViewById(R.id.tv_plat_mobil);
            status_mobil = itemView.findViewById(R.id.tv_status_mobil);
        }
    }
}
