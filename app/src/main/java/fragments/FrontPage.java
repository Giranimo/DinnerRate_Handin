package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s.dinnerrate.R;

/**
 * Created by Administrator on 03-11-2014.
 */
public class FrontPage extends Fragment {
    private static View v;

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


}
