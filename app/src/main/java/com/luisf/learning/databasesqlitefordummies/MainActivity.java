package com.luisf.learning.databasesqlitefordummies;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase appDatabase;
    private final String APP_DB = "dbSQLiteApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Database SQLite, For dummies");

        //because work with of database
        try {
            //Create local database
            appDatabase = openOrCreateDatabase(APP_DB, MODE_PRIVATE, null);

            //Create an table into opened database
            appDatabase.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INT(3));");

            //remove the table and all your data
            //appDatabase.execSQL("DROP TABLE pessoas;");

            //Insert data into table...
            Boolean lInsertOnlyFirstExec = false;
            //lInsertOnlyFirstExec = true; // only first execution...
            if (lInsertOnlyFirstExec) {
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Nehemiah Roach\", 30);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Colt Villarreal\", 17);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Roberto Chen\", 23);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Taryn Livingston\", 19);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Moriah Robertson\", 7);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Mercedes Alexander\", 36);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Bobby Summers\", 51);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Leroy Blackwell\", 28);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Devon Meadows\", 53);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Isabela Shelton\", 32);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Ariana Chan\", 45);");
                appDatabase.execSQL("INSERT INTO pessoas (name, age) VALUES (\"Gunner Christian\", 30);");
            }

            //To get all the data from database
            //Cursor cursorDatabase = appDatabase.rawQuery("SELECT id, name, age FROM pessoas WHERE age BETWEEN 1 AND 120;", null);
            Cursor cursorDatabase = appDatabase.rawQuery("SELECT id, name, age " +
                    "FROM pessoas " +
                    "WHERE (name LIKE '%a%') " +
                    "AND (age BETWEEN 1 AND 120)" +
                    "LIMIT 1000;", null); //Some commands are as an example

            //Getting of the columns index
            int iDatabaseField_id, iDatabaseField_name, iDatabaseField_age;

            iDatabaseField_id = cursorDatabase.getColumnIndex("id");
            iDatabaseField_name = cursorDatabase.getColumnIndex("name");
            iDatabaseField_age = cursorDatabase.getColumnIndex("age");

            //searching all data into database. It could also be a method, because use of try catch, :)
            cursorDatabase.moveToFirst();
            if (cursorDatabase != null) {
                for (int iPosition = cursorDatabase.getPosition(); iPosition < cursorDatabase.getCount(); iPosition++) {
                    String sId = cursorDatabase.getString(iDatabaseField_id);
                    String sName = cursorDatabase.getString(iDatabaseField_name);
                    String sAge = cursorDatabase.getString(iDatabaseField_age);

                    //out records for viewing into log
                    Log.i("Database Result", String.valueOf(cursorDatabase.getPosition()) + " --> " + sId + " | Name: " + sName + " | Age: " + sAge);

                    //move to next record
                    cursorDatabase.moveToNext();
                }
            }

            TextView textResult = findViewById(R.id.textResult);
            textResult.setText("Success\nSee your log 😉");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}