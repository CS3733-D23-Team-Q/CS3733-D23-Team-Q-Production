package edu.wpi.cs3733.D23.teamQ.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import net.kurobako.gesturefx.GesturePane;

public class HomeController implements Subscriber {
  Qdb qdb = Qdb.getInstance();
  @FXML GesturePane titleImage;
  @FXML Label title;
  @FXML Label datetime;
  @FXML VBox alertBox;
  @FXML TextArea notesField;
  @FXML CalendarView calendar;

  @FXML
  public void initialize() {
    qdb.subscribe(this);
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);
    title.setText("Welcome Back " + account.getFirstName() + "!");
    updateTime();

    Image im = new Image(App.class.getResourceAsStream("ShapiroCenter.jpg"));
    ImageView iv = new ImageView(im);
    titleImage.setContent(iv);

    setAlerts();
    notesField.setText(account.getNotes());
    notesField.setStyle("-fx-font-family: Roboto");

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
    calendar.getCalendarSources().add(SR);
  }

  public void saveNotes() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);
    account.setNotes(notesField.getText());
    qdb.updateAccount(username, account);
  }

  private boolean setAlerts() {
    alertBox.getChildren().clear();
    List<Alert> alerts = qdb.retrieveAllAlerts();
    for (int i = alerts.size() - 1; i >= 0; i--) {
      Label l = new Label();
      Date d = new Date(alerts.get(i).getTimestamp());
      l.setText(d + ": " + alerts.get(i).getMessage());
      l.setPadding(new Insets(4, 6, 4, 6));
      l.setMaxWidth(512);
      l.setMinHeight(24);
      l.setWrapText(true);
      l.setStyle(
          "-fx-text-fill: #CE3C49; -fx-font-family: Roboto; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-color: #CE3C49; -fx-border-radius: 16");
      alertBox.getChildren().add(l);
    }
    return true;
  }

  public void updateTime() {
    Long time = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mm a");
    java.sql.Date resultDate = new java.sql.Date(time);
    String timeString = sdf.format(resultDate);
    datetime.setText(timeString);
  }

  @Override
  public boolean update(List<String> context) {
    updateTime();
    return setAlerts();
  }
}
