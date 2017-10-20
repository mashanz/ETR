package com.adhyahan.expansetrackrecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RnEST on 10/19/2017.
 */

public class RowAdaptor extends ArrayAdapter<Row> {
    public RowAdaptor(@NonNull Context context,
                      @NonNull List<Row> objects){
        super(context, R.layout.expanse_item, R.id.textDescription, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View converView, @NonNull ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.expanse_item, parent, false);
        TextView descriptionView = (TextView) rowView.findViewById(R.id.textDescription);
        TextView amountView = (TextView) rowView.findViewById(R.id.textAmount);

        final Row row = getItem(position);
        descriptionView.setText(row.getDescription());
        amountView.setText("Rp " + String.format("%.2f", row.getAmount()));
        return rowView;
    }
}
