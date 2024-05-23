package com.example.aplikasisewamobil.ui.myorder;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class MyOrder {
    @PropertyName("tanggalPemesanan")
    private String tanggalPemesanan;

    @PropertyName("namamobil")
    private String namamobil;

    @PropertyName("durasi")
    private int durasi;

    @PropertyName("hargaTotal")
    private int hargaTotal;

    public MyOrder() {
        // Default constructor required for Firebase
    }

    public MyOrder(String tanggalPemesanan, String namamobil, int durasi, int hargaTotal) {
        this.tanggalPemesanan = tanggalPemesanan;
        this.namamobil = namamobil;
        this.durasi = durasi;
        this.hargaTotal = hargaTotal;
    }

    @PropertyName("tanggalPemesanan")
    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    @PropertyName("tanggalPemesanan")
    public void setTanggalPemesanan(String tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    @PropertyName("namamobil")
    public String getNamamobil() {
        return namamobil;
    }

    @PropertyName("namamobil")
    public void setNamamobil(String namamobil) {
        this.namamobil = namamobil;
    }

    @PropertyName("durasi")
    public int getDurasi() {
        return durasi;
    }

    @PropertyName("durasi")
    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    @PropertyName("hargaTotal")
    public int getHargaTotal() {
        return hargaTotal;
    }

    @PropertyName("hargaTotal")
    public void setHargaTotal(int hargaTotal) {
        this.hargaTotal = hargaTotal;
    }
}
