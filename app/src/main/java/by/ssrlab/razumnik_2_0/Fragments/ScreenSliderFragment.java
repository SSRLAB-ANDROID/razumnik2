package by.ssrlab.razumnik_2_0.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import by.ssrlab.razumnik_2_0.R;
import by.ssrlab.razumnik_2_0.Stuff.DoorMain;


public class ScreenSliderFragment extends Fragment {
    private DoorMain mDoorMain;

    public void setDoorMain(DoorMain doorMain) {
        mDoorMain = doorMain;
    }

    public DoorMain getDoorMain() {
        return mDoorMain;
    }

    private OnClickListener mListener;

    public interface OnClickListener {
        void onClick();
    }

    public void setListener(OnClickListener listener){
        mListener = listener;
    }

    public ScreenSliderFragment() {
        // Required empty public constructor
    }

    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen_slider, container, false);
        imageView = (ImageView) view.findViewById(R.id.house_imageView);
        setImageViewImage(mDoorMain.getStateImage(false));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick();
            }
        });
        return view;
    }

    public void setImageViewImage(int resId){
        imageView.setImageResource(resId);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(imageView!=null){
            setImageViewImage(mDoorMain.getStateImage(false));
        }
    }

    public void setClickable(boolean clickable){
        imageView.setClickable(clickable);
    }
}
