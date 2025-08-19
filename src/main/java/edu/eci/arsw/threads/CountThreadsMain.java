package edu.eci.arsw.threads;


public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread countThread1 = new CountThread();
        countThread1.setRange(0, 99);
        CountThread countThread2 = new CountThread();
        countThread2.setRange(99, 199);
        CountThread countThread3 = new CountThread();
        countThread3.setRange(200, 299);

        // ============ { Start/run } ============
        System.out.println("Primer hilo");
        countThread1.start();
        System.out.println("Segundo hilo");
        countThread2.start();
        System.out.println("Tercer hilo");
        countThread3.start();
    }
    
}
