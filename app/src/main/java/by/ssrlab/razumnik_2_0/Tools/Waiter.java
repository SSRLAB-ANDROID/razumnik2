package by.ssrlab.razumnik_2_0.Tools;

import android.os.AsyncTask;

/**
 * Created by Mihal on 16.10.2017.
 */

 public class Waiter extends AsyncTask<Integer, Void, Void> {

    public interface OnWaitCompleteListener{
        void OnWaitComplete();
    }
    private OnWaitCompleteListener mListener;
    private void setListener(OnWaitCompleteListener listener){
        mListener = listener;
    }
    public Waiter(OnWaitCompleteListener listener){
        setListener(listener);
    }

    @Override
    protected Void doInBackground(Integer... params) {
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(params[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mListener.OnWaitComplete();
    }
}
