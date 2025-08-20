package edu.eci.arsw.threads;

import edu.eci.arsw.blacklistvalidator.HostBlackListsValidator;

import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;

public class Worker extends Thread {
    private final int intervalA;
    private final int intervalB;
    private final IntFunction<Boolean> validation;
    private final List<Integer> found;
    private final int CONDITION;

    private static AtomicInteger totalOcurrences = new AtomicInteger(0);

    public Worker(int intervalA, int intervalB, IntFunction<Boolean> validation, int condition) {
        this.intervalA = intervalA;
        this.intervalB = intervalB;
        this.validation = validation;
        this.found = new ArrayList<>();
        this.CONDITION = condition;
    }

    @Override
    public void run() {
        for (int i = intervalA; i < intervalB && totalOcurrences.get() < CONDITION; i++) {
            if (validation.apply(i)) {
                found.add(i);
                totalOcurrences.incrementAndGet();
            }
        }
    }

    public List<Integer> getFound() {
        return found;
    }

    public static void resetCounter() {
        totalOcurrences.set(0);
    }
}
