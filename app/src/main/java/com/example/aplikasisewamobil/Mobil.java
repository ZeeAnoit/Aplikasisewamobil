package com.example.aplikasisewamobil;

public class Mobil {
    private String namaMobil;
    private int durasi;

    // Constructor
    public Mobil() {
        // Default constructor diperlukan untuk Firebase Realtime Database
    }

    public Mobil(String namaMobil, int durasi) {
        this.namaMobil = namaMobil;
        this.durasi = durasi;
    }

    // Getter dan setter
    public String getNamaMobil() {
        return namaMobil;
    }

    public void setNamaMobil(String namaMobil) {
        this.namaMobil = namaMobil;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }
}