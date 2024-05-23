package com.example.aplikasisewamobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class pencarianmobil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarianmobil);

        View shape1 = findViewById(R.id.shape1);
        View shape2 = findViewById(R.id.shape2);
        View shape3 = findViewById(R.id.shape3);
        ImageView btnback = findViewById(R.id.btnback);

        // Tambahkan OnTouchListener ke setiap shape dengan animasi hold
        shape1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, new Intent(getApplicationContext(), MenuPembayaran3.class));
                return true;
            }
        });

        shape2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, new Intent(getApplicationContext(), MenuPembayaran2.class));
                return true;
            }
        });

        shape3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, new Intent(getApplicationContext(), MenuPembayaran.class));
                return true;
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void handleTouchEvent(final View view, MotionEvent event, final Intent intent) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view.animate()
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setDuration(150)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(150)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                if (intent != null && event.getAction() == MotionEvent.ACTION_UP) {
                                    startActivity(intent);
                                }
                            }
                        })
                        .start();
                break;
        }
    }
}
