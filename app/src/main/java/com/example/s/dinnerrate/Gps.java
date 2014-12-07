package com.example.s.dinnerrate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import code.GpsObject;

/**
 * Created by Administrator on 01-11-2014.
 */
public class Gps extends Activity {
    private ProgressDialog pro;
    private RequestQueue mRequestQueue;
    private List<GpsObject> gpsList = new ArrayList<GpsObject>();
    private static final String url = "http://dinnerrate.dk/json.json";
    public GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {
        MapFragment mapFrag = new MapFragment();
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        setListData();
        for (int i = 0; i < gpsList.size(); i++) {
            GpsObject g = gpsList.get(i);
            mMap.addMarker(new MarkerOptions().position(new LatLng(g.getLatitude(), g.getLongitude())).title(g.getRestaurantName()));
        }
    }
    public void setListData() {
        mRequestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("main", response.toString());
                parseJSON(response);
                //mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("main", error.getMessage());
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
                rest.setLongitude(obj.getDouble("Longitude"));
                rest.setLatitude(obj.getDouble("Longitude"));
                gpsList.add(rest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
