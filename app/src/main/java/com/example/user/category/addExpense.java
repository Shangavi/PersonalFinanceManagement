package com.example.user.category;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * The class which used to add any new expenses
 */

public class addExpense extends ActionBarActivity implements View.OnClickListener {

    EditText text1;

    DatabaseOperator helper;
    SQLiteDatabase db;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        //helper = new DatabaseOperator(this);

        text1 = (EditText) findViewById(R.id.txtAddSubCatExpense);// assign the layout's EditText variable


    }
    @Override
    public void onClick(View v) {

    }

    private void clearfield() {


    }


}
