package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Person;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class DirectoryController {

  @FXML private TableColumn<Account, String> Email;

  @FXML private TableColumn<Person, String> FirstName;

  @FXML private TableColumn<Person, String> LastName;

  @FXML private TableColumn<Person, Number> PhoneNumber;

  @FXML private TableColumn<Person, String> Title;

  @FXML private TableView<edu.wpi.cs3733.D23.teamQ.db.obj.Person> Person;

  /** used to put Nodes from database arraylist to observablelist */
  public ObservableList<Person> People() {
    ObservableList<Person> person = FXCollections.observableArrayList();
    for (int i = 0; i < Qdb.getInstance().personTable.getAllRows().size(); i++) {
      person.add(Qdb.getInstance().personTable.getAllRows().get(i));
    }
    return person;
  }

  public void initialize() {
    /** input the Title to the table */
    Title.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> param) {
            SimpleStringProperty titles = new SimpleStringProperty(param.getValue().getTitle());
            return titles;
          }
        });

    /** input the FirstName to the table */
    FirstName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> param) {
            SimpleStringProperty firstName =
                new SimpleStringProperty(param.getValue().getFirstName());
            return firstName;
          }
        });

    /** input the LastName to the table */
    LastName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> param) {
            SimpleStringProperty lastName =
                new SimpleStringProperty(param.getValue().getLastName());
            return lastName;
          }
        });

    /** input the Email to the table */
    Email.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
            SimpleStringProperty email = new SimpleStringProperty(param.getValue().getEmail());
            return email;
          }
        });

    /** input the phoneNumber to the table */
    PhoneNumber.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Person, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Person, Number> param) {
            SimpleIntegerProperty phoneNumbers =
                new SimpleIntegerProperty(param.getValue().getPhoneNumber());
            return phoneNumbers;
          }
        });

    /** set the information tableview */
    Person.setItems(People());
  }
}
