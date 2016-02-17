package in.udiboy.ipb;

/**
 * The Thread class which controls the transition from one progress state to another
 */
public final class TransitionThread extends Thread{
    private ImageProgressBar mIPB;
    private Transition mTransition;
    private double currentProgress=0;
    private double finalProgress=0;

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
            if(currentProgress < finalProgress) {
                currentProgress = mTransition.getNext(currentProgress, finalProgress);
                mIPB.setCurrentProgress(currentProgress);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setTransition(Transition transition) {
        mTransition = transition;
    }

    public void setProgress(double p){
        finalProgress = p;
    }

    public void reset(){
        finalProgress=0;
        currentProgress=0;
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
