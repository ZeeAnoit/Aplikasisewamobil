package com.example.aplikasisewamobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.NumberFormat;

public class PembayaranDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_done);

        // Mengambil nilai total pembayaran dari Intent
        int totalPembayaran = getIntent().getIntExtra("TOTAL_PAYMENT", 0);

        // Format harga total menjadi string dengan pemisah ribuan
        String formattedTotalPembayaran = NumberFormat.getInstance().format(totalPembayaran);

        // Menampilkan total pembayaran di TextView
        TextView textViewTotalPembayaran = findViewById(R.id.totalpembayaran);
        textViewTotalPembayaran.setText("Total Pembayaran: Rp " + formattedTotalPembayaran);

        ImageView btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

