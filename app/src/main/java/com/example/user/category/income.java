package com.example.user.category;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class income extends Activity implements View.OnClickListener {

    TextView menuIncomebtn;
    Context context;
    DatabaseOperator helper;
    SQLiteDatabase db;
    ListView rldlist;
    int selected_id;
    String income;
    EditText textIncomeCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        helper = new DatabaseOperator(this);
        context = this;

        menuIncomebtn = (TextView) findViewById(R.id.txtIncome);
        menuIncomebtn.setOnClickListener(this);

        loadList();
    }

    @Override
    public void onClick(View v) {
        // get addIncomecategory.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.activity_add_income, null);

        textIncomeCat = (EditText) promptsView.findViewById(R.id.txtAddSubCatExpense);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set addcategory.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        //set dialog message

        alertDialogBuilder.setCancelable(false).setPositiveButton("ADD",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        ContentValues value = new ContentValues();
                        value.put(DatabaseOperator.categoryIncone_Name, textIncomeCat.getText().toString());
                        db = helper.getWritableDatabase();

                        String catInName = textIncomeCat.getText().toString();

                        //validation for edit text

                        if (isValidWord(catInName)){
                            //check whether the budget amount is already there or not
                            //addIncome ai = new addIncome();
                            if(helper.checkIncomeIdExist(textIncomeCat.getText().toString())){
                                //ai.addingIncome(this);

                                db.insert(DatabaseOperator.categoryIncone_table_name, null, value); //insert values to the database
                                db.close();
                                Toast.makeText(income.this, "New Category added Successfully",
                                        Toast.LENGTH_LONG).show();

                                Intent i = new Intent(income.this, MainActivity.class);
                                startActivity(i);

                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Duplication Category Name!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Enter a valid Category name!",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener(){


                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


        //create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        //show it
        alertDialog.show();
    }

    public boolean isValidWord(String w) {
        return w.matches("[A-Za-z][^.]*");
    }

    public void loadList() {

        rldlist = (ListView) findViewById(R.id.listViewIncome);

        ArrayList<categoryIncone> mArrayList = helper.getIncomeCategories(); //calling the method from DBhelper class
        Listata adapter = new Listata(mArrayList, this);

        //handle listview and assign adapter,view,position
        rldlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                category row = (category) arg0.getItemAtPosition(position); //assigning the position to the adapter
                selected_id = row.getId();
                income= row.getName();

                Intent myIntent = new Intent(income.this, addIncome.class); //redirecting to another activity
                myIntent.putExtra("passed data key", income); // pass your values and retrieve them in the other Activity using keyName
                startActivity(myIntent);
            }

        });
        rldlist.setAdapter(adapter); //set the adaptor which contain list of items

    }
}
