package com.example.aplikasisewamobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText newPassword, confirmPassword;
    private Button changePasswordButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword = findViewById(R.id.new_password);
        confirmPassword = findViewById(R.id.confirm_password);
        changePasswordButton = findViewById(R.id.change_password_button);
        auth = FirebaseAuth.getInstance();

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String newPasswordText = newPassword.getText().toString().trim();
        String confirmPasswordText = confirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(newPasswordText) || TextUtils.isEmpty(confirmPasswordText)) {
            Toast.makeText(getApplicationContext(), "Kolom tidak terisi semua", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPasswordText.equals(confirmPasswordText)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.updatePassword(newPasswordText)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePasswordActivity.this, "Password updated successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Password update failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
