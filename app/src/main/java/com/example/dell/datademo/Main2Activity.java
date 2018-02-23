package com.example.dell.datademo;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Button load;
    EditText textView;
    SQLiteDatabase db;
    SharedPreferences ss;
    SharedPreferences.Editor e1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        load = (Button) findViewById(R.id.load);
        textView = (EditText) findViewById(R.id.textView);
        ss=getSharedPreferences("ACS",MODE_PRIVATE);
        e1=ss.edit();
        load.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    savetosql();
                }
                display();
                Toast.makeText(Main2Activity.this, "DONE", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void savetosql() {
        db = openOrCreateDatabase("db1", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS ACS(count VARCHAR);");
        db.execSQL("INSERT INTO ACS(count) VALUES(\"" + textView.getText().toString() + "\")");
        e1.putString("ACS",textView.getText().toString());
        e1.commit();
    }

    void display() {
        Cursor resultSet = db.rawQuery("SELECT * FROM ACS;", null);
        resultSet.moveToFirst();
        for (int i = 0; i < resultSet.getCount(); i++) {
            Log.e("SQL", ""+resultSet.getString(0));
            resultSet.moveToNext();

        }
        String x=ss.getString("ACS","false");
        Log.e("Data Retrived",x);


    }
}


