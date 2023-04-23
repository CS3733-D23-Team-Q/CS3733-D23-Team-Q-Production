package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ProfileImage;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;

public class DirectoryController {
  @FXML TableView<EmployeeData> directoryTable;
  @FXML TableColumn<EmployeeData, Image> profileImageColumn;
  @FXML TableColumn<EmployeeData, String> nameColumn;
  @FXML TableColumn<EmployeeData, String> titleColumn;
  @FXML TableColumn<EmployeeData, String> statusColumn;
  @FXML TableColumn<EmployeeData, Button> chatButtonColumn;
  @FXML TableColumn<EmployeeData, Button> viewProfileButtonColumn;
  Qdb qdb = Qdb.getInstance();
  List<Account> allAccounts = qdb.retrieveAllAccounts();
  List<ProfileImage> allProfileImages = qdb.getAllProfileImages();

  private static String viewProfileUsername;

  public void initialize() {
    profileImageColumn.setCellValueFactory(
        cellData ->
            new SimpleObjectProperty<>(
                qdb.convertByteaToImage(cellData.getValue().getProfileImage().getImageData())));

    profileImageColumn.setCellFactory(
        column -> {
          return new TableCell<EmployeeData, Image>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {
              super.updateItem(image, empty);
              if (image == null || empty) {
                setGraphic(null);
              } else {
                imageView.setImage(image);
                imageView.setFitWidth(80); // Set the desired width for the image
                imageView.setPreserveRatio(true);
                setGraphic(imageView);
              }
            }
          };
        });

    nameColumn.setCellValueFactory(
        cellData ->
            new SimpleStringProperty(
                cellData.getValue().getAccount().getFirstName()
                    + " "
                    + cellData.getValue().getAccount().getLastName()));

    titleColumn.setCellValueFactory(
        cellData -> new SimpleStringProperty(cellData.getValue().getAccount().getTitle()));

    statusColumn.setCellValueFactory(
        cellData -> {
          EmployeeData employeeData = cellData.getValue();
          boolean status = employeeData.getAccount().isActive();
          String statusString = status ? "Online" : "Offline";
          return new SimpleStringProperty(statusString);
        });

    chatButtonColumn.setCellFactory(
        column -> {
          return new TableCell<EmployeeData, Button>() {
            private final Button button = new Button("Chat");

            @Override
            protected void updateItem(Button item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                EmployeeData employeeData = getTableView().getItems().get(getIndex());
                button.setOnAction(
                    event -> {
                      // Handle button click event
                      System.out.println(
                          "Button clicked for: " + employeeData.getAccount().getUsername());
                    });
                button.setStyle(
                    "-fx-background-color: #012d5a; -fx-text-fill: #FFFFFF; -fx-pref-width: 100");
                setGraphic(button);
              }
            }
          };
        });

    viewProfileButtonColumn.setCellFactory(
        column -> {
          return new TableCell<EmployeeData, Button>() {
            private final Button button = new Button("View Profile");

            @Override
            protected void updateItem(Button item, boolean empty) {
              super.updateItem(item, empty);
              if (empty) {
                setGraphic(null);
              } else {
                EmployeeData employeeData = getTableView().getItems().get(getIndex());
                button.setOnAction(
                    event -> {
                      // Handle button click event
                      System.out.println(
                          "Button clicked for: " + employeeData.getAccount().getUsername());
                      viewProfileUsername = employeeData.getAccount().getUsername();
                      Navigation.navigate(Screen.VIEW_PROFILE);
                    });
                Image image = new Image(getClass().getResourceAsStream("/ViewProfile.png"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(20.0);
                imageView.setFitWidth(20.0);
                button.setText("");
                button.setGraphic(imageView);
                button.setStyle("-fx-background-color: transparent;");
                setGraphic(button);
              }
            }
          };
        });

    directoryTable.setItems(populateData());
    directoryTable.getStyleClass().add("noheader");
  }

  @Getter
  public static class EmployeeData {
    private final Account account;
    private final ProfileImage profileImage;

    public EmployeeData(Account account, ProfileImage profileImage) {
      this.account = account;
      this.profileImage = profileImage;
    }
  }

  public ObservableList<EmployeeData> populateData() {
    ObservableList<EmployeeData> allData = FXCollections.observableArrayList();
    String loggedInUser = LoginController.getLoginUsername();

    for (Account account : allAccounts) {
      if (account.getUsername().equals(loggedInUser)) {
        continue;
      }
      if (qdb.getProfileImageIndex(account.getUsername()) != -1) {
        ProfileImage pfp = qdb.retrieveProfileImage(account.getUsername());
        EmployeeData employeeData = new EmployeeData(account, pfp);
        allData.add(employeeData);
      } else {
        Image image = new Image(getClass().getResourceAsStream("/default-profile.png"));
        byte[] imageData = qdb.convertImageToBytea(image);
        ProfileImage tempProfileImage = new ProfileImage(account.getUsername(), imageData);
        EmployeeData employeeData = new EmployeeData(account, tempProfileImage);
        allData.add(employeeData);
      }
    }
    return allData;
  }

  public static String getViewProfileUsername() {
    return viewProfileUsername;
  }
}
