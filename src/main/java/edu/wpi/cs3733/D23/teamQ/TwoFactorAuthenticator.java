package edu.wpi.cs3733.D23.teamQ;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import java.util.Scanner;

public class TwoFactorAuthenticator {
  //  String username = LoginController.getLoginUsername();
  GoogleAuthenticator gAuth = new GoogleAuthenticator();
  //  GoogleAuthenticator.
  GoogleAuthenticatorKey key = gAuth.createCredentials();

  public void createTwoFactor() {
    String qrCodeUrl =
        GoogleAuthenticatorQRGenerator.getOtpAuthURL("TeamQ2FactorTest", "test", key);
    System.out.println("Scan this QR code with your Google Authenticator app:");
    System.out.println(qrCodeUrl);
    runTwoFactor();
  }

  public void runTwoFactor() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the verification code: ");
    int verificationCode = scanner.nextInt();

    boolean isCodeValid = gAuth.authorize(key.getKey(), verificationCode);
    if (isCodeValid) {
      System.out.println("Authentication successful");
    } else {
      System.out.println("Authentication failed");
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter yes or no if 2factor has been set up on your account");
    String name = scanner.nextLine();

    TwoFactorAuthenticator twoF = new TwoFactorAuthenticator();

    if (name.equals("yes")) {
      twoF.runTwoFactor();
    } else {
      twoF.createTwoFactor();
    }
  }
}
