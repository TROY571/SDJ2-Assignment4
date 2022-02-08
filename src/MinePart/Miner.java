package MinePart;

import Log.MessageLog;

import java.util.Random;

public class Miner implements Runnable {
    //this is producer
    private Deposit deposit;

    public Miner(Deposit deposit) {
        this.deposit = deposit;
    }

    @Override
    public synchronized void run() {
        while (true) {
            for (int i = 0; i < (int) (Math.random() * ((60 - 1 + 1) + 1)); i++) {  //for get random number of valuables, the range is [1,60]
                deposit.add(0, Mine.getInstance((int) (Math.random() * ((3 - 0 + 1) + 0))));  //for get mines randomly
            }
            MessageLog.getInstance().addMessage("Mines get! And the number of the treasure is " + deposit.size(), "Miner");

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Miner");
            }
            notifyAll();
        }
    }
}

