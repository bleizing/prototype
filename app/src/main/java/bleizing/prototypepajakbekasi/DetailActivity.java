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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private String title;
    private String address;
    private ArrayList<Lokasi> lokasiArrayList;
    private Lokasi lokasi;
    private double lat, lng;
    private String nama, merk, alamat, jenis_induk_bangunan, jenis_bangunan, telpon, npwp, uraian, kpp, kanwil, tidak_lanjut, pembayaran, pelaporan, global_id;
    private int object_id, status_bangunan, status_npwp, spt, account_representative, seksi;
    private double latitude, longitude;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getIntent().getExtras().getString("title");
        lat = getIntent().getExtras().getDouble("lat");
        lng = getIntent().getExtras().getDouble("lng");

        requestQueue = Volley.newRequestQueue(this);

        Log.d(TAG, title);
        address = getCompleteAddressString(lat, lng);


        lokasiArrayList = Model.getLokasiList();

        if (!title.equals("")) {
            for (Lokasi l : lokasiArrayList) {
                if (l.getNama().equals(title)) {
                    lokasi = l;
                }
            }
        } else {
            lokasi = null;
        }

        if (lokasi != null) {
            object_id = lokasi.getObject_id();
            nama = lokasi.getNama();
            merk = lokasi.getMerk();
            alamat = lokasi.getAlamat();
            jenis_induk_bangunan = lokasi.getJenis_induk_bangunan();
            jenis_bangunan = lokasi.getJenis_bangunan();
            status_bangunan = lokasi.getStatus_bangunan();
            npwp = lokasi.getNpwp();
            status_npwp = lokasi.getStatus_npwp();
            telpon = lokasi.getTelpon();
            kpp = lokasi.getKpp();
            kanwil = lokasi.getKanwil();
            spt = lokasi.getSpt();
            seksi = lokasi.getSeksi();
            account_representative = lokasi.getAccount_representative();
            pelaporan = lokasi.getPelaporan();
            pembayaran = lokasi.getPembayaran();
            global_id = lokasi.getGlobal_id();
            latitude = lokasi.getLatitude();
            longitude = lokasi.getLongitude();
            uraian = lokasi.getUraian();
            tidak_lanjut = lokasi.getTidak_lanjut();
            viewMode();
        } else {
            alamat = address;
            latitude = lat;
            longitude = lng;
            editMode();
        }

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
        TextView tv_latitude = (TextView) findViewById(R.id.latitude);
        TextView tv_longitude = (TextView) findViewById(R.id.longitude);

        tv_nama.setText(nama);
        tv_merk.setText(merk);
        tv_alamat.setText(alamat);
        tv_jenis_induk_bangunan.setText(jenis_induk_bangunan);
        tv_jenis_bangunan.setText(jenis_bangunan);
        tv_status_bangunan.setText(String.valueOf(status_bangunan));
        tv_telpon.setText(telpon);
        tv_npwp.setText(npwp);
        tv_status_npwp.setText(String.valueOf(status_npwp));
        tv_uraian.setText(uraian);
        tv_kpp.setText(kpp);
        tv_kanwil.setText(kanwil);
        tv_spt.setText(String.valueOf(spt));
        tv_tidak_lanjut.setText(tidak_lanjut);
        tv_account_representative.setText(String.valueOf(account_representative));
        tv_seksi.setText(String.valueOf(seksi));
        tv_pembayaran.setText(pembayaran);
        tv_pelaporan.setText(pelaporan);
        tv_global_id.setText(global_id);
        tv_latitude.setText(String.valueOf(String.valueOf(latitude)));
        tv_longitude.setText(String.valueOf(String.valueOf(longitude)));

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
        final EditText edit_nama = (EditText) findViewById(R.id.nama);
        final EditText edit_merk = (EditText) findViewById(R.id.merk);
        final EditText edit_alamat = (EditText) findViewById(R.id.alamat);
        final EditText edit_jenis_induk_bangunan = (EditText) findViewById(R.id.jenis_induk_bangunan);
        final EditText edit_jenis_bangunan = (EditText) findViewById(R.id.jenis_bangunan);
        final EditText edit_status_bangunan = (EditText) findViewById(R.id.status_bangunan);
        final EditText edit_telpon = (EditText) findViewById(R.id.telpon);
        final EditText edit_npwp = (EditText) findViewById(R.id.npwp);
        final EditText edit_status_npwp = (EditText) findViewById(R.id.status_npwp);
        final EditText edit_uraian = (EditText) findViewById(R.id.uraian);
        final EditText edit_kpp = (EditText) findViewById(R.id.kpp);
        final EditText edit_kanwil = (EditText) findViewById(R.id.kanwil);
        final EditText edit_spt = (EditText) findViewById(R.id.spt);
        final EditText edit_tidak_lanjut = (EditText) findViewById(R.id.tidak_lanjut);
        final EditText edit_account_representative = (EditText) findViewById(R.id.account_representative);
        final EditText edit_seksi = (EditText) findViewById(R.id.seksi);
        final EditText edit_pembayaran = (EditText) findViewById(R.id.pembayaran);
        final EditText edit_pelaporan = (EditText) findViewById(R.id.pelaporan);
        final EditText edit_global_id = (EditText) findViewById(R.id.global_id);
        final EditText edit_latitude = (EditText) findViewById(R.id.latitude);
        final EditText edit_longitude = (EditText) findViewById(R.id.longitude);

        if (lokasi != null) {
            edit_nama.setText(nama);
            edit_merk.setText(merk);
            edit_alamat.setText(alamat);
            edit_jenis_induk_bangunan.setText(jenis_induk_bangunan);
            edit_jenis_bangunan.setText(jenis_bangunan);
            edit_status_bangunan.setText(String.valueOf(status_bangunan));
            edit_telpon.setText(telpon);
            edit_npwp.setText(npwp);
            edit_status_npwp.setText(String.valueOf(status_npwp));
            edit_uraian.setText(uraian);
            edit_kpp.setText(kpp);
            edit_kanwil.setText(kanwil);
            edit_spt.setText(String.valueOf(spt));
            edit_tidak_lanjut.setText(tidak_lanjut);
            edit_account_representative.setText(String.valueOf(account_representative));
            edit_seksi.setText(String.valueOf(seksi));
            edit_pembayaran.setText(pembayaran);
            edit_pelaporan.setText(pelaporan);
            edit_global_id.setText(global_id);
            edit_latitude.setText(String.valueOf(latitude));
            edit_longitude.setText(String.valueOf(longitude));
        } else {
            edit_alamat.setText(alamat);
            edit_latitude.setText(String.valueOf(latitude));
            edit_longitude.setText(String.valueOf(longitude));
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = edit_nama.getText().toString();
                merk = edit_merk.getText().toString();
                alamat = edit_alamat.getText().toString();
                jenis_induk_bangunan = edit_jenis_induk_bangunan.getText().toString();
                jenis_bangunan = edit_jenis_bangunan.getText().toString();
                status_bangunan = Integer.parseInt(edit_status_bangunan.getText().toString());
                telpon = edit_telpon.getText().toString();
                npwp = edit_npwp.getText().toString();
                status_npwp = Integer.parseInt(edit_status_npwp.getText().toString());
                uraian = edit_uraian.getText().toString();
                kpp = edit_kpp.getText().toString();
                kanwil = edit_kanwil.getText().toString();
                spt = Integer.parseInt(edit_spt.getText().toString());
                tidak_lanjut = edit_tidak_lanjut.getText().toString();
                account_representative = Integer.parseInt(edit_account_representative.getText().toString());
                seksi = Integer.parseInt(edit_seksi.getText().toString());
                pembayaran = edit_pembayaran.getText().toString();
                pelaporan = edit_pelaporan.getText().toString();
                global_id = edit_global_id.getText().toString();
                latitude = Double.parseDouble(edit_latitude.getText().toString());
                longitude = Double.parseDouble(edit_longitude.getText().toString());

                sendData();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lokasi != null) {
                    viewMode();
                } else {
                    finish();
                }
            }
        });
    }

    private void sendData() {
        if (lokasi == null) {
            object_id = 0;
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", "saveData");
            jsonObject.put("object_id", object_id);
            jsonObject.put("nama", nama);
            jsonObject.put("merk", merk);
            jsonObject.put("alamat", alamat);
            jsonObject.put("jenis_induk_bangunan", jenis_induk_bangunan);
            jsonObject.put("jenis_bangunan", jenis_bangunan);
            jsonObject.put("status_bangunan", status_bangunan);
            jsonObject.put("telpon", telpon);
            jsonObject.put("npwp", npwp);
            jsonObject.put("status_npwp", status_npwp);
            jsonObject.put("uraian", uraian);
            jsonObject.put("kpp", kpp);
            jsonObject.put("kanwil", kanwil);
            jsonObject.put("spt", spt);
            jsonObject.put("tidak_lanjut", tidak_lanjut);
            jsonObject.put("account_representative", account_representative);
            jsonObject.put("seksi", seksi);
            jsonObject.put("pembayaran", pembayaran);
            jsonObject.put("pelaporan", pelaporan);
            jsonObject.put("global_id", global_id);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // send data via volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://prototype.himsigalaksi.com/index.php", jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        jsonObjectRequest.setTag(TAG);
        requestQueue.add(jsonObjectRequest);
    }
}
