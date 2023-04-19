package cloud.isaura.dining.philosophers;


import cloud.isaura.dining.philosophers.channels.AnyToOneBufferedChannel;
import cloud.isaura.dining.philosophers.channels.AnyToOneChannel;

public class StandardPhilosopher implements Philosopher
{



    private final AnyToOneChannel<Integer> leftPickUpAnyToOneChannel;

    private final AnyToOneChannel<Integer> rightPickUpAnyToOneChannel;

    private final AnyToOneBufferedChannel<Integer> enter;

    private final Long thinkTime;

    private final Long eatTime;

    private final Integer cycles;

    private final Integer pos;

    private final DiningPhilosophersLog log;

    private AnyToOneBufferedChannel<Boolean> stop;



    public StandardPhilosopher(AnyToOneChannel<Integer> leftPickUpAnyToOneChannel, AnyToOneChannel<Integer> rightPickUpAnyToOneChannel,
                               Integer pos, Long thinkTime, Long eatTime,
                               Integer cycles, AnyToOneBufferedChannel<Integer> enter, DiningPhilosophersLog log,
                               AnyToOneBufferedChannel<Boolean> stop
    )
    {
        this.leftPickUpAnyToOneChannel = leftPickUpAnyToOneChannel;
        this.rightPickUpAnyToOneChannel = rightPickUpAnyToOneChannel;
        this.enter = enter;
        this.log = log;
        this.pos = pos;
        this.thinkTime = thinkTime;
        this.eatTime = eatTime;
        this.cycles = cycles;
        this.stop = stop;

    }


    @Override
    public void think() throws InterruptedException
    {
        //System.out.println(descr() + " start thinking");
        Thread.sleep(thinkTime);
        //System.out.println(descr() + " end thinking");

    }

    @Override
    public void eat() throws InterruptedException
    {

        //System.out.println(descr() + " start eating ");
        this.log.eatingAt(pos);
        Thread.sleep( eatTime);
        //System.out.println(descr() + " end eating ");

    }

    private String descr()
    {
        return "Phil number "+this.pos;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < cycles;i++)
            {
                try
                {
                    think();
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                try
                {
                    this.enter.send(pos);
                    //System.out.println(descr() + " admitted");
                    this.leftPickUpAnyToOneChannel.send(pos);
                    //System.out.println(descr() + " get left fork");
                    this.rightPickUpAnyToOneChannel.send(pos);
                    //System.out.println(descr() + " get right fork");
                    eat();
                    this.leftPickUpAnyToOneChannel.receive();
                    //System.out.println(descr() + " put down left fork");
                    this.rightPickUpAnyToOneChannel.receive();
                    //System.out.println(descr() + " put down right fork");
                    this.log.endEatingAt(pos);
                    this.enter.receive();
                    //System.out.println(descr() + " left");
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        try
        {
            this.stop.send(true);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

    }

}
