# Minpro-3-PBO-SistemPenyewaanLapanganPadel

## 1. Deskripsi Singkat Program

![Screenshot Menu Program](screenshot/menu.png)

Program ini adalah aplikasi console berbasis Java untuk mengelola penyewaan lapangan padel. Tujuannya membantu pengelola mencatat data lapangan, data peralatan (raket dan bola), dan transaksi penyewaan agar lebih rapi dibanding pencatatan manual.

Fitur program:
- Kelola Lapangan: tambah, lihat, ubah, hapus.
- Kelola Peralatan (raket dan bola): tambah, lihat, ubah, hapus.
- Kelola Penyewaan: sewa lapangan atau peralatan, lihat, ubah nama penyewa, batalkan sewa.
- Diskon otomatis untuk sewa dalam jumlah tertentu.
- Sudah ada dummy data awal, jadi menu Lihat langsung menampilkan data.

## 2. Penjelasan Struktur Package

![Screenshot Struktur Package](screenshot/package.png)

Program memakai struktur MVC (Model, View, Controller):

- **app**
  - Main.java
- **model** (data dan aturan hitung)
  - Diskon.java
  - ItemSewa.java
  - Lapangan.java
  - Peralatan.java
  - Penyewaan.java
- **view** (tampilan dan input)
  - MenuView.java
- **controller** (penghubung view dan model)
  - LapanganController.java
  - PeralatanController.java
  - PenyewaanController.java

Pembagian tugasnya:
- **Model**: menyimpan data dan cara menghitung (total harga, diskon).
- **View**: hanya menampilkan menu ke layar dan membaca input dari user.
- **Controller**: mengatur alur menu, menyimpan data di ArrayList, dan menjalankan proses tambah, lihat, ubah, hapus.
- **App**: titik mulai program.

## 3. Penjelasan Setiap Kode

### Package app

**Main.java**
Titik awal program. Di sini dibuat satu objek MenuView dan tiga controller. Lalu ditampilkan menu utama dalam perulangan do-while, dan pilihan user diproses dengan switch-case. Program berhenti hanya jika user memilih 0 (Keluar).

### Package model

**Diskon.java** (interface)
Berisi satu method, hitungDiskon, tanpa isi. Class yang memakai interface ini wajib menuliskan cara menghitung diskonnya sendiri.

**ItemSewa.java** (abstract class, superclass)
Induk dari Lapangan dan Peralatan. Menyimpan data yang sama untuk semua barang sewa: id, nama, dan harga, lengkap dengan getter dan setter. Punya dua abstract method yang wajib diisi subclass, yaitu tampilkanInfo dan hitungTotal. Juga punya hitungTotal versi kedua yang menerima diskon persen (overloading).

**Lapangan.java** (subclass dari ItemSewa)
Menambah atribut status (Tersedia atau Disewa). Isi tampilkanInfo menampilkan data lapangan beserta statusnya. Diskon 10% diberikan jika sewa 3 jam atau lebih. Total dihitung dari harga per jam dikali durasi, dikurangi diskon.

**Peralatan.java** (subclass dari ItemSewa)
Menambah atribut stok. Isi tampilkanInfo menampilkan data peralatan beserta stoknya. Diskon 5% diberikan jika sewa 5 unit atau lebih. Total dihitung dari harga sewa dikali jumlah, dikurangi diskon.

**Penyewaan.java**
Menyimpan satu transaksi sewa: id, nama penyewa, jenis (Lapangan atau Peralatan), item yang disewa, jumlah, dan total harga. Atribut item bertipe ItemSewa, jadi bisa berisi Lapangan maupun Peralatan.

### Package view

**MenuView.java**
Satu-satunya class yang memakai Scanner dan menampilkan teks ke layar. Isinya:
- tampilMenuUtama dan tampilMenuCrud: menampilkan daftar menu.
- tampilPesan: menampilkan pesan. Ada dua versi (overloading), satu untuk teks saja dan satu untuk teks dengan angka.
- tampilItem dan tampilPenyewaan: menampilkan data.
- bacaAngka, bacaTeks, bacaHarga, bacaJumlah: membaca input sekaligus memvalidasinya (dijelaskan di bagian Validasi Input).

### Package controller

**LapanganController.java**
Menyimpan ArrayList berisi data lapangan dan mengisi dummy data awal (Lapangan A dan B). Method menu menampilkan submenu dan memanggil method tambah, lihat, ubah, atau hapus sesuai pilihan. Method cariById dipakai untuk mencari lapangan berdasarkan ID.

