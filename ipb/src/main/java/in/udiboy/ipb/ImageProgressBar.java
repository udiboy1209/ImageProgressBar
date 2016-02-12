package in.udiboy.ipb;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class ImageProgressBar extends View {
    private Bitmap front, back, frontOriginal, backOriginal;
    private int progress=0;

    public ImageProgressBar(Context context, AttributeSet attrs) {
        super(context);
    }

    public void setFrontImage(int resID){
        frontOriginal = BitmapFactory.decodeResource(getResources(),resID);
    }

    public void setFrontImage(Bitmap bmp){
        frontOriginal = Bitmap.createBitmap(bmp);
    }

    public void setBackImage(int resID){
        backOriginal = BitmapFactory.decodeResource(getResources(),resID);
    }

    public void setBackImage(Bitmap bmp){
        backOriginal = Bitmap.createBitmap(bmp);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        front = Bitmap.createScaledBitmap(frontOriginal, right-left, bottom-top, true);
        back = Bitmap.createScaledBitmap(backOriginal, right-left, bottom-top, true);
    }

    @Override
    public void onDraw(Canvas canvas){
        try{
            canvas.drawBitmap(Bitmap.createBitmap(back,0,(100-progress)*back.getHeight()/100,back.getWidth(),progress*back.getHeight()/100)
                    ,0,(100-progress)*canvas.getHeight()/100,null);

            canvas.drawBitmap(front,0,0,null);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        canvas.drawBitmap(front, 0, 0, null);
    }

    public void setProgress(int progress){
        if(progress<0)
            this.progress=0;
        else if(progress>100)
            this.progress=100;
        else
            this.progress = progress;

        post(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        });
    }

    public int getProgress() {
        return progress;
    }
}