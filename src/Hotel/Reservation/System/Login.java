package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener{

    JTextField usernameField;
    JPasswordField passwordField;
    JButton login,exit,registration;

    Login(){

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(131,220,200,40);
        usernameLabel.setFont(new Font("Arial",Font.BOLD,32));
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(131,385,200,40);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,32));
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(331,220,200,40);
        usernameField.setFont(new Font("Arial",Font.PLAIN,32));
        usernameField.setForeground(Color.WHITE);
        usernameField.setBackground(Color.BLACK);
        add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(331,385,200,40);
        passwordField.setFont(new Font("Arial",Font.PLAIN,32));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(Color.BLACK);
        add(passwordField);


        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Logo.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(455, 500, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(531,100,455,500);
        add(label);

        login = new JButton("Login");
        login.setBounds(131,610,240,60);
        login.setFont(new Font("Arial",Font.BOLD,32));
        login.setForeground(Color.WHITE);
        login.setBackground(new Color(235,173,51));
        login.addActionListener(this);
        add(login);

        exit = new JButton("Exit");
        exit.setBounds(391,610,240,60);
        exit.setFont(new Font("Arial",Font.BOLD,32));
        exit.setForeground(Color.WHITE);
        exit.setBackground(new Color(235,173,51));
        exit.addActionListener(this);
        add(exit);

        registration = new JButton("Registration");
        registration.setBounds(651,610,240,60);
        registration.setFont(new Font("Arial",Font.BOLD,32));
        registration.setForeground(Color.WHITE);
        registration.setBackground(new Color(235,173,51));
        registration.addActionListener(this);
        add(registration);


        getContentPane().setBackground(Color.BLACK);

        setLayout(null);
        setLocation(Welcome.X_POSITION, Welcome.Y_POSITION);
        setSize(Welcome.WINDOW_WIDTH, Welcome.WINDOW_HEIGHT);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == login){
            loginUser();
        } else if(e.getSource() == registration){
            new Registration();
            setVisible(false);
        } else{
            System.exit(0);
        }
    }

    private void loginUser() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if(username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter username and password!","Login Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean loginSuccessful = false;
        String userRole = "";

        try{

            File file = new File("users.txt");

            if(file.exists()){
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while((line = reader.readLine()) != null){
                    String[] data = line.split("\\|");
                    if(data.length == 4){
                        if(data[0].equals(username) && data[2].equals(password)){
                            loginSuccessful = true;
                            userRole = data[3];
                            break;
                        }
                    }
                }
                reader.close();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        if(loginSuccessful){
            JOptionPane.showMessageDialog(this,"Login Successful!\nRole: " + userRole,"Success",JOptionPane.INFORMATION_MESSAGE);
            if(userRole.contains("User")){
                new User_Dashboard();
            } else if(userRole=="Admin"){
                new Admin_Dashboard();
            }
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this,"Invalid username or password!","Login Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args){
        new Login();
    }

}
