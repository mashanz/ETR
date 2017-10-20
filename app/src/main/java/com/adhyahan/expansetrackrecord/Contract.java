package com.adhyahan.expansetrackrecord;

import android.provider.BaseColumns;

/**
 * Created by RnEST on 10/19/2017.
 */

public class Contract {
    public static class Transaction implements BaseColumns {
        public static final String TABLE_NAME = "transaksi";
        public static final String COLUMN_NAME_AMOUNT = "tableAmount";
        public static final String COLUMN_NAME_DESCRIPTION = "tableDescription";
    }
}
