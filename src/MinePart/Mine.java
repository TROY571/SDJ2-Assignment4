package MinePart;

import Log.MessageLog;
import java.util.ArrayList;

public class Mine {
    //we assume that there're four kinds of valuableNames
    //they are: diamond, gold nugget, jewel and ruby
    //here we should use multiton for this to meet the requirement

    private static final int Number_of_valuableNames = 4;
    private static ArrayList<Mine> valuableNames = new ArrayList<>();
    private static int valuableNum = 0;
    private String name;

    private Mine(String name) {
        this.name = name;
    }


    static {
        valuableNames.add(new Mine("Diamond"));
        valuableNames.add(new Mine("Gold nugget"));
        valuableNames.add(new Mine("Jewel"));
        valuableNames.add(new Mine("Ruby"));
    }

    public static Object getInstance(int key) {
        valuableNum = key;
        if (valuableNum == 0) {
            return valuableNames.get(0).getName();
        } else if (valuableNum == 1) {
            return valuableNames.get(1).getName();
        } else if (valuableNum == 2) {
            return valuableNames.get(2).getName();
        } else if (valuableNum == 3) {
            return valuableNames.get(3).getName();
        } else {
            MessageLog.getInstance().addMessage("Error", "Mine");
            return null;
        }
    }

    public String getName() {
        return name;
    }
}
