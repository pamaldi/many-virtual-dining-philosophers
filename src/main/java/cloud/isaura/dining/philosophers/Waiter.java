package cloud.isaura.dining.philosophers;

import cloud.isaura.dining.philosophers.channels.AnyToOneBufferedChannel;

import java.util.concurrent.Callable;

public class Waiter implements Runnable
{

    private final AnyToOneBufferedChannel<Boolean> stop;

    private Integer numberOfPhilosophers;

    private Integer numberOfPhilosophersStop = 0;

    public Waiter(AnyToOneBufferedChannel<Boolean> stop, Integer numberOfPhilosophers)
    {
        this.stop = stop;
        this.numberOfPhilosophers = numberOfPhilosophers;
    }


    @Override
    public void run()
    {
        Long startTime = System.currentTimeMillis();
        boolean allStoppedCondition = this.numberOfPhilosophersStop.equals(this.numberOfPhilosophers);
        while(!allStoppedCondition)
        {
            try
            {
                Boolean stopValue = stop.receive();
                if(stopValue)
                {
                    this.numberOfPhilosophersStop++;
                    //System.out.println("Number of philosophers stop: " + this.numberOfPhilosophersStop);
                    allStoppedCondition = this.numberOfPhilosophersStop.equals(this.numberOfPhilosophers);
                    //System.out.println("All philosophers stop: " + allStopped);
                    if(allStoppedCondition)
                    {

                        Thread.sleep(10L);
                        //System.out.println("All philosophers stop");
                        Long endTime = System.currentTimeMillis();
                        //System.out.println("Total time: " + (endTime - startTime));

                    }
                }

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }

    }
}
