package com.example.trialappversion10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements LocInterfaceListener {

    private LocationManager locationManager;

    private TextView tvDistance, tvVelocity;

    private Location lastLocation;

    private MyLocListener myLocListener;

    private int distance;

    //private boolean SPEED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        tvDistance = findViewById(R.id.tvDistance);
        tvVelocity = findViewById(R.id.tvVelocity);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocListener = new MyLocListener();
        myLocListener.setLocInterfaceListener(this);
        checkPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults[0] == RESULT_OK)
        {
            checkPermissions();
        }

    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 100);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocListener );
        }
    }
    

        @Override
        public void OnLocationChanged (Location loc){

       // if (loc.hasSpeed()) SPEED = true;
       // else distance = (int)lastLocation.distanceTo(loc);

        if (loc.hasSpeed() && lastLocation != null) {
            distance += lastLocation.distanceTo(loc);
        }

        lastLocation = loc;
        tvDistance.setText(String.valueOf (distance ) + " " + "Метров");
        tvVelocity.setText(String.valueOf(loc.getSpeed() * 3.6 + "Км/ч"));

    }

   @Override
   protected void onDestroy () {
       super.onDestroy();
       locationManager.removeUpdates(myLocListener);
   }



}
