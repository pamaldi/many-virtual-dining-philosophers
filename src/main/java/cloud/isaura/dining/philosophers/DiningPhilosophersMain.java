package cloud.isaura.dining.philosophers;

public class DiningPhilosophersMain
{



    public static void main(String[] args)
    {

        DiningPhilosophersParams diningPhilosophersParams = DiningPhilosophersParams.parse(args);
        if(diningPhilosophersParams.numberOfPhilosophers() < 5)
        {
            System.out.println("Number of philosophers must be greater than 4");
            return;
        }
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        diningPhilosophers.agorazein(diningPhilosophersParams);

    }
}
