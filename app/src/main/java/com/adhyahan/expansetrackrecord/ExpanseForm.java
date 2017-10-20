package com.adhyahan.expansetrackrecord;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOError;
import java.io.IOException;

import static com.adhyahan.expansetrackrecord.R.id.buttonSubmit;
public class ExpanseForm extends AppCompatActivity {
    private TextView amount, description;
    private DbHelper mDbHelper;
    private static final String TAG = "ExpanseForm.class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanse_form);


        amount = (TextView) findViewById(R.id.editAmount);
        description = (TextView) findViewById(R.id.editDescription);
        mDbHelper = new DbHelper(this);
    }

    //TODO : Submit
    public void onSubmit(View view){
        // TODO :
        Log.i(TAG, "onSubmit");
        try {
            final Double submitAmount = Double.parseDouble(amount.getText().toString());
            final String submitDescription = description.getText().toString();
            Log.i(TAG, "SUCCESS GET VALUE, submitAmount=" + submitAmount + " submitDesription=" + submitDescription);

            final SQLiteDatabase db = mDbHelper.getWritableDatabase();
            final ContentValues values = new ContentValues();

            values.put(Contract.Transaction.COLUMN_NAME_AMOUNT, submitAmount);
            values.put(Contract.Transaction.COLUMN_NAME_DESCRIPTION, submitDescription);

            final long RowId = db.insert(Contract.Transaction.TABLE_NAME,null,values);
            Log.i(TAG, "SUCCESS INSERT TO DATABASE WITH ID " + RowId);

            Intent intent = new Intent(getApplicationContext(), ExpanseList.class);
            startActivities(new Intent[]{intent});
            finish();
        } catch (Exception main){
            Log.i(TAG, "FAIL TO GET VALUE, retry");
        }
    }
}
