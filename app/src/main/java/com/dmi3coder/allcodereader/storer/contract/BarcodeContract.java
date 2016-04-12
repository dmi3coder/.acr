package com.dmi3coder.allcodereader.storer.contract;

import android.content.ContentValues;
import android.provider.BaseColumns;

import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.COLUMN_NAME_CONTENT;
import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.COLUMN_NAME_DESCRIPTION;
import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.COLUMN_NAME_FORMAT;
import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.COLUMN_NAME_GROUP;
import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.COLUMN_NAME_IMAGE;
import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.COLUMN_NAME_NAME;

/**
 * Created by dmi3coder on 4/12/16 4:54 PM.
 */
public final class BarcodeContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_BARCODE =
            "CREATE TABLE " + BarcodeDbColumns.TABLE_NAME + " ("+
                    BarcodeDbColumns._ID + " INTEGER PRIMARY KEY,"+
                    BarcodeDbColumns.COLUMN_NAME_NAME+TEXT_TYPE+COMMA_SEP+
                    BarcodeDbColumns.COLUMN_NAME_CONTENT +TEXT_TYPE+COMMA_SEP+
                    BarcodeDbColumns.COLUMN_NAME_DESCRIPTION+TEXT_TYPE+COMMA_SEP+
                    BarcodeDbColumns.COLUMN_NAME_IMAGE+TEXT_TYPE+COMMA_SEP+
                    BarcodeDbColumns.COLUMN_NAME_GROUP+TEXT_TYPE+COMMA_SEP+
                    BarcodeDbColumns.COLUMN_NAME_FORMAT+TEXT_TYPE+
                    " )";
    public static final String SQL_DELETE_BARCODE=
            "DROP TABLE IF EXISTS "+ BarcodeDbColumns.TABLE_NAME;


    public BarcodeContract() {
    }

    public static abstract class BarcodeDbColumns implements BaseColumns{
        public static final String TABLE_NAME = "barcode";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_GROUP = "sector";
        public static final String COLUMN_NAME_FORMAT = "format";

    }

    public static ContentValues createCV(com.dmi3coder.allcodereader.storer.bean.Barcode barcode){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME,barcode.getName());
        values.put(COLUMN_NAME_CONTENT,barcode.getContent());
        values.put(COLUMN_NAME_DESCRIPTION,barcode.getDescription());
        values.put(COLUMN_NAME_FORMAT,barcode.getFormat().toString());
        values.put(COLUMN_NAME_GROUP,barcode.getGroup());
        values.put(COLUMN_NAME_IMAGE,barcode.getImage());
        return values;
    }

}
