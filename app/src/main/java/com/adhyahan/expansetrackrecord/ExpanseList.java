package com.adhyahan.expansetrackrecord;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.DOMImplementation;

import java.util.ArrayList;
import java.util.List;

public class ExpanseList extends AppCompatActivity {

    private static final String TAG = "ExpanseLisst.class";
    private DbHelper mDbHelper;
    private List<Row> trows = new ArrayList<>();
    private ListView listView;
    private TextView textView;
    private Double vTotal = 0.0;
    private Button buttonAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanse_list);

        listView = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textTotal);
        buttonAdd= (Button) findViewById(R.id.buttonAdd);
        mDbHelper = new DbHelper(this);
        openAndQueryDatabase();
        displayResultList();

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ExpanseForm.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void openAndQueryDatabase() {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final String sql = "SELECT * FROM " + Contract.Transaction.TABLE_NAME + " ORDER BY " + Contract.Transaction._ID;
        final Cursor c = db.rawQuery(sql, null);
        if(c != null){
            if(c.moveToFirst()){
                do {
                    final int id = c.getInt(c.getColumnIndex(Contract.Transaction._ID));
                    final Double amount = c.getDouble(c.getColumnIndex(Contract.Transaction.COLUMN_NAME_AMOUNT));
                    final String description = c.getString(c.getColumnIndex(Contract.Transaction.COLUMN_NAME_DESCRIPTION));
                    final Row row = new Row();
                    row.setId(id);
                    row.setAmount(amount);
                    row.setDescription(description);
                    trows.add(row);
                    vTotal += amount;
                } while (c.moveToNext());
            }
            textView.setText(String.format("Rp %.2f", vTotal));
        }
    }

    private void displayResultList(){
        final RowAdaptor adaptor = new RowAdaptor(this,trows);
        listView.setAdapter(adaptor);
    }
    protected void onDestroy(){
        mDbHelper.close();
        super.onDestroy();
    }
}