**PeralatanController.java**
Sama seperti LapanganController, tapi untuk peralatan. Dummy data awalnya Raket Padel dan Bola Padel.

**PenyewaanController.java**
Mengurus transaksi penyewaan. Pada saat menambah sewa, user memilih jenis sewa, memilih item, lalu mengisi jumlah. Controller memeriksa apakah lapangan masih tersedia atau stok peralatan cukup, menghitung total, lalu menyimpan transaksi. Saat sewa dibatalkan, lapangan kembali Tersedia atau stok peralatan dikembalikan.

## 4. Penjelasan Alur Program

1. Program dimulai dari Main. Objek MenuView dan ketiga controller dibuat, dan dummy data langsung terisi.
2. Menu utama tampil: 1. Kelola Lapangan, 2. Kelola Peralatan, 3. Kelola Penyewaan, 0. Keluar.
3. User mengetik angka. Program memilih menu dengan switch-case.
4. Menu 1, 2, atau 3 membuka submenu: Tambah, Lihat, Ubah, Hapus, Kembali.
5. Setiap input user divalidasi dulu. Jika salah, user diminta mengisi ulang.
6. Menu Lihat menampilkan semua data dengan perulangan for-each.
7. Setelah satu proses selesai, submenu muncul lagi. Memilih 0 akan kembali ke menu utama.
8. Menu utama terus berulang sampai user memilih 0 untuk keluar.

## 5. Penjelasan Penerapan Encapsulation dan Inheritance

**Encapsulation**
Semua atribut di class model dibuat private, sehingga tidak bisa diakses langsung dari luar class. Untuk membaca dan mengubahnya disediakan getter dan setter yang public. Contohnya atribut stok di Peralatan hanya bisa dibaca lewat getStok dan diubah lewat setStok.

**Inheritance**
ItemSewa adalah superclass yang menyimpan atribut umum (id, nama, harga). Lapangan dan Peralatan adalah subclass yang mewarisi ItemSewa dengan kata kunci extends, sehingga otomatis punya atribut tersebut. Masing-masing menambah atribut khusus: status untuk Lapangan dan stok untuk Peralatan. Constructor subclass memanggil constructor superclass dengan super.

## 6. Penjelasan Penerapan Polymorphism dan Abstraction

**Abstraction**
ItemSewa dibuat sebagai abstract class karena hanya konsep umum, bukan barang nyata yang disewa, jadi tidak bisa dibuat objeknya langsung. Class ini punya dua abstract method (method tanpa isi), yaitu tampilkanInfo dan hitungTotal, yang wajib diisi oleh Lapangan dan Peralatan.

**Polymorphism: Overriding**
Lapangan dan Peralatan sama-sama menulis ulang (override) tampilkanInfo dan hitungTotal dengan isi yang berbeda. Di PenyewaanController, variabel bertipe ItemSewa bisa berisi Lapangan atau Peralatan. Saat hitungTotal dipanggil, hasilnya otomatis mengikuti jenis objek yang sebenarnya.

**Polymorphism: Overloading**
Dua method dengan nama sama tapi parameter berbeda:
- ItemSewa: hitungTotal dengan satu parameter (jumlah) dan hitungTotal dengan dua parameter (jumlah dan diskon persen).
- MenuView: tampilPesan dengan satu parameter (teks) dan tampilPesan dengan dua parameter (teks dan angka).

## 7. Penjelasan Letak Penerapan Nilai Tambah (Interface)

Nilai tambah yang diterapkan adalah **interface**, yaitu Diskon di package model.

- Lapangan dan Peralatan memakai interface ini dengan kata kunci implements, lalu masing-masing menuliskan isi hitungDiskon.
- Hasil hitungDiskon dipakai di dalam hitungTotal.
- PenyewaanController mengecek apakah item termasuk Diskon (instanceof), lalu menampilkan info diskon kepada user.

| Class | Syarat diskon | Besar diskon |
|---|---|---|
| Lapangan | Sewa 3 jam atau lebih | 10% |
| Peralatan | Sewa 5 unit atau lebih | 5% |

## 8. Validasi Input

Validasi ada di MenuView:
- bacaAngka: input pilihan menu harus angka. Jika bukan, dianggap pilihan tidak valid dan program tidak error.
- bacaTeks: teks tidak boleh kosong.
- bacaHarga: harus angka dan lebih dari 0.
- bacaJumlah: harus bilangan bulat dan lebih dari 0.

Jika input salah, user diminta mengulang sampai benar. Controller juga memeriksa ID harus ditemukan, lapangan tidak boleh disewa dua kali, dan jumlah peralatan tidak boleh melebihi stok.
