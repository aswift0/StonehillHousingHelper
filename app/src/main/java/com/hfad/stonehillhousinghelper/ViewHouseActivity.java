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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ViewHouseActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "C";
    public static final String EXTRA_DESC = "M";
    public static final String EXTRA_IMAGE = "0";

    private SQLiteDatabase db;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house);
        SQLiteOpenHelper postDatabaseHelper = new HousingDatabaseHelper(this); //activity.this
        String ids = (String) getIntent().getExtras().get(EXTRA_IMAGE);
        String disc = (String) getIntent().getExtras().get(EXTRA_DESC);
        String n = (String) getIntent().getExtras().get(EXTRA_NAME);
        System.out.println(ids);
        System.out.println(disc);
        System.out.println(n);
        try {
            db = postDatabaseHelper.getReadableDatabase();
        } catch(SQLiteException e) {
            Toast toast = makeText(this, "Database unavailable", LENGTH_SHORT);
            toast.show();
        }
        TextView dis = (TextView)findViewById(R.id.request);
        dis.setText(disc);
        TextView name = (TextView)findViewById(R.id.title);
        name.setText(n);
        ImageView v = (ImageView)findViewById(R.id.imageView);
        v.setImageResource(Integer.parseInt(ids));
    }
}