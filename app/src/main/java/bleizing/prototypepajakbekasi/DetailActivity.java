package bleizing.prototypepajakbekasi;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private String title;
    private String address;
    private ArrayList<Lokasi> lokasiArrayList;
    private Lokasi lokasi;
    private TextView tv_title;
    private double lat, lng;
    private String object_id, id, nama, merk, alamat, jenis_induk_bangunan, jenis_bangunan, status_bangunan, telpon, npwp, status_npwp, uraian, kpp, kanwil, spt, tidak_lanjut, account_representative, seksi, pembayaran, pelaporan, global_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMode();

        title = getIntent().getExtras().getString("title");
        lat = getIntent().getExtras().getDouble("lat");
        lng = getIntent().getExtras().getDouble("lng");
//
        Log.d(TAG, title);
        address = getCompleteAddressString(lat, lng);
//        tv_title = (TextView) findViewById(R.id.title);
//
        lokasiArrayList = Model.getLokasiList();
//
        if (!title.equals("")) {
            for (Lokasi l : lokasiArrayList) {
                if (l.getTitle().equals(title)) {
                    lokasi = l;
                }
            }
        } else {
            lokasi = null;
        }
//
//        if (lokasi != null) {
//            tv_title.setText(title);
//        }

    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder
                    .getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                android.location.Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress
                            .append(returnedAddress.getAddressLine(i)).append(
                            ", ");
                }
                strAdd = strReturnedAddress.toString();
                Log.w(TAG,
                        "" + strReturnedAddress.toString());
            } else {
                Log.w(TAG, "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "Canont get Address!");
        }
        return strAdd;
    }

    private void viewMode() {
        setContentView(R.layout.activity_detail_view);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        Button btn_edit = (Button) findViewById(R.id.btn_save);
        TextView tv_nama = (TextView) findViewById(R.id.nama);
        TextView tv_merk = (TextView) findViewById(R.id.merk);
        TextView tv_alamat = (TextView) findViewById(R.id.alamat);
        TextView tv_jenis_induk_bangunan = (TextView) findViewById(R.id.jenis_induk_bangunan);
        TextView tv_jenis_bangunan = (TextView) findViewById(R.id.jenis_bangunan);
        TextView tv_status_bangunan = (TextView) findViewById(R.id.status_bangunan);
        TextView tv_telpon = (TextView) findViewById(R.id.telpon);
        TextView tv_npwp = (TextView) findViewById(R.id.npwp);
        TextView tv_status_npwp = (TextView) findViewById(R.id.status_npwp);
        TextView tv_uraian = (TextView) findViewById(R.id.uraian);
        TextView tv_kpp = (TextView) findViewById(R.id.kpp);
        TextView tv_kanwil = (TextView) findViewById(R.id.kanwil);
        TextView tv_spt = (TextView) findViewById(R.id.spt);
        TextView tv_tidak_lanjut = (TextView) findViewById(R.id.tidak_lanjut);
        TextView tv_account_representative = (TextView) findViewById(R.id.account_representative);
        TextView tv_seksi = (TextView) findViewById(R.id.seksi);
        TextView tv_pembayaran = (TextView) findViewById(R.id.pembayaran);
        TextView tv_pelaporan = (TextView) findViewById(R.id.pelaporan);
        TextView tv_global_id = (TextView) findViewById(R.id.global_id);

        tv_nama.setText(nama);
        tv_merk.setText(merk);
        tv_alamat.setText(alamat);
        tv_jenis_induk_bangunan.setText(jenis_induk_bangunan);
        tv_jenis_bangunan.setText(jenis_bangunan);
        tv_status_bangunan.setText(status_bangunan);
        tv_telpon.setText(telpon);
        tv_npwp.setText(npwp);
        tv_status_npwp.setText(status_npwp);
        tv_uraian.setText(uraian);
        tv_kpp.setText(kpp);
        tv_kanwil.setText(kanwil);
        tv_spt.setText(spt);
        tv_tidak_lanjut.setText(tidak_lanjut);
        tv_account_representative.setText(account_representative);
        tv_seksi.setText(seksi);
        tv_pembayaran.setText(pembayaran);
        tv_pelaporan.setText(pelaporan);
        tv_global_id.setText(global_id);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editMode();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void editMode() {
        setContentView(R.layout.activity_detail_edit);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        EditText edit_nama = (EditText) findViewById(R.id.nama);
        EditText edit_merk = (EditText) findViewById(R.id.merk);
        EditText edit_alamat = (EditText) findViewById(R.id.alamat);
        EditText edit_jenis_induk_bangunan = (EditText) findViewById(R.id.jenis_induk_bangunan);
        EditText edit_jenis_bangunan = (EditText) findViewById(R.id.jenis_bangunan);
        EditText edit_status_bangunan = (EditText) findViewById(R.id.status_bangunan);
        EditText edit_telpon = (EditText) findViewById(R.id.telpon);
        EditText edit_npwp = (EditText) findViewById(R.id.npwp);
        EditText edit_status_npwp = (EditText) findViewById(R.id.status_npwp);
        EditText edit_uraian = (EditText) findViewById(R.id.uraian);
        EditText edit_kpp = (EditText) findViewById(R.id.kpp);
        EditText edit_kanwil = (EditText) findViewById(R.id.kanwil);
        EditText edit_spt = (EditText) findViewById(R.id.spt);
        EditText edit_tidak_lanjut = (EditText) findViewById(R.id.tidak_lanjut);
        EditText edit_account_representative = (EditText) findViewById(R.id.account_representative);
        EditText edit_seksi = (EditText) findViewById(R.id.seksi);
        EditText edit_pembayaran = (EditText) findViewById(R.id.pembayaran);
        EditText edit_pelaporan = (EditText) findViewById(R.id.pelaporan);
        EditText edit_global_id = (EditText) findViewById(R.id.global_id);

        edit_nama.setText(nama);
        edit_merk.setText(merk);
        edit_alamat.setText(alamat);
        edit_jenis_induk_bangunan.setText(jenis_induk_bangunan);
        edit_jenis_bangunan.setText(jenis_bangunan);
        edit_status_bangunan.setText(status_bangunan);
        edit_telpon.setText(telpon);
        edit_npwp.setText(npwp);
        edit_status_npwp.setText(status_npwp);
        edit_uraian.setText(uraian);
        edit_kpp.setText(kpp);
        edit_kanwil.setText(kanwil);
        edit_spt.setText(spt);
        edit_tidak_lanjut.setText(tidak_lanjut);
        edit_account_representative.setText(account_representative);
        edit_seksi.setText(seksi);
        edit_pembayaran.setText(pembayaran);
        edit_pelaporan.setText(pelaporan);
        edit_global_id.setText(global_id);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMode();
            }
        });
    }
}
