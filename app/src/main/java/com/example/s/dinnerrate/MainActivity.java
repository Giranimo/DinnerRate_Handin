package com.example.s.dinnerrate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import code.CustomAdapter;
import code.MyVisitsObject;
import fragments.Friends;
import fragments.FrontPage;
import fragments.GpsActivity;
import fragments.Top20;


public class MainActivity extends FragmentActivity implements View.OnClickListener, SensorEventListener {
    private ImageView gpsButton, friendsButton, myVisitsButton, top20Button;
    UnderlinePageIndicator mIndicator;
    static ViewPager pager = MainActivity.pager;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    Handler sensorHandler;
    Runnable toggleRunnable;
    private ProgressDialog pro;
    private List<MyVisitsObject> myVisitsObjectList = new ArrayList<MyVisitsObject>();
    private static final String url = "http://dinnerrate.dk/json.json";
    private CustomAdapter mAdapter;
    private RequestQueue mRequestQueue;

    String[] restuarenter = {"DinnerRate", "Cofoco", "MadKlubben", "Tony's", "Noma", "BLa1", "Bla2", "Bla3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorHandler = new Handler();
        toggleRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    pager.setCurrentItem(3, true);
                } catch (Exception ex) {
                    Log.d("pager", "sensor thread cant affect main thread even in runnable");
                }

            }
        };
        this.myVisitsButton = (ImageView) findViewById(R.id.my_visits);
        this.friendsButton = (ImageView) findViewById(R.id.friends);
        this.top20Button = (ImageView) findViewById(R.id.top_20);
        this.gpsButton = (ImageView) findViewById(R.id.gps);


        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        myVisitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0, true);
            }
        });
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1, true);
            }
        });
        top20Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2, true);
            }
        });
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(3, true);
            }
        });
        mIndicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        mIndicator.setFades(false);
        mIndicator.setViewPager(pager);
    }
    @Override
    protected void onResume() {
        super.onResume();
        int hyppighed = SensorManager.SENSOR_DELAY_NORMAL;
        mSensorManager.registerListener(this, mAccelerometer, hyppighed);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    private void hidePro() {
        if (pro != null) {
            pro.dismiss();
            pro = null;
        }
    }

    public void searchClicked(View b) {


    }

    public void onSensorChanged(SensorEvent e) {
        int sensor = e.sensor.getType();
        if (sensor == Sensor.TYPE_ACCELEROMETER) {
            double sum = Math.abs(e.values[0]) + Math.abs(e.values[1]) + Math.abs(e.values[2]);
            if (sum > 3 * SensorManager.GRAVITY_EARTH) {
                Toast toast = Toast.makeText(getApplicationContext(), "Shake It", Toast.LENGTH_SHORT);
                toast.show();
                Intent gps = new Intent(this, Shake.class);
                startActivity(gps);
            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void settingsClicked(View b) {
        Intent set = new Intent(this, Settings.class);
        startActivity(set);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem() == 0){
            super.onBackPressed();
        } else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);

        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return FrontPage.newInstance("");
                case 1:
                    return Friends.newInstance("");
                case 2:
                    return Top20.newInstance("");

                case 3:
                    return GpsActivity.newInstance("");

                default:
                    return FrontPage.newInstance("");
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}