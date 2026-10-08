package controller;

import java.util.ArrayList;
import model.Diskon;
import model.ItemSewa;
import model.Lapangan;
import model.Penyewaan;
import model.Peralatan;
import view.MenuView;

/**
 * Controller untuk transaksi Penyewaan (lapangan & peralatan).
 */
public class PenyewaanController {
    private ArrayList<Penyewaan> daftar = new ArrayList<>();
    private MenuView view;
    private LapanganController lapanganController;
    private PeralatanController peralatanController;
    private int nomor = 1;

    public PenyewaanController(MenuView view, LapanganController lc, PeralatanController pc) {
        this.view = view;
        this.lapanganController = lc;
        this.peralatanController = pc;

        // Dummy data awal: Budi menyewa 2 raket
        Peralatan raket = pc.getDaftar().get(0);
        raket.setStok(raket.getStok() - 2);
        daftar.add(new Penyewaan("SW" + nomor++, "Budi", "Peralatan", raket, 2, raket.hitungTotal(2)));
    }

    public void menu() {
        int pilihan;
        do {
            view.tampilMenuCrud("Menu Penyewaan");
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

    private Penyewaan cariById(String id) {
        for (Penyewaan p : daftar) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private void tambah() {
        view.tampilPesan("Sewa apa? 1. Lapangan   2. Peralatan");
        int pilih = view.bacaAngka();

        ItemSewa item;   // tipe superclass, bisa berisi Lapangan atau Peralatan
        String jenis;
        String label;

        if (pilih == 1) {
            jenis = "Lapangan";
            label = "Durasi (jam): ";
            for (Lapangan l : lapanganController.getDaftar()) {
                view.tampilItem(l);
            }
            item = lapanganController.cariById(view.bacaTeks("Masukkan ID Lapangan: "));
        } else if (pilih == 2) {
            jenis = "Peralatan";
            label = "Jumlah: ";
            for (Peralatan p : peralatanController.getDaftar()) {
                view.tampilItem(p);
            }
            item = peralatanController.cariById(view.bacaTeks("Masukkan ID Peralatan: "));
        } else {
            view.tampilPesan("Pilihan tidak valid.");
            return;
        }

        if (item == null) {
            view.tampilPesan("Data tidak ditemukan.");
            return;
        }

        String nama = view.bacaTeks("Nama Penyewa: ");
        int jumlah = view.bacaJumlah(label);

        if (item instanceof Peralatan) {
            Peralatan p = (Peralatan) item;
            if (jumlah > p.getStok()) {
                view.tampilPesan("Stok tidak mencukupi. Stok tersedia: " + p.getStok());
                return;
            }
            p.setStok(p.getStok() - jumlah);
        } else if (item instanceof Lapangan) {
            Lapangan l = (Lapangan) item;
            if (l.getStatus().equals("Disewa")) {
                view.tampilPesan("Lapangan sedang disewa.");
                return;
            }
            l.setStatus("Disewa");
        }

        // Polymorphism: satu pemanggilan, hasilnya mengikuti jenis objek
        double total = item.hitungTotal(jumlah);

        if (item instanceof Diskon) {
            double diskon = ((Diskon) item).hitungDiskon(jumlah);
            if (diskon > 0) {
                view.tampilPesan("Selamat, dapat diskon (%)", diskon);
            }
        }

        daftar.add(new Penyewaan("SW" + nomor++, nama, jenis, item, jumlah, total));
        view.tampilPesan("Penyewaan berhasil! Total (Rp)", total);
    }

    private void lihat() {
        if (daftar.isEmpty()) {
            view.tampilPesan("Belum ada data penyewaan.");
            return;
        }
        for (Penyewaan p : daftar) {
            view.tampilPenyewaan(p);
        }
    }

    private void ubah() {
        Penyewaan p = cariById(view.bacaTeks("Masukkan ID Penyewaan: "));
        if (p == null) {
            view.tampilPesan("Penyewaan tidak ditemukan.");
            return;
        }
        p.setNamaPenyewa(view.bacaTeks("Nama Penyewa Baru: "));
        view.tampilPesan("Data berhasil diubah!");
    }

    // Menghapus/membatalkan penyewaan: lapangan jadi Tersedia, stok peralatan dikembalikan
    private void hapus() {
        Penyewaan p = cariById(view.bacaTeks("Masukkan ID Penyewaan: "));
        if (p == null) {
            view.tampilPesan("Penyewaan tidak ditemukan.");
            return;
        }
        ItemSewa item = p.getItem();
        if (item instanceof Lapangan) {
            ((Lapangan) item).setStatus("Tersedia");
        } else if (item instanceof Peralatan) {
            Peralatan pl = (Peralatan) item;
            pl.setStok(pl.getStok() + p.getJumlah());
        }
        daftar.remove(p);
        view.tampilPesan("Penyewaan dibatalkan.");
    }
}
