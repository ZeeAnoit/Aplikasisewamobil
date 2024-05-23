package com.example.aplikasisewamobil.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aplikasisewamobil.Experience;
import com.example.aplikasisewamobil.Promo;
import com.example.aplikasisewamobil.R;
import com.example.aplikasisewamobil.pencarianmobil;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Mengambil referensi ke setiap ImageView
        ImageView carRentalImageView = root.findViewById(R.id.car_rental);
        ImageView experienceImageView = root.findViewById(R.id.exkperience);
        ImageView promoImageView = root.findViewById(R.id.promo);
        ImageView memberImageView = root.findViewById(R.id.member);

        // Menambahkan OnTouchListener ke setiap ImageView dengan animasi hold
        carRentalImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, new Intent(requireContext(), pencarianmobil.class));
                return true;
            }
        });

        experienceImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, new Intent(requireContext(), Experience.class));
                return true;
            }
        });

        promoImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, new Intent(requireContext(), Promo.class));
                return true;
            }
        });

        memberImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handleTouchEvent(v, event, null);
                return true;
            }
        });

        return root;
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
                                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                                    Toast.makeText(getContext(), "Member clicked", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .start();
                break;
        }
    }
}
