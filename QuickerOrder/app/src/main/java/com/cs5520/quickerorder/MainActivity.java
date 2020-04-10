package com.cs5520.quickerorder;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cs5520.quickerorder.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "Main";

    // TODO: welcome screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotoMainService(View view) {

        Intent i = new Intent(this, MainService.class);
        startActivity(i);
    }

    static HashMap<Integer, Dishes> menu;

    static {
        menu = new HashMap<>();
        menu.put(1, new Dishes(1, "Shredded Potato", R.drawable.shredded_potato, 7.99));
        menu.put(2, new Dishes(2, "Crispy Chicken with Bone", R.drawable.crispy_chichen_with_bone, 14.99));
        menu.put(3, new Dishes(3, "Fried Pork in Scoop", R.drawable.fried_pork_in_scoop, 17.99));
        menu.put(4, new Dishes(4, "Pork Belly with Sauerkraut", R.drawable.pork_belly_with_sauerkraut, 15.99));
        menu.put(5,  new Dishes(5, "Potato and Bean with Rib", R.drawable.potato_and_bean_with_pork_rib, 14.99));
        menu.put(6, new Dishes(6, "Rib with Vermincelli", R.drawable.rib_with_vermincelli, 15.99));
        menu.put(7, new Dishes(7, "Stewed Chicken with Mushroom", R.drawable.stewed_chicken_with_mushroom, 16.99));
        menu.put(8, new Dishes(8, "Corn with Salted Egg Yolk", R.drawable.sweet_corn_with_salted_egg_yolk, 12.99));
        menu.put(9, new Dishes(9, "Sweet Riceball with Osmanthus", R.drawable.sweet_fermented_riceball_with_osmanthus, 13.99));
        menu.put(10, new Dishes(10, "Caramelized Sweet Potato", R.drawable.caramelized_sweet_potato, 14.99));
    }


    /*
        if (!mGoogleApiClient.isConnected()) {
            Toast.makeText(this, "Google API Client not connected!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            LocationServices.GeofencingApi.addGeofences(
                    mGoogleApiClient,
                    getGeofencingRequest(),
                    getGeofencePendingIntent()
            ).setResultCallback(this); // Result processed in onResult().
        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
            Log.e(TAG, "gotoMainService: error");
        }
    }

    public void populateGeofenceList() {
        for (Map.Entry<String, LatLng> entry : Constants.LANDMARKS.entrySet()) {
            mGeofenceList.add(new Geofence.Builder()
                    .setRequestId(entry.getKey())
                    .setCircularRegion(
                            entry.getValue().latitude,
                            entry.getValue().longitude,
                            Constants.GEOFENCE_RADIUS_IN_METERS
                    )
                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                            Geofence.GEOFENCE_TRANSITION_EXIT)
                    .build());
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mGoogleApiClient.isConnecting() || !mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnecting() || mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Toast.makeText(
                    this,
                    "Geofences Added",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            // Get the status code for the error and log it using a user-friendly message.
            // String errorMessage = GeofenceErrorMessages.getErrorString(this, status.getStatusCode());
        }

    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofences(mGeofenceList);
        return builder.build();
    }

    private PendingIntent getGeofencePendingIntent() {
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling addgeoFences()
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

     */


}