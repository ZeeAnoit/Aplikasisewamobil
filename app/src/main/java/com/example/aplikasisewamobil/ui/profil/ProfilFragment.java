package com.example.aplikasisewamobil.ui.profil;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aplikasisewamobil.ChangePasswordActivity;
import com.example.aplikasisewamobil.Login;
import com.example.aplikasisewamobil.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.bumptech.glide.Glide;

public class ProfilFragment extends Fragment {

    private TextView emailTextView;
    private ImageView fotoProfilImageView;
    private View privasiButton;
    private View logoutButton;

    public ProfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        // Temukan TextView dengan ID emailnya
        emailTextView = view.findViewById(R.id.emailnya);

        // Temukan ImageView dengan ID fotoprofil
        fotoProfilImageView = view.findViewById(R.id.fotoprofil);

        // Temukan RelativeLayout dengan ID logoutbtn dan tambahkan onTouchListener
        logoutButton = view.findViewById(R.id.logoutbtn);
        logoutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Ubah warna latar belakang menjadi gelap saat tombol ditekan
                    logoutButton.setBackgroundColor(Color.parseColor("#888888"));
                    // Lakukan animasi scale down
                    logoutButton.setScaleX(0.9f);
                    logoutButton.setScaleY(0.9f);
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    // Kembalikan warna latar belakang ke warna semula saat tombol dilepaskan
                    logoutButton.setBackgroundColor(Color.TRANSPARENT);
                    // Lakukan animasi scale up
                    logoutButton.setScaleX(1f);
                    logoutButton.setScaleY(1f);
                }
                return false;
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode logout saat tombol logout diklik
                logout();
            }
        });

        // Temukan RelativeLayout dengan ID privasibtn dan tambahkan onTouchListener
        privasiButton = view.findViewById(R.id.privasibtn);
        privasiButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Ubah warna latar belakang menjadi gelap saat tombol ditekan
                    privasiButton.setBackgroundColor(Color.parseColor("#888888"));
                    // Lakukan animasi scale down
                    privasiButton.setScaleX(0.9f);
                    privasiButton.setScaleY(0.9f);
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    // Kembalikan warna latar belakang ke warna semula saat tombol dilepaskan
                    privasiButton.setBackgroundColor(Color.TRANSPARENT);
                    // Lakukan animasi scale up
                    privasiButton.setScaleX(1f);
                    privasiButton.setScaleY(1f);
                }
                return false;
            }
        });
        privasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Dapatkan instance FirebaseUser yang sedang login
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Ambil alamat email dari pengguna dan set ke TextView
            String email = currentUser.getEmail();
            if (email != null) {
                emailTextView.setText(email);
            }

            // Ambil URL foto profil pengguna dan tampilkan di ImageView
            Uri photoUrl = currentUser.getPhotoUrl();
            if (photoUrl != null) {
                Glide.with(requireContext()) // Pastikan untuk menggunakan requireContext() di dalam fragment
                        .load(photoUrl)
                        .into(fotoProfilImageView);
            }
        }
    }

    // Metode untuk melakukan logout
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        // Redirect ke halaman login setelah logout
        startActivity(new Intent(getActivity(), Login.class));
        getActivity().finish();
    }
}
