package cloud.isaura.dining.philosophers;

public record DiningPhilosophersParams

        (
            PhilosopherType philosopherType,
            Integer numberOfPhilosophers,
            Long thinkingTime,
            Long eatingTime,
            Integer cycles

        )
{
        static DiningPhilosophersParams parse(String[] args) {
                return new DiningPhilosophersParams(
                        args.length >= 1 ? PhilosopherType.fromString(args[0]) : PhilosopherType.fromString("GREEK"),
                        args.length >= 2 ? Integer.parseInt(args[1]) : 5,
                        args.length >= 3 ? Integer.parseInt(args[2]) : 100L,
                        args.length >= 4 ? Integer.parseInt(args[3]) : 200L,
                        args.length >= 5 ? Integer.parseInt(args[5]) : 10
                );
        }

        public static DiningPhilosophersParams create(PhilosopherType  philosopherType , Integer numberOfPhilosophers, Long thinkingTime, Long eatingTime, Integer cycles) {
                return new DiningPhilosophersParams(
                        philosopherType,
                        numberOfPhilosophers,
                        thinkingTime,
                        eatingTime,
                        cycles
                );
        }
}
