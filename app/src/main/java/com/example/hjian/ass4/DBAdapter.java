package com.example.hjian.ass4;

/**
 * Created by hjian on 2017-11-05.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBAdapter {
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String TAG2 = "DBAdapter";

    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE_NURSE = "nurses";
    static final String DATABASE_TABLE = "nurses";
    static final String DATABASE_TEST_TABLE = "tbl_tests";
    static final String DATABASE_PATIENT_TABLE = "tbl_patients";
    static final int DATABASE_VERSION = 10;
    private static final String TAG = "MyActivity";

    static final String DATABASE_CREATE =
            "create table contacts (_id integer primary key autoincrement, "
                    + "name text not null, email text not null);";

    private static final String DROP_NURSE_TABLE = "DROP TABLE IF  EXISTS tbl_nurses; ";
    private static final String DROP_DOC_TABLE = "DROP TABLE IF  EXISTS tbl_doctors; ";
    private static final String DROP_PATIENT_TABLE = "DROP TABLE IF  EXISTS tbl_patients; ";
    private static final String DROP_TEST_TABLE = "DROP TABLE IF  EXISTS tbl_tests; ";
    private static final String CREATE_NURSE_TABLE="CREATE TABLE IF NOT EXISTS tbl_nurses( nurseId INTEGER PRIMARY KEY , firstname TEXT, lastname TEXT,department TEXT,password TEXT );";
    private static final String CREATE_DOCTOR_TABLE="CREATE TABLE IF NOT EXISTS tbl_doctors( doctorId INTEGER PRIMARY KEY , firstname TEXT, lastname TEXT,department TEXT,password TEXT );";
    private static final String CREATE_PATIENT_TABLE="CREATE TABLE IF NOT EXISTS tbl_patients( patientId INTEGER PRIMARY KEY , firstname TEXT, lastname TEXT,department TEXT,doctorId INTEGER,room TEXT );";
    private static final String CREATE_TEST_TABLE="CREATE TABLE IF NOT EXISTS tbl_tests( testId INTEGER PRIMARY KEY,patientId INTEGER,nurseId INTEGER, BPL TEXT,BPH TEXT,temperature TEXT,HCG TEXT,BloodType,TEXT );";
    private static final String INSERT_NURSE_TABLE="INSERT INTO tbl_nurses( nurseId  , firstname , lastname ,department ,password  ) VALUES(300,'hao','jiang','a','123');";
    private static final String INSERT_DOCTOR_TABLE="INSERT INTO tbl_doctors( doctorId  , firstname , lastname ,department ,password  ) VALUES(200,'hao','jiang','a','123');";
    private static final String INSERT_PATIENT_TABLE2="INSERT INTO tbl_patients( patientId  , firstname , lastname ,department ,doctorId,room  ) VALUES(400,'ap','ta','b',200,'318');";
    private static final String INSERT_PATIENT_TABLE="INSERT INTO tbl_patients( patientId  , firstname , lastname ,department ,doctorId,room  ) VALUES(401,'ap','ta','c',200,'319');";
    private static final String INSERT_TEST_TABLE="INSERT INTO tbl_tests( testId  , patientId , nurseId ,BPL ,BPH,temperature,HCG ,BloodType ) VALUES(500,400,300,'10','20','37.5','6','A');";
    private static final String INSERT_TEST_TABLE2="INSERT INTO tbl_tests( testId  , patientId , nurseId ,BPL ,BPH,temperature,HCG ,BloodType ) VALUES(501,401,300,'10','20','36.5','69','B');";
    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }

    public SQLiteDatabase getDb()
    {
        return db;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {



        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
//                db= openOrCreateDatabase(
//                        "my_sqlite_database.db",
//                        SQLiteDatabase.CREATE_IF_NECESSARY,
//                        null);



               // db = getWritableDatabase();
                db.execSQL(DROP_NURSE_TABLE);
                db.execSQL(CREATE_NURSE_TABLE);
                db.execSQL(INSERT_NURSE_TABLE);
//
                db.execSQL(DROP_DOC_TABLE);
                db.execSQL(CREATE_DOCTOR_TABLE);
                db.execSQL(INSERT_DOCTOR_TABLE);

                db.execSQL(DROP_PATIENT_TABLE);
                db.execSQL(CREATE_PATIENT_TABLE);
                db.execSQL(INSERT_PATIENT_TABLE);
                db.execSQL(INSERT_PATIENT_TABLE2);

                db.execSQL(DROP_TEST_TABLE);
                db.execSQL(CREATE_TEST_TABLE);
                db.execSQL(INSERT_TEST_TABLE);
                db.execSQL(INSERT_TEST_TABLE2);

                Log.i(TAG2,"called");
                //db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    //patientId , nurseId ,BPL ,BPH,temperature,HCG ,BloodType
    public long insertTest(int patientId,int nurseId,String BPL, String BPH,String temperature,String HCG,String BloodType)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("patientId", patientId);
        initialValues.put("nurseId", nurseId);
        initialValues.put("BPL", BPL);
        initialValues.put("BPH", BPH);
        initialValues.put("temperature", temperature);
        initialValues.put("HCG", HCG);
        initialValues.put("BloodType", BloodType);

        return db.insert(DATABASE_TEST_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TEST_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllTests()
    {
        return db.query(DATABASE_TEST_TABLE, new String[] {"testId", "patientId"
                }, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getTest(int rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TEST_TABLE, new String[] {"testId", "patientId","nurseId" ,"BPL" ,"BPH","temperature","HCG" ,"BloodType"
                        }, "testId" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getPatient(int rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_PATIENT_TABLE, new String[] { "patientId","firstname" , "lastname" ,"department" ,"doctorId","room"
                        }, "patientId" + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updatePatient(long rowId, String firstname, String lastname,String department,int doctorId,String room)
    {
        ContentValues args = new ContentValues();
        args.put("firstname", firstname);
        args.put("lastname", lastname);
        args.put("department", department);
        args.put("doctorId", doctorId);
        args.put("room", room);
        return db.update(DATABASE_PATIENT_TABLE, args, "patientId" + "=" + rowId, null) > 0;
    }

}

