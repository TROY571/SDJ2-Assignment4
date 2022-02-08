package TreasureRoomPart;

import Log.MessageLog;

import java.util.ArrayList;

public class Guardsman implements TreasureRoomDoor {
    private int reader;
    private int writer;
    private int waitingWriter;
    private ArrayList<Thread> allowedReadAccess;
    private ArrayList<Thread> allowedWriteAccess;
    private ReadProxy readProxy;
    private WriteProxy writeProxy;
    private TreasureRoom room;

    public Guardsman(TreasureRoom room) {
        this.room = room;
        this.reader = 0;
        this.writer = 0;
        this.waitingWriter = 0;
        this.allowedReadAccess = new ArrayList<>();
        this.allowedWriteAccess = new ArrayList<>();
        this.readProxy = new ReadProxy(this, room);
        this.writeProxy = new WriteProxy(this, room);
    }

    @Override
    public synchronized Read acquireReader() {
        reader++;
        allowedReadAccess.add(0, Thread.currentThread());
        try {
            wait();
        } catch (Exception e) {
            MessageLog.getInstance().addMessage(e.getMessage(), "Guardsman");
        }
        notifyAll();
        return readProxy;
    }

    @Override
    public synchronized void releaseReader() {
        reader--;
        readProxy.getAllValue();
        MessageLog.getInstance().addMessage(
                "The total value of the treasures is " + room.getAllValue() +
                        ", and there are " + reader + " readers.", "Guardsman");
        allowedReadAccess.remove(0);
        MessageLog.getInstance().addMessage("There are " + reader +
                " readers after one leaves.", "Guardsman");
        notifyAll();
    }

    @Override
    public synchronized ReadWrite acquireWriter(Object valuable) {
        //this is for save valuables in the room
        waitingWriter++;
        writeProxy.add(valuable);
        allowedWriteAccess.add(0, Thread.currentThread());
        MessageLog.getInstance().addMessage(
                "One writer is waiting and there are " + waitingWriter
                        + " is waiting now.", "Guardsman");

        waitingWriter--;
        writer++;
        notifyAll();
        return writeProxy;
    }

    @Override
    public synchronized Object acquireWriter(int index) {
        //this is for take valuables from the room
        waitingWriter++;
        writeProxy.takeValuable(index);
        allowedWriteAccess.add(0, Thread.currentThread());
        MessageLog.getInstance().addMessage(
                "One writer is waiting and there are " + waitingWriter
                        + " is waiting now.", "Guardsman");


        waitingWriter--;
        writer++;
        notifyAll();
        return writeProxy.takeValuable(index);
    }

    @Override
    public synchronized void releaseWriter() {
        writer--;
        try {
            allowedWriteAccess.remove(0);
        } catch (IndexOutOfBoundsException e) {
            MessageLog.getInstance().addMessage("No writer need to release.", "Guardsman");
        }
        MessageLog.getInstance().addMessage(
                "One writer is finished and there are " +
                        waitingWriter + " is waiting now.", "Guardsman");
        notifyAll();
    }


    public boolean hasReadAccess(Thread t) {
        return allowedReadAccess.contains(t);
    }

    public boolean hasWriteAccess(Thread t) {
        return allowedWriteAccess.contains(t);
    }

}
