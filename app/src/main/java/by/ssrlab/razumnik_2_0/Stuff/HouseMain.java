package by.ssrlab.razumnik_2_0.Stuff;

import android.content.Context;

/**
 * Created by Mihal on 20.10.2017.
 */

public class HouseMain {
    public static final String TYPE_ONE = "first";
    public static final String TYPE_TWO = "second";
    public static final String TYPE_THREE = "third";
    private static final String OPEN = "open";
    private static final String CLOSE = "close";
    private static final String NOT_AVAILABLE = "lock";

    private boolean availability;
    private String type;
    private Context mContext;
    private Class mClass;

    public HouseMain(Context context, String type, boolean availability, Class aClass){
        mContext = context;
        this.type = type;
        this.availability = availability;
        mClass = aClass;
    }

    public int getStateImage(boolean trigger){
        StringBuilder resIdPath = new StringBuilder();
        resIdPath.append(type);
        resIdPath.append("_");
        if(this.availability){
            if(trigger) {
                resIdPath.append(OPEN);
            }else {
                resIdPath.append(CLOSE);
            }
        }else {
            resIdPath.append(NOT_AVAILABLE);
        }

        int resId = mContext.getResources().getIdentifier(resIdPath.toString(), "drawable", mContext.getPackageName());
        return resId;
    }


    public boolean isAvailability() {
        return availability;
    }

    public String getType() {
        return type;
    }

    public Class getMClass() {
        return mClass;
    }
}
