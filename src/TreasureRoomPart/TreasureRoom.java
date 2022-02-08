package TreasureRoomPart;

import Log.MessageLog;

import java.util.ArrayList;

public class TreasureRoom implements ReadWrite {
    private ArrayList<Object> treasures;
    private ArrayList<Integer> treasuresValue;

    public TreasureRoom(int capacity) {
        this.treasures = new ArrayList<>(capacity);
        this.treasuresValue = new ArrayList<>();
    }

    public TreasureRoom() {
        this.treasures = new ArrayList<>(500);
        this.treasuresValue = new ArrayList<>();
    }

    @Override
    public void add(Object valuable) {
        try {
            treasures.add(valuable);
            if (valuable.toString().equals("Diamond")) {
                treasuresValue.add(200);
            } else if (valuable.toString().equals("Gold nugget")) {
                treasuresValue.add(150);
            } else if (valuable.toString().equals("Jewel")) {
                treasuresValue.add(100);
            } else if (valuable.toString().equals("Ruby")) {
                treasuresValue.add(50);
            }
        } catch (NullPointerException e) {
            MessageLog.getInstance().addMessage("No valuable detected.", "Room");
        }


    }

    private int getSingleValue(int index) {
        //get value of a single valuable
        return treasuresValue.get(index);
    }

    @Override
    public int getAllValue() {
        int sum = 0;
        for (int i = 0; i < treasuresValue.size(); i++) {
            sum += treasuresValue.get(i);
        }
        return sum;
    }

    @Override
    public int getValueInRange(int start, int end) {
        //get the sum within a certain range
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += treasuresValue.get(i);
        }
        return sum;
    }

    @Override
    public Object takeValuable(int index) {
        Object valuable = treasures.get(index);
        treasures.remove(index);
        treasuresValue.remove(index);
        return valuable;
    }

}
