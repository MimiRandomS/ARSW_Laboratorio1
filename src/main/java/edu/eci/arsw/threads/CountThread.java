package edu.eci.arsw.threads;

public class CountThread extends Thread {
    private int nummberA;
    private int nummberB;
    @Override
    public void run(){
        int a = this.nummberA;
        int b = this.nummberB;
        for (int i = a; i <= b; i++) {
            System.out.println(i);
        }
    }

    public void setRange(int a, int b){
        nummberA = a;
        nummberB = b;
    }
}
