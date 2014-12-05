package com.example.s.dinnerrate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import code.CustomAdapter;
import code.MyVisitsObject;
import code.Top20Object;
import code.VolleySingleton;

public class Restaurant extends Activity implements AdapterView.OnItemClickListener {
    private Activity activity;
    private CustomAdapter mAdapter;
    private ProgressDialog pro;
    private List<MyVisitsObject> restaurants = new ArrayList<MyVisitsObject>();
    private static final String url = "http://dinnerrate.dk/json.json";

    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant);
        NetworkImageView image = (NetworkImageView) this
                .findViewById(R.id.icon);
        TextView name = (TextView) findViewById(R.id.Name);
        //TextView rating = (TextView) this.findViewById(R.id.Rating);
        TextView comment1 = (TextView) findViewById(R.id.comment1);
        TextView comment2 = (TextView) findViewById(R.id.comment2);
        TextView comment3 = (TextView) findViewById(R.id.comment3);
        RatingBar rtBar = (RatingBar) findViewById(R.id.ratingBar);
        ImageView smiley = (ImageView) findViewById(R.id.smiley);
        if (getIntent().getIntExtra("From", -1) == 1) {
            if (mImageLoader == null)
                mImageLoader = VolleySingleton.getInstance().getImageLoader();
            final MyVisitsObject r = (MyVisitsObject) getIntent().getSerializableExtra("Object");

            image.setImageUrl(r.getImageUrl(), mImageLoader);

            name.setText(r.getRestaurantName());
            rtBar.setIsIndicator(true);
            rtBar.setStepSize((float) 0.5);

            rtBar.setRating(Float.parseFloat(r.getRating()));

            //rating.setText("Rating: "+ r.getRating());
            comment1.setText(r.getComment1());
            comment2.setText(r.getComment2());
            comment3.setText(r.getComment3());
            smiley.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Log.d("smiley", "click");
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(r.getSmileyURL()));
                        browserIntent.setPackage("com.android.chrome");
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(browserIntent);
                    } catch (ActivityNotFoundException e) {
                        Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
                        playStoreIntent.setData(Uri.parse("market://details?id=" + "com.android.chrome"));
                        playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(playStoreIntent);
                    }
                }
            });
        }
            if (getIntent().getIntExtra("From", -1) == 2) {
                if (mImageLoader == null)
                    mImageLoader = VolleySingleton.getInstance().getImageLoader();
                final Top20Object t = (Top20Object) getIntent().getSerializableExtra("Object");

                image.setImageUrl(t.getImageUrl(), mImageLoader);

                name.setText(t.getRestaurantName());
                rtBar.setIsIndicator(true);
                rtBar.setStepSize((float) 0.5);

                rtBar.setRating(Float.parseFloat(t.getRating()));

                //rating.setText("Rating: "+ r.getRating());
                comment1.setText(t.getComment1());
                comment2.setText(t.getComment2());
                comment3.setText(t.getComment3());
                smiley.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d("smiley", "click");
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(t.getSmileyURL()));
                            browserIntent.setPackage("com.android.chrome");
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(browserIntent);
                        } catch (ActivityNotFoundException e) {
                            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW);
                            playStoreIntent.setData(Uri.parse("market://details?id=" + "com.android.chrome"));
                            playStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(playStoreIntent);
                        }
                    }
                });
            }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.restaurent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
