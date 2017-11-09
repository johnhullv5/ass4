package com.example.hjian.ass4;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.Application;
import android.util.Log;

/**
 * Created by hjian on 2017-11-05.
 */

public class MyApplication extends Application {
    private static DBAdapter dbAdapter;

    public static DBAdapter getDbAdapter() {
        return dbAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("app","called");
        dbAdapter = new DBAdapter(getApplicationContext()).open();
    }
}
