package cloud.isaura.dining.philosophers.channels;

import java.util.concurrent.LinkedBlockingQueue;

public class AnyToOneBufferedChannel<T> implements Channel<T>
{


    private final  LinkedBlockingQueue<T> queue;

    public AnyToOneBufferedChannel(Integer dim)
    {
        this.queue = new LinkedBlockingQueue<>(dim);
    }

    @Override
    public void send(T x) throws InterruptedException
    {
        queue.put(x);
    }

    @Override
    public T receive() throws InterruptedException
    {
        return queue.take();
    }
}
