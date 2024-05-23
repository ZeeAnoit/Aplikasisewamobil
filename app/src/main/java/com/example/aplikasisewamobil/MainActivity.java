package com.example.aplikasisewamobil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000; // Waktu tampilan splash screen (ms)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Setelah waktu tampilan splash selesai, arahkan ke halaman login
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish(); // Menutup activity ini sehingga pengguna tidak dapat kembali lagi ke splash screen
            }
        }, SPLASH_TIME_OUT);
    }
}