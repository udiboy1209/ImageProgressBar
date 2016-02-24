package in.udiboy.ipb;

/**
 * Class to define the transition function for progress change
 */
public class Transition {
    public double getNext(double initialProgress, double finalProgress, double time, double duration){
        if(time>duration)
            return finalProgress;
        return initialProgress+(finalProgress-initialProgress)*time/duration;
    }
}
