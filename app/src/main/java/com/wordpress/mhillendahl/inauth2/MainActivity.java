package com.wordpress.mhillendahl.inauth2;

import android.app.Activity;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.List;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//end app list crap

public class MainActivity extends AppCompatActivity {

    //GoogleApiClient mGoogleApiClient;
    String mLat = "foo"; //placeholder for latitude info
    String mLon = "bar"; //longitude info
    List<Integer> nums = new ArrayList<Integer>(); //list for sorting thing later
    String sNums = ""; //string variable to hold formatted int list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GPS

        final GPSTracker gps = new GPSTracker(this); //calls my gps stuff from library

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //floating action button at the bottom to show gps stuff
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GPSTracker gps = new GPSTracker(this);
                if(gps.canGetLocation()){ //another library call
                    mLat = Double.toString(gps.getLatitude());
                    mLon = Double.toString(gps.getLongitude());
                }
                Snackbar.make(view, "GPS: "+mLat+", "+mLon+" (latitude, longitude)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //APPS

        List<String> lApps = stuff1.getApps(this); //get a list of strings
        //String[] aApps = lApps.toArray(new String[lApps.size()]);
        String sApps = ""; //build a giant string to put into text view
        for (String app : lApps) {
            if (app!=null)
                sApps += app+"\n";
        }

        TextView textView = (TextView) findViewById(R.id.tvAppList);
        textView.setText(sApps);
        //textView.setText("blahblah");

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

                if (!stuff1.isLegal(plaintext, cipher)) //if bad user input (any non-alphabetical)
                    et3.setText("<inputerror>");        //pr error
                else
                    et3.setText(stuff1.encrypt(plaintext, cipher)); //pass data and password to encrypt

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

                //et4.setText("test1");


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
                    //store new int in list
                    nums.add(Integer.parseInt(sTxt));
                    list.append(sTxt+", ");
                }

                //clear user input box
                txt.setText("");

                return;
            }
        });

        //SORT

        final Button but4 = (Button) findViewById(R.id.button4);
        but4.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                EditText list = (EditText) findViewById(R.id.extractEditText6);
                list.setText("");    //clear list

                //sort the list!
                //nums = stuff1.sort(nums);

                //SORT
                for (int i = 0; i<nums.size() - 1; i++) {                  //consider every index, starting w 0
                    int min = nums.get(i);                                 //remember the value at this index
                    for (int j = i + 1; j < nums.size() - i; j++) {        //  consider all subsequent indices
                        if (nums.get(j) < nums.get(i))                     //    if lower values are found
                            min = j;                                       //      remember the index of the lowest value
                    }
                    if (min < nums.get(i)) {                               //  if a lower value was found,
                        int num = nums.remove(min);                        //    remove and store the lower value.
                        nums.add(min, nums.get(i));                        //    replace it w the higher number
                        nums.set(i, num);                                  //    assign the lower number to the index of the higher number
                    }
                }

                //sNums = "";
                for (int i : nums)
                    //sNums = sNums + Integer.toString(i) + ", ";
                    list.append(Integer.toString(i)+", ");

                //list.setText(sNums);
                //list.setText("stuffblahbalahlaih;lkaiasdtf");

                return;
            }
        });

        //simple app reset button

        final Button but5 = (Button) findViewById(R.id.button5);
        but5.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

                return;
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
