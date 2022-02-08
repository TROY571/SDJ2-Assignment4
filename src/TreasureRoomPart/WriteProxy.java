package TreasureRoomPart;

public class WriteProxy implements ReadWrite {
    private Guardsman man;
    private TreasureRoom room;

    public WriteProxy(Guardsman man, TreasureRoom room) {
        this.man = man;
        this.room = room;
    }

    @Override
    public int getAllValue() throws IllegalStateException {
        if (man.hasWriteAccess(Thread.currentThread())) {
            return room.getAllValue();
        }
        return 0;
    }

    @Override
    public int getValueInRange(int start, int end) throws IllegalStateException {
        if (man.hasWriteAccess(Thread.currentThread())) {
            return room.getValueInRange(start, end);
        }
        return 0;
    }

    @Override
    public void add(Object valuable) throws IllegalStateException {
        if (man.hasWriteAccess(Thread.currentThread())) {
            room.add(valuable);
        }
    }

    @Override
    public Object takeValuable(int index) throws IllegalStateException {
        if (man.hasWriteAccess(Thread.currentThread())) {
            return room.takeValuable(index);
        }
        return null;
    }
}
