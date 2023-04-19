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
}
