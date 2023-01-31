package com.hfad.stonehillhousinghelper;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class PostActivity<pubic> extends AppCompatActivity {
    public static final String EXTRA_POSTUSERN = "C";
    public static final String EXTRA_LOCATION = "M";
    public static final String EXTRA_REQUEST = "R";
    public static final String EXTRA_USERCL = "ui";
    public static final String EXTRA_CELLY = "nuuu";
    public static final String EXTRA_DESC = "D";
    public static final String EXTRA_USERN = "n";

    private SQLiteDatabase db;
    private Cursor cursor;

/*
    public void getPost(View view,SQLiteDatabase dl, String uid) {
        TextView celln = (TextView) findViewById(R.id.cell);
        TextView cyear = (TextView) findViewById(R.id.classYear);
        TextView loc = (TextView) findViewById(R.id.location);
        TextView des = (TextView) findViewById(R.id.description);
        TextView name = (TextView) findViewById(R.id.title);
        TextView re = (TextView) findViewById(R.id.request);
        String[] columns = {"_id","NAME","CLASS", "LOCATION", "REQUEST","CELLNUMBER","DESCRIPTION"};

        String selection = "_id=?";
        String[] selectionArgs = {uid};
        Cursor cursor = dl.query("POSTS", columns, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        name.setText(cursor.getString(1));
        cyear.setText(cursor.getString(2));
        loc.setText(cursor.getString(3));
        re.setText(cursor.getString(4));
        celln.setText(cursor.getString(5));
        des.setText(cursor.getString(6));
    }

 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        SQLiteOpenHelper postDatabaseHelper = new PostDatabaseHelper(this); //activity.this
        String n = (String) getIntent().getExtras().get(EXTRA_USERN);
        String disc = (String) getIntent().getExtras().get(EXTRA_DESC);
        String theCell = (String) getIntent().getExtras().get(EXTRA_CELLY);
        String theYear = (String) getIntent().getExtras().get(EXTRA_USERCL);
        String req = (String) getIntent().getExtras().get(EXTRA_REQUEST);
        String loch = (String) getIntent().getExtras().get(EXTRA_LOCATION);
        String un = (String) getIntent().getExtras().get(EXTRA_POSTUSERN);
        System.out.println(loch);
        System.out.println(req);
        System.out.println(theCell);
        System.out.println(theYear);
        System.out.println(disc);
        System.out.println(un);
        try {
            db = postDatabaseHelper.getReadableDatabase();
        } catch(SQLiteException e) {
            Toast toast = makeText(this, "Database unavailable", LENGTH_SHORT);
            toast.show();
        }
        TextView celln = (TextView) findViewById(R.id.cell);
        TextView cyear = (TextView) findViewById(R.id.classYear);
        TextView loc = (TextView) findViewById(R.id.location);
        TextView des = (TextView) findViewById(R.id.description);
        TextView name = (TextView) findViewById(R.id.title);
        TextView re = (TextView) findViewById(R.id.request);
        name.setText(un); // these are class...
        //System.out.println(name);
        cyear.setText("Class of "+theYear);
        celln.setText("#" + theCell);
        //System.out.println(uc);
        // these arent right. for tomorrow- fix this, add a toolbar to navigate, and add some features like view
        //profile,  and other house info
        loc.setText(loch);
        //System.out.println(loch);
        re.setText("Requesting: " + req);
        //System.out.println(req);
        //System.out.println(celly);
        des.setText(disc);
        //System.out.println(disc);

        if(n.equals(un)){
            Button delete = (Button) findViewById(R.id.delete);
            delete.setVisibility(View.VISIBLE);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostDatabaseHelper p = new PostDatabaseHelper(PostActivity.this);
                    if(p.deletePost(db, un, theYear,loch,req, theCell, disc))
                    {
                        Toast toast = makeText(PostActivity.this, "Post Deleted", LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }

         /*
        String[] columns = {"_id","NAME","CLASS", "LOCATION", "REQUEST","CELLNUMBER","DESCRIPTION"};

        String selection = "_id=?";
        String[] selectionArgs = {Integer.toString(uid)};
        System.out.println(namee);
        System.out.println(uid);
        Cursor cursor = db.query("POSTS", columns, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        name.setText(cursor.getString(1));
        cyear.setText(cursor.getString(2));
        loc.setText(cursor.getString(3));
        re.setText(cursor.getString(4));
        celln.setText(cursor.getString(5));
        des.setText(cursor.getString(6));


        Button post = (Button) findViewById(R.id.back);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this,
                        SearchActivity.class);
                intent.putExtra(SearchActivity.EXTRA_USERID, username);
                intent.putExtra(SearchActivity.EXTRA_USERG, gender);
                intent.putExtra(SearchActivity.EXTRA_USERC, year);
                intent.putExtra(SearchActivity.EXTRA_USERN, namee);
                startActivity(intent);
            }
        });

          */
    }
}