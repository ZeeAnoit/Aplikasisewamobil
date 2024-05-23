package com.example.aplikasisewamobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText etemail, etpassword;
    private TextView btnDaftar;
    private Button btnlogin; // tambahkan tombol Daftar
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail = findViewById(R.id.Email);
        etpassword = findViewById(R.id.editTextPassword);
        btnlogin = findViewById(R.id.button_login);
        btnDaftar = findViewById(R.id.btndaftar); // inisialisasi tombol Daftar

        auth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tambahkan animasi saat tombol login diklik
                btnlogin.animate()
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setDuration(100)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                btnlogin.animate()
                                        .scaleX(1f)
                                        .scaleY(1f)
                                        .setDuration(100)
                                        .start();

                                // Proses login setelah animasi selesai
                                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = auth.getCurrentUser();
                                            if (user != null && user.isEmailVerified()) {
                                                Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                                String userId = user.getUid();
                                                saveDataToFirebase(userId); // Panggil metode ini dengan UID pengguna
                                                startActivity(new Intent(getApplicationContext(), Home.class));
                                                finish();
                                            } else {
                                                etemail.setError("Email belum diverifikasi atau akun tidak ditemukan");
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        })
                        .start();
            }
        });

        // Tambahkan onClickListener untuk tombol Daftar
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }

    private void saveDataToFirebase(String userId) {
        // Di sini Anda dapat memasukkan logika untuk menyimpan data ke Firebase Realtime Database
        // Contoh:
        // DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);
        // userRef.setValue(new User(email, name, age));
    }
}
