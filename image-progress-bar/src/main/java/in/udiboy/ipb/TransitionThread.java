package in.udiboy.ipb;

import android.util.Log;

import java.util.ArrayList;

/**
 * The Thread class which controls the transition from one progress state to another
 */
public final class TransitionThread extends Thread{
    private static final int MIN_STEP_DURATION = 25;
    private static final int MAX_STEP_DURATION = 1000;
    private static final double INCREASE_FACTOR = 5.0/4;

    private int step_duration=500;
    private int anim_fps=50;

    private ImageProgressBar mIPB;
    private Transition mTransition;
    private double currentProgress=0;
    private double finalProgress=0;
    private final ArrayList<Double> steps=new ArrayList<>();

    private boolean run=true;

    public TransitionThread(ImageProgressBar ipb){
        mIPB = ipb;
    }

    public TransitionThread(ImageProgressBar ipb, Transition transition){
        mIPB = ipb;
        mTransition = transition;
    }

    public void run(){
        while(run){
            synchronized (steps) {
                if (steps.size() > 0) {
                    double startProgress = currentProgress;
                    double endProgress = steps.remove(0);
                    long startTime = System.currentTimeMillis();

                    Log.d("TransitionThread", "step_duration: " + step_duration);
                    Log.d("TransitionThread", "startProgress: " + startProgress);
                    Log.d("TransitionThread", "endProgress: " + endProgress);
                    while (currentProgress < endProgress) {
                        long timeTaken = System.currentTimeMillis();
                        currentProgress = mTransition.getNext(startProgress, endProgress, System.currentTimeMillis() - startTime, step_duration);
                        mIPB.setCurrentProgress(currentProgress);
                        timeTaken=System.currentTimeMillis()-timeTaken;
                        try {
                            Thread.sleep(1000/anim_fps - timeTaken);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            step_duration= Math.min(MAX_STEP_DURATION, (int)(step_duration*INCREASE_FACTOR));
        }
    }

    public void setTransition(Transition transition) {
        mTransition = transition;
    }

    public void setProgress(double p){
        synchronized (steps) {
            finalProgress=p;
            steps.add(p);
            step_duration = Math.max(MIN_STEP_DURATION, (int)(step_duration / INCREASE_FACTOR));
        }
    }

    public void reset(){
        steps.clear();
        currentProgress=0;
        finalProgress=0;
        mIPB.setCurrentProgress(currentProgress);
    }

    public double getFinalProgress(){
        return finalProgress;
    }

    public void quit() throws InterruptedException {
        run=false;
        join();
    }
}
