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

import com.example.homeenvironment.ApiClasses.Aqi;
import com.example.homeenvironment.ApiClasses.Data;
import com.google.gson.Gson;

public class PollutionRetriever {

    //Token: 65b508772b7c6eb331f3e00c0f546cf23ab6ab82
    static String token = "65b508772b7c6eb331f3e00c0f546cf23ab6ab82";
    static String airQuality;
    static String coOrdinates="-36.05035;146.942";
    static int aqiNumber;
    static String aqiHuman;

    public PollutionRetriever() {
    }

    public static void getAirQuality() throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        Aqi aqi = new Aqi();

        Data data = new Data();

        aqi.setData(data);

        URL url = new URL("https://api.waqi.info/feed/geo: "+coOrdinates+"/?token="+token);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStreamReader reader = new InputStreamReader(conn.getInputStream()); //read JSON stream
        Aqi aqiObject = gson.fromJson(reader, Aqi.class);

        reader.close();

        aqiNumber=Integer.parseInt(aqiObject.getData().getAqi());
        aqiHuman= ReadableAqi(Integer.parseInt(aqiObject.getData().getAqi()));
    }



    /*
    Example sample data:
    "status":"success",
    "data":{
        "city":"Nashville",
        "state":"Tennessee",
        "country":"USA",
        "location":{
            "type":"Point",
            "coordinates":[-86.7386,36.1767]},
        "current":{
            "weather":{
                "ts":"2019-04-08T19:00:00.000Z",
                "__v":0,
                "createdAt":"2019-04-08T19:04:18.662Z",
                "hu":88,
                "ic":"04d",
                "pr":1012,
                "tp":18,
                "updatedAt":"2019-04-08T19:46:53.140Z",
                "wd":90,
                "ws":3.1},
            "pollution":{
                "ts":"2019-04-08T18:00:00.000Z",
                "aqius":10,
                "mainus":"p2",
                "aqicn":3,
                "maincn":"p2"}
     */

    //Based on example sample data from our API - Air Quality Open Data Platform:

    /*
    Example sample Json file:

    {
    "status": "ok",
    "data": {
        "aqi": 156,
        "idx": 1437,
        "attributions": [
            {
                "url": "http://106.37.208.233:20035/emcpublish/",
                "name": "China National Urban air quality real-time publishing platform (全国城市空气质量实时发布平台)"
            },
            {
                "url": "https://china.usembassy-china.org.cn/embassy-consulates/shanghai/air-quality-monitor-stateair/",
                "name": "U.S. Consulate Shanghai Air Quality Monitor"
            },
            {
                "url": "https://sthj.sh.gov.cn/",
                "name": "Shanghai Environment Monitoring Center(上海市环境监测中心)"
            },
            {
                "url": "https://waqi.info/",
                "name": "World Air Quality Index Project"
            }
        ],
        "city": {
            "geo": [
                31.2047372,
                121.4489017
            ],
            "name": "Shanghai (上海)",
            "url": "https://aqicn.org/city/shanghai"
        },
        "dominentpol": "pm25",
        "iaqi": {
            "co": {
                "v": 6.6
            },
            "h": {
                "v": 75
            },
            "no2": {
                "v": 9.2
            },
            "o3": {
                "v": 50.6
            },
            "p": {
                "v": 1006.4
            },
            "pm10": {
                "v": 57
            },
            "pm25": {
                "v": 156
            },
            "so2": {
                "v": 1.6
            },
            "t": {
                "v": 23.7
            },
            "w": {
                "v": 0.1
            }
        },
        "time": {
            "s": "2020-06-20 04:00:00",
            "tz": "+08:00",
            "v": 1592625600
        },
        "debug": {
            "sync": "2020-06-20T05:40:31+09:00"
        }
    }
}
     */

}