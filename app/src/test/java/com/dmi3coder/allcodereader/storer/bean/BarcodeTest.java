package com.dmi3coder.allcodereader.storer.bean;

import com.google.zxing.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dmi3coder on 4/12/16 6:20 PM.
 */
public class BarcodeTest {
    @Test
    public void testBarcodeFormat() throws Exception {
        com.google.zxing.BarcodeFormat format = BarcodeFormat.EAN_13;
        assertEquals(format.toString(),"EAN_13");
        assertNotEquals(format.toString(),"ean_13");
        assertEquals(BarcodeFormat.valueOf("EAN_13"),format);
    }


    @Test
    public void testBarcodeConstructors() throws Exception {
        Barcode barcodeWithEnum = new Barcode(1L,"Paper Book",
                "4820049492224","Some simple paper book",
                "no_image","Office Items",BarcodeFormat.EAN_13);
        Barcode barcodeWithString = new Barcode(1L,"Paper Book",
                "4820049492224","Some simple paper book",
                "no_image","Office Items","EAN_13");
        assertEquals(barcodeWithEnum,barcodeWithString);
        assertEquals(barcodeWithString.getFormat(),BarcodeFormat.EAN_13);
    }
}