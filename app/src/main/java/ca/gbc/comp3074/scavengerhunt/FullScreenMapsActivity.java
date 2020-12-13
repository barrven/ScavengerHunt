package ca.gbc.comp3074.scavengerhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import java.io.IOException;
import java.util.List;

import ca.gbc.comp3074.scavengerhunt.maphelper.FetchURL;
import ca.gbc.comp3074.scavengerhunt.maphelper.TaskLoadedCallback;

//changed class from extending FragmentActivity to this so that menu bar would show at top
public class FullScreenMapsActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private Point point;
    private List<Address> addresses;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map_layout_fullscreen);
        point = getPointFromIntent(getIntent());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        geocoder = new Geocoder(getApplicationContext());

        try {
            addresses = geocoder.getFromLocationName(point.getAddress(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Address a = addresses.get(0);


        Button btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Point Location
        if(addresses != null && !addresses.isEmpty()){
            Address a = addresses.get(0);
            LatLng pos = new LatLng(a.getLatitude(), a.getLongitude());
            if(mMap != null){
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 17));
                // Adding Point Marker
                mMap.addMarker(
                        new MarkerOptions().position(new LatLng(a.getLatitude(), a.getLongitude())).title("Point")
                );
            }
        }


    }


    private Point getPointFromIntent(Intent intent){

        // Getting Object from MapsActivity
        int id = Integer.parseInt(intent.getStringExtra("id"));
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String task = intent.getStringExtra("task");
        String tags = intent.getStringExtra("tags");
        double ratings = 0.0;
        if(intent.getStringExtra("ratings") != null && intent.getStringExtra("ratings").length() > 0){
            ratings = Double.parseDouble(intent.getStringExtra("ratings"));
        }

        assert tags != null;
        return new Point(id,name,address,task,tags,ratings);
    }



    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}