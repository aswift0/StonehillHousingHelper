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



public class NewPostFragment extends Fragment {/*
    public static final String EXTRA_USERID= "user";
    public static final String EXTRA_USERG= "G";
    public static final String EXTRA_USERC= "C";
    public static final String EXTRA_USERN= "N";
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        String nam = (String) getIntent().getExtras().get(EXTRA_USERN);
        String gen = (String) getIntent().getExtras().get(EXTRA_USERG);
        String cyear = (String) getIntent().getExtras().get(EXTRA_USERC);
        String uid = (String) getIntent().getExtras().get(EXTRA_USERID);
        if (view != null) {
            SQLiteOpenHelper postDatabaseHelper =  new PostDatabaseHelper(getActivity());
            try {
                db = postDatabaseHelper.getWritableDatabase();
                Button submit = (Button) view.findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createPost(v,db,nam,gen,cyear);
                    }
                });
            } catch(SQLiteException e) {
                Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }



    public void createPost(View view, SQLiteDatabase db, String name, String gender, String cyear) {
        EditText num = (EditText) view.findViewById(R.id.phone);
        EditText des = (EditText) view.findViewById(R.id.descript);
        Spinner loc = (Spinner) view.findViewById(R.id.place);
        Spinner req = (Spinner) view.findViewById(R.id.requesting);
        String pnum = num.getText().toString();
        System.out.println(pnum);
        String desc = des.getText().toString();
        System.out.println(desc);
        String loca = String.valueOf(loc.getSelectedItem());
        System.out.println(loca);
        String reqe = String.valueOf(req.getSelectedItem());
        System.out.println(reqe);
        PostDatabaseHelper postDatabaseHelper = new PostDatabaseHelper(getActivity());
        if((!pnum.isEmpty())&&(!desc.isEmpty())&&(!loca.isEmpty())&&(!reqe.isEmpty())) {
            boolean t = postDatabaseHelper.insertPost(db, name, cyear, loca, gender, reqe,pnum,desc);
            if (t) {
                Intent intent = new Intent(getActivity(),
                        SearchActivity.class);
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
    */

}