package Hotel.Reservation.System;

import java.awt.*;
import java.io.*;

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
                try{

                    BufferedReader br = new BufferedReader(new FileReader("currentSession.txt"));
                    String line = br.readLine();
                    if(line==null){
                        new Login();
                    }
                    String[] parts = line.split("\\|");
                    String userRole = parts[1];
                    if(userRole.equals("User")){
                        new User_Dashboard();
                    } else{
                        new Admin_Dashboard();
                    }
                    br.close();

                } catch(Exception ex){
                    return;
                }
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
