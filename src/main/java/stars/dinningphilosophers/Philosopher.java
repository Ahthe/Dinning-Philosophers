/*
Dining-Philosophers Problem Simulator
Name: Ahthesham Ali Syed
Date: 12/10/2023
*/

package stars.dinningphilosophers;

import java.util.Random;

public class Philosopher extends Thread {
    private final Fork leftFork, rightFork;
    private final Random random = new Random();

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        super(name);
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                think();
                leftFork.pickUp();
                rightFork.pickUp();
                eat();
                rightFork.putDown();
                leftFork.putDown();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected void think() throws InterruptedException {
        System.out.println(getName() + " is thinking.");
        Thread.sleep(random.nextInt(1000)); // Simulate thinking
    }

    protected void eat() throws InterruptedException {
        System.out.println(getName() + " is eating.");
        Thread.sleep(random.nextInt(1000)); // Simulate eating
    }
}
