package Misc.Server.Echo;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class that handles worker threads in a thread pool managing connections.
 * The worker threads sleep while trying to get a connection from the
 * wait queue, and only catch a connection when there is one in the wait queue.
 *
 * Note that for this class, The work queue is a class called WorkQueue2.
 * The difference is that WorkQueue2 uses a lock and condition object.
 *
 * @author Jacob Swineford
 */
class ThreadPool2 {

    private WorkQueue2 queue;

    private static final int MAX_THREADS = 3;

    ThreadPool2() {
        queue = new WorkQueue2();
        WorkerThread[] pool = new WorkerThread[MAX_THREADS];
        for (int i = 0; i < MAX_THREADS; i++) {
            pool[i] = new WorkerThread();
            pool[i].start();
        }
    }

    /**
     * Adds a connection to the waiting queue.
     *
     * @param connection <code> Connection </code> object containing
     *                   a client socket.
     */
    void addConnection(Connection connection) {
        queue.addConnection(connection);
    }

    /**
     * Inner class that manages the waiting queue for
     * Echo server connections. This version uses
     * Reentrant locks and conditions to manage
     * it's queue.
     *
     * TECHNICAL NOTE: This class works without using the
     *                 condition object. I'm not quite sure
     *                 what I need to do with it.
     *
     * @author Jacob Swineford
     */
    private class WorkQueue2 {

        LinkedList<Connection> queue;
        ReentrantLock lock;

        // doesn't need to be used?
        Condition condition;

        WorkQueue2() {
            queue = new LinkedList<>();
            lock = new ReentrantLock();
            condition = lock.newCondition();
        }

        /**
         * Adds a connection to the queue.
         *
         * @param connection <code> Connection </code> object to add
         */
        void addConnection(Connection connection) {
            lock.lock();
            queue.add(connection);
            lock.unlock();
        }

        /**
         * Gets the first connection in the queue. if there are no connections
         * in the queue, then this method returns null.
         */
        Connection getQueuedConnection() {
            lock.lock();
            try {
                Connection connection = queue.getFirst();
                queue.removeFirst();
                return connection;
            } catch (NoSuchElementException e) {
                return null;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Inner class that picks up connections in the waiting queue
     * and runs the <code> Connection </code> object picked up.
     * These threads will block if there are not connections
     * to execute, and be freed back up when a client disconnects
     * from the Echo server.
     *
     * @author Jacob Swineford
     */
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Connection next = queue.getQueuedConnection();
                if (next != null) {
                    try {
                        Thread r = new Thread(next);
                        r.start();
                        r.join();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }
    }
}
