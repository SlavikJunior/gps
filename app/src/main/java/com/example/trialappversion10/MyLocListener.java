package com.example.trialappversion10;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.nio.Buffer;

public class MyLocListener implements LocationListener {
    private LocInterfaceListener LocInterfaceListener;

    @Override
    public void onLocationChanged(Location location) {
        LocInterfaceListener.OnLocationChanged(location);
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

    public void setLocInterfaceListener(com.example.trialappversion10.LocInterfaceListener locInterfaceListener) {
        LocInterfaceListener = locInterfaceListener;
    }
}

