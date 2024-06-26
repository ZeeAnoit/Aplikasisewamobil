package com.example.aplikasisewamobil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aplikasisewamobil.ui.myorder.MyOrder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Calendar;

public class MenuPembayaran3 extends AppCompatActivity {
    private TextView totalPriceTextView;
    private Button btnDatePicker;
    private int hargaTotal = 0;
    private String namamobil = "NEW INNOVA ZENIX";  // Default car name
    private String tanggalPemesanan;
    private int durasi;
    private DatabaseReference userOrderRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pembayaran3);

        Spinner durationSpinner = findViewById(R.id.duration3);
        totalPriceTextView = findViewById(R.id.total_price_textview3);
        btnDatePicker = findViewById(R.id.btn_date_picker3); // Button for date picker

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            userOrderRef = FirebaseDatabase.getInstance().getReference("UserOrders").child(userId);
        }

        String[] durations = {"1 Hari", "2 Hari", "3 Hari", "4 Hari", "5 Hari", "6 Hari", "7 Hari"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, durations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(adapter);

        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                durasi = Integer.parseInt(durationSpinner.getSelectedItem().toString().split(" ")[0]);

                int hargaMobilPerHari = 800000;
                hargaTotal = durasi * hargaMobilPerHari;

                String formattedHargaTotal = "Rp " + NumberFormat.getInstance().format(hargaTotal);
                totalPriceTextView.setText(formattedHargaTotal);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Button click listener for date picker
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button btnbayar = findViewById(R.id.btnbayar3);
        ImageView btnback = findViewById(R.id.btnback);

        btnbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFirebase();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pencarianmobil.class);
                startActivity(intent);
            }
        });
    }

    // Method to show date picker dialog
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MenuPembayaran3.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, (monthOfYear + 1), year);
                btnDatePicker.setText(selectedDate);  // Set selected date to button
                tanggalPemesanan = selectedDate;
            }
        }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    // Method to save data to Firebase
    private void saveDataToFirebase() {
        MyOrder myOrder = new MyOrder(tanggalPemesanan, namamobil, durasi, hargaTotal);

        userOrderRef.push().setValue(myOrder)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MenuPembayaran3.this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show();
                        redirectToPaymentDone();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MenuPembayaran3.this, "Pembayaran Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Method to redirect to payment done activity
    private void redirectToPaymentDone() {
        Intent intent = new Intent(MenuPembayaran3.this, PembayaranDone.class);
        intent.putExtra("TOTAL_PAYMENT", hargaTotal);
        startActivity(intent);
        finish();
    }
}