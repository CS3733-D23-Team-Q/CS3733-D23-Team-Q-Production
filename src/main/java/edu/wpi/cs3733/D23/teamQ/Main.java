package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;

public class Main {

  static int counter = 0;

  public static void main(String[] args) throws InterruptedException {
    Qdb.getInstance();
    App.launch(App.class, args);
    /*
    Object lock = new Object();
    Thread thread1 =
        new Thread(
            () -> {
              synchronized (lock) {
                try {
                  System.out.println("Thread 1 is waiting");
                  // while (counter == 0 || counter != 6) {
                  lock.wait();
                  counter++;
                  // }
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println("Thread 1 has been notified: " + counter);
              }
            });
    Thread thread2 =
        new Thread(
            () -> {
              synchronized (lock) {
                counter = counter + 6;
                System.out.println("Thread 2 is performing an operation: " + counter);
                lock.notify();
              }
            });
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
     */
  }
}

  // shortcut: psvm
