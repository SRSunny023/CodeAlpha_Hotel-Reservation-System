package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Admin_Dashboard extends JFrame {

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

        JButton addEmployee = new JButton("Add Employee");
        addEmployee.setBounds(30,30,200,30);
        addEmployee.setFont(new Font("Arial",Font.BOLD,16));
        addEmployee.setForeground(Color.WHITE);
        addEmployee.setBackground(Color.BLACK);
        addEmployee.setFocusPainted(false);
        leftPanel.add(addEmployee);
        addEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton addRoom = new JButton("Add Room");
        addRoom.setBounds(30,70,200,30);
        addRoom.setFont(new Font("Arial",Font.BOLD,16));
        addRoom.setForeground(Color.WHITE);
        addRoom.setBackground(Color.BLACK);
        leftPanel.add(addRoom);
        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton logout = new JButton("Logout");
        logout.setBounds(30,110,200,30);
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
        exit.setBounds(30,150,200,30);
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
