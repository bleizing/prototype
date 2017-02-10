package bleizing.prototypepajakbekasi;

import java.util.ArrayList;

/**
 * Created by Bleizing on 2/9/2017.
 */

public class Model {
    private static ArrayList<Lokasi> lokasiArrayList = new ArrayList<>();

    public static void addLokasi(Lokasi lokasi) {
        lokasiArrayList.add(lokasi);
    }

    public static ArrayList<Lokasi> getLokasiList() {
        return lokasiArrayList;
    }
}
