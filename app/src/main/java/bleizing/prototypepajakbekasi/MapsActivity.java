package bleizing.prototypepajakbekasi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private static final String TAG = "MapsActivity";

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager locationManager;
    private LocationRequest mLocationRequest;
    private Marker currentLocationMarker;
    private double lat;
    private double lng;
    private RequestQueue requestQueue;
    private String title;
    private ArrayList<Lokasi> lokasiArrayList;
    private double cur_lat, cur_lng;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        title = "";
        requestQueue = Volley.newRequestQueue(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

//        prepareData();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbClicked();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lokasiArrayList == null) {
            lokasiArrayList = new ArrayList<>();
        }
        getDataFromServer();
        if (mMap != null) {
            addMarkers();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady()");
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                title = "";
                addCurrentMarker();

                return false;
            }
        });
        addMarkers();
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void fbClicked() {
        Intent intent = new Intent(MapsActivity.this, DetailActivity.class);
        if (title.equals("")) {
            intent.putExtra("lat", cur_lat);
            intent.putExtra("lng", cur_lng);
        } else {
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
        }
        intent.putExtra("title", title);
        startActivity(intent);
    }

    /*private void prepareData() {
        Lokasi l1 = new Lokasi("Ini Toko1", "Vaping Addict", -6.150957, 106.897097);
        Model.addLokasi(l1);
        Lokasi l2 = new Lokasi("Ini Toko2", "Indo Vaping", -6.150376, 106.880316);
        Model.addLokasi(l2);
        Lokasi l3 = new Lokasi("Ini Toko3", "Vaporex", -6.145171, 106.876906);
        Model.addLokasi(l3);
        Lokasi l4 = new Lokasi("Ini Toko4", "Vapor Prime", -6.137993, 106.867319);
        Model.addLokasi(l4);
    }*/

    private void addMarkers() {
        Log.d(TAG, "addMarkers, list = " + lokasiArrayList.size());
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-6.150957, 106.897097), 11.0f));
        if (lokasiArrayList.size() != 0 || lokasiArrayList != null) {
            for (int i = 0; i < lokasiArrayList.size(); i++) {
                Lokasi lokasi = lokasiArrayList.get(i);
                mMap.addMarker(new MarkerOptions().position(new LatLng(lokasi.getLatitude(), lokasi.getLongitude())).title(lokasi.getNama()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            }
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    title = "";
                    for (int i = 0; i < lokasiArrayList.size(); i++) {
                        Lokasi lokasi = lokasiArrayList.get(i);
                        if (marker.getTitle().equals(lokasi.getNama())) {
                            title = lokasi.getNama();
                            lat = lokasi.getLatitude();
                            lng = lokasi.getLongitude();
                            floatingActionButton.setImageResource(R.drawable.edit);

                            break;
                        }
                    }
                    return false;
                }
            });
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                title = "";
                floatingActionButton.setImageResource(R.drawable.add);
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");
        startLocationUpdates();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLocation == null) {
            startLocationUpdates();
        }
        if (mLocation != null) {
            double latitude = mLocation.getLatitude();
            double longitude = mLocation.getLongitude();
            this.cur_lat = latitude;
            this.cur_lng = longitude;
            addCurrentMarker();

        } else {
            // Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    protected void startLocationUpdates() {
        Log.d(TAG, "startLocationUpdates");
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(3 * 1000);
        // Request location updates

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
//        addCurrentMarker(location.getLatitude(), location.getLongitude());
        this.cur_lat = location.getLatitude();
        this.cur_lng = location.getLongitude();
    }

    private void addCurrentMarker() {
        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
            Log.d(TAG, "currentLocationMarker != null");
        }

        Log.d(TAG, "lat = " + cur_lat + ", lng = " + cur_lng);
        floatingActionButton.setImageResource(R.drawable.add);
        currentLocationMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(cur_lat, cur_lng)).title("Anda Disini").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

    }

    private void getDataFromServer() {
        lokasiArrayList.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://prototype.himsigalaksi.com/index.php/getLokasi", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    if (response.getString("type").equals("success")) {
                        Model.getLokasiList().clear();
                        JSONArray jsonArray = response.getJSONArray("list");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            Lokasi lokasi = new Lokasi();
                            lokasi.setObject_id(object.getInt("object_id"));
                            lokasi.setNama(object.getString("nama"));
                            lokasi.setMerk(object.getString("merk"));
                            lokasi.setAlamat(object.getString("alamat"));
                            lokasi.setJenis_induk_bangunan(object.getString("jenis_induk_bangunan"));
                            lokasi.setJenis_bangunan(object.getString("jenis_bangunan"));
                            lokasi.setStatus_bangunan(object.getInt("status_bangunan"));
                            lokasi.setAccount_representative(object.getInt("account_representative"));
                            lokasi.setGlobal_id(object.getString("global_id"));
                            lokasi.setKanwil(object.getString("kanwil"));
                            lokasi.setKpp(object.getString("kpp"));
                            lokasi.setTelpon(object.getString("telpon"));
                            lokasi.setNpwp(object.getString("npwp"));
                            lokasi.setStatus_npwp(object.getInt("status_npwp"));
                            lokasi.setLatitude(object.getDouble("latitude"));
                            lokasi.setLongitude(object.getDouble("longitude"));
                            lokasi.setPelaporan(object.getString("pelaporan"));
                            lokasi.setPembayaran(object.getString("pembayaran"));
                            lokasi.setSpt(object.getInt("spt"));
                            lokasi.setSeksi(object.getInt("seksi"));
                            lokasi.setUraian(object.getString("uraian"));
                            lokasi.setTidak_lanjut(object.getString("tidak_lanjut"));
                            lokasiArrayList.add(lokasi);
                            Model.addLokasi(lokasi);
                        }
                        Log.d(TAG, "lokasiList = " + lokasiArrayList.size());
                        addMarkers();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
