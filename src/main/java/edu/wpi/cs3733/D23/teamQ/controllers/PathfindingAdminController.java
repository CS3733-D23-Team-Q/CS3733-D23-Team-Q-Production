package edu.wpi.cs3733.D23.teamQ.controllers;

import java.io.IOException;

public class PathfindingAdminController extends PathfindingController {
  public void settingButtonClicked() throws IOException {
    PathfindingSettingController.display();
  }
}
