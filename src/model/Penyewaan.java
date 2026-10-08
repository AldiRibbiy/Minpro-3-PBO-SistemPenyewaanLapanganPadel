package model;

/**
 * Class Penyewaan: data transaksi sewa lapangan maupun peralatan.
 * Menyimpan referensi bertipe ItemSewa (superclass), sehingga bisa
 * berisi objek Lapangan ataupun Peralatan.
 */
public class Penyewaan {
    private String id;
    private String namaPenyewa;
    private String jenis; // "Lapangan" atau "Peralatan"
    private ItemSewa item;
    private int jumlah;
    private double totalHarga;

    public Penyewaan(String id, String namaPenyewa, String jenis, ItemSewa item, int jumlah, double totalHarga) {
        this.id = id;
        this.namaPenyewa = namaPenyewa;
        this.jenis = jenis;
        this.item = item;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }

    public String getId() {
        return id;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getJenis() {
        return jenis;
    }

    public ItemSewa getItem() {
        return item;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    @Override
    public String toString() {
        return "ID Sewa: " + id + " | Penyewa: " + namaPenyewa + " | Jenis: " + jenis
                + " | Item: " + item.getNama() + " | Jumlah: " + jumlah
                + " | Total: Rp" + String.format("%.0f", totalHarga);
    }
}
