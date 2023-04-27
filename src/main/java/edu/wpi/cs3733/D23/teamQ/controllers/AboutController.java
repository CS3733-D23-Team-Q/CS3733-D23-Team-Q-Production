package edu.wpi.cs3733.D23.teamQ.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class AboutController {
  @FXML ImageView kenyonImage;
  @FXML Label kenyon1;
  @FXML Label kenyon2;
  @FXML Label kenyon3;
  @FXML ImageView olegImage;
  @FXML Label oleg1;
  @FXML Label oleg2;
  @FXML Label oleg3;
  @FXML ImageView ashleyImage;
  @FXML Label ashley1;
  @FXML Label ashley2;
  @FXML Label ashley3;
  @FXML ImageView sophiaImage;
  @FXML Label sophia1;
  @FXML Label sophia2;
  @FXML Label sophia3;
  @FXML ImageView joshImage;
  @FXML Label josh1;
  @FXML Label josh2;
  @FXML Label josh3;
  @FXML ImageView lydiaImage;
  @FXML Label lydia1;
  @FXML Label lydia2;
  @FXML Label lydia3;
  @FXML ImageView amyImage;
  @FXML Label amy1;
  @FXML Label amy2;
  @FXML Label amy3;
  @FXML ImageView willImage;
  @FXML Label will1;
  @FXML Label will2;
  @FXML Label will3;
  @FXML ImageView dovImage;
  @FXML Label dov1;
  @FXML Label dov2;
  @FXML Label dov3;
  @FXML ImageView cameronImage;
  @FXML Label cameron1;
  @FXML Label cameron2;
  @FXML Label cameron3;

  @FXML
  public void initialize() {}

  @FXML
  public void kenyonShow() {
    kenyon1.setText("Favorite color is green");
    kenyon1.setStyle("-fx-text-fill: green");
    kenyon2.setText("");
    kenyon3.setText("");
  }

  @FXML
  public void kenyonHide() {
    kenyon1.setText("Kenyon Coleman");
    kenyon1.setStyle("-fx-text-fill: #012d5a");
    kenyon2.setText("Assistant Lead Software Engineer");
    kenyon3.setText("Computer Science, 2025");
  }

  @FXML
  public void olegShow() {
    oleg1.setText("BIG pathfinding fan");
    oleg2.setText("");
    oleg3.setText("");
  }

  @FXML
  public void olegHide() {
    oleg1.setText("Oleg Zabolotnev");
    oleg2.setText("Full-Time Software Engineer");
    oleg3.setText("Computer Science, 2025");
  }

  @FXML
  public void ashleyShow() {
    ashley1.setText("Andrew Garfield Spiderman fan");
    ashley2.setText("");
    ashley3.setText("");
  }

  @FXML
  public void ashleyHide() {
    ashley1.setText("Ashley Jacobs");
    ashley2.setText("Scrum");
    ashley3.setText("Computer Science, 2025");
  }

  @FXML
  public void sophiaShow() {
    sophia1.setText("I like to paint");
    sophia2.setText("");
    sophia3.setText("");
  }

  @FXML
  public void sophiaHide() {
    sophia1.setText("Sophia John");
    sophia2.setText("Project Owner");
    sophia3.setText("Computer Science, 2025");
  }

  @FXML
  public void joshShow() {
    josh1.setText("Favorite Book: Name of the Wind");
    josh2.setText("");
    josh3.setText("");
  }

  @FXML
  public void joshHide() {
    josh1.setText("Josh Keselman");
    josh2.setText("Documentation Analyst");
    josh3.setText("Robotics Engineering, 2025");
  }

  @FXML
  public void lydiaShow() {
    lydia1.setText("Favorite color is yellow");
    lydia1.setStyle("-fx-text-fill: #FDE541");
    lydia2.setText("");
    lydia3.setText("");
  }

  @FXML
  public void lydiaHide() {
    lydia1.setText("Kelu Liu");
    lydia1.setStyle("-fx-text-fill: #012d5a");
    lydia2.setText("Assistant Lead Software Engineer");
    lydia3.setText("Computer Science, 2025");
  }

  @FXML
  public void amyShow() {
    amy1.setText("Favorite color is blue");
    amy2.setText("");
    amy3.setText("");
  }

  @FXML
  public void amyHide() {
    amy1.setText("Yuran Xue");
    amy2.setText("Full-Time Software Engineer");
    amy3.setText("Computer Science, 2025");
  }

  @FXML
  public void willShow() {
    will1.setText("Plays In Hill House the Band");
    will2.setText("");
    will3.setText("");
  }

  @FXML
  public void willHide() {
    will1.setText("Will Merry");
    will2.setText("Project Manager");
    will3.setText("Robotics Engineering, 2025");
  }

  @FXML
  public void dovShow() {
    dov1.setText("Has a golden-doodle");
    dov2.setText("");
    dov3.setText("");
  }

  @FXML
  public void dovHide() {
    dov1.setText("Dov Ushman");
    dov2.setText("Assistant Lead Software Engineer");
    dov3.setText("Computer Science, 2026");
  }

  @FXML
  public void cameronShow() {
    cameron1.setText("QUOTEQUOTEQUOTEQUOTEQUOTEQUOTEQUOTEQUOTEQUOTEQUOTEQUOTEQUOTE");
    cameron2.setText("");
    cameron3.setText("");
  }

  @FXML
  public void cameronHide() {
    cameron1.setText("Cameron Robbins");
    cameron2.setText("Lead Software Engineer");
    cameron3.setText("Computer Science, 2025");
  }
}
