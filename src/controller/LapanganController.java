package controller;

import java.util.ArrayList;
import model.Lapangan;
import view.MenuView;

/**
 * Controller untuk data Lapangan: mengatur alur menu & CRUD,
 * menghubungkan View dengan Model.
 */
public class LapanganController {
    private ArrayList<Lapangan> daftar = new ArrayList<>();
    private MenuView view;
    private int nomor = 1;

    public LapanganController(MenuView view) {
        this.view = view;
        // Dummy data awal
        daftar.add(new Lapangan("L" + nomor++, "Lapangan A", 150000));
        daftar.add(new Lapangan("L" + nomor++, "Lapangan B", 175000));
    }

    public ArrayList<Lapangan> getDaftar() {
        return daftar;
    }

    public Lapangan cariById(String id) {
        for (Lapangan l : daftar) {
            if (l.getId().equalsIgnoreCase(id)) {
                return l;
            }
        }
        return null;
    }

    public void menu() {
        int pilihan;
        do {
            view.tampilMenuCrud("Menu Lapangan");
            pilihan = view.bacaAngka();
            switch (pilihan) {
                case 1:
                    tambah();
                    break;
                case 2:
                    lihat();
                    break;
                case 3:
                    ubah();
                    break;
                case 4:
                    hapus();
                    break;
                case 0:
                    break;
                default:
                    view.tampilPesan("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    private void tambah() {
        String nama = view.bacaTeks("Nama Lapangan: ");
        double harga = view.bacaHarga("Harga per Jam: ");
        daftar.add(new Lapangan("L" + nomor++, nama, harga));
        view.tampilPesan("Lapangan berhasil ditambahkan!");
    }

    private void lihat() {
        if (daftar.isEmpty()) {
            view.tampilPesan("Belum ada data lapangan.");
            return;
        }
        for (Lapangan l : daftar) {
            view.tampilItem(l);
        }
    }

    private void ubah() {
        Lapangan l = cariById(view.bacaTeks("Masukkan ID Lapangan: "));
        if (l == null) {
            view.tampilPesan("Lapangan tidak ditemukan.");
            return;
        }
        l.setNama(view.bacaTeks("Nama Baru: "));
        l.setHarga(view.bacaHarga("Harga Baru: "));
        view.tampilPesan("Data berhasil diubah!");
    }

    private void hapus() {
        Lapangan l = cariById(view.bacaTeks("Masukkan ID Lapangan: "));
        if (l == null) {
            view.tampilPesan("Lapangan tidak ditemukan.");
            return;
        }
        daftar.remove(l);
        view.tampilPesan("Data berhasil dihapus!");
    }
}
