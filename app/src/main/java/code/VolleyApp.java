package code;
import android.app.Application;
import android.content.Context;

import com.parse.Parse;

/**
 * Created by Administrator on 04-12-2014.
 */
public class VolleyApp extends Application {
    private static VolleyApp mInstance;
    private static Context mAppContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.setAppContext(getApplicationContext());
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ZuT7jpItEW8hDekfPHplExHI69BOZghsDe9yeylQ", "W8Nfe2qVO8VFOejc4FtXSjbxmY6YbkrmeWqZOdSY");
    }
    public static VolleyApp getInstance() {
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }
}
