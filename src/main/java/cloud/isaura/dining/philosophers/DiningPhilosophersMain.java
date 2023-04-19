package cloud.isaura.dining.philosophers;

public class DiningPhilosophersMain
{
    static DiningPhilosophersParams parse(String[] args) {
        return new DiningPhilosophersParams(
                args.length >= 1 ? PhilosopherType.fromString(args[0]) : PhilosopherType.fromString("GREEK"),
                args.length >= 2 ? Integer.parseInt(args[1]) : 500000,
                args.length >= 3 ? Integer.parseInt(args[2]) : 100L,
                args.length >= 4 ? Integer.parseInt(args[3]) : 200L,
                args.length >= 5 ? Integer.parseInt(args[5]) : 10
               );
    }


    public static void main(String[] args)
    {

        DiningPhilosophersParams diningPhilosophersParams = parse(args);
        if(diningPhilosophersParams.numberOfPhilosophers() < 5)
        {
            System.out.println("Number of philosophers must be greater than 4");
            return;
        }
        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();
        diningPhilosophers.agorazein(diningPhilosophersParams);

    }
}
