/*
Dining-Philosophers Problem Simulator
Name: Ahthesham Ali Syed
Date: 12/10/2023
*/

package stars.dinningphilosophers;

public class Fork {
    private boolean inUse = false;

    public synchronized void pickUp() throws InterruptedException {
        while (inUse) {
            wait();
        }
        inUse = true;
    }

    public synchronized void putDown() {
        inUse = false;
        notifyAll();
    }
}
