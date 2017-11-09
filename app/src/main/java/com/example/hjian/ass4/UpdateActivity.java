package com.example.hjian.ass4;

import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {
    DBAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        adapter = MyApplication.getDbAdapter();
        adapter.open();
        String patientID = getIntent().getStringExtra("patientID");
        Log.v("patientID",patientID);
//
        Cursor c = adapter.getPatient(Integer.valueOf(patientID));
        if (c.moveToFirst())
        {
            do {


                TextView patientIdValue= (TextView)findViewById(R.id.patientIDUpdateValue);
                patientIdValue.setText(c.getString(0).toString());
                patientIdValue.setTextColor(Color.BLUE);

                EditText firstNameValue= (EditText)findViewById(R.id.firstNameValue);
                firstNameValue.setText(c.getString(1).toString());
                firstNameValue.setTextColor(Color.CYAN);

                EditText lastnameValue= (EditText)findViewById(R.id.lastnameValue);
                lastnameValue.setText(c.getString(2).toString());
                lastnameValue.setTextColor(Color.CYAN);

                EditText departmentValue= (EditText)findViewById(R.id.departmentValue);
                departmentValue.setText(c.getString(3).toString());
                departmentValue.setTextColor(Color.CYAN);

                EditText doctorValue= (EditText)findViewById(R.id.doctorValue);
                doctorValue.setText(c.getString(4).toString());
                doctorValue.setTextColor(Color.CYAN);

                EditText roomValue= (EditText)findViewById(R.id.roomValue);
                roomValue.setText(c.getString(5).toString());
                roomValue.setTextColor(Color.CYAN);




            } while (c.moveToNext());
        }
        c.close();
   }

    public void updatePatient(View v)
    {
        try
        {
             TextView patientIdValue= (TextView)findViewById(R.id.patientIDUpdateValue);
            int patientID =Integer.valueOf(patientIdValue.getText().toString());//patientIdValue
            EditText lastnameW= (EditText)findViewById(R.id.lastnameValue);
            String lastname = lastnameW.getText().toString();
            EditText firstnameW= (EditText)findViewById(R.id.firstNameValue);
            String firstname = firstnameW.getText().toString();
            EditText departmentW= (EditText)findViewById(R.id.departmentValue);
            String department = departmentW.getText().toString();
            EditText doctorW= (EditText)findViewById(R.id.doctorValue);
            int doctorID = Integer.valueOf(doctorW.getText().toString());
            EditText roomW= (EditText)findViewById(R.id.roomValue);
            String room = roomW.getText().toString();
//patientId , nurseId ,BPL ,BPH,temperature,HCG ,BloodType
            //adapter.insertTest(401,300,"10","20","36.5","69","D");

            adapter.updatePatient(patientID,lastname,firstname,department,doctorID,room);
            TextView updated = (TextView)findViewById(R.id.updated);
            updated.setVisibility(View.VISIBLE);
            updated.setTextColor(Color.RED);

        } catch (SQLException e) {
        e.printStackTrace();
            Log.e("error","update failed.");
    }
    }

}
