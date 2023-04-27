package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import javafx.scene.chart.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

import java.sql.Date;
import java.time.LocalDate;

public class StatisticsController {
  Qdb qdb = Qdb.getInstance();
  @FXML Label users;
  @FXML Label nodes;
  @FXML Label requests;

  @FXML
  public void initialize() {
    users.setText("Total users: " + qdb.retrieveAllAccounts().size());
    nodes.setText("Total nodes: " + qdb.retrieveAllNodes().size());
    requests.setText("Total requests: " + qdb.retrieveAllServiceRequests().size());
//
//    BarChart<Date, Number> chart = new BarChart<>(new DateAxis(), new NumberAxis());
//    chart.setTitle("Sales by Date");
//
//    // Add data to the chart
//    XYChart.Series<Date, Number> series = new XYChart.Series<>();
//    series.setName("Sales");
//    series.getData().add(new XYChart.Data<>(LocalDate.of(2022, 1, 1), 100));
//    series.getData().add(new XYChart.Data<>(LocalDate.of(2022, 2, 1), 200));
//    series.getData().add(new XYChart.Data<>(LocalDate.of(2022, 3, 1), 150));
//    series.getData().add(new XYChart.Data<>(LocalDate.of(2022, 4, 1), 300));
//    chart.getData().add(series);


  }
}
