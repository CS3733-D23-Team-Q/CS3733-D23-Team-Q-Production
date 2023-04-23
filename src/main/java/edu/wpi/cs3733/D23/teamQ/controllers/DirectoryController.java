package edu.wpi.cs3733.D23.teamQ.controllers;

import edu.wpi.cs3733.D23.teamQ.db.obj.Account;
import edu.wpi.cs3733.D23.teamQ.db.obj.ProfileImage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;

public class DirectoryController {
    @FXML TableView<EmployeeData> directoryTable;
    @FXML TableColumn<EmployeeData, Image> profileImageColumn;
    @FXML TableColumn<EmployeeData, String> nameColumn;
    @FXML TableColumn<EmployeeData, String> titleColumn;
    @FXML TableColumn<EmployeeData, String> statusColumn;

    public static class EmployeeData {
        private final Account account;
        private final ProfileImage profileImage;

        public EmployeeData(Account account, ProfileImage profileImage) {
            this.account = account;
            this.profileImage = profileImage;
        }
    }


}
