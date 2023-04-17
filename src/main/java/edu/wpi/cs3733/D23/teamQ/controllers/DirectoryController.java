package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
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

  @FXML private TableColumn<edu.wpi.cs3733.D23.teamQ.db.obj.Account, String> Email;

  @FXML private TableColumn<Account, String> FirstName;

  @FXML private TableColumn<Account, String> LastName;

  @FXML private TableColumn<Account, Number> PhoneNumber;

  @FXML private TableColumn<Account, String> Title;

  @FXML private TableView<edu.wpi.cs3733.D23.teamQ.db.obj.Account> account;

  Account accountSelected;

  /** used to put Nodes from database arraylist to observablelist */
  public ObservableList<Account> Accounts() {
    ObservableList<Account> account = FXCollections.observableArrayList();
    for (int i = 0; i < Qdb.getInstance().getRows().size(); i++) {
      account.add(Qdb.getInstance().getRows().get(i));
    }
    return account;
  }

  public void initialize() {
    /** input the Title to the table */
    Title.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
            SimpleStringProperty titles = new SimpleStringProperty(param.getValue().getTitle());
            return titles;
          }
        });

    /** input the FirstName to the table */
    FirstName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
            SimpleStringProperty firstName =
                new SimpleStringProperty(param.getValue().getFirstName());
            return firstName;
          }
        });

    /** input the LastName to the table */
    LastName.setCellValueFactory(
        new Callback<TableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
          @Override
          public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> param) {
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
        new Callback<TableColumn.CellDataFeatures<Account, Number>, ObservableValue<Number>>() {
          @Override
          public ObservableValue<Number> call(TableColumn.CellDataFeatures<Account, Number> param) {
            SimpleIntegerProperty phoneNumbers =
                new SimpleIntegerProperty(param.getValue().getPhoneNumber());
            return phoneNumbers;
          }
        });

    /** set the information tableview */
    account.setItems(Accounts());
  }

  public void tableClicked() {
    accountSelected = account.getSelectionModel().getSelectedItems().get(0);
    Navigation.navigateRight(Screen.DISPLAY_PROFILE);
  }

  public Account getAccount() {
    return accountSelected;
  }
}
