package TreasureRoomPart;

public interface TreasureRoomDoor {
    Read acquireReader();

    void releaseReader();

    ReadWrite acquireWriter(Object valuable);

    Object acquireWriter(int index);

    void releaseWriter();

}
