package fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.s.dinnerrate.R;

import code.VolleySingleton;

/**
 * Created by Administrator on 03-11-2014.
 */
public class FrontPage extends Fragment {
    private static View v;
    ImageLoader mImageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null)
                parent.removeView(v);
        }
        try {
            v = inflater.inflate(R.layout.activity_myvisits, container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }
        return v;
    }

    public static FrontPage newInstance(String text) {

        FrontPage f = new FrontPage();
        Bundle b = new Bundle();
        b.putString("msg", text);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SharedPreferences pref = getActivity().getPreferences(0);
        final NetworkImageView image = (NetworkImageView) getActivity().findViewById(R.id.icon);
        final TextView name = (TextView) getActivity().findViewById(R.id.name);
        final RatingBar rtBar = (RatingBar) getActivity().findViewById(R.id.ratingBar);
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance().getImageLoader();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                image.setImageUrl(pref.getString("icon", "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ78Lca8l5CjapNa3W0yZmFD2CpSBy15DS54NLOnxgL_xYrDCHDDfI2zA4"), mImageLoader);
                name.setText(pref.getString("name", "No name"));
                rtBar.setIsIndicator(true);
                rtBar.setStepSize((float) 0.5);
                rtBar.setRating(Float.parseFloat(pref.getString("rating", "0")));
            }
        }, 1500);


    }

    }

