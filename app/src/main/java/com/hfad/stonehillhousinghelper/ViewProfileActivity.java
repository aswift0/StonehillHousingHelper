package com.hfad.stonehillhousinghelper;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ViewProfileActivity extends AppCompatActivity
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
    private SQLiteDatabase db2;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        SQLiteOpenHelper userDatabaseHelper = new UserDatabaseHelper(this); //activity.this
        SQLiteOpenHelper postDatabaseHelper = new PostDatabaseHelper(this); //activity.this
        username = (String) getIntent().getExtras().get(EXTRA_USERID);
        gender = (String) getIntent().getExtras().get(EXTRA_USERG);
        year = (String) getIntent().getExtras().get(EXTRA_USERC);
        namee = (String) getIntent().getExtras().get(EXTRA_USERN);
        try {
            db = postDatabaseHelper.getReadableDatabase();
            db2 = userDatabaseHelper.getReadableDatabase();
        } catch(SQLiteException e) {
            Toast toast = makeText(this, "Database unavailable", LENGTH_SHORT);
            toast.show();
        }
        TextView cuser = (TextView) findViewById(R.id.myUsername);
        TextView cyear = (TextView) findViewById(R.id.myYear);
        TextView cname = (TextView) findViewById(R.id.myName);
        cname.setText(namee);
        cuser.setText(username);
        cyear.setText(year);
        Button delete = (Button) findViewById(R.id.deleteUser);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDatabaseHelper p = new PostDatabaseHelper(ViewProfileActivity.this);
                UserDatabaseHelper l = new UserDatabaseHelper(ViewProfileActivity.this);
                if(p.deletePosts(db, namee)&& l.deleteUser(db2,username))
                {
                    Toast toast = makeText(ViewProfileActivity.this, "User Deleted", LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(ViewProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
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