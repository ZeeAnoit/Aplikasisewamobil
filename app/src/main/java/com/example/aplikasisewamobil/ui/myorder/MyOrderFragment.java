package com.example.aplikasisewamobil.ui.myorder;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.aplikasisewamobil.R; // Sesuaikan dengan package Anda
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<MyOrder, MyOrderAdapter.MyOrderViewHolder> myOrderAdapter;
    private DatabaseReference databaseReference;

    public MyOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);

        recyclerView = view.findViewById(R.id.tampilanorder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Mendapatkan UID pengguna yang sedang login
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = currentUser != null ? currentUser.getUid() : null;

        // Inisialisasi Firebase Database
        if (currentUserId != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("UserOrders").child(currentUserId);
        }

        // Penggunaan FirebaseRecyclerOptions untuk mengkonfigurasi query dari database
        FirebaseRecyclerOptions<MyOrder> options =
                new FirebaseRecyclerOptions.Builder<MyOrder>()
                        .setQuery(databaseReference, MyOrder.class)
                        .build();

        // Inisialisasi adapter
        myOrderAdapter = new MyOrderAdapter(options);

        // Set adapter ke RecyclerView
        recyclerView.setAdapter(myOrderAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myOrderAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myOrderAdapter.stopListening();
    }
}
