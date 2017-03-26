package com.example.rssongira.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.StringBuilderPrinter;
import android.widget.Toast;

/**
 * Created by RSSongira on 3/24/2017.
 */
public class AppLocationService extends Service implements LocationListener {



    protected LocationManager manager;
    protected Location location;

    private static final long min_dis_for_update=10;
    // The minimum time between updates in milliseconds
    private static final long min_time_for_update=1000*60*2;// 1 minute


    public AppLocationService(Context context)
    {
        manager=(LocationManager)context.getSystemService(LOCATION_SERVICE);

    }
    public Location getLocation(String Provider)
    {
        if(manager.isProviderEnabled(Provider)){
        try{
            manager.requestLocationUpdates(Provider,min_time_for_update,min_dis_for_update,this);
            if(manager!=null)
            {
                location=manager.getLastKnownLocation(Provider);
                return location;

            }

        }catch (SecurityException e){
            Toast.makeText(this,"security",Toast.LENGTH_SHORT).show();
        }


    }

        return null;
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
