package ge.edu.freeuni.chat.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


public final class BitMapUtils {

    public static Bitmap toBitMap(String base64) {
        byte[] base64Bytes = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(base64Bytes, 0, base64Bytes.length);
    }

}
