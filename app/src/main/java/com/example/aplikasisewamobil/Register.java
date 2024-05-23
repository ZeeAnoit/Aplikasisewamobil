package com.example.aplikasisewamobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText etemail, etpassword, etconfirmpassword ;
    private TextView btnlogin;
    private Button btnregister;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etemail = findViewById(R.id.Email);
        etpassword = findViewById(R.id.password);
        etconfirmpassword = findViewById(R.id.confirm_password);
        btnregister = findViewById(R.id.button_singup);
        btnlogin = findViewById(R.id.login_sudahpunyaakun);

        auth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();
                String confirm = etconfirmpassword.getText().toString();

                if (!email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {

                    if (password.length() > 6){
                        if (confirm.equals(password)){
                            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(getApplicationContext(),"Daftar Berhasil, Silahkan cek Email", Toast.LENGTH_SHORT).show();

                                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                                }else {
                                                    Toast.makeText(getApplicationContext(),"Verifiasi Gagal", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                    }else {
                                        Toast.makeText(getApplicationContext(),"Daftar Gagal", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }else {
                            etconfirmpassword.setError("Password Tidak sama!!");
                        }
                    }else {
                        etpassword.setError("Password Harus Lebih Dari 6 Karakter!!");
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}