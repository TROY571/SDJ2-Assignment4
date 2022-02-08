import KingPart.Accountant;
import KingPart.King;
import MinePart.Deposit;
import MinePart.Mine;
import MinePart.Miner;
import MinePart.ValuablesTransporter;
import TreasureRoomPart.Guardsman;
import TreasureRoomPart.TreasureRoom;
import TreasureRoomPart.TreasureRoomDoor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Deposit deposit = new Deposit();
        Miner miner = new Miner(deposit);
        Miner miner2 = new Miner(deposit);
        TreasureRoom room = new TreasureRoom();
        TreasureRoomDoor door = new Guardsman(room);

        ValuablesTransporter transporter = new ValuablesTransporter(deposit, door);
        ValuablesTransporter transporter1 = new ValuablesTransporter(deposit, door);

        Accountant accountant = new Accountant(door);
        King king = new King(door);

        Thread t1 = new Thread(miner);
        Thread t2 = new Thread(transporter);
        Thread t3 = new Thread(transporter1);
        Thread t4 = new Thread(accountant);
        Thread t5 = new Thread(king);
        Thread t6 = new Thread(miner2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
