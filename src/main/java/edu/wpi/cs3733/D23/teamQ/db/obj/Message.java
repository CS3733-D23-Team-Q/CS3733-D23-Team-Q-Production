package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
  private Account sender;
  private Account receiver;
  private String message;
  private long timeStamp;
  private boolean read;

  public Message(Account sender, Account receiver, String message, long timeStamp) {
    this.sender = sender;
    this.receiver = receiver;
    this.message = message;
    this.timeStamp = timeStamp;
    this.read = false;
  }

  public Message(Account sender, Account receiver, String message, long timeStamp, boolean read) {
    this.sender = sender;
    this.receiver = receiver;
    this.message = message;
    this.timeStamp = timeStamp;
    this.read = read;
  }

  public String toString() {
    return sender.getUsername() + "," + receiver.getUsername() + "," + message + "," + timeStamp;
  }

  public boolean getRead() {
    return read;
  }
}
