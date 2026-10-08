package app;

import controller.LapanganController;
import controller.PenyewaanController;
import controller.PeralatanController;
import view.MenuView;

/**
 * Entry point program. Hanya membuat View & Controller lalu menjalankan menu utama.
 */
public class Main {
    public static void main(String[] args) {
        MenuView view = new MenuView();
        LapanganController lapanganController = new LapanganController(view);
        PeralatanController peralatanController = new PeralatanController(view);
        PenyewaanController penyewaanController =
                new PenyewaanController(view, lapanganController, peralatanController);

        int pilihan;
        do {
            view.tampilMenuUtama();
            pilihan = view.bacaAngka();
            switch (pilihan) {
                case 1:
                    lapanganController.menu();
                    break;
                case 2:
                    peralatanController.menu();
                    break;
                case 3:
                    penyewaanController.menu();
                    break;
                case 0:
                    view.tampilPesan("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    view.tampilPesan("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }
}
