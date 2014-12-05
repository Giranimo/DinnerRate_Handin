package code;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.s.dinnerrate.R;

import java.util.List;

/**
 * Created by Administrator on 25-10-2014.
 */
public class CustomAdapterTop extends BaseAdapter {
    private Activity activity;
    private List<Top20Object> restaurants;
    ImageLoader mImageLoader;


    private static LayoutInflater inflater = null;

    public CustomAdapterTop(Activity a, List<Top20Object> restaurants) {

        this.activity = a;
        this.restaurants = restaurants;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return restaurants.size();
    }

    public Object getItem(int position) {
        return restaurants.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list, null);
        }
        if (mImageLoader == null)
            mImageLoader = VolleySingleton.getInstance().getImageLoader();

        NetworkImageView image = (NetworkImageView) convertView
                .findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.Name);
        TextView rating = (TextView) convertView.findViewById(R.id.Rating);
        Top20Object r = restaurants.get(position);
        image.setImageUrl(r.getImageUrl(), mImageLoader);
        name.setText(r.getRestaurantName());
        rating.setText("Rating: " + r.getRating());
        return convertView;

    }

}



