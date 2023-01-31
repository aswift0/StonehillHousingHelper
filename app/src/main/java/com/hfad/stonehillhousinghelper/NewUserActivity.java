package com.hfad.stonehillhousinghelper;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
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


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;


public class NewUserActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    public void createUser(View view, SQLiteDatabase db) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password2);
        EditText n = (EditText) findViewById(R.id.name1);
        Spinner year = (Spinner) findViewById(R.id.year);
        Spinner gen = (Spinner) findViewById(R.id.gender);
        String usern = username.getText().toString();
        System.out.println(usern);
        String pass = password.getText().toString();
        System.out.println(pass);
        String namee = n.getText().toString();
        System.out.println(namee);
        String cyear = String.valueOf(year.getSelectedItem());
        System.out.println(cyear);
        String gender = String.valueOf(gen.getSelectedItem());
        System.out.println(gender);
        UserDatabaseHelper databaseHelper = new UserDatabaseHelper(this);
        if((!namee.isEmpty())&&(!pass.isEmpty())&&(!gender.isEmpty())&&(!cyear.isEmpty())&&(!usern.isEmpty())) {
            boolean t = databaseHelper.insertUser(db, usern, pass, namee, cyear, gender);
            if (t) {
                int num = databaseHelper.getProfiles(db);
                System.out.println(num);
                Intent intent = new Intent(NewUserActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        }
        else{
            System.out.println("USER NOT MADE");
        }
    }

    /*  public static NewUser newInstance(String param1, String param2) {
          NewUser fragment = new NewUser();
          Bundle args = new Bundle();
          args.putString(ARG_PARAM1, param1);
          args.putString(ARG_PARAM2, param2);
          fragment.setArguments(args);
          return fragment;
      }

   */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        SQLiteOpenHelper userDatabaseHelper =  new UserDatabaseHelper(this);
        try {
            db = userDatabaseHelper.getWritableDatabase();
            Button submit = (Button) findViewById(R.id.submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createUser(v,db);
                }
            });
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
/*
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_user, container, false);
    } */
}