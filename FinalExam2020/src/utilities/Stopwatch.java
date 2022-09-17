/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author Curt Jones
 */

public class Stopwatch { 

    private long startMilliseconds;

    /**
     * Initializes a new stopwatch.
     */
    public Stopwatch() {
        startMilliseconds = System.currentTimeMillis();
    } 
    
    /**
     * Resets the starting time on the stop watch.
     * 
     */
    
    public void start(){
        startMilliseconds = System.currentTimeMillis();
    }
    
    /**
     * Returns the number of seconds since the stop watch was created or started. 
     * Resets the stating time.
     * 
     * @return elapsed CPU time (in seconds) since the stopwatch was created or started.
     * 
     */
     public long Stop(){
        long stopMilliseconds = System.currentTimeMillis();
        long time = (stopMilliseconds- startMilliseconds)/1000;
        startMilliseconds = System.currentTimeMillis(); 
        return time;
    }   
    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created or started.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created or started.
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - startMilliseconds) / 1000.0;
    }

    
    /**
     * Unit tests the {@code Stopwatch} data type.

     */
    public static void main(String[] args) {
        int n = 5000;

        // sum of square roots of integers from 1 to n using Math.sqrt(x).
        Stopwatch timer1 = new Stopwatch();
        double sum1 = 0.0;
        for (int i = 1; i <= n; i++) {
            sum1 += Math.sqrt(i);
        }
        double time1 = timer1.elapsedTime();
        System.out.printf("(%.2f seconds)\n", time1);

        // sum of square roots of integers from 1 to n using Math.pow(x, 0.5).
        Stopwatch timer2 = new Stopwatch();
        double sum2 = 0.0;
        for (int i = 1; i <= n; i++) {
            sum2 += Math.pow(i, 0.5);
        }
        double time2 = timer2.elapsedTime();
        System.out.printf("(%.2f seconds)\n", time2);
    }
} 