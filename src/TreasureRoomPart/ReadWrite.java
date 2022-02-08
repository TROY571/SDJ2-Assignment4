package TreasureRoomPart;

public interface ReadWrite extends Read {
    void add(Object valuable);

    Object takeValuable(int index);
}
