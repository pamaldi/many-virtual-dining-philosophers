package cloud.isaura.dining.philosophers;
import cloud.isaura.dining.philosophers.channels.AnyToOneChannel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class AnyToOneChannelMultipleSenderTest
{
    @Test
    public void when_send_ten_integer_on_channel_then()
    {
        AnyToOneChannel anyToOneChannel = new AnyToOneChannel();

        assertNotNull(anyToOneChannel);
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < 10; i++)
        {
            int finalI = i;
            Thread sender = new Thread(() -> {
                try
                {
                    anyToOneChannel.send(finalI);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }

            });
            threadList.add(sender);
        }

        for(int i = 0; i < 10; i++)
        {
           threadList.get(i).start();

        }
        List<Object> received = new ArrayList<>();
        Thread blockedReceiver = new Thread(() -> {
            Object receive = null;
            while (received.size() < 10)
            {
                try
                {
                    receive = anyToOneChannel.receive();
                    received.add(receive);
                    System.out.println("received " + received);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                assertNotNull(receive);
            }


        });
        blockedReceiver.start();

    }
}
