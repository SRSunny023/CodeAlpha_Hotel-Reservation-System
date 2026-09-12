package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class My_Portfolio extends JFrame {

    My_Portfolio(){

        String userName = loadUserName();
        String[] profile = new String[5];
        profile[0] = userName;
        profile[1] = ""; profile[2] = ""; profile[3] = ""; profile[4] = "";

        JLabel title = new JLabel("My Profile");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JLabel fullName = new JLabel("Full Name:");
        fullName.setBounds(40, 85, 250, 30);
        fullName.setFont(new Font("Arial", Font.BOLD, 18));
        fullName.setForeground(Color.WHITE);
        add(fullName);
        fullName.setVisible(false);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(300, 85, 250, 30);
        fullNameField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(fullNameField);
        fullNameField.setVisible(false);

        JLabel phoneNumber = new JLabel("Phone Number:");
        phoneNumber.setBounds(40, 140, 250, 30);
        phoneNumber.setFont(new Font("Arial", Font.BOLD, 18));
        phoneNumber.setForeground(Color.WHITE);
        add(phoneNumber);
        phoneNumber.setVisible(false);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setBounds(300, 140, 250, 30);
        phoneNumberField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(phoneNumberField);
        phoneNumberField.setVisible(false);

        JLabel nidNumber = new JLabel("NID Number:");
        nidNumber.setBounds(40, 195, 250, 30);
        nidNumber.setFont(new Font("Arial", Font.BOLD, 18));
        nidNumber.setForeground(Color.WHITE);
        add(nidNumber);
        nidNumber.setVisible(false);

        JTextField nidNumberField = new JTextField();
        nidNumberField.setBounds(300, 195, 250, 30);
        nidNumberField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(nidNumberField);
        nidNumberField.setVisible(false);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(40, 250, 100, 30);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBackground(Color.BLACK);
        add(submitBtn);
        submitBtn.setVisible(false);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String userFullName = fullNameField.getText();
                String userPhoneNumber = phoneNumberField.getText();
                String userNidNumber = nidNumberField.getText();
                boolean updated = false;
                try{
                    BufferedReader br = new BufferedReader(new FileReader("userProfile.txt"));
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) {
                            continue;
                        }
                        String[] data = line.split("\\|");
                        if (data.length >= 0 && data[0].equals(userName)) {
                            line = userName + "|" + userFullName + "|" + userPhoneNumber + "|" + userNidNumber;
                            updated = true;
                        }
                        content.append(line);
                        content.append(System.lineSeparator());
                    }

                    br.close();

                    if(updated){
                        FileWriter fw = new FileWriter("userProfile.txt");
                        fw.write(content.toString());
                        fw.close();
                        JOptionPane.showMessageDialog(null,"Profile Created Successfully!","Profile",JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch(Exception ex){
                    ex.printStackTrace();
                }
                setVisible(false);
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(150, 250, 100, 30);
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        add(backBtn);
        backBtn.setVisible(false);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });

        JButton editBtn = new JButton("Edit");
        editBtn.setBounds(255, 250, 100, 30);
        editBtn.setFont(new Font("Arial", Font.BOLD, 18));
        editBtn.setForeground(Color.WHITE);
        editBtn.setBackground(Color.BLACK);
        add(editBtn);
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                fullNameField.setEditable(true);
                phoneNumberField.setEditable(true);
                nidNumberField.setEditable(true);
                submitBtn.setVisible(true);
            }
        });

        if(loadProfile(userName,profile)){
            fullName.setVisible(true);
            fullNameField.setVisible(true);
            phoneNumber.setVisible(true);
            phoneNumberField.setVisible(true);
            nidNumber.setVisible(true);
            nidNumberField.setVisible(true);
            fullNameField.setEditable(false);
            phoneNumberField.setEditable(false);
            nidNumberField.setEditable(false);
            fullNameField.setText(profile[1]);
            phoneNumberField.setText(profile[2]);
            nidNumberField.setText(profile[3]);
            backBtn.setVisible(true);
        } else{
            fullName.setVisible(true);
            fullNameField.setVisible(true);
            phoneNumber.setVisible(true);
            phoneNumberField.setVisible(true);
            nidNumber.setVisible(true);
            nidNumberField.setVisible(true);
            submitBtn.setVisible(true);
            backBtn.setVisible(true);
            editBtn.setVisible(false);
        }

        getContentPane().setBackground(Color.BLACK);

        setUndecorated(true);
        setLayout(null);
        setLocation(Welcome.X_POSITION + 280,Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);
    }

    public boolean loadProfile(String currentUserName, String[] profile){
        try{

            BufferedReader br = new BufferedReader(new FileReader("userProfile.txt"));
            String line;
            while((line = br.readLine())!=null){
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                if(parts.length>=5){
                    String userName = parts[0];
                    String fullName = parts[1];
                    String phoneNumber = parts[2];
                    String nidNumber = parts[3];
                    String paymentCard = parts[4];

                    if(userName.equals(currentUserName)){
                        if(fullName==""){
                            br.close();
                            return false;
                        } else{
                            br.close();
                            profile[1] = fullName;
                            profile[2] = phoneNumber;
                            profile[3] = nidNumber;
                            profile[4] = paymentCard;
                            return true;
                        }
                    }

                }
            }
            br.close();
            return false;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String loadUserName(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("currentSession.txt"));
            String line = br.readLine();
            String[] parts = line.split("\\|");
            br.close();
            return parts[0];

        } catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args){
        new My_Portfolio();
    }

}
