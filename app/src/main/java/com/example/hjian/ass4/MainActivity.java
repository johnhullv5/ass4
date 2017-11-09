package com.example.hjian.ass4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import 	android.database.sqlite.SQLiteCursor;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    DBAdapter adapter;

    SQLiteDatabase mDatabase;
    private static final String TAG = "MyActivity";
    public static final String PREFS_NAME = "MyPrefsFile";
    private String loginID = "";
    private static final String DROP_NURSE_TABLE = "DROP TABLE IF  EXISTS tbl_nurses; ";
    private static final String DROP_DOC_TABLE = "DROP TABLE IF  EXISTS tbl_doctors; ";
    private static final String CREATE_NURSE_TABLE="CREATE TABLE IF NOT EXISTS tbl_nurses( nurseId INTEGER PRIMARY KEY , firstname TEXT, lastname TEXT,department TEXT,password TEXT );";
    private static final String CREATE_DOCTOR_TABLE="CREATE TABLE IF NOT EXISTS tbl_doctors( doctorId INTEGER PRIMARY KEY , firstname TEXT, lastname TEXT,department TEXT,password TEXT );";
    private static final String INSERT_NURSE_TABLE="INSERT INTO tbl_nurses( nurseId  , firstname , lastname ,department ,password  ) VALUES(100,'hao','jiang','a','123');";
    private static final String INSERT_DOCTOR_TABLE="INSERT INTO tbl_doctors( doctorId  , firstname , lastname ,department ,password  ) VALUES(200,'hao','jiang','a','123');";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = MyApplication.getDbAdapter();
        mDatabase = adapter.getDb();
//        mDatabase= openOrCreateDatabase(
//                "my_sqlite_database.db",
//                SQLiteDatabase.CREATE_IF_NECESSARY,
//                null);
//
//
//
//
//        mDatabase.execSQL(DROP_NURSE_TABLE);
//        mDatabase.execSQL(CREATE_NURSE_TABLE);
//        mDatabase.execSQL(INSERT_NURSE_TABLE);
//
//        mDatabase.execSQL(DROP_DOC_TABLE);
//        mDatabase.execSQL(CREATE_DOCTOR_TABLE);
//        mDatabase.execSQL(INSERT_DOCTOR_TABLE);
//
//        DBAdapter db = new DBAdapter(this);


    }

    public void login(View v)
    {

        if(mDatabase.isOpen()==false)
        {
//            mDatabase= openOrCreateDatabase(
//                    "my_sqlite_database.db",
//                    SQLiteDatabase.CREATE_IF_NECESSARY,
//                    null);
            adapter = MyApplication.getDbAdapter();
            mDatabase = adapter.getDb();


        }
        EditText userName1 = (EditText)findViewById(R.id.userName);
        loginID = userName1.getText().toString();

        EditText passwordWiget = (EditText)findViewById(R.id.password);
        //loginID = passwordWiget.getText().toString();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("loginID", loginID);

        // Commit the edits!
        editor.commit();

        String password = "";
        boolean isNurse = false;
        boolean isDoctor = false;
        Cursor c = mDatabase.rawQuery("SELECT password FROM tbl_nurses WHERE nurseId = "+loginID+";", null);
        Cursor c1 = mDatabase.rawQuery("SELECT password FROM tbl_doctors WHERE doctorId = "+loginID+";", null);
        //Cursor c = mDatabase.rawQuery("SELECT password FROM tbl_nurses WHERE TRIM(nurseId) = ''"+loginID+"'';", null);
        if(c.moveToFirst()){
            do{
                //assing values
                password= c.getString(0);
                if(passwordWiget.getText().toString().equals(password.toString()))
                    isNurse = true;
                //Do something Here with values

            }while(c.moveToNext());
        }

        if(isNurse==false)
        {

            if(c1.moveToFirst()){
                do{
                    //assing values
                    password= c1.getString(0);
                    if(passwordWiget.getText().toString().equals(password.toString()))

                        isDoctor = true;
                    //Do something Here with values

                }while(c1.moveToNext());
            }
            c1.close();
        }
        c.close();


        if(isNurse==true)
    {
        intent = new Intent(MainActivity.this,NurseActivity.class);
        intent.putExtra("loginID",loginID );

        startActivity(intent);
        mDatabase.close();
        return;
    }

        if(isDoctor==true)
        {
            intent = new Intent(MainActivity.this,DoctorActivity.class);
            intent.putExtra("loginID",loginID );

            startActivity(intent);
            mDatabase.close();
            return;
        }
        TextView mismatch = (TextView)findViewById(R.id.mismatch);
        mismatch.setVisibility(View.VISIBLE);
        mismatch.setTextColor(Color.RED);



        Log.v(TAG, "index=" + password.length());
        Log.v(TAG, "index=" + passwordWiget.getText().toString().length());



       //Toast.makeText(v.getContext(), "Selected: " + loginID, Toast.LENGTH_LONG).show();

    }
}
