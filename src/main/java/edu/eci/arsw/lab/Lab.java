package edu.eci.arsw.lab;

import edu.eci.arsw.blacklistvalidator.HostBlackListsValidator;

public class Lab {

    private HostBlackListsValidator validator = new HostBlackListsValidator();

    public void runTest(String ip, int N) {
        long start = System.currentTimeMillis();

        validator.checkHost(ip, N);

        long end = System.currentTimeMillis();
        System.out.println("Tiempo con " + N + " hilos: " + (end - start) + " ms");
    }




    public static void main() {
        String ip = "202.24.34.55";



    }

}
