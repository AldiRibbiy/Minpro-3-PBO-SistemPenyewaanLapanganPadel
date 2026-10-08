package model;

/**
 * Abstract class ItemSewa (abstraction + superclass).
 * Induk dari Lapangan dan Peralatan. Tidak bisa dibuat objeknya langsung.
 */
public abstract class ItemSewa {
    // Encapsulation: atribut private
    private String id;
    private String nama;
    private double harga;

    public ItemSewa(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // ===== Abstract method: wajib di-override oleh subclass =====
    public abstract String tampilkanInfo();

    public abstract double hitungTotal(int jumlah);

    // ===== Overloading: nama sama dengan hitungTotal(int), parameter berbeda =====
    public double hitungTotal(int jumlah, double diskonPersen) {
        double total = harga * jumlah;
        return total - (total * diskonPersen / 100);
    }
}
