package Hotel.Reservation.System.view;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import Hotel.Reservation.System.view.admin.*;
import Hotel.Reservation.System.view.auth.*;
import Hotel.Reservation.System.view.user.*;

public class Welcome extends JFrame {

    public static final String DATA_FOLDER = "data/";
    public static final String CURRENT_SESSION_FILE = DATA_FOLDER + "currentSession.txt";
    public static final String USERS_FILE = DATA_FOLDER + "users.txt";
    public static final String ROOM_FILE = DATA_FOLDER + "room.txt";
    public static final String USER_PROFILE = DATA_FOLDER + "userProfile.txt";
    public static final String RESERVATION_FILE = DATA_FOLDER + "reservation.txt";
    public static final String PAYMENT_FILE = DATA_FOLDER + "payment.txt";
    public static final String CARD_FILE = DATA_FOLDER + "cardInfo.txt";

    public static final String ICON_FOLDER = "resources/icon/";
    public static final String BED_ICON = ICON_FOLDER + "Bed.png";
    public static final String LOGO_ICON = ICON_FOLDER + "Logo.png";
    public static final String WELCOME_ICON = ICON_FOLDER + "Welcome.png";

    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    public static final int X_POSITION = 448;
    public static final int Y_POSITION = 156;
    public static final int STARTING_TIME = 2000;

    Welcome() {

        ImageIcon imageIcon = new ImageIcon(WELCOME_ICON);
        Image scaledImage = imageIcon.getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        add(label);

        setLayout(null);
        setUndecorated(true);
        setLocation(X_POSITION, Y_POSITION);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);

        try {
            Timer timer = new Timer(STARTING_TIME, e -> {
                setVisible(false);
                try {

                    BufferedReader br = new BufferedReader(new FileReader(CURRENT_SESSION_FILE));
                    String line = br.readLine();
                    if (line == null) {
                        new Login();
                    }
                    String[] parts = line.split("\\|");
                    String userRole = parts[1];
                    if (userRole.equals("User")) {
                        new User_Dashboard();
                    } else {
                        new Admin_Dashboard();
                    }
                    br.close();

                } catch (Exception ex) {
                    return;
                }
            });

            timer.setRepeats(false);
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Welcome();
    }
}
