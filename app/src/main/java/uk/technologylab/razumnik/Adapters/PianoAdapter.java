package uk.technologylab.razumnik.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mihal on 01.02.2018.
 */

public class PianoAdapter extends RecyclerView.Adapter<PianoAdapter.ViewHolder> {

    private int viewId;
    private int type;

    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener {
        void OnItemClick(int i);
    }

    public PianoAdapter(int viewId, int type) {
        this.viewId = viewId;
        this.type = type;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;

        public ViewHolder(View v) {
            super(v);
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (type == 1 && (position == 2)) {
            holder.mView.setVisibility(View.GONE);
            holder.mView.setEnabled(false);
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    mListener.OnItemClick(holder.getAdapterPosition());
                } else {
                    mListener.OnItemClick(holder.getAdapterPosition() + 8);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        if (type == 0) {
            return 7;
        } else {
            return 6;
        }

    }
}
