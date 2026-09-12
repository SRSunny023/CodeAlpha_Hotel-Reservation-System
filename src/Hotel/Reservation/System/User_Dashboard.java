package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class User_Dashboard extends JFrame {

    User_Dashboard(){

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

        JButton searchRooms = new JButton("Search Rooms");
        searchRooms.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE,Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        searchRooms.setFont(new Font("Arial",Font.BOLD,16));
        searchRooms.setForeground(Color.WHITE);
        searchRooms.setBackground(Color.BLACK);
        searchRooms.setFocusPainted(false);
        leftPanel.add(searchRooms);
        searchRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    new Search_Room();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton makeReservation = new JButton("Make Reservation");
        makeReservation.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(1*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        makeReservation.setFont(new Font("Arial",Font.BOLD,16));
        makeReservation.setForeground(Color.WHITE);
        makeReservation.setBackground(Color.BLACK);
        leftPanel.add(makeReservation);
        makeReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton myReservations = new JButton("My Reservations");
        myReservations.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(2*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        myReservations.setFont(new Font("Arial",Font.BOLD,16));
        myReservations.setForeground(Color.WHITE);
        myReservations.setBackground(Color.BLACK);
        leftPanel.add(myReservations);
        myReservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton cancelReservation = new JButton("Cancel Reservation");
        cancelReservation.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(3*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        cancelReservation.setFont(new Font("Arial",Font.BOLD,16));
        cancelReservation.setForeground(Color.WHITE);
        cancelReservation.setBackground(Color.BLACK);
        leftPanel.add(cancelReservation);
        cancelReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton payment = new JButton("Payment");
        payment.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(4*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        payment.setFont(new Font("Arial",Font.BOLD,16));
        payment.setForeground(Color.WHITE);
        payment.setBackground(Color.BLACK);
        leftPanel.add(payment);
        payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton myPortfolio = new JButton("My Portfolio");
        myPortfolio.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(5*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        myPortfolio.setFont(new Font("Arial",Font.BOLD,16));
        myPortfolio.setForeground(Color.WHITE);
        myPortfolio.setBackground(Color.BLACK);
        leftPanel.add(myPortfolio);
        myPortfolio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(6*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
        logout.setFont(new Font("Arial",Font.BOLD,16));
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.BLACK);
        leftPanel.add(logout);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    setVisible(false);
                    new Login();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton exit = new JButton("Exit");
        exit.setBounds(Admin_Dashboard.LABEL_X_POSITION,Admin_Dashboard.DISTANCE+(7*Admin_Dashboard.DISTANCE_GAP),Add_Room.LABEL_WIDTH,Add_Room.LABEL_HEIGHT);
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
        setUndecorated(true);
        setLocation(Welcome.X_POSITION, Welcome.Y_POSITION);
        setSize(Welcome.WINDOW_WIDTH, Welcome.WINDOW_HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args){
        new User_Dashboard();
    }

}
