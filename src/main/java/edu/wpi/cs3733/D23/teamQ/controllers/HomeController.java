package edu.wpi.cs3733.D23.teamQ.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomeController implements IController, Subscriber {
  @FXML CalendarView Calender;
  @FXML private Text UserMessage;
  @FXML ScrollPane alertPane;
  @FXML VBox alertBox;
  Qdb qdb = Qdb.getInstance();

  @FXML
  public void initialize() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);

    UserMessage.setText("Welcome Back " + account.getFirstName() + "!");

    setAlerts();

    Calendar serviceRequests = new Calendar("Service Requests");

    CalendarSource SR = new CalendarSource("testing");

    for (int i = 0; i < qdb.retrieveUserAssignServiceRequests(username).size(); i++) {
      int num = qdb.retrieveUserAssignServiceRequests(username).get(i).getRequestID();
      Entry<String> temp = new Entry<>("Service Request ID Num-" + num);
      serviceRequests.addEntry(temp);
      temp.changeStartDate(
          qdb.retrieveUserAssignServiceRequests(username).get(i).getDate().toLocalDate());
      temp.changeEndDate(
          qdb.retrieveUserAssignServiceRequests(username).get(i).getDate().toLocalDate());
      temp.changeStartTime(
          LocalTime.parse(qdb.retrieveUserAssignServiceRequests(username).get(i).getTime()));
      temp.changeEndTime(
          LocalTime.parse(qdb.retrieveUserAssignServiceRequests(username).get(i).getTime()));
    }
    SR.getCalendars().add(serviceRequests);
    Calender.getCalendarSources().add(SR);
  }

  @FXML
  private void setAlerts() {
    List<Alert> alerts = qdb.retrieveAllAlerts();
    for (int i = 0; i < alerts.size(); i++) {
      Label l = new Label();
      Date d = new Date(alerts.get(i).getTimestamp());
      l.setText(d + ": " + alerts.get(i).getMessage());
      l.setStyle("-fx-font: roboto; -fx-font-size: 16");
      alertBox.getChildren().add(l);
    }
  }

  public boolean update(List<String> context) {
    //    AudioClip sound = new AudioClip(getClass().getResourceAsStream("alert.wav").toString());
    //    sound.play();
    if (context.contains("alert")) setAlerts();
    return false;
  }
  //  Qdb qdb = Qdb.getInstance();
  //
  //  @FXML Button ServiceHubButton;
  //  @FXML Button ListRequestsButton;
  //  @FXML Button SPButton;
  //  @FXML Button LMButton;
  //
  //  @FXML Button nextButton;
  //
  //  @FXML MenuItem exit;
  //  @FXML MenuItem logout;
  //
  //  @FXML MenuItem serviceRequestHubMenu;
  //
  //  @FXML MenuItem listServiceRequestMenu;
  //
  //  @FXML MenuItem signagePageMenu;
  //
  //  @FXML MenuItem learnMoreMenu;
  //  @FXML Button settingButton;
  //  @FXML TextField searchField;
  //
  //  @FXML
  //  public void initialize() {}
  //
  //  /** Navigate to the conference room request page when the CRReservationButton is clicked. */
  //  @FXML
  //  public void ServiceHubButtonClicked() {
  //    Navigation.navigate(Screen.SERVICE_REQUEST_HUB);
  //  }
  //
  //  /** Navigate to the flower delivery request page when the FDRequestButton is clicked. */
  //  @FXML
  //  public void ListRequestsButtonClicked() {
  //    Navigation.navigate(Screen.LIST_REQUESTS);
  //  }
  //
  //  /** Navigate to the signage page when the SPButton is clicked. */
  //  @FXML
  //  public void SPButtonClicked() {
  //    Navigation.navigate(Screen.SIGNAGE);
  //  }
  //
  //  /** Navigate to the help page when the LMButton is clicked. */
  //  @FXML
  //  public void LMButtonClicked() {
  //    Navigation.navigate(Screen.HELP);
  //    // System.out.println("Navigate to an information screen.");
  //  }
  //
  //  /** Exit the application when the exitMenu is clicked. */
  //  @FXML
  //  public void exitMenuClicked() {
  //    Platform.exit();
  //  }
  //  /** Navigates to profile page * */
  //  @FXML
  //  public void Home_ProfileButton_Clicked() {
  //    Navigation.navigate(Screen.PROFILE_PAGE);
  //  }
  //
  //  @FXML
  //  public void nextButtonClicked() {
  //    Navigation.navigate(Screen.HOME2);
  //  }
  //
  //  /** Features might be added in the future. */
  //  @FXML
  //  public void settingButtonClicked() {
  //    System.out.println("Pops up a setting screen.");
  //  }
  //
  //  @FXML
  //  public void searchFieldEntered(KeyEvent e) {
  //    if (e.getCode().equals(KeyCode.ENTER)) {
  //      System.out.println("Navigate to a specific page according to the text being entered.");
  //    }
  //  }
  //
  //  @FXML
  //  public void logout() throws IOException {
  //    LoginController lc = (LoginController) Navigation.getController(Screen.LOGIN);
  //    String username = lc.getUsername();
  //    Account a = qdb.retrieveAccount(username);
  //    a.setActive(false);
  //    qdb.updateAccount(username, a);
  //    Navigation.navigate(Screen.LOGIN);
  //  }
}
