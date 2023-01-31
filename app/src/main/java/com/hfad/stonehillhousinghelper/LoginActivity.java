package com.hfad.stonehillhousinghelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.*;

public class LoginActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;
    public void checkCredentials(View view,SQLiteDatabase db) {
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        String usern = username.getText().toString();
        String pass = password.getText().toString();
        //System.out.println(usern);
        //System.out.println(pass);
        UserDatabaseHelper databaseHelper = new UserDatabaseHelper(this);
        if(databaseHelper.checkUserExist(db,usern,pass)){
            Toast toast = makeText(this, "You're in", LENGTH_SHORT);
            toast.show();
            String g = databaseHelper.getUserGender(db,usern,pass);
            String n = databaseHelper.getName(db,usern,pass);
            String c = databaseHelper.getUserClass(db,usern,pass);
            Intent intent = new Intent(LoginActivity.this,
                    SearchActivity.class);
            intent.putExtra(SearchActivity.EXTRA_USERID, usern);
            intent.putExtra(SearchActivity.EXTRA_USERG, g);
            intent.putExtra(SearchActivity.EXTRA_USERN, n);
            intent.putExtra(SearchActivity.EXTRA_USERC, c);
            startActivity(intent);

        } else {
            Toast toast = makeText(this, "User not found", LENGTH_SHORT);
            toast.show();
        }

/*
        if(cursor.moveToFirst()) {
          while (cursor.moveToNext()) {
              String user = cursor.getString(0);
                String passt = cursor.getString(1);
                System.out.println("USERNAME: "+user + " PASSWORD:"+passt);
                    // Intent intent = new Intent(LoginFragment.this, MainActivity.class);
                    //intent.putExtra(ContactsContract.Profile.EXTRA_USERNAME, (String) usern);
                    // startActivity(intent);
                }

        }
         */
/*
        String[] columns = {"USERNAME"};

        String selection = "username=? and password = ?";
        String[] selectionArgs = {usern, pass};

        Cursor cursor = db.query("USER", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        System.out.println(count);
        if(count == 1){
            Toast toast = makeText(this, "You're in", LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = makeText(this, "User not found", LENGTH_SHORT);
            toast.show();
            //cursor.close();
            //db.close();
        }
*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        SQLiteOpenHelper userDatabaseHelper = new UserDatabaseHelper(this); //activity.this
        try {
            db = userDatabaseHelper.getReadableDatabase();
        } catch(SQLiteException e) {
            Toast toast = makeText(this, "Database unavailable", LENGTH_SHORT);
            toast.show();
        }
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials(v,db);
            }
        });
        Button newUse = (Button) findViewById(R.id.newuser);
        newUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        NewUserActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

}