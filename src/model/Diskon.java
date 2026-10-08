package model;

/**
 * Interface Diskon (nilai tambah).
 * Kontrak untuk setiap item sewa yang bisa memberikan diskon.
 * Cara hitung diskon ditentukan oleh class yang meng-implement-nya.
 */
public interface Diskon {
    // mengembalikan persentase diskon berdasarkan jumlah sewa
    double hitungDiskon(int jumlah);
}
