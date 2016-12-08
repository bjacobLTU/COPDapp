package edu.ltu.copdapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "symptomDB.db";
    public static final String TABLE_SYMPTOMS = "symptoms";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SYMPTOMNAME = "symptomname";


    public ListDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_SYMPTOMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SYMPTOMNAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOMS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addSymptom(Symptoms symptom){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SYMPTOMNAME, symptom.get_symptomname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SYMPTOMS, null, values);
        db.close();
    }
    //Delete a symptom from the database
    public void deleteSymptom(String symptomNum){
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM " + TABLE_SYMPTOMS + " WHERE " + COLUMN_SYMPTOMNAME + "=\"" + symptomName + "\";");
        db.execSQL("DELETE FROM " + TABLE_SYMPTOMS + " WHERE " + COLUMN_ID + "=\"" + symptomNum + "\";");
    }

    //Print out the database as a string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SYMPTOMS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);

        //Move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("_id")) != null){
                dbString += c.getString(c.getColumnIndex("_id"));
                dbString += " ";
            }
            if (c.getString(c.getColumnIndex("symptomname")) != null){
                dbString += c.getString(c.getColumnIndex("symptomname"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}

