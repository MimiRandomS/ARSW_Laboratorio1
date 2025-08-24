/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {
    
    public static void main(String a[]) throws InterruptedException {
        // Data
        String ipaddress = "202.24.34.55";
        int processors = Runtime.getRuntime().availableProcessors();

        // Threads
        int numberThreads = 1;  // 1) Case '1 Thread'
        // int numberThreads = processors;  // 2) Case 'As many threads as processing cores'
        // int numberThreads = processors * 2;  // 3) Case 'As many threads as twice as many processing cores'
        // int numberThreads = 50;  // 4) Case '50 Threads'
        // int numberThreads = 100;  // 5) Case '100 Threads'


        System.out.println("Number of Threads: " + String.valueOf(numberThreads));
        // Thread.sleep(3000);
        HostBlackListsValidator hblv =new HostBlackListsValidator();
        List<Integer> blackListOcurrences = hblv.checkHost(ipaddress, numberThreads);
        if (!blackListOcurrences.isEmpty()) {
            System.out.println("The host " + ipaddress +  " was found in the following blacklists: "+blackListOcurrences);

        } else {
            System.out.println("The host " + ipaddress + " wasn't found in the blacklists.");

        }

    }

}
