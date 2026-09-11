package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Admin_Dashboard extends JFrame {

    public static final int LABEL_X_POSITION = 30;
    public static final int DISTANCE = 30;
    public static final int DISTANCE_GAP = 40;

    Admin_Dashboard(){

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(280,5,800,Welcome.WINDOW_HEIGHT);
        rightPanel.setBackground(new Color(235,173,51));
        add(rightPanel);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBounds(5,5,270,Welcome.WINDOW_HEIGHT);
        leftPanel.setBackground(new Color(235,120,51));
        add(leftPanel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Welcome.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(800, Welcome.WINDOW_HEIGHT, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(0,0,800,Welcome.WINDOW_HEIGHT);
        rightPanel.add(label);

        JButton addRoom = new JButton("Add Room");
        addRoom.setBounds(LABEL_X_POSITION,DISTANCE,Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        addRoom.setFont(new Font("Arial",Font.BOLD,16));
        addRoom.setForeground(Color.WHITE);
        addRoom.setBackground(Color.BLACK);
        addRoom.setFocusPainted(false);
        leftPanel.add(addRoom);
        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    new Add_Room();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton manageRooms = new JButton("Manage Rooms");
        manageRooms.setBounds(LABEL_X_POSITION,DISTANCE+(1*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        manageRooms.setFont(new Font("Arial",Font.BOLD,16));
        manageRooms.setForeground(Color.WHITE);
        manageRooms.setBackground(Color.BLACK);
        leftPanel.add(manageRooms);
        manageRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton roomAvailability = new JButton("Room Availability");
        roomAvailability.setBounds(LABEL_X_POSITION,DISTANCE+(2*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        roomAvailability.setFont(new Font("Arial",Font.BOLD,16));
        roomAvailability.setForeground(Color.WHITE);
        roomAvailability.setBackground(Color.BLACK);
        roomAvailability.setFocusPainted(false);
        leftPanel.add(roomAvailability);
        roomAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton manageUser = new JButton("Manage User");
        manageUser.setBounds(LABEL_X_POSITION,DISTANCE+(3*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        manageUser.setFont(new Font("Arial",Font.BOLD,16));
        manageUser.setForeground(Color.WHITE);
        manageUser.setBackground(Color.BLACK);
        manageUser.setFocusPainted(false);
        leftPanel.add(manageUser);
        manageUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    new Manage_Users();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton allReservations = new JButton("All Reservations");
        allReservations.setBounds(LABEL_X_POSITION,DISTANCE+(4*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        allReservations.setFont(new Font("Arial",Font.BOLD,16));
        allReservations.setForeground(Color.WHITE);
        allReservations.setBackground(Color.BLACK);
        leftPanel.add(allReservations);
        allReservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton customerInformation = new JButton("Customer Info");
        customerInformation.setBounds(LABEL_X_POSITION,DISTANCE+(5*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        customerInformation.setFont(new Font("Arial",Font.BOLD,16));
        customerInformation.setForeground(Color.WHITE);
        customerInformation.setBackground(Color.BLACK);
        customerInformation.setFocusPainted(false);
        leftPanel.add(customerInformation);
        customerInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton paymentRecords = new JButton("Payment Records");
        paymentRecords.setBounds(LABEL_X_POSITION,DISTANCE+(6*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        paymentRecords.setFont(new Font("Arial",Font.BOLD,16));
        paymentRecords.setForeground(Color.WHITE);
        paymentRecords.setBackground(Color.BLACK);
        leftPanel.add(paymentRecords);
        paymentRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton reports = new JButton("Reports");
        reports.setBounds(LABEL_X_POSITION,DISTANCE+(7*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        reports.setFont(new Font("Arial",Font.BOLD,16));
        reports.setForeground(Color.WHITE);
        reports.setBackground(Color.BLACK);
        leftPanel.add(reports);
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(LABEL_X_POSITION,DISTANCE+(8*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        logout.setFont(new Font("Arial",Font.BOLD,16));
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.BLACK);
        leftPanel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    new Login();
                    setVisible(false);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton exit = new JButton("Exit");
        exit.setBounds(LABEL_X_POSITION,DISTANCE+(9*DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        exit.setFont(new Font("Arial",Font.BOLD,16));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        leftPanel.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    System.exit(0);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        getContentPane().setBackground(Color.BLACK);

        setLayout(null);
        setLocation(Welcome.X_POSITION, Welcome.Y_POSITION);
        setSize(Welcome.WINDOW_WIDTH, Welcome.WINDOW_HEIGHT);
        setVisible(true);

    }

    public static void main(String[] args){
        new Admin_Dashboard();
    }

}
