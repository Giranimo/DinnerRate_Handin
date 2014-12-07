package fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import code.CustomAdapterTop;
import code.MyVisitsObject;
import code.Top20Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-10-2014.
 */


public class Top20 extends ListFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private CustomAdapterTop mAdapter;
    private ProgressDialog pro;
    private List<Top20Object> Top20ObjectList = new ArrayList<Top20Object>();
    private static final String url = "http://dinnerrate.dk/Top20.json";
    private RequestQueue mRequestQueue;
    public static Top20 newInstance(String text) {

        Top20 f = new Top20();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListData();

        mAdapter = new CustomAdapterTop(getActivity(), Top20ObjectList);
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
        if (Top20ObjectList.isEmpty() ) {
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
                    Log.i("main", error.getMessage());
                }
            });
            mRequestQueue.add(jr);
        }
    }

    private void parseJSON(JSONObject response) {
        try {
            Top20ObjectList.clear();
            JSONObject jsnObj = response.getJSONObject("JsnObj");
            JSONArray Myvis = jsnObj.getJSONArray("MyVisits");
            Log.i("myvisits", "parser json");
            for (int i = 0; i < Myvis.length(); i++) {

                JSONObject obj = Myvis.getJSONObject(i);
                Top20Object rest = new Top20Object();
                rest.setRestaurantName(obj.getString("name"));
                rest.setImageUrl(obj.getString("image"));
                rest.setRating((obj.getString("rating")));
                rest.setComment1(obj.getString("Comment1"));
                rest.setComment2(obj.getString("Comment2"));
                rest.setComment3(obj.getString("Comment3"));
                rest.setSmileyURL(obj.getString("smiley"));
                rest.setURL(obj.getString("url"));
                Top20ObjectList.add(rest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Top20Object j = Top20ObjectList.get(position);
        Log.d("myvist", "click");
        Intent i = new Intent(getActivity().getApplicationContext(), Restaurant.class);
        i.putExtra("From", 2);
        i.putExtra("Object", j);
        startActivity(i);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
