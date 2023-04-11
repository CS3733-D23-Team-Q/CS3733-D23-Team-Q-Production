package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {
  private int IDNum;
  private String firstName;
  private String lastName;
  private String title;
  private int phoneNumber;
  private Account account;

  public Person(
      int IDNum,
      String firstName,
      String lastName,
      String title,
      int phoneNumber,
      Account account) {
    this.IDNum = IDNum;
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.phoneNumber = phoneNumber;
    this.account = account;
  }

  public Person(String firstName, String lastName, String title, int phoneNumber, Account account) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.phoneNumber = phoneNumber;
    this.account = account;
  }
}
