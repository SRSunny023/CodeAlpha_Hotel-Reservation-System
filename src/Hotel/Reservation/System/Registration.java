package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Registration extends JFrame implements ActionListener {

    JTextField usernameField;
    JTextField emailField;

    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    JComboBox<String> roleBox;

    JButton register;
    JButton back;


    Registration(){

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(131,120,220,40);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        usernameLabel.setForeground(Color.WHITE);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(400,120,200,40);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 24));
        usernameField.setForeground(Color.WHITE);
        usernameField.setBackground(Color.BLACK);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(131,200,220,40);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 28));
        emailLabel.setForeground(Color.WHITE);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(400,200,200,40);
        emailField.setFont(new Font("Arial", Font.PLAIN, 24));
        emailField.setForeground(Color.WHITE);
        emailField.setBackground(Color.BLACK);
        add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(131,280,220,40);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 28));
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(400,280,200,40);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(Color.BLACK);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(131,360,260,40);
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 28));
        confirmPasswordLabel.setForeground(Color.WHITE);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(400,360,200,40);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 24));
        confirmPasswordField.setForeground(Color.WHITE);
        confirmPasswordField.setBackground(Color.BLACK);
        add(confirmPasswordField);

        register = new JButton("Register");
        register.setBounds(200,570,250,60);
        register.setFont(new Font("Arial", Font.BOLD, 28));
        register.setForeground(Color.WHITE);
        register.setBackground(new Color(235, 173, 51));
        register.addActionListener(this);
        add(register);

        back = new JButton("Back");
        back.setBounds(480,570,250,60);
        back.setFont(new Font("Arial", Font.BOLD, 28));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(235, 173, 51));
        back.addActionListener(this);
        add(back);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Logo.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(455, 500, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(531,100,455,500);
        add(label);

        getContentPane().setBackground(Color.BLACK);

        setLayout(null);
        setUndecorated(true);
        setLocation( Welcome.X_POSITION, Welcome.Y_POSITION);
        setSize( Welcome.WINDOW_WIDTH, Welcome.WINDOW_HEIGHT);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == register){
            registerUser();
        } else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    }

    private void registerUser() {

        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = "User";

        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){

            JOptionPane.showMessageDialog(this,"Please fill all fields!","Registration Error",JOptionPane.ERROR_MESSAGE);
            return;

        }

        if(!email.contains("@") || !email.contains(".")){

            JOptionPane.showMessageDialog(this,"Please enter a valid email address!","Registration Error",JOptionPane.ERROR_MESSAGE);
            return;

        }

        if(!password.equals(confirmPassword)){

            JOptionPane.showMessageDialog(this,"Passwords do not match!","Registration Error",JOptionPane.ERROR_MESSAGE);
            return;

        }

        if(usernameExists(username)){

            JOptionPane.showMessageDialog(this,"Username already exists!","Registration Error",JOptionPane.ERROR_MESSAGE);
            return;

        }

        try{

            FileWriter writer = new FileWriter("users.txt",true);

            writer.write(username + "|" + email + "|" + password + "|" + role + "\n");
            writer.close();

            JOptionPane.showMessageDialog(this,"Registration Successful!","Success",JOptionPane.INFORMATION_MESSAGE);

            new Login();

            setVisible(false);


        } catch (IOException e){

            JOptionPane.showMessageDialog(this,"Error saving user information!","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        }
    }

    private boolean usernameExists(String username) {

        File file = new File("users.txt");

        if(!file.exists()){
            return false;
        }

        try{

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while((line = reader.readLine()) != null){

                String[] data = line.split("\\|");

                if (data.length >= 1 && data[0].equals(username)){
                    reader.close();
                    return true;
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return false;
    }

    public static void main(String[] args) {

        new Registration();
    }
}