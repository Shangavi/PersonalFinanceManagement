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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


/**
 * what should do under expense class
 */

public class expense extends Activity implements View.OnClickListener{

    int selected_id;
    TextView menubtn;
    ListView rldlist;
    EditText textlsdk;
    Context context;
    DatabaseOperator helper;
    SQLiteDatabase db;
    Button btnd;
    String expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        context = this;
        helper = new DatabaseOperator(this);

        menubtn = (TextView) findViewById(R.id.txtExpense);
        menubtn.setOnClickListener(this);

        loadList();


    }

    @Override
    public void onClick(View v) {
        // get addcategory.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.activity_add_expense, null);

        textlsdk = (EditText) promptsView.findViewById(R.id.txtAddSubCatExpense);

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
                        value.put(DatabaseOperator.CATEGORY_NAME, textlsdk.getText().toString());
                        db = helper.getWritableDatabase();

                        String catExName = textlsdk.getText().toString();

                        //validation for edit text

                        if (isValidWord(catExName)){
                            //check whether the budget amount is already there or not
                            //addExpense ae = new addExpense();
                            if(helper.checkIdExist(textlsdk.getText().toString())){
                                //ae.addingExpense(this, catExName);

                                db.insert(DatabaseOperator.CATEGORY_TABLE_NAME, null, value); //insert values to the database
                                db.close();
                                Toast.makeText(expense.this, "New Category added Successfully",
                                        LENGTH_LONG).show();

                                Intent i = new Intent(expense.this, MainActivity.class);
                                startActivity(i);

                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Duplication Category Name!",
                                        LENGTH_LONG).show();
                            }
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Enter a valid Category name!",
                                    LENGTH_LONG).show();
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

        rldlist = (ListView) findViewById(R.id.listViewRowExpense);

        ArrayList<category> mArrayList = helper.getCategories(); //calling the method from DBhelper class
        ListDataAdapter adapter = new ListDataAdapter(mArrayList, this);

        //handle listview and assign adapter,view,position
        rldlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                category row = (category) arg0.getItemAtPosition(position); //assigning the position to the adapter
                selected_id = row.getId();
                expense = row.getName();

                Intent myIntent = new Intent(expense.this, addExpense.class); //redirecting to another activity
                myIntent.putExtra("passed data key", expense); // pass your values and retrieve them in the other Activity using keyName
                startActivity(myIntent);
            }

        });
        rldlist.setAdapter(adapter); //set the adaptor which contain list of items

    }
}
