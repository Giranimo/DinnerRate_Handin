package com.example.s.dinnerrate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Administrator on 15-10-2014.
 */


public class Settings extends Activity {
    private SharedPreferences WorldPref, PublicPref;
    private CheckBox World, Public;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);


        WorldPref = getApplicationContext().getSharedPreferences("World", 0);
        PublicPref = getApplicationContext().getSharedPreferences("Public", 0);

        World = (CheckBox) findViewById(R.id.World);
        Public = (CheckBox) findViewById(R.id.Public);


        if (WorldPref.getBoolean("World", false)) {
            World.setChecked(true);
        } else {
            World.setChecked(false);
        }

        if (PublicPref.getBoolean("Public", false)) {
            Public.setChecked(true);
        } else {
            Public.setChecked(false);
        }
    }


    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        WorldPref = getApplicationContext().getSharedPreferences("World", 0);
        PublicPref = getApplicationContext().getSharedPreferences("Public", 0);
        SharedPreferences.Editor WorldPrefEdit = WorldPref.edit();
        SharedPreferences.Editor PublicPrefEdit = PublicPref.edit();


        switch (view.getId()) {
            case R.id.World:
                if (checked) {
                    WorldPrefEdit.putBoolean("World", true);
                    WorldPrefEdit.commit();
                } else {
                    WorldPrefEdit.putBoolean("World", false);
                    WorldPrefEdit.commit();
                }

                break;
            case R.id.Public:
                if (checked) {
                    PublicPrefEdit.putBoolean("World", true);
                    PublicPrefEdit.commit();
                } else {
                    PublicPrefEdit.putBoolean("World", false);
                    PublicPrefEdit.commit();
                }
                break;

        }


    }
}
