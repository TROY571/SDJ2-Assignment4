package KingPart;

import Log.MessageLog;
import TreasureRoomPart.TreasureRoomDoor;

import java.util.ArrayList;

public class King implements Runnable {
    private TreasureRoomDoor door;
    private ArrayList<Object> valuables;

    public King(TreasureRoomDoor door) {
        this.door = door;
        this.valuables = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            //the king will hold the party three times
            int pay = (int) (Math.random() * (200 - 150 + 1) + 150);
            //the range of money the king pay for the party is [650,800]

            MessageLog.getInstance().addMessage("The king needs to pay "
                    + pay + " for the next party.", "King");

            try {
                for (int i = 0; i < 3; i++) {
                    //the king takes three items from the room each time
                    valuables.add(door.acquireWriter(i));
                }
            } catch (IndexOutOfBoundsException e){
                MessageLog.getInstance().addMessage("The treasure left isn't enough.", "King");
            }

            int sum = 0;
            try {
                for (int i = 0; i < valuables.size(); i++) {
                    if (valuables.get(i).toString().equals("Diamond")) {
                        sum += 200;
                    } else if (valuables.get(i).toString().equals("Gold nugget")) {
                        sum += 150;
                    } else if (valuables.get(i).toString().equals("Jewel")) {
                        sum += 100;
                    } else if (valuables.get(i).toString().equals("Ruby")) {
                        sum += 50;
                    }
                }
            } catch (NullPointerException e){
                MessageLog.getInstance().addMessage("There is no valuable in the room", "King");
            }
            if (sum < pay) {
                MessageLog.getInstance().addMessage("Insufficient founds.", "King");
                for (int i = 0; i < valuables.size(); i++) {
                    door.acquireWriter(valuables.get(i));
                }
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    MessageLog.getInstance().addMessage(e.getMessage(), "King");
                }
            } else {
                MessageLog.getInstance().addMessage("King holds a party.", "King");
                valuables.clear();
            }

            //maybe here needn't notifyAll();  ?
        }
    }
}
