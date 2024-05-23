package com.example.aplikasisewamobil.ui.myorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplikasisewamobil.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.aplikasisewamobil.ui.myorder.MyOrder;

public class MyOrderAdapter extends FirebaseRecyclerAdapter<MyOrder, MyOrderAdapter.MyOrderViewHolder> {

    public MyOrderAdapter(@NonNull FirebaseRecyclerOptions<MyOrder> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position, @NonNull MyOrder model) {
        holder.tvNamaMobil.setText(model.getNamamobil());
        holder.tvDurasi.setText("Durasi: " + model.getDurasi() + " hari");
        holder.tvTanggal.setText("Tanggal: " + model.getTanggalPemesanan());
        holder.tvHarga.setText("Harga: Rp " + model.getHargaTotal());
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyOrderViewHolder(view);
    }

    public static class MyOrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaMobil, tvDurasi, tvTanggal, tvHarga;
        CardView cardView;

        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaMobil = itemView.findViewById(R.id.tv_nama);
            tvDurasi = itemView.findViewById(R.id.tv_durasi);
            tvTanggal = itemView.findViewById(R.id.tanggal);
            tvHarga = itemView.findViewById(R.id.harga);
            cardView = itemView.findViewById(R.id.cardhasil);
        }
    }
}
