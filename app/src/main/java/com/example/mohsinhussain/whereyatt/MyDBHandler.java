package com.example.mohsinhussain.whereyatt;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "name.db";
    public static final String TABLE_NAME = "nameof";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTACTS_NAME = "contactname";
    public static final String COLUMN_CONTACTS_NUMBER = "contactnumber";
    public static final String COLUMN_PURCHASED = "purchased";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_CONTACTS_NAME + " TEXT," +
                COLUMN_CONTACTS_NUMBER + " TEXT)";

        db.execSQL(query);

        /*
        db.execSQL(
                "create table " + TABLE_NAME +
                        "(" +COLUMN_ID+ " integer primary key autoincrement, " + COLUMN_CONTACTS_NAME + " text)" );
*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add a new row to the database

    // code to add the new contact
    void addContact(Whereyatt whereyatt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTACTS_NAME, whereyatt.get_contactname()); // Contact Name
        values.put(COLUMN_CONTACTS_NUMBER, whereyatt.get_contactnumber()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public void removeSingleContact(String title) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_CONTACTS_NAME + "= '" + title + "'");

        //Close the database
        database.close();
    }

    public List<Whereyatt> getAllContacts() {
        List<Whereyatt> contactList = new ArrayList<Whereyatt>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Whereyatt whereyatt = new Whereyatt();
                whereyatt.set_id(Integer.parseInt(cursor.getString(0)));
                whereyatt.set_contactname(cursor.getString(1));
                whereyatt.set_contactnumber(cursor.getString(2));
                //contact.setID(Integer.parseInt(cursor.getString(0)));
             //   contact.setName(cursor.getString(1));
             //   contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(whereyatt);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteContact(Whereyatt whereyatt) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_CONTACTS_NAME + " = ?",
                new String[] { String.valueOf(whereyatt.get_id())});
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

    public void deleteContact(String contactid) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=\"" + contactid + "\";" );
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




