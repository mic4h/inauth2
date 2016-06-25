package com.wordpress.mhillendahl.inauth2;

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

//end gps crap

//app list crap
import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.ToggleButton;
//end app list crap

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

        //GPS

        final GPSTracker gps = new GPSTracker(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //GPSTracker gps = new GPSTracker(this);
                if(gps.canGetLocation()){
                    mLat = Double.toString(gps.getLatitude());
                    mLon = Double.toString(gps.getLongitude());
                }

                Snackbar.make(view, "GPS: "+mLat+", "+mLon+" (latitude, longitude)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        //APPS

        List<String> lApps = stuff1.getApps(this);
        String[] aApps = lApps.toArray(new String[lApps.size()]);
        String sApps = "";
        for (String app : lApps) {
            if (app!=null)
                sApps += app+"\n";
        }

        TextView textView = (TextView) findViewById(R.id.tvAppList);
        textView.setText(sApps);
        //textView.setText("fuck");

        //ENCRYPT

        ToggleButton tog = (ToggleButton) findViewById(R.id.tog);
        tog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do shit when you click the tog button
            }
        });
    }


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
