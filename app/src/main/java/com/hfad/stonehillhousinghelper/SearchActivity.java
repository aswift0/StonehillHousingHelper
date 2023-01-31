package com.hfad.stonehillhousinghelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
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


    public void searchPosts(View view,SQLiteDatabase dl, String gender, String username) {
        Spinner loc = (Spinner) findViewById(R.id.houses);
        String location = String.valueOf(loc.getSelectedItem());
        PostDatabaseHelper databaseHelper = new PostDatabaseHelper(this);

        String[] columns = {"_id","NAME","CLASS", "LOCATION", "GENDER","REQUEST"};

        String selection = "location=? and gender=?";
        String[] selectionArgs = {location, gender};
        Cursor cursor = dl.query("POSTS", columns, selection, selectionArgs, null, null, null);
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME", "CLASS", "REQUEST"}, new int[]{android.R.id.text1}, 0);
        ListView it = (ListView) findViewById(R.id.list_options);
        it.setAdapter(listAdapter);
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> it,
                                            View itemView,
                                            int position,
                                            long id) {
                        String[] columns = {"_id", "NAME", "CLASS", "LOCATION", "REQUEST", "CELLNUMBER", "DESCRIPTION"};
                        String selection = "location=? and gender=?";
                        String[] selectionArgs = {location, gender};
                        Cursor cursor = dl.query("POSTS", columns, selection, selectionArgs, null, null, null);
                        cursor.moveToFirst();
                        for (int i = 1; i < position; i++) {
                            cursor.moveToNext();
                        }
                        Intent intent = new Intent(SearchActivity.this, PostActivity.class);

                        String des = cursor.getString(6);
                        System.out.println(des);
                        String nu = cursor.getString(5);
                        System.out.println(nu);
                        String req = cursor.getString(4);
                        System.out.println(req);
                        String lo = cursor.getString(3);
                        System.out.println(lo);
                        String cla = cursor.getString(2);
                        System.out.println(cla);
                        String nam = cursor.getString(1);
                        System.out.println(nam);
                        intent.putExtra(PostActivity.EXTRA_USERN, username);
                        intent.putExtra(PostActivity.EXTRA_DESC, des);
                        intent.putExtra(PostActivity.EXTRA_CELLY, nu);
                        intent.putExtra(PostActivity.EXTRA_REQUEST, req);
                        intent.putExtra(PostActivity.EXTRA_LOCATION, lo);
                        intent.putExtra(PostActivity.EXTRA_USERCL, cla);
                        intent.putExtra(PostActivity.EXTRA_POSTUSERN, nam);
                        startActivity(intent);

                    }
                };
        it.setOnItemClickListener(itemClickListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SQLiteOpenHelper postDatabaseHelper = new PostDatabaseHelper(this); //activity.this
        username = (String) getIntent().getExtras().get(EXTRA_USERID);
       // System.out.println("Username is " + username);
        gender = (String) getIntent().getExtras().get(EXTRA_USERG);
       // System.out.println("Gender is " + gender);
        year = (String) getIntent().getExtras().get(EXTRA_USERC);
       // System.out.println("Year is " + year);
        namee = (String) getIntent().getExtras().get(EXTRA_USERN);
        System.out.println(EXTRA_USERN);
        try {
            db = postDatabaseHelper.getReadableDatabase();
        } catch(SQLiteException e) {
            Toast toast = makeText(this, "Database unavailable", LENGTH_SHORT);
            toast.show();
        }
        Button post = (Button) findViewById(R.id.getPosts);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPosts(v,db,gender,namee);
            }
        });
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