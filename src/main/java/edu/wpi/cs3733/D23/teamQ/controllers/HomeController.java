package edu.wpi.cs3733.D23.teamQ.controllers;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.Calendar.Style;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import edu.wpi.cs3733.D23.teamQ.App;
import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.dao.Subscriber;
import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

    refreshCalendar();
  }

  public void refreshCalendar() {
    qdb.subscribe(this);
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);

    calendar.getCalendarSources().clear();

    Calendar serviceRequestsBlank = new Calendar("Service Requests");
    Calendar serviceRequestsProgress = new Calendar("Service Requests");
    Calendar serviceRequestsDone = new Calendar("Service Requests");

    CalendarSource SRB = new CalendarSource("Service Request Blank");
    CalendarSource SRP = new CalendarSource("Service Request Progress");
    CalendarSource SRD = new CalendarSource("Service Request Done");

    serviceRequestsBlank.setStyle(Style.STYLE5);
    serviceRequestsProgress.setStyle(Style.STYLE3);
    serviceRequestsDone.setStyle(Style.STYLE1);

    for (int i = 0; i < qdb.retrieveUserAssignServiceRequests(username).size(); i++) {
      int num = qdb.retrieveUserAssignServiceRequests(username).get(i).getRequestID();
      String type = qdb.retrieveUserAssignServiceRequests(username).get(i).getType();
      ServiceRequest.Progress status =
          qdb.retrieveUserAssignServiceRequests(username).get(i).getProgress();

      Entry<String> temp = new Entry<>(type + " ID Num-" + num);
      if (status.equals(ServiceRequest.Progress.BLANK)) {
        serviceRequestsBlank.addEntry(temp);
      } else if (status.equals(ServiceRequest.Progress.PROCESSING)) {
        serviceRequestsProgress.addEntry(temp);
      } else {
        serviceRequestsDone.addEntry(temp);
      }

      temp.changeStartDate(
          qdb.retrieveUserAssignServiceRequests(username).get(i).getDate().toLocalDate());
      temp.changeEndDate(
          qdb.retrieveUserAssignServiceRequests(username).get(i).getDate().toLocalDate());
      temp.changeStartTime(
          LocalTime.parse(qdb.retrieveUserAssignServiceRequests(username).get(i).getTime()));
      temp.changeEndTime(
          LocalTime.parse(qdb.retrieveUserAssignServiceRequests(username).get(i).getTime()));
    }
    SRB.getCalendars().add(serviceRequestsBlank);
    SRP.getCalendars().add(serviceRequestsProgress);
    SRD.getCalendars().add(serviceRequestsDone);

    calendar.getCalendarSources().addAll(SRB, SRP, SRD);

    // adding moves to calendar
    Calendar moves = new Calendar("Moves");
    CalendarSource m = new CalendarSource("testing 2");

    moves.setStyle(Style.STYLE7);

    for (int i = 0; i < qdb.retrieveAllMoves().size(); i++) {
      int currMove = qdb.retrieveAllMoves().get(i).getMoveID();
      Entry<String> temp = new Entry<>("Move ID- " + currMove);
      moves.addEntry(temp);
      temp.changeStartDate(qdb.retrieveAllMoves().get(i).getDate().toLocalDate());
      temp.changeEndDate(qdb.retrieveAllMoves().get(i).getDate().toLocalDate());
      temp.setFullDay(true);
    }
    m.getCalendars().add(moves);
    calendar.getCalendarSources().add(m);
    // birthday stuff below
    //
    //    Calendar birthdays = new Calendar("Birthdays");
    //    CalendarSource b = new CalendarSource("testing 3");
    //    birthdays.setStyle(Style.STYLE2);
    //    Entry<String> temp = new Entry<>("Birthday Test");
    //    birthdays.addEntry(temp);
    //
    //    temp.changeStartDate(java.time.LocalDate.now());
    //    temp.setFullDay(true);
    //    b.getCalendars().add(birthdays);
    //    calendar.getCalendarSources().add(b);
  }

  public void saveNotes() {
    String username = LoginController.getLoginUsername();
    Account account = qdb.retrieveAccount(username);
    account.setNotes(notesField.getText());
    qdb.updateAccount(username, account);
  }

  private boolean setAlerts() {
    Date today = new Date(System.currentTimeMillis());
    alertBox.getChildren().clear();
    List<Alert> alerts = qdb.retrieveAllAlerts();
    for (int i = alerts.size() - 1; i >= 0; i--) {
      Label l = new Label();
      Date d = new Date(alerts.get(i).getTimestamp());
      boolean isToday = isSameDay(today, d);
      l.setText(d + ": " + alerts.get(i).getMessage());
      l.setPadding(new Insets(4, 6, 4, 6));
      l.setMaxWidth(480);
      l.setMinHeight(24);
      l.setWrapText(true);
      if (isToday)
        l.setStyle(
            "-fx-text-fill: #CE3C49; -fx-font-family: Roboto; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-color: #CE3C49; -fx-border-radius: 16");
      else
        l.setStyle(
            "-fx-text-fill: #012d5a; -fx-font-family: Roboto; -fx-font-size: 16; -fx-font-weight: bold; -fx-border-color: #012d5a; -fx-border-radius: 16");
      alertBox.getChildren().add(l);
    }
    return true;
  }

  public static boolean isSameDay(Date date1, Date date2) {
    LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    return localDate1.isEqual(localDate2);
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
    Qdb qdb = Qdb.getInstance();
    if (context.contains("alert")) {
      Alert alert = qdb.retrieveAllAlerts().get(qdb.retrieveAllAlerts().size() - 1);
      alertSound(alert.getMessage());
      setAlerts();
    }
    if (context.contains("serviceRequest") || context.contains("move")) {
      refreshCalendar();
      //      calendar.des();
    }

    updateTime();
    return true;
  }

  public void alertSound(String message) {
    String path = getClass().getResource("/alert.wav").getPath();
    String voice = qdb.retrieveSettings(LoginController.getLoginUsername()).getVoice().toString();
    String s1 = voice.substring(0, 1).toUpperCase();
    String s2 = voice.substring(1).toLowerCase();
    voice = s1 + s2;
    System.out.println("THE VOICE IS" + voice);

    if (message.contains("Code Blue"))
      path = getClass().getResource("/blue" + voice + ".wav").getPath();
    if (message.contains("Code Red"))
      path = getClass().getResource("/red" + voice + ".wav").getPath();
    if (message.contains("Code Black"))
      path = getClass().getResource("/black" + voice + ".wav").getPath();
    if (message.contains("Code Gray"))
      path = getClass().getResource("/gray" + voice + ".wav").getPath();
    if (message.contains("Code Yellow"))
      path = getClass().getResource("/yellow" + voice + ".wav").getPath();
    if (message.contains("Code Orange"))
      path = getClass().getResource("/orange" + voice + ".wav").getPath();
    if (message.contains("Code Pink"))
      path = getClass().getResource("/pink" + voice + ".wav").getPath();
    if (message.contains("Code Purple"))
      path = getClass().getResource("/purple" + voice + ".wav").getPath();
    if (message.contains("Code Green"))
      path = getClass().getResource("/green" + voice + ".wav").getPath();
    if (message.contains("Code Silver"))
      path = getClass().getResource("/silver" + voice + ".wav").getPath();

    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }
}
