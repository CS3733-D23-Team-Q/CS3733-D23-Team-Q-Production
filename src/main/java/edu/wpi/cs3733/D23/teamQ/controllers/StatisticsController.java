package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.obj.Alert;
import edu.wpi.cs3733.D23.teamQ.db.obj.Message;
import edu.wpi.cs3733.D23.teamQ.db.obj.Node;
import edu.wpi.cs3733.D23.teamQ.db.obj.ServiceRequest;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StatisticsController {
  Qdb qdb = Qdb.getInstance();

  @FXML VBox vbox;
  @FXML MFXScrollPane sp;

  @FXML
  public void initialize() {
    Qdb qdb = Qdb.getInstance();
    messages();
    serviceRequests();
    serviceRequestPieChart();
    alerts();
    nodes();
    sp.setVvalue(vbox.getHeight());
  }

  public void messages() {
    Map<String, Integer> messageCounts = new HashMap<>();
    for (Message message : qdb.retrieveAllMessages()) {
      String date = new SimpleDateFormat("MMM-dd-yyy").format(new Date(message.getTimeStamp()));
      if (!messageCounts.containsKey(date)) {
        messageCounts.put(date, 0);
      }
      messageCounts.put(date, messageCounts.get(date) + 1);
    }

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Messages Sent Per Day");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Messages");
    for (String date : messageCounts.keySet()) {
      series.getData().add(new XYChart.Data<>(date, messageCounts.get(date)));
    }

    barChart.getData().add(series);
    barChart.setMinHeight(300);
    vbox.getChildren().add(barChart);
  }

  public void serviceRequests() {
    Map<String, Integer> srCounts = new HashMap<>();
    for (ServiceRequest sr : qdb.getAllServiceRequestsObservable()) {
      String date = new SimpleDateFormat("MMM-dd-yyy").format(sr.getDate());
      if (!srCounts.containsKey(date)) {
        srCounts.put(date, 0);
      }
      srCounts.put(date, srCounts.get(date) + 1);
    }

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Service Requests Per Day");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Service Request");
    for (String date : srCounts.keySet()) {
      series.getData().add(new XYChart.Data<>(date, srCounts.get(date)));
    }
    barChart.getData().add(series);
    barChart.setMinHeight(300);
    vbox.getChildren().add(barChart);
  }

  public void serviceRequestPieChart() {
    Qdb qdb = Qdb.getInstance();
    PieChart.Data slice1 =
        new PieChart.Data("Conference Room Requests", qdb.retrieveAllConferenceRequests().size());
    PieChart.Data slice2 =
        new PieChart.Data("Flower Requests", qdb.retrieveAllFlowerRequests().size());
    PieChart.Data slice3 =
        new PieChart.Data("Office Supply Requests", qdb.retrieveAllOfficeSuppliesRequests().size());
    PieChart.Data slice4 =
        new PieChart.Data("Furniture Requests", qdb.retrieveAllFurnitureRequests().size());
    PieChart.Data slice5 =
        new PieChart.Data("Meal Delivery Requests", qdb.retrieveAllMealRequests().size());
    PieChart.Data slice6 =
        new PieChart.Data(
            "Medial Supply Requests", qdb.retrieveAllMedicalSuppliesRequests().size());

    PieChart pieChart = new PieChart();
    pieChart.getData().add(slice1);
    pieChart.getData().add(slice2);
    pieChart.getData().add(slice3);
    pieChart.getData().add(slice4);
    pieChart.getData().add(slice5);
    pieChart.getData().add(slice6);
    Label srLabel = new Label("Service Requests by type");
    srLabel.setFont(Font.font(16));
    vbox.getChildren().add(srLabel);
    Label srNum =
        new Label("Total Service Requests: " + qdb.getAllServiceRequestsObservable().size());
    srNum.setFont(Font.font(16));
    vbox.getChildren().add(srNum);
    vbox.getChildren().add(pieChart);
  }

  public void alerts() {
    Map<String, Integer> alertCounts = new HashMap<>();
    for (Alert alert : qdb.retrieveAllAlerts()) {
      String date = new SimpleDateFormat("MMM-dd-yyy").format(new Date(alert.getTimestamp()));
      if (!alertCounts.containsKey(date)) {
        alertCounts.put(date, 0);
      }
      alertCounts.put(date, alertCounts.get(date) + 1);
    }

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Alerts Sent Per Day");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Alerts");
    for (String date : alertCounts.keySet()) {
      series.getData().add(new XYChart.Data<>(date, alertCounts.get(date)));
    }

    barChart.getData().add(series);
    barChart.setMinHeight(300);
    vbox.getChildren().add(barChart);
  }

  public void nodes() {
    Map<String, Integer> nodeCounts = new HashMap<>();
    for (Node node : qdb.retrieveAllNodes()) {
      String floor = node.getFloor();
      if (!nodeCounts.containsKey(floor)) {
        nodeCounts.put(floor, 0);
      }
      nodeCounts.put(floor, nodeCounts.get(floor) + 1);
    }

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();

    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
    barChart.setTitle("Nodes by Floor");

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Nodes");
    for (String date : nodeCounts.keySet()) {
      series.getData().add(new XYChart.Data<>(date, nodeCounts.get(date)));
    }

    barChart.getData().add(series);
    barChart.setMinHeight(300);
    vbox.getChildren().add(barChart);
  }
}
