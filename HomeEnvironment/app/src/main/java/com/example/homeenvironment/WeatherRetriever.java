package com.example.homeenvironment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class WeatherRetriever {
    String content;
    LocationListener locationListener;
    LocationManager locationManager;
    String cityName;
    String country;
    CityListener cityListener;
    String temperature;
    String humidity;

    //Første string er URL som string, sidste string er return typen.
    class Weather extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... address) {
            try {
                //Åbn adresse samt opret forbindelse.
                URL url = new URL(address[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                //Hent data
                InputStream mInputStream = connection.getInputStream();
                InputStreamReader mInputStreamReader = new InputStreamReader(mInputStream);

                //Formatering og afhentning af data
                int data = mInputStreamReader.read();
                String contentOf = "";
                char ch;
                while (data != -1) {
                    ch = (char) data;
                    contentOf = contentOf + ch;
                    data = mInputStreamReader.read();
                }
                return contentOf;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private class CityListener implements LocationListener {
        View view;

        public CityListener(View view) {
            this.view = view;
        }


        @Override
        public void onLocationChanged(Location location) {
            String longitude = "Longitude: " + location.getLongitude();
            String latitude = "Latitude: " + location.getLatitude();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    public WeatherRetriever(View view) {
        Context context = view.getContext();
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.cityListener = new CityListener(view);
        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_DENIED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 1, cityListener);
        } else {
            Log.i("WeatherRetriever", "What are you thinking? That's illegal! ");
        }


        //Log.i("WeatherRetriever", "End of method");
    }

    //Hvis humidity eller temperatur er null, så betyder det at brugeren ikke befinder sig i en by. Returner "base" værdi.
    public String getHumidity()  {
        Log.i("WeatherRetriever", "This is the humidity " + humidity);
        if(humidity == null) return ""+0;
        else return humidity;
    }


    public String getTemperature()  {
        Log.i("WeatherRetriever", "This is the temperature " + temperature);
        if(temperature == null) return ""+0;
        else return temperature;
    }

    public void setWeather(View view) {
        if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(view.getContext(),"That isn't allowed", Toast.LENGTH_LONG);
        }else{

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Geocoder gcd = new Geocoder(view.getContext(), Locale.US);
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    this.cityName = addresses.get(0).getLocality();
                    //Log.i("WeatherRetriever", "This is the city's name " + cityName);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Weather weather = new Weather();
            try {
                /**I tilfælde af at brugeren ikke er i en by, så kan programmet ikke hente dataen omrking brugeren.
                 I så fald vil vi afbryde funktionen, for ikke at crash programmet.
                 Problemet kan eventuelt løses ved at finde en anden måde at hente vejdata som ikke er afhængig af byer, men dette er udenfor ekspertise samt tidsbudget.
                */
                 if(this.cityName == null) return;
                //Log.i("WeatherRetriever", "final city name: "+cityName);
                content = weather.execute("https://openweathermap.org/data/2.5/weather?q="+cityName+"&appid=439d4b804bc8187953eb36d2a8c26a02").get();
                JSONObject json = new JSONObject(content);
                JSONObject jsonMain = new JSONObject(json.getString("main"));
                temperature = jsonMain.getString("temp");
                humidity = jsonMain.getString("humidity");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
