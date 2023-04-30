package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.RefreshThread;

public class Main {
  public static Thread refresh = new Thread(new RefreshThread());

  public static void main(String[] args) {
    Qdb.getInstance();
    refresh.start();
    App.launch(App.class, args);
  }
}
