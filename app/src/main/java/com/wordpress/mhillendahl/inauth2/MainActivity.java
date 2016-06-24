package com.wordpress.mhillendahl.inauth2;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
//import "testlibrary"

//my library! -mike
import com.wordpress.mhillendahl.testlibrary.GPSTracker;
import com.wordpress.mhillendahl.testlibrary.stuff1;

//gps crap -mike

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
//end gps crap

public class MainActivity extends AppCompatActivity {

    //GoogleApiClient mGoogleApiClient;
    String mLat = "foo";
    String mLon = "bar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            //gps enabled. or something.

            mLat = Double.toString(gps.getLatitude());
            mLon = Double.toString(gps.getLongitude());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */

                /*
                Snackbar.make(view, stuff1.getCoords(), Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();
                */


                Snackbar.make(view, "GPS: "+mLat+", "+mLon+" (latitude, longitude)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        /*
        //create an instance of google api client -mike
        if (true) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    //.addApi(LocationServices.API)
                    //.build();
        }
        */

        /*
        //create an instance of google api client -mike
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        */

    }

    /*

    //gps api connect?
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    //gps pi disconnect?
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    //gps api
    public void onConnected(Bundle connectionHint){
        android.location.Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null){

            //mLat.setText(String.valueOf(mLastLocation.getLatitude()));
            //mLon.setText(String.valueOf(mLastLocation.getLongitude()));
            mLat = String.valueOf(mLastLocation.getLatitude());
            mLon = String.valueOf(mLastLocation.getLongitude());
        }
    }

    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
