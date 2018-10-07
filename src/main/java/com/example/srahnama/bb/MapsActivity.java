package com.example.srahnama.bb;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker myMarker;
    private Context ct = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        Marker m = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(32.246750, 50.547618))
                .title("سر کوچه"));
        m.setTag(new LatLng(32.246750, 50.547618));
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(32.246530, 50.546964);
        Marker m2 = mMap.addMarker(new MarkerOptions().position(sydney).title("این یک تست است"));
        m2.setTag(new LatLng(32.246530, 50.546964));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.246530, 50.546964), zoomLevel));

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList points = null;
                PolylineOptions lineOptions = null;
                points = new ArrayList();
                lineOptions = new PolylineOptions();
                points.add(new LatLng(32.246530, 50.546964));
                points.add(new LatLng(32.246750, 50.547618));

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.RED);
                lineOptions.geodesic(false);

                mMap.addPolyline(lineOptions);
            }

        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng position = (LatLng)(marker.getTag());
                //Using position get Value from arraylist
                TextView tx = (TextView)findViewById(R.id.textView2);
                tx.setText(position.toString());
//                Toast.makeText(ct, "this is test" + position,Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

//    public void onMarkerClick(final Marker marker) {
//
//        if (marker.equals(myMarker))
//        {
//            //handle click here
//            Toast.makeText(this, "this is test",Toast.LENGTH_LONG).show();
//        }
//    }
}
