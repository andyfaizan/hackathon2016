package de.rwth.hack.superdsa;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import de.dsa.hackathon2016.lib.IVehicleStatus;
import de.dsa.hackathon2016.lib.VehicleStatusReceiver;
import de.dsa.hackathon2016.lib.VehicleStatusUtils;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker shownMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
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

        String vehicleId = "0410";
        VehicleStatusUtils.getLastVehicleStatus(vehicleId, new VehicleStatusReceiver() {
            @Override
            public void onStatusReceived(IVehicleStatus vehicleStatus) {
                if (vehicleStatus == null) {
                    Toast.makeText(LocationActivity.this, "No vehicle status could be obtained.", Toast.LENGTH_SHORT).show();
                    return;
                }
                float lat = vehicleStatus.getGpsLatitude();
                float lon = vehicleStatus.getGpsLongitude();
                LatLng nearestLocation = new LatLng(lat, lon);
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                List<Marker> markersList = new ArrayList<Marker>();
                final CameraUpdate cu;

                Marker nearestMarker = mMap.addMarker(new MarkerOptions().position(nearestLocation).title("Goodwill Motor Works, Friedensplatz 16"));
                nearestMarker.showInfoWindow();
                markersList.add(nearestMarker);
                shownMarker = nearestMarker;

                LatLng fakeLocation = new LatLng(lat + 0.01, lon - 0.01);
                Marker fakeMarker = mMap.addMarker(new MarkerOptions().position(fakeLocation).title("Super Auto, Pariser Straße 22"));
                markersList.add(fakeMarker);

                LatLng anotherFakeLocation = new LatLng(lat + 0.02, lon + 0.02);
                Marker anotherFakeMarker = mMap.addMarker(new MarkerOptions().position(anotherFakeLocation).title("Fahren Wir, Marktplatz 112"));
                markersList.add(anotherFakeMarker);

                for (Marker m : markersList) {
                    builder.include(m.getPosition());
                }

                int padding = 50;

                LatLngBounds bounds = builder.build();
                cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        mMap.animateCamera(cu);
                    }
                });

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.showInfoWindow();
                        return false;
                    }
                });

                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        Intent intent = new Intent(LocationActivity.this, CurrentDataActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}