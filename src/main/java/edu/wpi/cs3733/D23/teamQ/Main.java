package edu.wpi.cs3733.D23.teamQ;

import edu.wpi.cs3733.D23.teamQ.db.Qdb;
import edu.wpi.cs3733.D23.teamQ.db.RefreshThread;

public class Main {

  public static void main(String[] args) {
    Thread refresh = new Thread(new RefreshThread());
    refresh.start();

    Qdb.getInstance();
    App.launch(App.class, args);
  }
}
