package in.udiboy.ipb;

/**
 * Class to define the transition function for progress change
 */
public class Transition {
    public double getNext(double currentProgress, double finalProgress){
        if(finalProgress-currentProgress>0.01)
            return currentProgress+0.01; // One percent per iteration
        else
            return finalProgress;
    }
}
