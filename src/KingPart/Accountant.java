package KingPart;

import Log.MessageLog;
import TreasureRoomPart.TreasureRoomDoor;

public class Accountant implements Runnable {
    private TreasureRoomDoor door;

    public Accountant(TreasureRoomDoor door) {
        this.door = door;
    }

    @Override
    public synchronized void run() {
        while (true) {
            door.acquireReader();
            try {
                Thread.sleep(700);
            } catch (Exception e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Accountant");
            }
            door.releaseReader();
            MessageLog.getInstance().addMessage("Accountant finishes the count.", "Accountant");

            try {
                Thread.sleep(700);
            } catch (Exception e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Accountant");
            }
            notifyAll();
        }

    }
}
