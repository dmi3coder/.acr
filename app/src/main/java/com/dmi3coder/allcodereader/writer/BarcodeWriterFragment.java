package com.dmi3coder.allcodereader.writer;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by naomi on 4/10/16.
 */
public class BarcodeWriterFragment extends Fragment {
    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;
    public static final String BARCODE_FORMAT_ARG = "format";
    public static final String BARCODE_BARCODE_ARG = "barcode";


    private Context context;

    public static BarcodeWriterFragment newInstance(BarcodeFormat format,String barcode) {

        Bundle args = new Bundle();
        args.putSerializable(BARCODE_FORMAT_ARG,format);
        args.putString(BARCODE_BARCODE_ARG,barcode);
        BarcodeWriterFragment fragment = new BarcodeWriterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        Bundle args = getArguments();
        LinearLayout l = new LinearLayout(context);
        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        l.setOrientation(LinearLayout.VERTICAL);

        // barcode data
        String barcode_data = args.getString(BARCODE_BARCODE_ARG);

        // barcode image
        Bitmap bitmap = null;
        ImageView iv = new ImageView(context);

        try {

            bitmap = encodeAsBitmap(barcode_data, (BarcodeFormat) args.getSerializable(BARCODE_BARCODE_ARG), 600, 300);
            iv.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        l.addView(iv);

        //barcode text
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(barcode_data);

        l.addView(tv);
        return l;
    }

    Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }

}
