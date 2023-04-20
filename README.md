# Many virtual dining philosophers.

Many virtual dining philosophers is a Java implementation of a classic concurrency problem:
[Dining Philosophers](https://en.wikipedia.org/wiki/Dining_philosophers_problem)
using [communicating sequential processes (CSP)](https://en.wikipedia.org/wiki/Communicating_sequential_processes).

It's an extension of the original one problem, from five to many philosophers.

The purpose of this extension is to compare a Virtual Threads based implementation with a Native Threads based one.

## CSP solution

The CSP solution is based on a number of channels.
For each philosopher there are two channels, one for the left fork and one for the right fork.
To avoid deadlock there is an any to one channel for every philosopher, which is used to signal that the philosopher is done eating.
The any to one channel il an n-1 blocking queue, where n is the number of philosophers.

## Virtual Threads implementation

Every philosopher is a virtual thread, which is started by the main thread, which is also the coordinator.

## Native Threads implementation

Every philosopher is a native thread, which is started by the main thread, which is also the coordinator.

## Build

 [OpenJDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html) 
is required.


## Command Line Arguments
1.   PhilosopherType philosopherType: GREEK for native Threads, GERMAN for virtual Threads
2.   Integer numberOfPhilosophers
3.   Long thinkingTime (in milliseconds)
4.   Long eatingTime (in milliseconds)
5.   Integer cycles:  number of eating cycles for each philosopher



