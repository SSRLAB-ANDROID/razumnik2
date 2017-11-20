package by.ssrlab.razumnik_2_0.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import by.ssrlab.razumnik_2_0.R;

/**
 * Created by Mihal on 20.11.2017.
 */

public class MyRecycleViewAdapter<T> extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private int viewId;
    private T[] mObjects;

    private OnItemClickListener mListener;
    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void OnItemClick();
    }

    public MyRecycleViewAdapter(T[] objects, int viewId) {
        this.viewId = viewId;
        mObjects = objects;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
            mView = v;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(viewId, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnItemClick();
            }
        });
        if (mObjects[position] instanceof String) {
            holder.mTextView.setText((String) mObjects[position]);
        }
        if (mObjects[position] instanceof Integer) {
            holder.mImageView.setBackgroundResource((Integer)mObjects[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.length;
    }
}
