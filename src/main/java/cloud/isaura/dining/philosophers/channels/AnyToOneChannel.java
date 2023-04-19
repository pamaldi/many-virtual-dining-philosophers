package cloud.isaura.dining.philosophers.channels;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class AnyToOneChannel<T> implements Channel<T>
{

    private final Lock lock = new ReentrantLock(true);
    private final Condition notFull  = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private T value;


    @Override
    public void send(T x) throws InterruptedException {
        lock.lock();
        try {
            if (value != null) {
                notFull.await();

            }
            value = x;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }


    @Override
    public T receive() throws InterruptedException {
        lock.lock();

        try {
            if (value == null) {
                notEmpty.await();
            }
            T x = value;
            value = null;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
