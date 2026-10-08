package model;

/**
 * Subclass Peralatan: extends ItemSewa dan implements Diskon.
 */
public class Peralatan extends ItemSewa implements Diskon {
    private int stok;

    public Peralatan(String id, String nama, double harga, int stok) {
        super(id, nama, harga);
        this.stok = stok;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Overriding abstract method
    @Override
    public String tampilkanInfo() {
        return "ID: " + getId() + " | Nama: " + getNama()
                + " | Harga Sewa: Rp" + String.format("%.0f", getHarga())
                + " | Stok: " + stok;
    }

    // Implementasi interface Diskon: sewa >= 5 unit diskon 5%
    @Override
    public double hitungDiskon(int jumlah) {
        if (jumlah >= 5) {
            return 5;
        }
        return 0;
    }

    // Overriding abstract method (memanggil versi overloading)
    @Override
    public double hitungTotal(int jumlah) {
        return hitungTotal(jumlah, hitungDiskon(jumlah));
    }
}
