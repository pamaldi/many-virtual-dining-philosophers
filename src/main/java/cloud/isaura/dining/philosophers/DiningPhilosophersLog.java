package cloud.isaura.dining.philosophers;

import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersLog
{


    private int[] numberOfEat = null;

    private int concurrentEating = 0;

    private int numberOfPhilosophers = 0;

    private ReentrantLock reentrantLock;



    public DiningPhilosophersLog(Integer numberOfPhilosophers)
    {
        this.numberOfEat= new int[numberOfPhilosophers];
        this.numberOfPhilosophers = numberOfPhilosophers;
        this.reentrantLock = new ReentrantLock();

    }

    public void eatingAt(int i)
    {
        this.reentrantLock.lock();
        this.numberOfEat[i]= this.numberOfEat[i]+1;
        this.concurrentEating++;
        System.out.println("Concurrent eating: " + this.concurrentEating);
        if(this.concurrentEating > this.numberOfPhilosophers/2)
        {
            System.out.println("ERROR: Concurrent eating: " + this.concurrentEating);
        }
        this.reentrantLock.unlock();
    }

    public void endEatingAt(int i)
    {
        this.reentrantLock.lock();
        this.concurrentEating--;
        System.out.println("Concurrent eating: " + this.concurrentEating);
        this.reentrantLock.unlock();
    }



}
