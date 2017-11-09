package com.example.hjian.ass4;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {
    DBAdapter adapter;
    TableLayout tableLayout1;
    TextView testid1;
    TextView test_patient1;
    List<TextView> testIDList;
    Intent intent;
    String testID="";
    String patientID;
    HashMap map;
    HashMap patientMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        adapter = MyApplication.getDbAdapter();
        adapter.open();
        map = new HashMap<Integer,Integer>();
        patientMap = new HashMap<Integer,Integer>();
        Cursor c = adapter.getAllTests();
        int count = c.getCount();

        tableLayout1 = (TableLayout)findViewById(R.id.tableLayout1);
        TableRow rowOne = new TableRow(this);
        TextView testid = new TextView(this);
        testid.setText("testID");
        testid.setTextColor(Color.RED);
        testid.setPadding(10,10,20,10);
        TextView patientID = new TextView(this);
        patientID.setText("patientID");
        patientID.setPadding(10,10,20,10);
        patientID.setTextColor(Color.RED);
        rowOne.addView(testid);
        rowOne.addView(patientID);
        tableLayout1.addView(rowOne);

        int id = 0;
        if (c.moveToFirst())
        {
            do {
                id+=1;
                TableRow row = new TableRow(this);
            TextView testid1 = new TextView(this);
            testid1.setText(c.getString(0).toString());
            testid1.setTextColor(Color.RED);
            testid1.setPadding(10,10,20,10);
            TextView testid2 = new TextView(this);
            testid2.setText(c.getString(1).toString());
            testid2.setPadding(10,10,20,10);
            testid2.setTextColor(Color.RED);
            ImageButton buttonDisPlay = new ImageButton(this);
            //buttonDisPlay.setText("display");
                buttonDisPlay.setId(id);
                map.put(id,Integer.valueOf(c.getString(0).toString()));
                buttonDisPlay.setOnClickListener( buttonClickListener);
                buttonDisPlay.setImageResource(R.drawable.displaybutton);
                ImageButton buttonUpdate = new ImageButton(this);
            //buttonUpdate.setText("update");
                buttonUpdate.setId(id);
                patientMap.put(id,Integer.valueOf(c.getString(1).toString()));
                buttonUpdate.setOnClickListener( buttonClickUpdate);
                buttonUpdate.setImageResource(R.drawable.updatebutton);
            row.addView(testid1);
            row.addView(testid2);
            row.addView(buttonDisPlay);
            row.addView(buttonUpdate);
            tableLayout1.addView(row);
            } while (c.moveToNext());
        }
        c.close();

        //test_patient1 = (TextView)findViewById(R.id.patientID1);
        //test_patient1.setText(c.getString(1).toString());
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(DoctorActivity.this,DisplayActivity.class);
            intent.putExtra("testID",map.get(v.getId()).toString() );
            Log.i("testid",map.get(v.getId()).toString());

            startActivity(intent);

        }


    };

    private View.OnClickListener buttonClickUpdate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(DoctorActivity.this,UpdateActivity.class);
            intent.putExtra("patientID",patientMap.get(v.getId()).toString() );

            startActivity(intent);

        }


    };


}
