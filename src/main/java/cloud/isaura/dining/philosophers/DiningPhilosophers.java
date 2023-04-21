package cloud.isaura.dining.philosophers;




import cloud.isaura.dining.philosophers.channels.AnyToOneBufferedChannel;
import cloud.isaura.dining.philosophers.channels.AnyToOneChannel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class DiningPhilosophers
{
    private Philosopher[] philosophers;

    private AnyToOneChannel<Integer>[] forkAnyToOneChannels;
    private AnyToOneBufferedChannel<Integer> enter;

    private AnyToOneBufferedChannel<Boolean> stop;

    private Waiter waiter;



    public void agorazein(DiningPhilosophersParams diningPhilosophersParams)
    {

        goToAgora(diningPhilosophersParams);

    }

    public void goToAgora(DiningPhilosophersParams diningPhilosophersParams)
    {
        init(diningPhilosophersParams);
        start(diningPhilosophersParams);
        this.waiter = new Waiter(this.stop, diningPhilosophersParams.numberOfPhilosophers());
       Thread t = new Thread(waiter);
       t.start();
    }

    public void init(DiningPhilosophersParams diningPhilosophersParams)
    {
        this.enter = new AnyToOneBufferedChannel<>(diningPhilosophersParams.numberOfPhilosophers()-1);
        this.stop= new AnyToOneBufferedChannel<>(diningPhilosophersParams.numberOfPhilosophers());
        this.waiter = new Waiter(this.stop, diningPhilosophersParams.numberOfPhilosophers());
        DiningPhilosophersLog diningPhilosophersLog = new DiningPhilosophersLog(diningPhilosophersParams.numberOfPhilosophers());
        initChannels(diningPhilosophersParams);
        initPhilosophers(diningPhilosophersParams, diningPhilosophersLog);
    }

    public void start(DiningPhilosophersParams diningPhilosophersParams)
    {

        IntStream.range(0, diningPhilosophersParams.numberOfPhilosophers())
                .forEach(i ->
                    {
                        if(diningPhilosophersParams.philosopherType().equals(PhilosopherType.GREEK))
                        {
                            //Thread t = new Thread(this.philosophers[i]);
                            //t.start();
                            Executors.newFixedThreadPool(diningPhilosophersParams.numberOfPhilosophers(), r -> {
                                Thread t = new Thread(r);
                                t.setDaemon(true);
                                return t;

                            }).submit(this.philosophers[i]);
                        }
                        else if(diningPhilosophersParams.philosopherType().equals(PhilosopherType.GERMAN))
                        {
                            Thread.startVirtualThread(this.philosophers[i]::run);
                        }

                    }
                );
    }




    private void initPhilosophers(DiningPhilosophersParams diningPhilosophersParams, DiningPhilosophersLog diningPhilosophersLog)
    {
        Integer numberOfPhilosophers = diningPhilosophersParams.numberOfPhilosophers();
        this.philosophers= new Philosopher[numberOfPhilosophers];

        for(int i = 0; i < numberOfPhilosophers; i++)

                        {
                            int rightIndex = i==diningPhilosophersParams.numberOfPhilosophers()-1?0: i +1;
                            int leftIndex = i==0?diningPhilosophersParams.numberOfPhilosophers()-1: i -1;
                            this.philosophers[i]=
                                    new StandardPhilosopher(
                                            this.forkAnyToOneChannels[i],
                                            this.forkAnyToOneChannels[rightIndex],
                                            i,
                                            diningPhilosophersParams.eatingTime(),
                                            diningPhilosophersParams.thinkingTime(),
                                            diningPhilosophersParams.cycles(),
                                            enter,diningPhilosophersLog,stop);
                        }
    }



    private void initChannels(DiningPhilosophersParams diningPhilosophersParams)
    {
        this.forkAnyToOneChannels = new AnyToOneChannel[diningPhilosophersParams.numberOfPhilosophers()];
        IntStream.range(0, diningPhilosophersParams.numberOfPhilosophers())
                .forEach(i ->
                        {
                            this.forkAnyToOneChannels[i] = new AnyToOneChannel<>();


                        }
                );
    }


}
