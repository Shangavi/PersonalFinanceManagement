package com.example.user.category;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.com.example.user.category.category.addSubCategory;

import java.util.ArrayList;

/**
 * what should do under Subcategory expense class
 */

public class SubCategory_expense extends Activity implements View.OnClickListener{


    int selected_id;
    TextView menubtn;
    ListView listSubcat;
    EditText textlsdk;
    Context context;
    DatabaseOperator helper;
    SQLiteDatabase db;
    Button btnd;
    String SubCatexpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_expense);

        context = this;
        helper = new DatabaseOperator(this);

        menubtn = (TextView) findViewById(R.id.txtAddSubCatExpense);
        menubtn.setOnClickListener(this);

        loadList();
    }

    @Override
    public void onClick(View v) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.activity_add_sub_category, null);

        textlsdk = (EditText) promptsView.findViewById(R.id.txtAddSubCatexpense);

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
                        value.put(DatabaseOperator.subcategoryExpense_TABLE_NAME, textlsdk.getText().toString());
                        db = helper.getWritableDatabase();

                        String catExName = textlsdk.getText().toString();

                        //validation for edit text

                        if (isValidWord(catExName)){
                            //check whether the budget amount is already there or not
                            //addExpense ae = new addExpense();
                            if(helper.checkIdExist(textlsdk.getText().toString())){
                                //ae.addingExpense(this, catExName);

                                db.insert(DatabaseOperator.subcategoryExpense_TABLE_NAME, null, value); //insert values to the database
                                db.close();
                                Toast.makeText(SubCategory_expense.this, "New SubCategory added Successfully",
                                        Toast.LENGTH_LONG).show();

                                Intent i = new Intent(SubCategory_expense.this, addSubCategory.class);
                                startActivity(i);

                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Duplication SubCategory Name!",
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Enter a valid SubCategory name!",
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

        listSubcat = (ListView) findViewById(R.id.listViewSubCat);

        ArrayList<subcategoryExpense> mArrayList = helper.getSubCategories(); //calling the method from DBhelper class
        //ListDataAdapterSubcategory adapter = new ListDataAdapterSubcategory(mArrayList, this);

        //handle listview and assign adapter,view,position
        /*listSubcat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                SubCategory row = (SubCategory) arg0.getItemAtPosition(position); //assigning the position to the adapter
                selected_id = row.getSuCatId();
                expense = row.getSubName();

                Intent myIntent = new Intent(SubCategory_expense.this, addSubCategory.class); //redirecting to another activity
                myIntent.putExtra("passed data key", expense); // pass your values and retrieve them in the other Activity using keyName
                startActivity(myIntent);
            }

        });
        listSubcat.setAdapter(adapter); //set the adaptor which contain list of items    */

    }
}
