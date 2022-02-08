package Log;

import java.util.ArrayList;

public class MessageLog {
    private static MessageLog instance;
    private ArrayList<Message> messages;

    private MessageLog() {
        messages = new ArrayList<>();
    }

    public static MessageLog getInstance() {
        if (instance == null)
            instance = new MessageLog();
        return instance;
    }

    public void addMessage(String message, String sender) {
        if (messages.size() == 0){
            Message message1 = new Message(message, sender);
            messages.add(message1);
            System.out.println("log: " + message + '\n' + "Sender: " + sender);
        }
        if (! messages.get(messages.size() - 1).getMessageBody().equals(message)) {
            Message message1 = new Message(message, sender);
            messages.add(message1);
            System.out.println("log: " + message + '\n' + "Sender: " + sender);
        }
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

}
