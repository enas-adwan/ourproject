package com.example.eodwan.addrecipe;

/**
 * Created by eodwan on 3‏/9‏/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;
import android.database.Cursor;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food";
    private SQLiteDatabase sqliteDBInstance = null;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
		/*
		 * Create the employee table and populate it with sample data.
		 * In step 6, we will move these hardcoded statements to an XML document.
		 */
     //   db.execSQL("DROP TABLE IF EXISTS food");
        String sql = "CREATE TABLE IF NOT EXISTS food (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
               "name TEXT, " +

               "noId INTEGER)";
        db.execSQL(sql);


    }
    boolean isTableExists( String tableName)
    {  SQLiteDatabase db = this.getWritableDatabase();
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[] {"table", tableName});
        if (!cursor.moveToFirst())
        {
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }
    public Integer findid(String s) {

        String rrr="n";
        Integer rr=0;
        String mm;
        SQLiteDatabase db = this.getWritableDatabase();

        String selectStmt = "select * from food where name='"+s+"'";

        Cursor cursor = db.rawQuery(selectStmt, null);

        if (cursor.moveToFirst()) {



            rrr= cursor.getString(1);
            rr=     cursor.getInt(2);


        }
        mm=rr.toString();
        return rr;


    }
    public void addContact(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("DROP TABLE IF EXISTS food");
        String sql = "CREATE TABLE IF NOT EXISTS food (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +

                "noId INTEGER)";
        db.execSQL(sql);


        ContentValues values = new ContentValues();
        values.put("name", record.getName());
        values.put("noId", record.getNoid());


        db.insert("food", null, values);
        db.close();
    }

    public List<Record> getAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Record> contactlist = new ArrayList<Record>();
        String selectStmt = "select * from food ";

        Cursor cursor = db.rawQuery(selectStmt, null);

        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId(cursor.getInt(0));
                record.setName(cursor.getString(1));
                record.setNoid(cursor.getInt(2));
                contactlist.add(record);
            } while (cursor.moveToNext());
        }
        return contactlist;
    }


    public String[] SelectAllData(String searchTerm) {

        // TODO Auto-generated method stub

        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data



            String strSQL = "SELECT name FROM food ";
            strSQL += " WHERE name LIKE '"+searchTerm+"%'";
            strSQL +=  " ORDER BY _id DESC ";
            Cursor cursor = db.rawQuery(strSQL, null);

            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    /***
                     *  [x] = Name
                     */
                    int i= 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());

                }
            }
            cursor.close();

            return arrData;

        } catch (Exception e) {
            return null;
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS food");
        onCreate(db);
    }
    @Override

    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

}