package Log;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Message implements Serializable {

    public static final String speciality1 = "EXIT";
    public static final String speciality2 = "NEW_USER_NAME";
    public static final String speciality3 = "NUMBER_OF_CLIENTS";
    public static final String speciality4 = "LIST_OF_CLIENTS";

    private String messageBody;
    private String sender;
    private ArrayList<String> clients;
    private Timestamp timestamp;

    public Message(String messageBody, String sender) {
        this.messageBody = messageBody;
        this.sender = sender;

        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Message(ArrayList<String> clients, String sender) {
        this.messageBody = null;
        this.sender = sender;
        this.clients = clients;

        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getClients()
    {
        return clients;
    }
    @Override
    public String toString() {
        return sender +": " + messageBody;
    }

    public String getDateTime()
    {
        return new SimpleDateFormat("HH:mm:ss MM.dd.yyyy").format(this.timestamp);
    }

    public String getDate()
    {
        return new SimpleDateFormat("dd.MM.yyyy").format(this.timestamp);
    }

    public String getTime()
    {
        return new SimpleDateFormat("HH:mm:ss").format(this.timestamp);
    }
}
