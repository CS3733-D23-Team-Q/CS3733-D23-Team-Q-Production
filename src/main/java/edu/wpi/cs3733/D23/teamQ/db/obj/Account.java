package edu.wpi.cs3733.D23.teamQ.db.obj;

public class Account {
  private String username;
  private String password;
  private String email;
  private int securityQuestion1;
  private int securityQuestion2;
  private String securityAnswer1;
  private String securityAnswer2;
  private boolean active;

  private int IDNum;
  private String firstName;
  private String lastName;
  private String title;
  private String phoneNumber;

  private String notes;
  private String todo;

  public Account(
      String username,
      String password,
      String email,
      int securityQuestion1,
      int securityQuestion2,
      String securityAnswer1,
      String securityAnswer2,
      boolean active,
      int IDNum,
      String firstName,
      String lastName,
      String title,
      String phoneNumber,
      String notes,
      String todo) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.securityQuestion1 = securityQuestion1;
    this.securityQuestion2 = securityQuestion2;
    this.securityAnswer1 = securityAnswer1;
    this.securityAnswer2 = securityAnswer2;
    this.active = active;
    this.IDNum = IDNum;
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.phoneNumber = phoneNumber;
    this.notes = notes;
    this.todo = todo;
  }

  public Account(
      String username,
      String password,
      String email,
      int securityQuestion1,
      int securityQuestion2,
      String securityAnswer1,
      String securityAnswer2,
      boolean active,
      String firstName,
      String lastName,
      String title,
      String phoneNumber,
      String notes,
      String todo) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.securityQuestion1 = securityQuestion1;
    this.securityQuestion2 = securityQuestion2;
    this.securityAnswer1 = securityAnswer1;
    this.securityAnswer2 = securityAnswer2;
    this.active = active;
    this.firstName = firstName;
    this.lastName = lastName;
    this.title = title;
    this.phoneNumber = phoneNumber;
    this.notes = notes;
    this.todo = todo;
  }

  public Account(String username) {
    this.username = username;
    this.password = null;
    this.email = null;
    this.securityQuestion1 = 2;
    this.securityQuestion2 = 1;
    this.securityAnswer1 = null;
    this.securityAnswer2 = null;
    this.active = true;
    this.firstName = null;
    this.lastName = null;
    this.title = null;
    this.phoneNumber = "";
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getSecurityQuestion1() {
    return securityQuestion1;
  }

  public void setSecurityQuestion1(int securityQuestion1) {
    this.securityQuestion1 = securityQuestion1;
  }

  public int getSecurityQuestion2() {
    return securityQuestion2;
  }

  public void setSecurityQuestion2(int securityQuestion2) {
    this.securityQuestion2 = securityQuestion2;
  }

  public String getSecurityAnswer1() {
    return securityAnswer1;
  }

  public void setSecurityAnswer1(String securityAnswer1) {
    this.securityAnswer1 = securityAnswer1;
  }

  public String getSecurityAnswer2() {
    return securityAnswer2;
  }

  public void setSecurityAnswer2(String securityAnswer2) {
    this.securityAnswer2 = securityAnswer2;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public int getIDNum() {
    return IDNum;
  }

  public void setIDNum(int ID) {
    this.IDNum = ID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String FN) {
    this.firstName = FN;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String LN) {
    this.lastName = LN;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String t) {
    this.title = t;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String PN) {
    this.phoneNumber = PN;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getTodo() {
    return todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }

  @Override
  public String toString() {
    return username
        + ","
        + password
        + ","
        + email
        + ","
        + securityQuestion1
        + ","
        + securityQuestion2
        + ","
        + securityAnswer1
        + ","
        + securityAnswer2
        + ","
        + active
        + ","
        + firstName
        + ","
        + lastName
        + ","
        + title
        + ","
        + phoneNumber
        + ","
        + todo
        + ","
        + notes;
  }
}
