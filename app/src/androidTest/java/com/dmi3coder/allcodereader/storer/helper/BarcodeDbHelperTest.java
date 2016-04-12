package com.dmi3coder.allcodereader.storer.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.dmi3coder.allcodereader.MainActivity;
import com.dmi3coder.allcodereader.storer.bean.Barcode;
import com.dmi3coder.allcodereader.storer.contract.BarcodeContract;
import com.google.zxing.BarcodeFormat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static com.dmi3coder.allcodereader.storer.contract.BarcodeContract.BarcodeDbColumns.*;
/**
 * Created by dmi3coder on 4/12/16 6:54 PM.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BarcodeDbHelperTest {

    Barcode barcode = new Barcode(1L,"Paper Book",
            "4820049492224","Some simple paper book",
            "no_image","Office Items", BarcodeFormat.EAN_13);
    long newRowId;
    static BarcodeDbHelper barcodeDbHelper;

    @Before
    public void setUp() throws Exception {
        barcodeDbHelper = new BarcodeDbHelper(mActivityRule.getActivity().getApplicationContext());
    }

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);


    @Test
    public void testDBInsertion() throws Exception {
        SQLiteDatabase database = barcodeDbHelper.getWritableDatabase();
        ContentValues values = BarcodeContract.createCV(barcode);
        newRowId = database.insert(TABLE_NAME,"null",values);
        assertNotEquals(newRowId,0L);
        assertNotEquals(newRowId,-1);
    }

    @Test
    public void testDBRetrieving() throws Exception {
        SQLiteDatabase database = barcodeDbHelper.getReadableDatabase();
        String[] projection = {
                _ID,
                COLUMN_NAME_NAME,
                COLUMN_NAME_CONTENT,
                COLUMN_NAME_FORMAT,
        };
        String sortOrder = COLUMN_NAME_NAME+ " DESC";
        Cursor c = database.query(TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        c.moveToFirst();
        String s = c.getString(c.getColumnIndex(COLUMN_NAME_FORMAT));
        assertEquals(s,barcode.getFormat().toString());
        c.close();
    }

}