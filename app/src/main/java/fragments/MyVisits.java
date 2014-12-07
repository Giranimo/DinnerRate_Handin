package fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import code.CustomAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.s.dinnerrate.R;
import com.example.s.dinnerrate.Restaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import code.MyVisitsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-10-2014.
 */


public class MyVisits extends ListFragment {
    private ListView listView;
    private CustomAdapter mAdapter;
    private ProgressDialog pro;
    private List<MyVisitsObject> myVisitsObjectList = new ArrayList<MyVisitsObject>();
    private static final String url = "http://dinnerrate.dk/MyVisits.json";
    private RequestQueue mRequestQueue;

    public static MyVisits newInstance(String text) {

        MyVisits f = new MyVisits();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListData();

        mAdapter = new CustomAdapter(getActivity(), myVisitsObjectList);
        setListAdapter(mAdapter);
        listView = (ListView) getActivity().findViewById(R.id.list);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setListData() {
        Log.d("setlist", myVisitsObjectList.toString());
        if (myVisitsObjectList.isEmpty() ) {
            mRequestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("main", response.toString());
                    parseJSON(response);
                    ;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("main", "onErrorResponse");
                }
            });
            mRequestQueue.add(jr);
        }
    }

    private void parseJSON(JSONObject response) {
        try {
            myVisitsObjectList.clear();
            JSONObject jsnObj = response.getJSONObject("JsnObj");
            JSONArray Myvis = jsnObj.getJSONArray("MyVisits");
            for (int i = 0; i < Myvis.length(); i++) {

                JSONObject obj = Myvis.getJSONObject(i);
                MyVisitsObject rest = new MyVisitsObject();
                rest.setRestaurantName(obj.getString("name"));
                rest.setImageUrl(obj.getString("image"));
                rest.setRating((obj.getString("rating")));
                rest.setComment1(obj.getString("Comment1"));
                rest.setComment2(obj.getString("Comment2"));
                rest.setComment3(obj.getString("Comment3"));
                rest.setSmileyURL(obj.getString("smiley"));
                rest.setURL(obj.getString("url"));
                myVisitsObjectList.add(rest);
                if (i == 0){
                    SharedPreferences pref = getActivity().getPreferences(0);
                    SharedPreferences.Editor edt = pref.edit();
                    edt.putString("name", obj.getString("name"));
                    edt.putString("rating", obj.getString("rating"));
                    edt.putString("icon", obj.getString("image"));
                    edt.commit();


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();

    }

/*
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.fragment));
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }
*/
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MyVisitsObject j = myVisitsObjectList.get(position);
        Intent i = new Intent(getActivity().getApplicationContext(), Restaurant.class);
        i.putExtra("From", 1);
        i.putExtra("Object", j);
        startActivity(i);
    }


}
