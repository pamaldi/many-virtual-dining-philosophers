package cloud.isaura.dining.philosophers.benchmarks;

import cloud.isaura.dining.philosophers.DiningPhilosophers;
import cloud.isaura.dining.philosophers.DiningPhilosophersParams;
import cloud.isaura.dining.philosophers.PhilosopherType;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.JavaFlightRecorderProfiler;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.profile.WinPerfAsmProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(1)
public class DiningPhilosophersContextSwitchBenchmark
{

    @Param({"GREEK", "GERMAN"})
    private String philosopherType;

    @Param({"10"}) // 874, 5074, 49324 cells
    private int numberOfPhilosophers;

    private DiningPhilosophersParams diningPhilosophersParams;
    private DiningPhilosophers diningPhilosophers;

    @Setup
    public void setup() {



    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    public void benchmark() {
        this.diningPhilosophersParams = DiningPhilosophersParams.create
                (PhilosopherType.fromString(philosopherType),
                        numberOfPhilosophers, 10L,
                        20L, 5);
        diningPhilosophers = new DiningPhilosophers();

            diningPhilosophers.agorazein(this.diningPhilosophersParams);

    }

    public static void main(String[] args) throws  RunnerException
    {
        Options opt = new OptionsBuilder()
                .include(DiningPhilosophersContextSwitchBenchmark.class.getSimpleName())
                .addProfiler(JavaFlightRecorderProfiler.class)
                .build();

        new Runner(opt).run();
    }
}
