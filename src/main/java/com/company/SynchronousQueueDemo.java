package com.company;
import java.util.concurrent.SynchronousQueue;
/** * Java Program to solve Producer Consumer problem using SynchronousQueue. A
 * call to put() will block until there is a corresponding thread to take() that
 * element.
 * * @author Javin Paul
 */
public class SynchronousQueueDemo{ public static void main(String args[])
{
    final SynchronousQueue queue = new SynchronousQueue(true);
    Thread producer = new Thread("PRODUCER")
    {
        public void run() {
            String event = "pr1EVENT-";
            try
            {
                queue.put(event); // thread will block here
                System.out.printf("[%s] published event : %s %n", Thread .currentThread()
                        .getName(), event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    Thread producer2 = new Thread("PRODUCER2")
    {
        public void run() {
            String event = "pr2EVENT-";
            for (int i=1;i<6;i++) {
                try
                {
                    queue.put(event+i); // thread will block here
                    System.out.printf("[%s] published event : %s %n", Thread .currentThread()
                            .getName(), event+i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    producer2.start(); // starting publisher thread
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    producer.start();


    Thread consumer = new Thread("CONSUMER") {
        public void run() {

            while (true) {
                try
                {
                    String event = (String) queue.take(); // thread will block here
                    System.out.printf("[%s] consumed event : %s %n", Thread .currentThread()
                            .getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Thread consumer2 = new Thread("CONSUMER2") {
        public void run() {

            while (true) {
                try
                {
                    String event = (String) queue.take(); // thread will block here
                    System.out.printf("[%s] consumed event : %s %n", Thread .currentThread()
                            .getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    consumer.start();
    consumer2.start();



}
}
