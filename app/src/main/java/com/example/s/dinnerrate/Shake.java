package com.example.s.dinnerrate;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import code.GpsObject;

public class Shake extends FragmentActivity {
    private ProgressDialog pro;
    private RequestQueue mRequestQueue;
    private List<GpsObject> gpsList = new ArrayList<GpsObject>();
    private static final String url = "http://dinnerrate.dk/Top20.json";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        setListData();

        setUpMapIfNeeded();
    }

    public void setListData() {
        mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("main", response.toString());
                parseJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("main", "hi");
            }
        });
        mRequestQueue.add(jr);
    }

    private void parseJSON(JSONObject response) {
        try {
            JSONObject jsnObj = response.getJSONObject("JsnObj");
            JSONArray Myvis = jsnObj.getJSONArray("MyVisits");
            Log.i("myvisits", "parser json");
            for (int i = 0; i < Myvis.length(); i++) {

                JSONObject obj = Myvis.getJSONObject(i);
                GpsObject rest = new GpsObject();
                rest.setRestaurantName(obj.getString("name"));
                rest.setLatitude(obj.getDouble("Longitude"));
                rest.setLongitude(obj.getDouble("Latitude"));
                gpsList.add(rest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUpMap();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {
        mMap.clear();
        for (GpsObject g : gpsList) {

            Log.d("map", "" + g.getLatitude());
            mMap.addMarker(new MarkerOptions().position(new LatLng(g.getLongitude(), g.getLatitude())).title(g.getRestaurantName()));
        }
        LatLng la = new LatLng(55.686907, 12.570198);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(la)
                .zoom(11)
                .bearing(90)
                .tilt(20)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
