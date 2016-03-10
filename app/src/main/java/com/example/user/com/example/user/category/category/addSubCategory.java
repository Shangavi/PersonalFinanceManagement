package com.example.user.com.example.user.category.category;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.category.R;

public class addSubCategory extends ActionBarActivity implements View.OnClickListener {

    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_category);

        text1 = (EditText) findViewById(R.id.txtAddSubCatexpense); // assign the layout's EditText variable


    }

    @Override
    public void onClick(View v) {

    }
}
