package com.example.contentproviderdemo1;

import android.net.Uri;
public class Util {
    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.contentproviderdemo1" +
                    "/electric_cars");
    public static final String ID_FIELD = "_id";
    public static final String MAKE_FIELD = "make";
    public static final String MODEL_FIELD = "model";

}
