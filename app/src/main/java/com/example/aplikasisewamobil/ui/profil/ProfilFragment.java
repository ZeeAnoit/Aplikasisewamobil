package com.example.aplikasisewamobil.ui.profil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aplikasisewamobil.Login;
import com.example.aplikasisewamobil.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.bumptech.glide.Glide;

public class ProfilFragment extends Fragment {

    private TextView emailTextView;
    private ImageView fotoProfilImageView;

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

        // Temukan RelativeLayout dengan ID logoutbtn dan tambahkan onClickListener
        View logoutButton = view.findViewById(R.id.logoutbtn);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Panggil metode animateLogout dengan animasi saat tombol logout diklik
                animateLogout(v);
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

    // Metode untuk melakukan logout dengan animasi
    private void animateLogout(View view) {
        view.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .alpha(0.7f)
                .setDuration(150)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        view.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .alpha(1f)
                                .setDuration(150)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        logout();
                                    }
                                })
                                .start();
                    }
                })
                .start();
    }

    // Metode untuk melakukan logout
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        // Redirect ke halaman login setelah logout
        startActivity(new Intent(getActivity(), Login.class));
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
