/*
Dining-Philosophers Problem Simulator
Name: Ahthesham Ali Syed
Date: 12/10/2023
*/

package stars.dinningphilosophers;

public interface PhilosopherEventHandler {

    void eating(Philosopher philosopher);

    void thinking(Philosopher philosopher);
}
