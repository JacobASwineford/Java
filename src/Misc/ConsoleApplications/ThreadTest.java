package Misc.ConsoleApplications;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Jacob Swineford
 */
public class ThreadTest {

    private static ReadWriteLock rwlock = new ReentrantReadWriteLock();
    private static Lock read = rwlock.readLock();
    private static Lock write = rwlock.writeLock();

    private static int i = 0;

    public static void main(String[] args) throws Exception {
        new ReadStuff().start();
        new ReadStuff().start();
        new WriteStuff().start();
        new ReadStuff().start();
        new WriteStuff().start();
        new WriteStuff().start();
    }

    public static class WriteStuff extends Thread {

        @Override
        public void run() {
            write.lock();
            System.out.println("write acquired by " + Thread.currentThread().toString());
            try {
                Thread.sleep(1000);
                i += 1;
                System.out.println("changed int to " + i);
            } catch (Exception e) {
                write.unlock();
            } finally {
                write.unlock();
            }
        }
    }

    public static class ReadStuff extends Thread {

        @Override
        public void run() {
            read.lock();
            System.out.println("read acquired by " + Thread.currentThread().toString());
            try {
                Thread.sleep(2000);
                System.out.println("The int is " + i);
            } catch (Exception e) {
                read.unlock();
            } finally {
                read.unlock();
            }
        }
    }
}
