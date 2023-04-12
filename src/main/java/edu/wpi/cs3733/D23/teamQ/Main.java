package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;

public class Main {

  static int counter = 0;

  public static void main(String[] args) throws InterruptedException {
    Qdb.getInstance();
    App.launch(App.class, args);
  }
}

  // shortcut: psvm
