package leefoster.me.workoutapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by leefoster on 2017-08-10.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

}
