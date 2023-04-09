package edu.wpi.cs3733.D23.teamQ.db.obj;

public class Person {
  private int IDNum;
  private String firstName;
  private String lastName;
  private String title;
  private int phoneNumber;
  private String username;

  public Person(
      int IDNum,
      String firstName,
      String lastName,
      String title,
      int phoneNumber,
      String username) {
    this.IDNum = IDNum;
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.phoneNumber = phoneNumber;
    this.username = username;
  }

  public Person(String firstName, String lastName, String title, int phoneNumber, String username) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.phoneNumber = phoneNumber;
    this.username = username;
  }

  public int getIDNum() {
    return IDNum;
  }

  public void setIDNum(int IDNum) {
    this.IDNum = IDNum;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String FirstName) {
    this.firstName = FirstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String LastName) {
    this.lastName = LastName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String Title) {
    this.title = Title;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhone(int PhoneNumber) {
    this.phoneNumber = PhoneNumber;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String UN) {
    this.username = UN;
  }
}
