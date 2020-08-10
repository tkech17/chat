package ge.edu.freeuni.chat.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.commons.codec.binary.Base64;

public final class BitMapUtils {

    public static Bitmap toBitMap(String base64) {
        byte[] base64Bytes = Base64.decodeBase64(base64);
        return BitmapFactory.decodeByteArray(base64Bytes, 0, base64.length());
    }

}
