package controller;

import java.util.ArrayList;
import model.Peralatan;
import view.MenuView;

/**
 * Controller untuk data Peralatan (raket & bola).
 */
public class PeralatanController {
    private ArrayList<Peralatan> daftar = new ArrayList<>();
    private MenuView view;
    private int nomor = 1;

    public PeralatanController(MenuView view) {
        this.view = view;
        // Dummy data awal
        daftar.add(new Peralatan("P" + nomor++, "Raket Padel", 25000, 10));
        daftar.add(new Peralatan("P" + nomor++, "Bola Padel", 20000, 20));
    }

    public ArrayList<Peralatan> getDaftar() {
        return daftar;
    }

    public Peralatan cariById(String id) {
        for (Peralatan p : daftar) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public void menu() {
        int pilihan;
        do {
            view.tampilMenuCrud("Menu Peralatan (Raket & Bola)");
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
        String nama = view.bacaTeks("Nama Peralatan (Raket/Bola): ");
        double harga = view.bacaHarga("Harga Sewa: ");
        int stok = view.bacaJumlah("Jumlah Stok: ");
        daftar.add(new Peralatan("P" + nomor++, nama, harga, stok));
        view.tampilPesan("Peralatan berhasil ditambahkan!");
    }

    private void lihat() {
        if (daftar.isEmpty()) {
            view.tampilPesan("Belum ada data peralatan.");
            return;
        }
        for (Peralatan p : daftar) {
            view.tampilItem(p);
        }
    }

    private void ubah() {
        Peralatan p = cariById(view.bacaTeks("Masukkan ID Peralatan: "));
        if (p == null) {
            view.tampilPesan("Peralatan tidak ditemukan.");
            return;
        }
        p.setNama(view.bacaTeks("Nama Baru: "));
        p.setHarga(view.bacaHarga("Harga Baru: "));
        p.setStok(view.bacaJumlah("Stok Baru: "));
        view.tampilPesan("Data berhasil diubah!");
    }

    private void hapus() {
        Peralatan p = cariById(view.bacaTeks("Masukkan ID Peralatan: "));
        if (p == null) {
            view.tampilPesan("Peralatan tidak ditemukan.");
            return;
        }
        daftar.remove(p);
        view.tampilPesan("Data berhasil dihapus!");
    }
}
