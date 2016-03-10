package com.example.user.category;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    DatabaseOperator helper;
    public static TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("1");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("2");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Expense");
        Intent intent = new Intent(MainActivity.this, expense.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab1.setContent(new Intent(intent));

        tab2.setIndicator("Income");
        Intent intent2 = new Intent(MainActivity.this, income.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tab2.setContent(new Intent(intent2));


        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        tabHost.getTabWidget().setBackgroundColor(Color.parseColor("#FFFFFF"));
        //helper.insertTransactionData();
    }


}
