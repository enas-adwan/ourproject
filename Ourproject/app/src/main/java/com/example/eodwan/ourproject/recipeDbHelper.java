package com.example.eodwan.ourproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by hp on 03/09/2016.
 */
public class recipeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="project";
    private static final int DATABASE_VERSION=5;
    private static final String CREATE_QUERY="CREATE TABLE "+ ShowCalory.NewrecipeInfo.TABLE_NAME+"("+
            ShowCalory.NewrecipeInfo.recipe_calory+" FLOAT );";


    public recipeDbHelper(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATION", "Database created / opened.....");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATION", "Table create..."+CREATE_QUERY);
    }
    public void addinnformation(Float caloryNum,SQLiteDatabase db){

        ContentValues contentValue = new ContentValues();
        contentValue.put(ShowCalory.NewrecipeInfo.recipe_calory,caloryNum);
        db.insert(ShowCalory.NewrecipeInfo.TABLE_NAME,null,contentValue);
        Log.e("DATABASE OPERATION", "One row is insert");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getinformation(SQLiteDatabase db){
        Cursor cursor;
        String[] projections={ ShowCalory.NewrecipeInfo.recipe_calory};
        cursor= db.query(ShowCalory.NewrecipeInfo.TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public float getTotal(SQLiteDatabase db) {

        Cursor cursor = db.rawQuery(
                "SELECT SUM(calory) FROM project", null);
        if(cursor.moveToFirst()) {
            return cursor.getFloat(0);
        }
        return cursor.getFloat(0);

    }

}
