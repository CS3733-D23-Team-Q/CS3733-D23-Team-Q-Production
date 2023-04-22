package edu.wpi.cs3733.D23.teamQ.db.impl;

import edu.wpi.cs3733.D23.teamQ.db.dao.GenDao;
import edu.wpi.cs3733.D23.teamQ.db.obj.ProfileImage;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class ProfileImageDaoImpl implements GenDao<ProfileImage, String> {
  private static ProfileImageDaoImpl single_instance = null;
  private List<ProfileImage> profileImages = new ArrayList<ProfileImage>();

  private ProfileImageDaoImpl() throws SQLException {
    populate();
  }

  public static synchronized ProfileImageDaoImpl getInstance() {
    if (single_instance == null) {
      try {
        single_instance = new ProfileImageDaoImpl();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return single_instance;
  }

  @Override
  public List<ProfileImage> getAllRows() {
    return profileImages;
  }

  @Override
  public ProfileImage retrieveRow(String username) {
    int index = this.getIndex(username);
    return profileImages.get(index);
  }

  @Override
  public boolean updateRow(String username, ProfileImage x) throws SQLException {
    boolean result = false;
    byte[] imageData = x.getImageData();
    try (Connection connection = GenDao.connect()) {
      String sql = "UPDATE \"profileImage\" SET image = ? WHERE username = ?";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setBytes(1, imageData);
      statement.setString(2, username);

      int rs = statement.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(username);
        profileImages.get(index).setImageData(imageData);
        System.out.println("Updated successful!");
      } else {
        System.out.println("Failed to update.");
      }
      connection.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean deleteRow(String username) throws SQLException {
    boolean result = false;
    try (Connection connection = GenDao.connect()) {
      String sql = "DELETE FROM \"profileImage\" WHERE username = ?";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, username);

      int rs = statement.executeUpdate();
      if (rs == 1) {
        result = true;
        int index = this.getIndex(username);
        profileImages.remove(index);
        System.out.println("ProfileImage deleted.");
      } else {
        System.out.println("Failed to delete ProfileImage.");
      }
      connection.close();
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean addRow(ProfileImage x) {
    boolean result = false;
    String username = x.getUsername();
    byte[] imageData = x.getImageData();
    try (Connection connection = GenDao.connect()) {
      String sql = "INSERT INTO \"profileImage\" (username, image) VALUES (?, ?)";
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, username);
      statement.setBytes(2, imageData);

      int rs = statement.executeUpdate();
      if (rs == 1) {
        result = true;
        profileImages.add(x);
        System.out.println("ProfileImage created.");
      } else {
        System.out.println("Failed to create ProfileImage.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean populate() throws SQLException {
    Connection connection = GenDao.connect();
    try {
      String query = "SELECT * FROM \"profileImage\"";
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      while (rs.next()) {
        ProfileImage image = new ProfileImage(rs.getString("username"), rs.getBytes("image"));
        profileImages.add(image);
      }
      connection.close();
      statement.close();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public int getIndex(String uname) {
    for (int i = 0; i < profileImages.size(); i++) {
      ProfileImage image = profileImages.get(i);
      if (image.getUsername().equals(uname)) {
        return i;
      }
    }
    return -1;
  }

  public byte[] convertImageToBytea(Image image) {
    try {
      BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

      File tempFile = File.createTempFile("temp", ".png");
      ImageIO.write(bufferedImage, "png", tempFile);

      byte[] imageData;
      try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        BufferedImage img = ImageIO.read(tempFile);
        ImageIO.write(img, "png", baos);
        imageData = baos.toByteArray();
      }

      tempFile.delete();

      return imageData;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Image convertByteaToImage(byte[] imageData) {
    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
    Image pfp = new Image(bis);
    return pfp;
  }
}
