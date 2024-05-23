package com.example.aplikasisewamobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class Rental extends AppCompatActivity {

    @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_rental);


                    ImageButton btnsearchcar = findViewById(R.id.serchcar);

                    btnsearchcar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                // Meneruskan harga total ke Activity berikutnya (misalnya: pencarianmobil)
                Intent intent = new Intent(Rental.this, pencarianmobil.class);

                startActivity(intent);
            }
        });
    }
}
