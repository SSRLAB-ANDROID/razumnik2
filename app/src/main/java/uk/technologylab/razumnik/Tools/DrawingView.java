package uk.technologylab.razumnik.Tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class DrawingView extends View {

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private Context context;
    private Paint circlePaint;
    private Path circlePath;
    private int need_pixels;
    private Paint mPaint;

    private DrawingView dv;

    private Bitmap superBitmap;
    MyMediaPlayer myMediaPlayer;

    public DrawingView(Context c, Bitmap superBitmap) {
        super(c);
        context = c;
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        circlePaint = new Paint();
        circlePath = new Path();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f);

        this.superBitmap = superBitmap;
        need_pixels = getAll();

        int strokeWidth = 75;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(strokeWidth);
        myMediaPlayer = new MyMediaPlayer(this.context);
        dv = this;
    }

    public void check() {
        int height = mBitmap.getHeight();
        int width = mBitmap.getWidth();
        int count = 0;
        int count_error = 0;
        for (int i = 0; i < height - 1; i = i + 2) {
            for (int j = 0; j < width - 1; j = j + 2) {
                int pixel = mBitmap.getPixel(j, i);
                int m_pixel = superBitmap.getPixel(j, i);
                if (Color.green(pixel) == 255 && Color.green(m_pixel) == 200) {
                    count++;
                }
                if (Color.green(pixel) == 255 && Color.red(pixel) == 0 && Color.blue(pixel) == 0
                        && Color.green(m_pixel) != 200 && Color.red(m_pixel) != 200 && Color.blue(m_pixel) != 200) {
                    count_error++;
                }

            }
        }
        Random rnd = new Random(System.currentTimeMillis());
        if ((count * 100 / need_pixels) > 30 && (count_error * 100) / (((width/2) * (height/2)) - need_pixels) < 50) {
            myMediaPlayer = new MyMediaPlayer(this.context);
            myMediaPlayer.play("voice/dobra_"+ rnd.nextInt(3 - 0 + 1)+".mp3");
            //Toast.makeText(context, "Добра!", Toast.LENGTH_SHORT).show();
        }
        else {
            myMediaPlayer = new MyMediaPlayer(this.context);
            myMediaPlayer.play("voice/ne_dobra_"+ rnd.nextInt(4 - 0 + 1)+".mp3");
           // Toast.makeText(context, "Кепска!", Toast.LENGTH_SHORT).show();
        }
    }

    public int getAll() {
        int pixels = 0;
        int height = superBitmap.getHeight();
        int width = superBitmap.getWidth();
        for (int i = 0; i < height - 1; i = i + 2) {
            for (int j = 0; j < width - 1; j = j + 2) {
                int m_pixel = superBitmap.getPixel(j, i);
                if (Color.green(m_pixel) == 200 & Color.red(m_pixel) == 200 & Color.blue(m_pixel) == 200) {
                    pixels++;
                }
            }
        }
        return pixels;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(circlePath, circlePaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;

            circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        circlePath.reset();
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        // kill this so we don't double draw
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                dv.check();
                invalidate();
                break;
        }
        return true;
    }

}
