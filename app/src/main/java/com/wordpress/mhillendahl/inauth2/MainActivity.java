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

//app list crap
import java.util.List;

import android.widget.Button;
import android.widget.EditText;
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

        final Button but1 = (Button) findViewById(R.id.button);
        but1.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {

                EditText et1 = (EditText) findViewById(R.id.extractEditText);
                String plaintext = et1.getText().toString();
                EditText et2 = (EditText) findViewById(R.id.extractEditText2);
                String cipher = et2.getText().toString();
                EditText et3 = (EditText) findViewById(R.id.extractEditText3);

                if (!stuff1.isLegal(plaintext, cipher))
                    et3.setText("<inputerror>");
                else
                    et3.setText(stuff1.encrypt(plaintext, cipher));

                return;
            }
        });

        //DECRYPT

        final Button but2 = (Button) findViewById(R.id.button2);
        but2.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                EditText et3 = (EditText) findViewById(R.id.extractEditText3);
                String encrypted = et3.getText().toString();
                EditText et2 = (EditText) findViewById(R.id.extractEditText2);
                String cipher = et2.getText().toString();
                EditText et4 = (EditText) findViewById(R.id.extractEditText4);


                if (!stuff1.isLegal(encrypted, cipher))
                    et4.setText("<inputerror>");
                else
                    et4.setText(stuff1.decrypt(encrypted, cipher));

                //et4.setText("kiss my ass");


                return;
            }
        });

        //ADD INTEGER TO LIST

        final Button but3 = (Button) findViewById(R.id.button3);
        but3.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                EditText txt = (EditText) findViewById(R.id.extractEditText5);
                String sTxt = txt.getText().toString();
                EditText list = (EditText) findViewById(R.id.extractEditText6);

                if (stuff1.isNum(sTxt)){
                    list.append(sTxt);
                }
                txt.setText("");

                return;
            }
        });

        // ?

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
