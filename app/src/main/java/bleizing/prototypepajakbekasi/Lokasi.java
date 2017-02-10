package bleizing.prototypepajakbekasi;

/**
 * Created by Bleizing on 2/9/2017.
 */

public class Lokasi {
    private String nama;
    private String title;
    private double lat;
    private double lng;

    public Lokasi(String nama, String title, double lat, double lng) {
        this.nama = nama;
        this.title = title;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNama() {
        return nama;
    }

    public String getTitle() {
        return title;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
