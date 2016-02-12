package in.udiboy.examples.ipb;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import in.udiboy.example.ipb.R;
import in.udiboy.ipb.ImageProgressBar;

public class MainActivity extends AppCompatActivity {
    ImageProgressBar mIPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIPB = new ImageProgressBar(this, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mIPB.setLayoutParams(lp);
        mIPB.setBackImage(R.drawable.star);
        mIPB.setFrontImage(R.drawable.star_border);

        ((FrameLayout) findViewById(R.id.ipb)).addView(mIPB);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int incr = 2;

                while(true) {
                    try {
                        int progress = mIPB.getProgress()+incr;
                        mIPB.setProgress(progress);
                        if(progress>100 || progress < 0)
                            incr = -incr;
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
