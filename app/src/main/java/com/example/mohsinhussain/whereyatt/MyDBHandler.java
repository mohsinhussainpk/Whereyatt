package com.example.mohsinhussain.whereyatt;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.provider.ContactsContract;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "whereyatt.db";
    public static final String TABLE_NAME = "whereyatttable";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_LOCATION = "_location";
    public static final String COLUMN_CONTACTS_NAME = "contactname";
    public static final String COLUMN_CONTACTS_NUMBER = "_contactnumber";
    public static final String COLUMN_CATEGORY = "_category";
    public static final String COLUMN_SENDLOCATION = "_sendlocation";
    public static final String COLUMN_MAKECALL = "_makecall";
    public static final String COLUMN_MAKESMS = "_makesms";
    public static final String COLUMN_PURCHASED = "_purchased";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/*
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                 COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_LOCATION + " TontactsEXT, " +
                 COLUMN_CONTACTS_NAME + " TEXT, " +
                COLUMN_CONTACTS_NUMBER + " TEXT, " +
                COLUMN_MAKESMS + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_SENDLOCATION + " TEXT, " +
                COLUMN_MAKECALL + " TEXT, " +
                COLUMN_PURCHASED + " TEXT);";

        db.execSQL(query);
*/
        /*
        db.execSQL(
                "create table " + TABLE_NAME +
                        "(" +COLUMN_ID+ " integer primary key autoincrement, " + COLUMN_CONTACTS_NAME + " text)" );
*/
        db.execSQL(
                "create table whereyatttable " +
                        "(_id integer primary key autoincrement, _contactname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add a new row to the database

    public void addContact(Whereyatt whereyatt) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACTS_NAME, whereyatt.get_contactname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

/*
    public boolean insertContact (String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        db.insert("contacts", null, contentValues);
        return true;
    }
*/

    public void deleteContact(String contactsname) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_CONTACTS_NAME + "=\"" + contactsname + "\";" );
    }

    //Print out the database as a string

    public String databaseToString() {

        String dbString = " ";
        SQLiteDatabase db = getWritableDatabase();
       // String query = "SELECT "+ COLUMN_CONTACTS_NAME + " FROM " + TABLE_NAME + " WHERE 1 = Abu" ;
        String query = "SELECT  "+ COLUMN_CONTACTS_NAME + " FROM " + TABLE_NAME + " WHERE 1 = Abu" ;

        //Cursor point to a location in your results

      Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

       dbString = c.getString(2);
/*
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("contactname"))!= null){
              //  dbString += c.getString(c.getColumnIndex("contactsname"));
              //  dbString += "\n";

            }
        }
*/
        db.close();
        return dbString;

    }

}




