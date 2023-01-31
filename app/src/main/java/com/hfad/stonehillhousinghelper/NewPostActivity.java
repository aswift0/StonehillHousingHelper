package com.hfad.stonehillhousinghelper;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class NewPostActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    public static final String EXTRA_USERID= "user";
    public static final String EXTRA_USERG= "G";
    public static final String EXTRA_USERC= "C";
    public static final String EXTRA_USERN= "N";

    public static String username= "user";
    public static String gender= "G";
    public static String year= "C";
    public static String namee= "N";
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        namee = (String) getIntent().getExtras().get(EXTRA_USERN);
        gender = (String) getIntent().getExtras().get(EXTRA_USERG);
        year = (String) getIntent().getExtras().get(EXTRA_USERC);
        username = (String) getIntent().getExtras().get(EXTRA_USERID);
            SQLiteOpenHelper postDatabaseHelper =  new PostDatabaseHelper(this);
            try {
                db = postDatabaseHelper.getWritableDatabase();
                Button submit = (Button) findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createPost(v,db,namee,gender,year,username);
                    }
                });
            } catch(SQLiteException e) {
                Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar, R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    public void createPost(View view, SQLiteDatabase db, String name, String gender, String cyear, String uid) {
        EditText num = (EditText) findViewById(R.id.phone);
        EditText des = (EditText) findViewById(R.id.descript);
        Spinner loc = (Spinner) findViewById(R.id.place);
        Spinner req = (Spinner) findViewById(R.id.requesting);
        String pnum = num.getText().toString();
        System.out.println(pnum);
        String desc = des.getText().toString();
        System.out.println(desc);
        String loca = String.valueOf(loc.getSelectedItem());
        System.out.println(loca);
        String reqe = String.valueOf(req.getSelectedItem());
        System.out.println(reqe);
        PostDatabaseHelper postDatabaseHelper = new PostDatabaseHelper(this);
        if((!pnum.isEmpty())&&(!desc.isEmpty())&&(!loca.isEmpty())&&(!reqe.isEmpty())) {
            boolean t = postDatabaseHelper.insertPost(db, name, cyear, loca, gender, reqe,pnum,desc);
            if (t) {
                Intent intent = new Intent(this,
                        SearchActivity.class);
                intent.putExtra(SearchActivity.EXTRA_USERID, uid);
                intent.putExtra(SearchActivity.EXTRA_USERG, gender);
                intent.putExtra(SearchActivity.EXTRA_USERN, name);
                intent.putExtra(SearchActivity.EXTRA_USERC, cyear);
                startActivity(intent);
            }
        }
        else{
            System.out.println("POST NOT MADE");
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch(id){
            case R.id.nav_search_posts:
                intent = new Intent(this, SearchActivity.class);
                intent.putExtra(SearchActivity.EXTRA_USERID, username);
                intent.putExtra(SearchActivity.EXTRA_USERG, gender);
                intent.putExtra(SearchActivity.EXTRA_USERC, year);
                intent.putExtra(SearchActivity.EXTRA_USERN, namee);
                break;
            case R.id.nav_profile:
                intent = new Intent(this, ViewProfileActivity.class);
                intent.putExtra(ViewProfileActivity.EXTRA_USERID, username);
                intent.putExtra(ViewProfileActivity.EXTRA_USERG, gender);
                intent.putExtra(ViewProfileActivity.EXTRA_USERC, year);
                intent.putExtra(ViewProfileActivity.EXTRA_USERN, namee);
                break;
            case R.id.nav_post:
                intent = new Intent(this, NewPostActivity.class);
                intent.putExtra(NewPostActivity.EXTRA_USERID, username);
                intent.putExtra(NewPostActivity.EXTRA_USERG, gender);
                intent.putExtra(NewPostActivity.EXTRA_USERC, year);
                intent.putExtra(NewPostActivity.EXTRA_USERN, namee);
                break;
            case R.id.nav_search_houses:
                intent = new Intent(this, HousingSearchActivity.class);
                intent.putExtra(HousingSearchActivity.EXTRA_USERID, username);
                intent.putExtra(HousingSearchActivity.EXTRA_USERG, gender);
                intent.putExtra(HousingSearchActivity.EXTRA_USERC, year);
                intent.putExtra(HousingSearchActivity.EXTRA_USERN, namee);
                break;
            case R.id.nav_logout:
                intent = new Intent(this, LoginActivity.class);
                break;
            default:
        }
        startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}