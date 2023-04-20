package edu.wpi.cs3733.D23.teamQ.db.obj;

public class Message {
    private Account sender;
    private Account receiver;
    private String message;

    private long timeStamp;

    public Message(Account sender, Account receiver, String message, long timeStamp) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
