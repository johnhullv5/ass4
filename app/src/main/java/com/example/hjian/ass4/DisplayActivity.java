package com.example.hjian.ass4;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    DBAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = MyApplication.getDbAdapter();
       adapter.open();
        setContentView(R.layout.activity_display);
       String testID = getIntent().getStringExtra("testID");
        //String testID = "504";
        Cursor c = adapter.getTest(Integer.valueOf(testID));
        if (c.moveToFirst())
        {
            do {
                TextView testid= (TextView)findViewById(R.id.testIDValue);
                testid.setText(c.getString(0).toString());
                testid.setTextColor(Color.CYAN);
//
                TextView patientIdValue= (TextView)findViewById(R.id.patientIdValue);
                patientIdValue.setText(c.getString(1).toString());
                patientIdValue.setTextColor(Color.BLUE);
//
                TextView nurseIdValue= (TextView)findViewById(R.id.nurseIdValue);
                nurseIdValue.setText(c.getString(2).toString());
                nurseIdValue.setTextColor(Color.CYAN);
//
                TextView BPLValue= (TextView)findViewById(R.id.BPLValue);
                BPLValue.setText(c.getString(3).toString());
                BPLValue.setTextColor(Color.CYAN);
//
                TextView BPHValue= (TextView)findViewById(R.id.BPHValue);
                BPHValue.setText(c.getString(4).toString());
                BPHValue.setTextColor(Color.CYAN);
//
                TextView temperatureValue= (TextView)findViewById(R.id.temperatureValue);
                temperatureValue.setText(c.getString(5).toString());
                temperatureValue.setTextColor(Color.CYAN);
//
                TextView HCGValue= (TextView)findViewById(R.id.HCGValue);
                HCGValue.setText(c.getString(6).toString());
                HCGValue.setTextColor(Color.CYAN);
//
                TextView BloodTypeValue= (TextView)findViewById(R.id.BloodTypeValue);
                BloodTypeValue.setText(c.getString(7).toString());
                BloodTypeValue.setTextColor(Color.CYAN);


            } while (c.moveToNext());
        }
        c.close();
    }
}
