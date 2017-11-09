package com.example.hjian.ass4;

import android.database.SQLException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NurseActivity extends AppCompatActivity {
    DBAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse);
        adapter = MyApplication.getDbAdapter();
        adapter.open();
    }

    public void addTest(View v)
    {
        try
        {
            TextView patientIdValue= (TextView)findViewById(R.id.patientIdValueInput);
            int patientID =Integer.valueOf(patientIdValue.getText().toString());//patientIdValue

            EditText nurseIdW= (EditText)findViewById(R.id.nurseIdValueInput);
            int nurseId = Integer.valueOf(nurseIdW.getText().toString());


            EditText bplW= (EditText)findViewById(R.id.BPLValueInput);
            String bpl = bplW.getText().toString();


            EditText bphW= (EditText)findViewById(R.id.BPHValueInput);
            String bph = bphW.getText().toString();

            EditText tempW= (EditText)findViewById(R.id.temperatureValueInput);
            String temp = tempW.getText().toString();

            EditText hcgW= (EditText)findViewById(R.id.HCGValueInput);
            String hcg = hcgW.getText().toString();

            EditText bloodW= (EditText)findViewById(R.id.BloodTypeValueInput);
            String blood = bloodW.getText().toString();





//patientId , nurseId ,BPL ,BPH,temperature,HCG ,BloodType
            //adapter.insertTest(401,300,"10","20","36.5","69","D");

            adapter.insertTest(patientID,nurseId,bpl,bph,temp,hcg,blood);


           TextView added = (TextView)findViewById(R.id.added);
            added.setVisibility(View.VISIBLE);
           added.setTextColor(Color.RED);

        } catch (SQLException e) {
            e.printStackTrace();
            Log.e("error","add failed.");
        }

    }
}
