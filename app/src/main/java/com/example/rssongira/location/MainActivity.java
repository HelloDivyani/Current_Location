package com.example.rssongira.location;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   Button gps,np;
    AppLocationService app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = new AppLocationService(this);
        gps = (Button) findViewById(R.id.gps);
        np = (Button) findViewById(R.id.np);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Inside gps method",Toast.LENGTH_LONG).show();

                Location gpslocation=app.getLocation(LocationManager.GPS_PROVIDER);

                if(gpslocation!=null)
                {
                    Toast.makeText(getApplicationContext(),"GPS NOT NULL",Toast.LENGTH_LONG).show();


                    double lat=gpslocation.getLatitude();
                    double lg=gpslocation.getLongitude();
                    Toast.makeText(getApplicationContext(),"lat:"+lat+"long:"+lg,Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"GPS NOT NULL method",Toast.LENGTH_LONG).show();

                    // showSettingsAlert("GPS");
                }

            }
        });

      np.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Location nl = app.getLocation(LocationManager.NETWORK_PROVIDER);

              if (nl != null) {
                  double lat = nl.getLatitude();
                  double lg = nl.getLongitude();
                  Toast.makeText(getApplicationContext(), "lat:" + lat + "long:" + lg, Toast.LENGTH_LONG).show();
              } else {
                  showSettingsAlert("NETWORK");
                  // Location should be enbled

              }
          }

      });
    }
    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog.setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }


}
