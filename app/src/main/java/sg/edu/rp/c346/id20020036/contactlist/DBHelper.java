package sg.edu.rp.c346.id20020036.contactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "CONTACTS";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MOBILE = "mobile";
    private static final String COLUMN_HOME = "home";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_INFO = "info";
    private static final String COLUMN_FAV = "false";

    public DBHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createContactTableSql = "CREATE TABLE " + TABLE_CONTACTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT, "
                + COLUMN_MOBILE + " TEXT, "
                + COLUMN_HOME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_GENDER + " TEXT, "
                + COLUMN_INFO + " TEXT, "
                + COLUMN_FAV + " TEXT )";

        db.execSQL(createContactTableSql);
        Log.i("info", createContactTableSql + "\ncreated tables");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public long insertContact(String name, String mobile, String home, String email, String address, String gender, String info, String fav) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_MOBILE, mobile);
        values.put(COLUMN_HOME, home);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_INFO, info);
        values.put(COLUMN_FAV, fav);
        // Insert the row into the TABLE_SONG
        long result = db.insert(TABLE_CONTACTS, null, values);
        // Close the database connection
        db.close();
        Log.d("SQL Insert","" + result);
        return result;
    }

    public ArrayList<Contacts> getAllContacts() {
        ArrayList<Contacts> booksList = new ArrayList<Contacts>();
        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + "," + COLUMN_MOBILE + ","
                + COLUMN_HOME + "," + COLUMN_EMAIL + ","
                + COLUMN_ADDRESS + "," + COLUMN_GENDER + ","
                + COLUMN_INFO + "," + COLUMN_FAV + " FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String mobile = cursor.getString(2);
                String home = cursor.getString(3);
                String email = cursor.getString(4);
                String address = cursor.getString(5);
                String gender = cursor.getString(6);
                String info = cursor.getString(7);
                String fav = cursor.getString(8);

                Contacts newContact = new Contacts(id, name, mobile, home, email, address, gender, info, fav);
                booksList.add(newContact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return booksList;
    }

    public ArrayList<Contacts> getAllContactsByFav(String fav) {
        ArrayList<Contacts> contactsList = new ArrayList<Contacts>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_NAME, COLUMN_MOBILE, COLUMN_HOME, COLUMN_EMAIL, COLUMN_ADDRESS, COLUMN_GENDER, COLUMN_INFO, COLUMN_FAV};
        String condition = COLUMN_FAV + "== ?";
        String[] args = {String.valueOf(fav)};


        Cursor cursor;
        cursor = db.query(TABLE_CONTACTS, columns, condition, args, null, null, null, null);

        // Loop through all rows and add to ArrayList
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String mobile = cursor.getString(2);
                String home = cursor.getString(3);
                String email = cursor.getString(4);
                String address = cursor.getString(5);
                String gender = cursor.getString(6);
                String info = cursor.getString(7);

                Contacts newContact = new Contacts(id, name, mobile, home, email, address, gender, info, fav);
                contactsList.add(newContact);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return contactsList;
    }



    public int updateContact(Contacts data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_MOBILE, data.getMobile());
        values.put(COLUMN_HOME, data.getHome());
        values.put(COLUMN_EMAIL, data.getEmail());
        values.put(COLUMN_ADDRESS, data.getAddress());
        values.put(COLUMN_GENDER, data.getGender());
        values.put(COLUMN_INFO, data.getInfo());
        values.put(COLUMN_FAV, data.getInfo());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_CONTACTS, values, condition, args);
        db.close();
        return result;
    }


    public int deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_CONTACTS, condition, args);
        db.close();
        return result;
    }
}
