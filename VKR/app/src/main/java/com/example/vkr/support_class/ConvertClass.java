package com.example.vkr.support_class;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ConvertClass {

    public static Bitmap convertStringToBitmap(String encodedString) {
        byte[] decodeString = Base64.decode(encodedString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
    }
    public static String convertBitmapToString(Bitmap bitmap) {
        if(bitmap == null) return null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        try { stream.close(); }
        catch (IOException ignored) { }
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
