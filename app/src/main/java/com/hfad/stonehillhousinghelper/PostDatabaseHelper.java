package com.hfad.stonehillhousinghelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class PostDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "posts"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    PostDatabaseHelper(Context context){
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
    public boolean insertPost(SQLiteDatabase db, String name, String year, String location, String gender, String requesting, String cellNumber, String description){ //int resourceId) {
        ContentValues post = new ContentValues();
        post.put("NAME", name);
        post.put("CLASS", year);
        post.put("LOCATION", location);
        post.put("GENDER", gender);
        post.put("REQUEST", requesting);
        post.put("CELLNUMBER", cellNumber);
        post.put("DESCRIPTION", description);
        long insert = db.insert("POSTS", null, post);
        if(insert>0){
            return true;
        }
        return false;
    }
    public static boolean deletePost(SQLiteDatabase db, String name, String year, String location, String request, String cellnum, String desc){
        String[] columns = {"_id","NAME","CLASS", "LOCATION","REQUEST", "CELLNUMBER", "DESCRIPTION"};
        String selection = "name=? and class=? and location=? and request=? and cellnumber=? and description=?";
        String[] selectionArgs = {name, year,location,request,cellnum,desc};
        //Cursor cursor = db.query("POSTS", columns, selection, selectionArgs, null, null, null);
        //cursor.moveToFirst();

        return db.delete("POSTS",selection, selectionArgs)>0;
    }

    public static boolean deletePosts(SQLiteDatabase db, String name){
        String selection = "name=?";
        String[] selectionArgs = {name};
        //Cursor cursor = db.query("POSTS", columns, selection, selectionArgs, null, null, null);
        //cursor.moveToFirst();

        return db.delete("POSTS",selection, selectionArgs)>0;
    }


    public List<Posting> getPosts(SQLiteDatabase db, String location, String gender){
        List<Posting> returnPosts= new ArrayList<>();
        String[] columns = {"NAME","CLASS","LOCATION","GENDER","REQUEST","CELLNUMBER","DESCRIPTION"};

        String selection = "location=? and gender = ?";
        String[] selectionArgs = {location, gender};

        Cursor cursor = db.query("POSTS", columns, selection, selectionArgs, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Posting p = new Posting(cursor.getString(0), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(1));
                returnPosts.add(p);
            } while(cursor.moveToNext());
        }

        cursor.close();
        return returnPosts;
    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE POSTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "CLASS TEXT, "
                    + "LOCATION TEXT, "
                    + "GENDER TEXT, "
                    + "REQUEST TEXT, "
                    + "CELLNUMBER TEXT, "
                    + "DESCRIPTION TEXT);");
            insertPost(db, "Ashley Swift", "2023", "Pilgrim Heights","1", "7", "1112223334","Hi, looking for a group to live with!");
            insertPost(db, "Lisa Small", "2025", "Ohara Hall","1", "1", "1112223434","Hi, looking for a roommate to live with!");
            insertPost(db, "Mike Smith","2024", "Village Trailers","0", "2", "1112223734","Hi, looking for a group of 2 to live with!");
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE POSTS ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
