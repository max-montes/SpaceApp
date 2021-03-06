package com.divyanshu.spacestuff1;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class ISSTracker extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iss_tracker);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        FloatingActionButton fab = findViewById(R.id.updateISS);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new getISSLoation().execute();
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new getISSLoation().execute();
    }


    public class getISSLoation extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {
            //Returns the query searched for JSON
            return NetworkUtils.getISSLocation();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            double latitude;
            double longitude;

            try {
                //...

                JSONObject jsonObject = new JSONObject(s);

                latitude = jsonObject.getDouble("latitude");
                longitude = jsonObject.getDouble("longitude");

                if (longitude != 0 && longitude != 0) {

                    LatLng spaceStation = new LatLng(latitude,longitude);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(spaceStation).title("International Space Station").icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_iss)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(spaceStation));
                   // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(spaceStation,5f));

                }
                else {
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}