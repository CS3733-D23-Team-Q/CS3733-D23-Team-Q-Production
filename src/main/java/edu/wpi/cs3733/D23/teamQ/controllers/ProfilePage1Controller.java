package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import edu.wpi.cs3733.D23.teamQ.navigation.Navigation;
import edu.wpi.cs3733.D23.teamQ.navigation.Screen;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class ProfilePage1Controller implements Subscriber {

  Qdb qdb = Qdb.getInstance();
  @FXML private HBox myHBox;
  @FXML private ImageView profileImage;
  @FXML private Label fullName;
  @FXML private Label title;
  @FXML private Label displayUsername;
  @FXML private Label email;
  @FXML private Label phone;
  @FXML private Label status;
  @FXML private Circle statusCircle;
  @FXML private VBox statsVbox;
  @FXML private Label incompleteRequests;
  @FXML private Label unreadMessages;

  public void initialize() throws IOException, SQLException {
    String username = LoginController.getLoginUsername();
    Qdb qdb = Qdb.getInstance();
    qdb.subscribe(this);
    Account account = qdb.retrieveAccount(username);

    fullName.setText(account.getFirstName() + " " + account.getLastName());
    title.setText(account.getTitle());
    displayUsername.setText(username);
    email.setText(account.getEmail());
    phone.setText(account.getPhoneNumber());

    unreadMessages.setOnMouseClicked(
        event -> {
          Navigation.navigate(Screen.MESSAGES);
        });
    incompleteRequests.setOnMouseClicked(
        event -> {
          if (LoginController.isAdmin()) Navigation.navigate(Screen.ADMIN_LIST_REQUESTS);
          else Navigation.navigate(Screen.LIST_REQUESTS);
        });

    statusCircle.setStroke(null);
    if (account.isActive()) {
      status.setText("Online");
      statusCircle.setStyle("-fx-fill: #37AC2B");
    } else {
      status.setText("Offline");
      statusCircle.setStyle("-fx-fill: #CE3C49");
    }

    if (qdb.getProfileImageIndex(username) != -1) {
      Image pfp = qdb.convertByteaToImage(qdb.retrieveProfileImage(username).getImageData());
      profileImage.setImage(pfp);
    }
    serviceRequestPieChart();

    int incompleteCount = 0;
    List<ServiceRequest> assignedRequets = qdb.retrieveUserAssignServiceRequests(username);
    for (int i = 0; i < assignedRequets.size(); i++) {
      if (assignedRequets.get(i).getProgress().ordinal() != 2) {
        incompleteCount++;
      }
    }
    unreadMessages.setText("You have " + qdb.getNumUnread(username) + " unread message(s)");
    incompleteRequests.setText("You have " + incompleteCount + " incomplete assigned requests");
  }

  public void editPressed() {
    Navigation.navigate(Screen.EDIT_PROFILE);
  }

  public void serviceRequestPieChart() {
    String username = LoginController.getLoginUsername();
    Qdb qdb = Qdb.getInstance();

    List<ServiceRequest> allUserRequests = qdb.getUserRequestedRows(username);
    List<ServiceRequest> blankRequests = new ArrayList<>();
    List<ServiceRequest> processingRequests = new ArrayList<>();
    List<ServiceRequest> doneRequests = new ArrayList<>();

    for (int i = 0; i < allUserRequests.size(); i++) {
      if (allUserRequests.get(i).getProgress().ordinal() == 0) {
        blankRequests.add(allUserRequests.get(i));
      } else if (allUserRequests.get(i).getProgress().ordinal() == 1) {
        processingRequests.add(allUserRequests.get(i));
      } else {
        doneRequests.add(allUserRequests.get(i));
      }
    }

    PieChart.Data slice1 = new PieChart.Data("Blank", blankRequests.size());
    PieChart.Data slice2 = new PieChart.Data("Processing", processingRequests.size());
    PieChart.Data slice3 = new PieChart.Data("Complete", doneRequests.size());

    PieChart pieChart = new PieChart();
    pieChart.getData().add(slice1);
    pieChart.getData().add(slice2);
    pieChart.getData().add(slice3);

    statsVbox.getChildren().add(pieChart);
  }

  public void viewMoreStatsClicked() {
    Navigation.navigate(Screen.STATISTICS);
  }

  public boolean update(List<String> context) throws URISyntaxException {
    if (context.contains("account")) {
      boolean isActive = qdb.getAccountFromUsername(LoginController.getLoginUsername()).isActive();
      if (isActive) {
        status.setText("Online");
        statusCircle.setStyle("-fx-fill: #37AC2B");
      } else {
        status.setText("Offline");
        statusCircle.setStyle("-fx-fill: #CE3C49");
      }
    }
    return false;
  }
}
