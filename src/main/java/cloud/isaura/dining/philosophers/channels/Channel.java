package cloud.isaura.dining.philosophers.channels;

public interface Channel<T>
{
    void send(T x) throws InterruptedException;

    T receive() throws InterruptedException;
}
