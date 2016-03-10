package com.example.user.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 2/1/2016.
 */

/**
 * Create databse for the project
 */
public class DatabaseOperator extends SQLiteOpenHelper{

    SQLiteDatabase db;

    public static final String DATABASE_NAME = "personal_finance";


    /*Category table attributes*/
    public static final String Category_ID = "Category_ID";
    public static final String CATEGORY_NAME = "Category_Name";
    public static final String CATEGORY_TABLE_NAME = "category";

    /*Income type category table attributes*/
    public static final String CategoryIncome_ID = "categoryIncone_ID";
    public static final String categoryIncone_Name = "categoryIncone_Name";
    public static final String categoryIncone_table_name = "categoryIncone";

    /*subcategoryExpense table attributes*/
    public static final String subcategoryExpense_ID = "SuCat_ID";
    public static final String CATID = "CategoryID";
    public static final String SUBCAT_NAME = "subcategoryExpenseName";
    public static final String subcategoryExpense_TABLE_NAME = "subcategoryExpense";

    /*Income type subcategoryExpense table attributes*/
    public static final String subcategoryExpenseINCOME_ID = "SuCatINCOME_ID";
    public static final String CATINCOMEID = "CategoryICOMEID";
    public static final String SUBCATINCOME_NAME = "subcategoryExpenseINCOMEName";
    public static final String subcategoryExpenseINCOME_TABLE_NAME = "subcategoryExpenseincome";


    public static final int database_version = 1;
    Log log;


    /**
     * Create table queries with the use of defined attributes names
     */
    public String CREATE_CATEGORY_QUERY =
                    "Create table "+ CATEGORY_TABLE_NAME+
                    "("+ Category_ID+ " Integer PRIMARY KEY AUTOINCREMENT, "+
                    CATEGORY_NAME+ " Text " +
                    " );";

    public String CREATE_categoryIncone_QUERY =
                    "Create table "+ categoryIncone_table_name+
                    "("+ CategoryIncome_ID+ " Integer PRIMARY KEY AUTOINCREMENT, "+
                    categoryIncone_Name+ " Text " +
                    " );";

    public String CREATE_subcategoryExpense_QUERY = 
                    "CREATE TABLE "+ subcategoryExpense_TABLE_NAME+
                    "("+subcategoryExpense_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    //CATID+" INTEGER, "+
                    SUBCAT_NAME+ " TEXT"+
                    //" CONSTRAINT FOREIGN KEY ("+CATID+") REFERENCES "+CATEGORY_TABLE_NAME+" ("+Category_ID+")"+
                    ")";

    public String CREATE_subcategoryExpenseINCOME_QUERY =
                    "CREATE TABLE "+ subcategoryExpenseINCOME_TABLE_NAME+
                    "("+subcategoryExpenseINCOME_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    //CATID+" INTEGER, "+
                    SUBCATINCOME_NAME+ " TEXT"+
                    //" CONSTRAINT FOREIGN KEY ("+CATID+") REFERENCES "+CATEGORY_TABLE_NAME+" ("+Category_ID+")"+
                    ")";
    
    public DatabaseOperator(Context context) {
        super(context, DATABASE_NAME, null, database_version);
        log.e("Database operation", "Database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORY_QUERY);
        db.execSQL(CREATE_categoryIncone_QUERY);
        db.execSQL(CREATE_subcategoryExpense_QUERY);
        db.execSQL(CREATE_subcategoryExpenseINCOME_QUERY);
        log.e("Database operation", "Table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*Check whether the same data is in the table*/
    public boolean checkIdExist(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DatabaseOperator.CATEGORY_TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            if (c.getString(1).equals(name))
                return false;
        }

        return true;
    }

    public boolean checkIncomeIdExist(String name) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DatabaseOperator.categoryIncone_table_name, null, null, null, null, null, null);
        while (c.moveToNext()) {
            if (c.getString(1).equals(name))
                return false;
        }

        return true;
    }


    /**
     * methods for each type of categories and subcategories to get the values
     * @return it
     */

    public ArrayList<category> getCategories() {
        ArrayList<category> arrayList = new ArrayList<category>(); //create an arraylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DatabaseOperator.CATEGORY_TABLE_NAME, null, null, null, null, null, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            category cat = new category(c.getString(1)); //get the name
            arrayList.add(cat);//assign those name to the arraylist

        }

        return arrayList;
    }

    public ArrayList<categoryIncone> getIncomeCategories() {
        ArrayList<categoryIncone> arrayList = new ArrayList<categoryIncone>(); //create an arraylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DatabaseOperator.categoryIncone_table_name, null, null, null,
                null, null, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            categoryIncone cat = new categoryIncone(c.getString(1)); //get the name
            arrayList.add(cat);//assign those name to the arraylist

        }

        return arrayList;
    }

    public ArrayList<subcategoryExpense> getSubCategories() {
        ArrayList<subcategoryExpense> arrayList = new ArrayList<subcategoryExpense>(); //create an arraylist having objects in it
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(DatabaseOperator.subcategoryExpense_TABLE_NAME, null, null, null,
                null, null, null); //cursor contain collection of data for particula query
        while (c.moveToNext()) {
            subcategoryExpense cat = new subcategoryExpense(c.getString(1)); //get the name
            arrayList.add(cat);//assign those name to the arraylist

        }

        return arrayList;
    }

}
