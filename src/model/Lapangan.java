package model;

/**
 * Subclass Lapangan: extends ItemSewa dan implements Diskon.
 */
public class Lapangan extends ItemSewa implements Diskon {
    private String status; // "Tersedia" atau "Disewa"

    public Lapangan(String id, String nama, double harga) {
        super(id, nama, harga);
        this.status = "Tersedia";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Overriding abstract method
    @Override
    public String tampilkanInfo() {
        return "ID: " + getId() + " | Nama: " + getNama()
                + " | Harga/Jam: Rp" + String.format("%.0f", getHarga())
                + " | Status: " + status;
    }

    // Implementasi interface Diskon: sewa >= 3 jam diskon 10%
    @Override
    public double hitungDiskon(int jumlah) {
        if (jumlah >= 3) {
            return 10;
        }
        return 0;
    }

    // Overriding abstract method (memanggil versi overloading)
    @Override
    public double hitungTotal(int jumlah) {
        return hitungTotal(jumlah, hitungDiskon(jumlah));
    }
}
