package Hotel.Reservation.System;

import java.awt.*;
import javax.swing.*;

public class Welcome extends JFrame {

    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;
    public static final int X_POSITION = 448;
    public static final int Y_POSITION = 156;
    public static final int STARTING_TIME = 2000;

    Welcome(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Welcome.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        add(label);

        setLayout(null);
        setUndecorated(true);
        setLocation(X_POSITION,Y_POSITION);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setVisible(true);

        try{
            Timer timer = new Timer(STARTING_TIME, e -> {
                setVisible(false);
                new Login();
            });

            timer.setRepeats(false);
            timer.start();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Welcome();
    }
}
