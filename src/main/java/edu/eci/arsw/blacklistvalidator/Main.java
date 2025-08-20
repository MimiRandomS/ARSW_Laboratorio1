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
    
    public static void main(String a[]){
        String ipaddress = "202.24.34.55";
        int numberThreads = 8;
        HostBlackListsValidator hblv =new HostBlackListsValidator();
        List<Integer> blackListOcurrences = hblv.checkHost(ipaddress, numberThreads);
        if (!blackListOcurrences.isEmpty()) System.out.println("The host " + ipaddress +  " was found in the following blacklists: "+blackListOcurrences);
        else System.out.println("The host " + ipaddress + " wasn't found in the blacklists.");
    }
}
