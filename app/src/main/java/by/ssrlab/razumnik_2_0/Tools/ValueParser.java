package by.ssrlab.razumnik_2_0.Tools;

import android.content.Context;

import by.ssrlab.razumnik_2_0.R;

/**
 * Created by Mihal on 21.11.2017.
 */

public  class ValueParser {
    public static String[] parseValue(Context context, int id){
        return context.getString(id).split(",");
    }
}
