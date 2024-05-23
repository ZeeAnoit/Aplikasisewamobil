package com.example.aplikasisewamobil;

public class pembayaran {
    private String namaPelanggan;
    private String namaMobil;
    private int durasi;
    private int totalHarga;

    public pembayaran() {
        // Default constructor diperlukan untuk Firebase
    }

    public pembayaran(String namaPelanggan, String namaMobil, int durasi, int totalHarga) {
        this.namaPelanggan = namaPelanggan;
        this.namaMobil = namaMobil;
        this.durasi = durasi;
        this.totalHarga = totalHarga;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

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

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }
}
