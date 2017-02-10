package bleizing.prototypepajakbekasi;

/**
 * Created by Bleizing on 2/9/2017.
 */

public class Lokasi {
    private String nama, merk, alamat, jenis_induk_bangunan, jenis_bangunan, telpon, npwp, uraian, kpp, kanwil, tidak_lanjut, pembayaran, pelaporan, global_id;
    private int object_id, status_bangunan, status_npwp, spt, account_representative, seksi;
    private double  latitude, longitude;

    public String getNama() {
        return nama;
    }

    public String getMerk() {
        return merk;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_induk_bangunan() {
        return jenis_induk_bangunan;
    }

    public String getJenis_bangunan() {
        return jenis_bangunan;
    }

    public String getTelpon() {
        return telpon;
    }

    public String getNpwp() {
        return npwp;
    }

    public int getStatus_npwp() {
        return status_npwp;
    }

    public int getAccount_representative() {
        return account_representative;
    }

    public int getObject_id() {
        return object_id;
    }

    public int getSeksi() {
        return seksi;
    }

    public int getSpt() {
        return spt;
    }

    public int getStatus_bangunan() {
        return status_bangunan;
    }

    public String getGlobal_id() {
        return global_id;
    }

    public String getKanwil() {
        return kanwil;
    }

    public String getKpp() {
        return kpp;
    }

    public String getPelaporan() {
        return pelaporan;
    }

    public String getPembayaran() {
        return pembayaran;
    }

    public String getTidak_lanjut() {
        return tidak_lanjut;
    }

    public String getUraian() {
        return uraian;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAccount_representative(int account_representative) {
        this.account_representative = account_representative;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setGlobal_id(String global_id) {
        this.global_id = global_id;
    }

    public void setJenis_bangunan(String jenis_bangunan) {
        this.jenis_bangunan = jenis_bangunan;
    }

    public void setJenis_induk_bangunan(String jenis_induk_bangunan) {
        this.jenis_induk_bangunan = jenis_induk_bangunan;
    }

    public void setKanwil(String kanwil) {
        this.kanwil = kanwil;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public void setPelaporan(String pelaporan) {
        this.pelaporan = pelaporan;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }

    public void setSeksi(int seksi) {
        this.seksi = seksi;
    }

    public void setSpt(int spt) {
        this.spt = spt;
    }

    public void setStatus_bangunan(int status_bangunan) {
        this.status_bangunan = status_bangunan;
    }

    public void setStatus_npwp(int status_npwp) {
        this.status_npwp = status_npwp;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public void setTidak_lanjut(String tidak_lanjut) {
        this.tidak_lanjut = tidak_lanjut;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
