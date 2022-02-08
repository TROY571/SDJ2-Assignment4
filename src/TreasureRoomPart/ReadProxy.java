package TreasureRoomPart;

public class ReadProxy implements Read {
    private Guardsman man;
    private TreasureRoom room;

    public ReadProxy(Guardsman man, TreasureRoom room) {
        this.man = man;
        this.room = room;
    }

    @Override
    public int getAllValue() {
        if (man.hasReadAccess(Thread.currentThread())) {
            return room.getAllValue();
        }
        return 0;
    }

    @Override
    public int getValueInRange(int start, int end) {
        if (man.hasReadAccess(Thread.currentThread())) {
            return room.getValueInRange(start, end);
        }
        return 0;
    }
}
