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

        JButton newCustomerForm = new JButton("New Customer Form");
        newCustomerForm.setBounds(30,30,200,30);
        newCustomerForm.setFont(new Font("Arial",Font.BOLD,16));
        newCustomerForm.setForeground(Color.WHITE);
        newCustomerForm.setBackground(Color.BLACK);
        newCustomerForm.setFocusPainted(false);
        leftPanel.add(newCustomerForm);
        newCustomerForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton room = new JButton("Room");
        room.setBounds(30,70,200,30);
        room.setFont(new Font("Arial",Font.BOLD,16));
        room.setForeground(Color.WHITE);
        room.setBackground(Color.BLACK);
        leftPanel.add(room);
        room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton department = new JButton("Department");
        department.setBounds(30,110,200,30);
        department.setFont(new Font("Arial",Font.BOLD,16));
        department.setForeground(Color.WHITE);
        department.setBackground(Color.BLACK);
        leftPanel.add(department);
        department.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton allEmployeeInfo = new JButton("All Employee Info");
        allEmployeeInfo.setBounds(30,150,200,30);
        allEmployeeInfo.setFont(new Font("Arial",Font.BOLD,16));
        allEmployeeInfo.setForeground(Color.WHITE);
        allEmployeeInfo.setBackground(Color.BLACK);
        leftPanel.add(allEmployeeInfo);
        allEmployeeInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton customerInfo = new JButton("Customer Info");
        customerInfo.setBounds(30,190,200,30);
        customerInfo.setFont(new Font("Arial",Font.BOLD,16));
        customerInfo.setForeground(Color.WHITE);
        customerInfo.setBackground(Color.BLACK);
        leftPanel.add(customerInfo);
        customerInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton managerInfo = new JButton("Manager Info");
        managerInfo.setBounds(30,230,200,30);
        managerInfo.setFont(new Font("Arial",Font.BOLD,16));
        managerInfo.setForeground(Color.WHITE);
        managerInfo.setBackground(Color.BLACK);
        leftPanel.add(managerInfo);
        managerInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton checkOut = new JButton("Check Out");
        checkOut.setBounds(30,270,200,30);
        checkOut.setFont(new Font("Arial",Font.BOLD,16));
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        leftPanel.add(checkOut);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton updateCheckIn = new JButton("Update Check-In Details");
        updateCheckIn.setBounds(30,310,200,30);
        updateCheckIn.setFont(new Font("Arial",Font.BOLD,12));
        updateCheckIn.setForeground(Color.WHITE);
        updateCheckIn.setBackground(Color.BLACK);
        leftPanel.add(updateCheckIn);
        updateCheckIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton updateRoomStatus = new JButton("Update Room Status");
        updateRoomStatus.setBounds(30,350,200,30);
        updateRoomStatus.setFont(new Font("Arial",Font.BOLD,16));
        updateRoomStatus.setForeground(Color.WHITE);
        updateRoomStatus.setBackground(Color.BLACK);
        leftPanel.add(updateRoomStatus);
        updateRoomStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton pickUpService = new JButton("Pick Up Service");
        pickUpService.setBounds(30,390,200,30);
        pickUpService.setFont(new Font("Arial",Font.BOLD,16));
        pickUpService.setForeground(Color.WHITE);
        pickUpService.setBackground(Color.BLACK);
        leftPanel.add(pickUpService);
        pickUpService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        JButton searchRoom = new JButton("Search Room");
        searchRoom.setBounds(30,430,200,30);
        searchRoom.setFont(new Font("Arial",Font.BOLD,16));
        searchRoom.setForeground(Color.WHITE);
        searchRoom.setBackground(Color.BLACK);
        leftPanel.add(searchRoom);
        searchRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{

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
        new User_Dashboard();
    }

}
