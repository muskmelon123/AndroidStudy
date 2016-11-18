package com.example.lvhang.androidstudyproject.DatabaseStudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lvhang.androidstudyproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lv.hang on 2016/11/16.
 */

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_study_main);

        button = (Button) findViewById(R.id.main_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDbOperation();
                //dbOperation(0);
            }
        });
    }

    private void testDbOperation() {
        for (int i = 0; i < 1000; i++) {
            dbOperation(i);
        }
    }

    private void dbOperation(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Contact contact = new Contact("Dagou " + index, index);
                DBOperator dbOperator = new DBOperator(MainActivity.this);
                dbOperator.insertOrUpdateContact(contact);
//                List<Contact> contacts = new ArrayList<Contact>();
//                for (int i = 0; i < 1000000; i++) {
//                    contacts.add(new Contact("Dagou " + i, i));
//                }
//
//                DBOperator dbOperator = new DBOperator(MainActivity.this);
//                dbOperator.insertOrUpdateContacts(contacts);
            }
        }).start();
    }
}
