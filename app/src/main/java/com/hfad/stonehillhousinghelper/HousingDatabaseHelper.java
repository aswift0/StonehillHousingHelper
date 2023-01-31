package com.hfad.stonehillhousinghelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

class HousingDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "houses"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    HousingDatabaseHelper(Context context){
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
    public boolean insertHouse(SQLiteDatabase db, String name, String description, String resourceid){ //int resourceId) {
        ContentValues post = new ContentValues();
        post.put("NAME", name);
        post.put("DESCRIPTION", description);
        post.put("RESOURCEID", resourceid);
        long insert = db.insert("HOUSES", null, post);
        if(insert>0){
            return true;
        }
        return false;
    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE HOUSES (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "RESOURCEID TEXT);");
            insertHouse(db, "Ohara Hall", " A traditional corridor-style hall, O'Hara houses approximately 250 first year residents and is co-ed by wing . Most rooms in O'Hara are double occupancy. O'Hara features a large common lounge at the front the building, two kitchens, a laundry room and a basement lounge.", String.valueOf(R.drawable.ohara));
            insertHouse(db, "Boland Hall", "A traditional corridor-style hall, Boland primarily houses first year students and sophomores and is co-ed by floor with three floors of women and one floor of men. Although there are a few natural triple rooms on each floor, most rooms are double occupancy. Boland Hall features two main programming lounges, a common area kitchen, laundry room and a few small study rooms throughout the building.", String.valueOf(R.drawable.boland));
            insertHouse(db, "Colonial Courts","Colonial Court is one of two townhouse style residence areas on campus for upperclass students. Each of the 10 townhouses in Colonial Court feature a common lounge. kitchen and laundry room on the first floor.", String.valueOf(R.drawable.colonial));
            insertHouse(db, "Commonwealth Courts", "Commonwealth Court is one of two townhouse style residence areas on campus for upperclass students. Each of the 10 townhouses in Commonwealth Court feature a common lounge. kitchen and laundry room on the first floor. Each townhouse also features an outdoor brick patio with picnic tables and outdoor grills to help foster a greater sense of community.", String.valueOf(R.drawable.commonwealth));
            insertHouse(db, "Corr Hall", "Corr Hall is a traditional corridor-style hall that houses first year students and sophomores and is co-ed by wing . Corr Hall features a main lounge for student programming, a common area kitchen, laundry room, and multiple study lounges.",String.valueOf(R.drawable.corr));
            insertHouse(db, "Holy Cross Center","Affectionately referred to as \"The Sem\" because it was formerly used as a seminary, the Holy Cross Center now houses approximately 160 first year students. Located near the college's Route 138 entrance, the Holy Cross Center is co-ed by floor. \"The Sem\" has its own dining hall, a large study room known as \"The Library,\" a common area lounge, a laundry room and a full kitchen. ", String.valueOf(R.drawable.holycross));
            insertHouse(db, "New Hall","New Hall is an senior/junior suite style hall with 31 suites housing 6-10 students each. Most suites feature a common area living room, two full bathrooms and a well-appointed kitchen. New Hall also features two laundry rooms, several quiet study rooms, a game room, a common-area kitchen, chapel for Mass/quiet reflection and a main programming lounge on the first floor.",String.valueOf(R.drawable.newhall));
            insertHouse(db, "Notre Dame du Lac", "Notre Dame du Lac (or du Lac for short) is a suite style hall with eight suites housing 14 to 15 students. Each single-gender suite features a common area lounge, bathroom and kitchenette. du Lac also features two laundry rooms, four study rooms, a main programming lounge and an outdoor seating area adjacent to the main lobby.", String.valueOf(R.drawable.dulac));
            insertHouse(db, "Village Trailers","Each house contains four double bedrooms, two full bathrooms, a stacked washer/dryer, a living room, and an eat-in kitchen. Each house is single gender.",String.valueOf(R.drawable.village));
            insertHouse(db, "Pilgrim Heights", "Houses sophomore and junior students in five different buildings. All five buildings are suite style and feature either 5 or 7 bedrooms around a common area living room and bathroom. In addition, each building features its own laundry room and kitchen.",String.valueOf(R.drawable.pilgrim));
            insertHouse(db, "Villa Theresa", "Villa Theresa Hall is a traditional corridor-style hall that houses first year and sophomore students and is co-ed by wing. Villa Theresa features a main lounge for student programming, a common area kitchen, laundry room, and multiple study lounges.",String.valueOf(R.drawable.villa));
            insertHouse(db, "No Preference", "Any of the Stonehill Residence halls.",String.valueOf(R.drawable.logo));
            insertHouse(db, "Off-Campus", "Somewhere off campus, not associated with Stonehill",String.valueOf(R.drawable.logo));
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE HOUSES ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}