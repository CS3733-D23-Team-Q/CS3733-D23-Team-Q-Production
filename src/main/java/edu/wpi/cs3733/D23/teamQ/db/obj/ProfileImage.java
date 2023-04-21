package edu.wpi.cs3733.D23.teamQ.db.obj;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProfileImage {
    private String username;
    private byte[] imageData;

    public ProfileImage(String username, byte[] imageData) {
        this.username = username;
        this.imageData = imageData;
    }
}
