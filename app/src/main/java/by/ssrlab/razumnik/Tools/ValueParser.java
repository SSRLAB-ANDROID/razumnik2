package by.ssrlab.razumnik.Tools;

import android.content.Context;

/**
 * Created by Mihal on 21.11.2017.
 */

public  class ValueParser {
    public static String[] parseValue(Context context, int id){
        return context.getString(id).split(",");
    }
}
