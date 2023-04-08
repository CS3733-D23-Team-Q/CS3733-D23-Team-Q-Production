package edu.wpi.cs3733.D23.teamQ.db.obj;

public class Person {
  private int IDNum;
  private String FirstName;
  private String LastName;
  private String Title;
  private int PhoneNumber;
  private String username;

  public Person(
      int IDNum,
      String Firstname,
      String LastName,
      String Title,
      int PhoneNumber,
      String username) {
    this.IDNum = IDNum;
    this.FirstName = FirstName;
    this.LastName = LastName;
    this.Title = Title;
    this.PhoneNumber = PhoneNumber;
    this.username = username;
  }

  public Person(String Firstname, String LastName, String Title, int PhoneNumber, String username) {

    this.FirstName = FirstName;
    this.LastName = LastName;
    this.Title = Title;
    this.PhoneNumber = PhoneNumber;
    this.username = username;
  }

  public int getIDNum() {
    return IDNum;
  }

  public void setIDNum(int IDNum) {
    this.IDNum = IDNum;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String FirstName) {
    this.FirstName = FirstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String LastName) {
    this.LastName = LastName;
  }

  public String getTitle() {
    return Title;
  }

  public void setTitle(String Title) {
    this.Title = Title;
  }

  public int getPhoneNumber() {
    return PhoneNumber;
  }

  public void setPhone(int PhoneNumber) {
    this.PhoneNumber = PhoneNumber;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String UN) {
    this.username = UN;
  }
}
