package MinePart;

import Log.MessageLog;
import TreasureRoomPart.TreasureRoomDoor;

import java.util.ArrayList;
import java.util.Random;

public class ValuablesTransporter implements Runnable {
    //this is the consumer
    private Deposit deposit;
    private ArrayList<Object> valuableList;
    private int targetNum;
    private TreasureRoomDoor door;

    public ValuablesTransporter(Deposit deposit, TreasureRoomDoor door) {
        this.deposit = deposit;
        this.valuableList = new ArrayList<>();
        this.door = door;
        //the number of valuables the transporter wants to take is random, the range is [150,80] each time
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            this.targetNum = random.nextInt(70) + 150;
            for (int i = 0; i <= targetNum + 5; i++) {
                //here i set the transporter stops when the number is 5 larger than the target
                Object valuable = deposit.get(targetNum);
                try {
                    valuableList.add(valuable.toString());
                } catch (NullPointerException e) {
//                MessageLog.getInstance().addMessage("There is not enough treasures to be transported.", "Transporter");
                }
            }

            MessageLog.getInstance().addMessage("The transporter delivers " + valuableList.size() + " treasure.",
                    "Transporter");

            try {
                Thread.sleep(1800);
            } catch (Exception e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Transporter");
            }

            //the transporter delivers the goods to the room door

            for (int i = 0; i < valuableList.size(); i++) {
                door.acquireWriter(valuableList.get(i));

            }
            door.releaseWriter();

            try {
                notifyAll();
            } catch (IllegalMonitorStateException e) {
                MessageLog.getInstance().addMessage("There's no other transporter.", "Transporter");
            }
        }
    }
}

