package com.example.s10047816.heatisland;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

class SendGetTask extends AsyncTask<String, Void, Void> {
    private Exception exception;

    private void executeReq(URL urlObject) throws IOException {
        HttpURLConnection conn = null;
    }

    protected Void doInBackground(String... url) {
        Log.i("testing", "url:"+url[0]);
        String urls = url[0];
        try {
            URL obj = new URL(urls);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            //this is the http response code of the website - currently not used
            int responseCode = con.getResponseCode();
        }catch(Exception e){}
        return null;
    }
}

public class Form extends AppCompatActivity{

    String provider;
    Location currentLocation;
    HashMap<String,Boolean> descValues = new HashMap<>();
    LocationManager locationManager;

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.i("Info","locationChanged method");
        }

        public void onStatusChanged(String provider, int status, Bundle extras){
            Log.i("Info","Status changed");

        }

        public void onProviderEnabled(String provider){

        }

        public void onProviderDisabled(String provider){

        }
    };

    final int PERMISSION_GET_LOCATION = 1;

    public void submit(View view){


        /*
        //LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_GET_LOCATION);


        }

        else {
            Log.i("Info","First else statement");
            currentLocation= LocationServices.FusedLocationApi.getLastLocation(apiClient);
        }
        Log.i("Info", currentLocation.toString());
        runOnUiThread(new Runnable() {
            public void run() {
                Log.i("Info","USING TOAST NOW");
                Log.i("Info","Printing currentLocation");
                Log.i("Info",currentLocation.toString());
                Log.i("Info","After print of currentLocation");
                Toast.makeText(getApplicationContext(), currentLocation.toString(), Toast.LENGTH_LONG).show();
            }
        });
        */
        double lon=-1;
        double lat=-1;

        try {
            Location GPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location Network = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            if(provider.equals(LocationManager.GPS_PROVIDER)){
                lon=GPS.getLongitude();
                lat=GPS.getLatitude();
            }
            else{
                lon=Network.getLongitude();
                lat=Network.getLatitude();
            }
        }
        catch(SecurityException e){
            Log.i("Info","Second priveleges check required");
        }

        Toast.makeText(getApplicationContext(),lon + " " + lat,Toast.LENGTH_LONG).show();


        Log.i("Info","Printing coordinate values: " + lon + " " + lat);
        Log.i("submitting", "sending data to database");
        //just to clarify, temp is for temperature NOT temporary
        final TextView temp = (TextView) findViewById(R.id.temp);
        final TextView desc = (TextView) findViewById(R.id.desc);
        String descT = desc.getText().toString();
        try {
            Log.i("preencoded", ""+descT);
            descT = URLEncoder.encode(descT, "UTF-8");
            descT.replace("%20", "+");
            Log.i("postencoded", ""+descT);
            String parameters = "?location="; //
            parameters += "12.3%2C23.5";
            parameters += "&description="+descT;
            parameters += "&temperature="+temp.getText().toString();
            URL url = new URL("http://carter-lasa-guess.appspot.com/" + parameters);
            String urls = "http://carter-lasa-guess.appspot.com/" + parameters;
            Log.i("url", "sending: "+url);
            new SendGetTask().execute(urls);
        }catch(Exception e){Log.i("error", ""+e);}
    }


    public void onClickSendToDescriptions(View view){
        startActivity(new Intent(Form.this,ListViewDescription.class));
    }


    /*
    public void onConnectionFailed(ConnectionResult connectionResult){

    }

    public void onConnected(Bundle connectionHint){


    }


    public void onConnectionSuspended(int i){

    }


    public Location getLoc() throws SecurityException{
        Location currentLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);


        return currentLocation;
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_GET_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    currentLocation = getLoc();


                } else {

                    Log.i("Info","else statement of permission handling");
                    currentLocation = null;

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    protected void onStart(){
        apiClient.connect();
        super.onStart();
    }

    protected void onStop(){
        apiClient.disconnect();
        super.onStop();
    }
    */

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_GET_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    try {
                        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
                        } else {
                            Log.i("Info", "rip");
                        }
                    }
                    catch(SecurityException e){
                        Log.i("Info",e.getStackTrace().toString());
                    }


                } else {

                    Log.i("Info","else statement of permission handling");
                    currentLocation = null;

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle extras = getIntent().getExtras();

        if(extras!=null) {
            if (extras.containsKey("description")) {
                final EditText desc = (EditText) findViewById(R.id.desc);
                String description = "";
                for (String d : (ArrayList<String>) extras.get("description")) {
                    description += d + ", ";
                }
                desc.setText(description);
            }
        }

        EditText desc = (EditText) findViewById(R.id.desc);

        desc.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        EditText temp = (EditText) findViewById(R.id.temp);

        temp.getBackground().setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);



        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_GET_LOCATION);


        }

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 1, locationListener);
            provider = LocationManager.GPS_PROVIDER;
        }
        else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 200, 1, locationListener);
            provider = LocationManager.NETWORK_PROVIDER;
        }
        else{
            Log.i("Info","rip");
        }

        /*
        if(apiClient==null){
            apiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        */


    }
}