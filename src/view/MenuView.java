package view;

import java.util.Scanner;
import model.ItemSewa;
import model.Penyewaan;

/**
 * View: khusus menangani tampilan (output) dan input dari user.
 * Tidak berisi logika pengolahan data.
 */
public class MenuView {
    private Scanner sc = new Scanner(System.in);

    public void tampilMenuUtama() {
        System.out.println("\n===== SISTEM PENYEWAAN LAPANGAN PADEL =====");
        System.out.println("1. Kelola Lapangan");
        System.out.println("2. Kelola Peralatan (Raket & Bola)");
        System.out.println("3. Kelola Penyewaan");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    public void tampilMenuCrud(String judul) {
        System.out.println("\n-- " + judul + " --");
        System.out.println("1. Tambah");
        System.out.println("2. Lihat");
        System.out.println("3. Ubah");
        System.out.println("4. Hapus");
        System.out.println("0. Kembali");
        System.out.print("Pilih: ");
    }

    // ===== Overloading: dua method tampilPesan dengan parameter berbeda =====
    public void tampilPesan(String pesan) {
        System.out.println(pesan);
    }

    public void tampilPesan(String pesan, double nilai) {
        System.out.println(pesan + ": " + String.format("%.0f", nilai));
    }

    // Menerima tipe superclass; hasil tampilan mengikuti objek aslinya (polymorphism)
    public void tampilItem(ItemSewa item) {
        System.out.println(item.tampilkanInfo());
    }

    public void tampilPenyewaan(Penyewaan p) {
        System.out.println(p);
    }

    // ===== Input + validasi =====
    public int bacaAngka() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String bacaTeks(String label) {
        String teks;
        do {
            System.out.print(label);
            teks = sc.nextLine().trim();
            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong!");
            }
        } while (teks.isEmpty());
        return teks;
    }

    public double bacaHarga(String label) {
        while (true) {
            System.out.print(label);
            try {
                double nilai = Double.parseDouble(sc.nextLine().trim());
                if (nilai <= 0) {
                    System.out.println("Harga harus lebih besar dari 0!");
                } else {
                    return nilai;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public int bacaJumlah(String label) {
        while (true) {
            System.out.print(label);
            try {
                int nilai = Integer.parseInt(sc.nextLine().trim());
                if (nilai <= 0) {
                    System.out.println("Jumlah harus lebih besar dari 0!");
                } else {
                    return nilai;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
    }
}
