package MinePart;

import Log.MessageLog;

import java.util.ArrayList;

import utility.collection.ListADT;

public class Deposit<Object> implements ListADT<Object> {
    //the adapter for the blocking queue
    //due to the doc, i use the arraylist in the utility
    private ArrayList<Object> valuables;

    public Deposit() {
        this.valuables = new ArrayList<>();
    }

    @Override
    public synchronized void add(int index, Object element) {
        while (valuables.size() >= 300) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        valuables.add(index, element);
        notifyAll();
    }

    @Override
    public synchronized void add(Object element) {
        while (valuables.size() >= 300) {
            try {
                wait();
            } catch (InterruptedException e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Deposit");
            }
        }
        valuables.add(element);
        notifyAll();
    }

    @Override
    public synchronized void set(int index, Object element) {
        valuables.set(index, element);
    }

    @Override
    public synchronized Object get(int index) {
        //i assume that the transporter takes the first item of the list
        while (valuables.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Deposit");
            }
        }
        try {
            Object itemTaken = valuables.get(index);
            valuables.remove(index);
            return itemTaken;
        } catch (IndexOutOfBoundsException e) {
//            MessageLog.getInstance().addMessage("The quantity is not enough.", "Deposit");
        }
        return null;
    }

    public synchronized Object getIndex(int index) {
        while (valuables.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                MessageLog.getInstance().addMessage(e.getMessage(), "Deposit");
            }
        }
        Object itemTaken = valuables.get(index);
        return itemTaken;
    }

    @Override
    public synchronized Object remove(int index) {
        return valuables.remove(index);
    }

    @Override
    public synchronized Object remove(Object element) {
        int o = indexOf(element);
        return remove(o);
    }

    @Override
    public synchronized int indexOf(Object element) {
        return valuables.indexOf(element);
    }

    @Override
    public boolean contains(Object element) {
        return valuables.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return valuables.isEmpty();
    }

    @Override
    public boolean isFull() {
        return valuables.isEmpty();
    }

    @Override
    public synchronized int size() {
        return valuables.size();
    }

    public String getAll() {
        String string = null;
        for (int i = 0; i < valuables.size(); i++) {
            string += getIndex(i).toString();
        }
        return string;
    }
}
