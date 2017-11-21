package by.ssrlab.razumnik_2_0.Tools;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

/**
 * Created by Mihal on 21.11.2017.
 */

public class MyMediaPlayer {
    MediaPlayer mMediaPlayer;
    Context mContext;

    public MyMediaPlayer(Context context) {
        mContext = context;
    }

    public void play(String path) {

        try {
            AssetFileDescriptor afd = mContext.getAssets().openFd(path);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
