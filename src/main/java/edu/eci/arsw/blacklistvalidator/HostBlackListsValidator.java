/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import edu.eci.arsw.threads.Worker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hcadavid
 */
public class HostBlackListsValidator {
    private static final int BLACK_LIST_ALARM_COUNT = 5;

    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     *
     * @param ipaddress suspicious host's IP address.
     * @return Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int numberThreads) {
        List<Worker> workers = new ArrayList<>();
        LinkedList<Integer> blackListOcurrences = new LinkedList<>();
        HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();

        int numberOfBlackLists = skds.getRegisteredServersCount();
        int intervalA = 0;
        int intervalB = numberOfBlackLists / numberThreads;

        for (int k = 0; k < numberThreads; k++) {
            if (k == numberThreads - 1) {
                intervalB = numberOfBlackLists;
            }

            Worker w = new Worker(intervalA, intervalB, i -> skds.isInBlackListServer(i, ipaddress), BLACK_LIST_ALARM_COUNT);
            workers.add(w);

            intervalA = intervalB;
            intervalB += numberOfBlackLists / numberThreads;
        }

        for (Worker w : workers) {
            w.start();
        }

        for (Worker w : workers) {
            try {
                w.join();
                blackListOcurrences.addAll(w.getFound());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int ocurrencesCount = blackListOcurrences.size();

        if (ocurrencesCount >= BLACK_LIST_ALARM_COUNT) {
            skds.reportAsNotTrustworthy(ipaddress);
        } else {
            skds.reportAsTrustworthy(ipaddress);
        }

        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}",
                new Object[]{numberOfBlackLists, skds.getRegisteredServersCount()});

        return blackListOcurrences;
    }


    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());


}
