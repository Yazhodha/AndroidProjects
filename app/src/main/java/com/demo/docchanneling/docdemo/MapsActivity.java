package com.demo.docchanneling.docdemo;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TAG = "so47492459";

    FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        //Getting Current Location========
        requestPermission();

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
        mMap = googleMap;

        //------------------------
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapsActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

            return;
        }

        client.getLastLocation().addOnSuccessListener(MapsActivity.this, new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if(location !=null){
//                    TextView textView = findViewById(R.id.tvCurrentLocationLat);
//                    textView.setText(String.valueOf(location.getLatitude()));
//                    TextView textView1 = findViewById(R.id.tvCurrentLocationLng);
//                    textView1.setText(String.valueOf(location.getLongitude()));

                    //============
                    LatLng cLocation = new LatLng(location.getLatitude(),location.getLongitude());

                    mMap.addMarker(new MarkerOptions().position(cLocation).title("Your Location"));

                    LatLng kandy = new LatLng(6.9210,79.8535);
                    mMap.addMarker(new MarkerOptions().position(kandy).title("Nawaloka Hospitals"));

//                    LatLng kegalle = new LatLng(7.2513,80.3464);

                    //Define list to get all latlng for the route
                    List<LatLng> path = new ArrayList();

                    //Create single origin String current location
                    String currentLocation = String.valueOf(location.getLatitude()).trim()+","+String.valueOf(location.getLongitude()).trim();

                    //Execute Directions API request
                    GeoApiContext context = new GeoApiContext.Builder()
                            .apiKey("AIzaSyBrPt88vvoPDDn_imh-RzCXl5Ha2F2LYig")
                            .build();
                    DirectionsApiRequest req = DirectionsApi.getDirections(context, currentLocation, "6.9210,79.8535");
                    try {
                        DirectionsResult res = req.await();

                        //Loop through legs and steps to get encoded polylines of each step
                        if (res.routes != null && res.routes.length > 0) {
                            DirectionsRoute route = res.routes[0];

                            if (route.legs !=null) {
                                for(int i=0; i<route.legs.length; i++) {
                                    DirectionsLeg leg = route.legs[i];
                                    if (leg.steps != null) {
                                        for (int j=0; j<leg.steps.length;j++){
                                            DirectionsStep step = leg.steps[j];
                                            if (step.steps != null && step.steps.length >0) {
                                                for (int k=0; k<step.steps.length;k++){
                                                    DirectionsStep step1 = step.steps[k];
                                                    EncodedPolyline points1 = step1.polyline;
                                                    if (points1 != null) {
                                                        //Decode polyline and add points to list of route coordinates
                                                        List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                        for (com.google.maps.model.LatLng coord1 : coords1) {
                                                            path.add(new LatLng(coord1.lat, coord1.lng));
                                                        }
                                                    }
                                                }
                                            } else {
                                                EncodedPolyline points = step.polyline;
                                                if (points != null) {
                                                    //Decode polyline and add points to list of route coordinates
                                                    List<com.google.maps.model.LatLng> coords = points.decodePath();
                                                    for (com.google.maps.model.LatLng coord : coords) {
                                                        path.add(new LatLng(coord.lat, coord.lng));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch(Exception ex) {
                        Log.e(TAG, ex.getLocalizedMessage());
                    }

                    //Draw the polyline
                    if (path.size() > 0) {
                        PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                        mMap.addPolyline(opts);
                    }

                    mMap.getUiSettings().setZoomControlsEnabled(true);

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cLocation, 10));

                }
            }
        });

        //----------------------------------

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION}, 1);
    }
}
