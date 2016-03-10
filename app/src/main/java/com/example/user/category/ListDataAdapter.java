package com.example.user.category;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 3/10/2016.
 */
public class ListDataAdapter extends BaseAdapter implements ListAdapter {

    public ArrayList<category> list = new ArrayList<category>();
    public Context context;

    public ListDataAdapter (ArrayList<category> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row, null);
        }

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView) view.findViewById(R.id.lblReload);
        listItemText.setText(list.get(position).getName());

        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category row = (category) list.get(position);
                int selected_id = row.getId();
                String expenseCateory = row.getName();

                Intent myIntent = new Intent(context, SubCategory_expense.class); //Redirecting to another activity

                // pass your values and retrieve them in the other Activity using keyName
                myIntent.putExtra("passed data key", expenseCateory);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(myIntent);
            }
        });

        /*listItemText.setOnLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub
                String str = list.getItemAtPosition(index).toString();

                //Log.d("long click : " + str);
                return true;
            }
        });*/

        SQLiteDatabase db = new DatabaseOperator(context.getApplicationContext()).getWritableDatabase();
        //Handle buttons and add onClickListeners
        TextView deleteBtn = (TextView) view.findViewById(R.id.btnDelete);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof expense) {
                    new AlertDialog.Builder((expense) context) //Alert dialog box
                            .setTitle("Delete Category")
                            .setMessage("Are you sure you want to Delete this Category?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SQLiteDatabase db = new DatabaseOperator(context.getApplicationContext()).
                                            getWritableDatabase();
                                    db.delete(DatabaseOperator.CATEGORY_TABLE_NAME, DatabaseOperator.Category_ID + "=?",
                                            new String[]{Integer.toString(list.get(position).getId())});
                                    db.close();
                                    list.remove(position);  //delete the item
                                    notifyDataSetChanged(); //refersh the listview

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();  //show dialog box

                }
            }
        });

        return view;
    }

}
