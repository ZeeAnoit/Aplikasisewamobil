package com.example.aplikasisewamobil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

public class Experience extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        // Menginisialisasi tombol kembali
        ImageButton backButton = findViewById(R.id.imageButton2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasi aksi untuk tombol kembali
                onBackPressed();
            }
        });

        // Menginisialisasi pencarian
        SearchView searchView = findViewById(R.id.editTextSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Implementasi aksi saat pengguna menekan tombol kirim pada keyboard
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Implementasi aksi saat teks pencarian berubah
                return false;
            }
        });

        // Menyiapkan RecyclerView untuk daftar paket pemesanan
        RecyclerView recyclerViewPackages = findViewById(R.id.recyclerViewPackages);
        // Set up RecyclerView adapter and layout manager here

        // Menyiapkan RecyclerView untuk paket pilihan
        RecyclerView recyclerViewOptions = findViewById(R.id.recyclerViewOptions);
        // Set up RecyclerView adapter and layout manager here
    }
}
