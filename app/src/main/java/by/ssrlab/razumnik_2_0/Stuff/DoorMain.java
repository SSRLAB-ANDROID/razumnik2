package by.ssrlab.razumnik_2_0.Stuff;

import android.content.Context;

/**
 * Created by Mihal on 20.10.2017.
 */

public class DoorMain {
    public static final String TYPE_HOUSE = "house";
    public static final String TYPE_DOOR = "door";
    public static final String TYPE_ONE = "first";
    public static final String TYPE_TWO = "second";
    public static final String TYPE_THREE = "third";
    private static final String OPEN = "open";
    private static final String CLOSE = "close";
    private static final String NOT_AVAILABLE = "lock";

    private boolean availability;
    private String type;
    private String type_c;
    private Context mContext;
    private Class mClass;
    private String title = null;

    public DoorMain(Context context, String type, String type_c, boolean availability, Class aClass) {
        mContext = context;
        this.type = type;
        this.type_c = type_c;
        this.availability = availability;
        mClass = aClass;
    }

    public DoorMain(Context context, String type, String type_c, boolean availability, Class aClass, String title) {
        mContext = context;
        this.type = type;
        this.type_c = type_c;
        this.availability = availability;
        mClass = aClass;
        this.title = title;
    }

    public int getStateImage(boolean trigger) {
        StringBuilder resIdPath = new StringBuilder();
        resIdPath.append(type);
        resIdPath.append("_");
        resIdPath.append(type_c);
        resIdPath.append("_");
        if (this.availability) {
            if (trigger) {
                resIdPath.append(OPEN);
            } else {
                resIdPath.append(CLOSE);
            }
        } else {
            resIdPath.append(NOT_AVAILABLE);
        }

        return mContext.getResources().getIdentifier(resIdPath.toString(), "drawable", mContext.getPackageName());
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

    public String getTitle() {
        return title;
    }
}
