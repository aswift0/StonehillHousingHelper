package com.hfad.stonehillhousinghelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class UserDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "users"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    UserDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }
    public boolean insertUser(SQLiteDatabase db, String username,
                                   String password, String name, String classyear, String gender){ //int resourceId) {
        ContentValues user1 = new ContentValues();
        user1.put("USERNAME", username);
        user1.put("PASSWORD", password);
        user1.put("CLASS", classyear);
        user1.put("NAME", name);
        user1.put("GENDER", gender);
        long insert = db.insert("USER", null, user1);
        if(insert>0){
            return true;
        }
        return false;
    }

    public boolean deleteUser(SQLiteDatabase db, String username){
        String[] columns = {"_id","USERNAME"};
        String selection = "username=?";
        String[] selectionArgs = {username};

        return db.delete("USER",selection, selectionArgs)>0;
    }



    public boolean checkUserExist(SQLiteDatabase db, String username, String password){
       /*
        Cursor cursor = db.query("USER",
                new String[] {"USERNAME"},
                "USERNAME = ?",
                new String[] {username},
                null, null, null);
        //finish
        /*
        cursor.moveToFirst();
        int num = cursor.getPosition();
        cursor = db.query("USER",
                new String[] {"PASSWORD"},
                "PASSWORD = ?",
                new String[] {username},
                null, null, null);

        Cursor cursor = db.query("USER",
                new String[] {"_id","USERNAME", "PASSWORD"},
                null, null, null, null, null);
        cursor.moveToFirst();
        do {
            System.out.println(cursor.getString(0));
            System.out.println(cursor.getString(1));
        }while(cursor.moveToNext());

        int count = cursor.getCount();
        cursor.close();
        close();

        if(count > 0){
            return true;
        } else {
            return false;
        }

        */
          String[] columns = {"USERNAME"};

        String selection = "username=? and password = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query("USER", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        System.out.println(count);
        if(count > 0){
            return true;
        } else {
            return false;
        }


    }

    public String getUserGender(SQLiteDatabase db, String username, String password){
        String[] columns = {"USERNAME", "PASSWORD", "CLASS", "NAME", "GENDER"};

        String selection = "username=? and password=?";
        String[] selectionArgs = {username,password};

        Cursor cursor = db.query("USER", columns, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        String gen = cursor.getString(4);
        cursor.close();
        return gen;
    }

    public String getName(SQLiteDatabase db, String username, String password){
        String[] columns = {"USERNAME", "PASSWORD", "CLASS", "NAME", "GENDER"};

        String selection = "username=? and password=?";
        String[] selectionArgs = {username,password};

        Cursor cursor = db.query("USER", columns, selection, selectionArgs, null, null, null);
        System.out.println(cursor.getCount());
        cursor.moveToFirst();
        String gen = cursor.getString(3);
        cursor.close();
        return gen;
    }
    public String getUserClass(SQLiteDatabase db, String username, String password){
        String[] columns = {"USERNAME", "PASSWORD", "CLASS", "NAME", "GENDER"};

        String selection = "username=? and password=?";
        String[] selectionArgs = {username,password};

        Cursor cursor = db.query("USER", columns, selection, selectionArgs, null, null, null);
        System.out.println(cursor.getCount());
        cursor.moveToFirst();
        String gen = cursor.getString(2);
        cursor.close();
        return gen;
    }

    public int getProfiles(SQLiteDatabase db){
        String[] columns = {"USERNAME"};

        String selection = "username=?";
        Cursor cursor = db.query("USER",
                new String[] {"_id","USERNAME"},
                null, null, null, null, null);
        int num = cursor.getCount();
        cursor.close();
        close();
        return num;
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE USER (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "USERNAME TEXT, "
                    + "PASSWORD TEXT, "
                    + "CLASS TEXT, "
                    + "NAME TEXT, "
                    + "GENDER TEXT);");
            //populAte
            insertUser(db,"ashleySwift", "bruin32", "Ashley Swift","2023", "1");
            insertUser(db,"lisaSmall", "password1", "Lisa Small", "2025", "1");
            insertUser(db,"mikeSmith", "password", "Mike Smith", "2024", "0");
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE USER ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
    /*
    private static void populate(SQLiteDatabase db){
        ContentValues user1 = new ContentValues();
        user1.put("USERNAME", "ashleySwift");
        user1.put("PASSWORD", "bruin32");
        user1.put("CLASS", 2023);
        user1.put("NAME", "Ashley Swift");
        user1.put("GENDER", 1);
        db.insert("USER", null, user1);
        ContentValues user2 = new ContentValues();
        user2.put("USERNAME", "mikeSmith");
        user2.put("PASSWORD", "password");
        user2.put("CLASS", 2024);
        user2.put("NAME", "Mike Smith");
        user2.put("GENDER", 0);
        db.insert("USER", null, user2);
        ContentValues user3 = new ContentValues();
        user3.put("USERNAME", "lisaSmall");
        user3.put("PASSWORD", "password2");
        user3.put("CLASS", 2025);
        user3.put("NAME", "Lisa Small");
        user3.put("GENDER", 1);
        db.insert("USER", null, user3);
    } */
