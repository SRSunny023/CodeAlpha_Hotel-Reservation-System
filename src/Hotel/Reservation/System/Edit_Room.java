package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Edit_Room extends JFrame {

    Edit_Room(String roomNumber){

        JLabel title = new JLabel("Edit Room");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(40, 100, 150, 30);
        roomNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        roomNumberLabel.setForeground(Color.WHITE);
        add(roomNumberLabel);

        JTextField roomNumberField = new JTextField(roomNumber);
        roomNumberField.setBounds(200, 100, 180, 30);
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 18));
        roomNumberField.setEditable(false);
        add(roomNumberField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(40, 155, 150, 30);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setForeground(Color.WHITE);
        add(categoryLabel);

        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        categoryBox.setBounds(200, 155, 180, 30);
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(categoryBox);

        JLabel bedLabel = new JLabel("Bed Type:");
        bedLabel.setBounds(40, 210, 150, 30);
        bedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bedLabel.setForeground(Color.WHITE);
        add(bedLabel);

        JComboBox<String> bedBox =new JComboBox<>(new String[]{"Single Bed","Double Bed","King","Twin"});
        bedBox.setBounds(200, 210, 180, 30);
        bedBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(bedBox);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(40, 265, 150, 30);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(Color.WHITE);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(200, 265, 180, 30);
        priceField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(priceField);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(40, 320, 150, 30);
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availabilityLabel.setForeground(Color.WHITE);
        add(availabilityLabel);

        JComboBox<String> availabilityBox =new JComboBox<>(new String[]{"Available", "Maintenance"});
        availabilityBox.setBounds(200, 320, 180, 30);
        availabilityBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(availabilityBox);

        loadRoomData(roomNumber,categoryBox,bedBox,priceField,availabilityBox);

        JButton saveBtn = new JButton("Save Changes");
        saveBtn.setBounds(40, 400, 250, 40);
        saveBtn.setFont(new Font("Arial", Font.BOLD, 20));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(Color.BLACK);
        add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomNumber = roomNumberField.getText().trim();
                String category = categoryBox.getSelectedItem().toString();
                String bedType = bedBox.getSelectedItem().toString();
                String price = priceField.getText().trim();
                String availability = availabilityBox.getSelectedItem().toString();

                if(price.isEmpty()){
                    JOptionPane.showMessageDialog(Edit_Room.this,"Please enter the price!","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try{
                    Double.parseDouble(price);
                } catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(Edit_Room.this,"Price must be a valid number!","Invalid Price",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                updateRoom(roomNumber,category,bedType,price,availability);
            }

        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(430, 400, 200, 40);
        backBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Manage_Room();
            }
        });

        getContentPane().setBackground(Color.BLACK);
        setUndecorated(true);
        setLayout(null);
        setLocation(Welcome.X_POSITION + 280,Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);
    }

    public void loadRoomData(String selectedRoomNumber,JComboBox<String> categoryBox,JComboBox<String> bedBox,JTextField priceField,JComboBox<String> availabilityBox){

        try{
            BufferedReader br = new BufferedReader(new FileReader("room.txt"));
            String line;
            while((line = br.readLine()) != null){

                if(line.trim().isEmpty()){
                    continue;
                }

                String[] data = line.split("\\|");

                if(data.length >= 5){
                    String roomNumber = data[0];
                    String category = data[1];
                    String bedType = data[2];
                    String price = data[3];
                    String availability = data[4];

                    if(roomNumber.equals(selectedRoomNumber)){
                        categoryBox.setSelectedItem(category);
                        bedBox.setSelectedItem(bedType);
                        priceField.setText(price);
                        availabilityBox.setSelectedItem(availability);
                        break;
                    }

                }

            }

            br.close();

        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(this,"room.txt not found!","Error",JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public void updateRoom(String roomNumber,String category,String bedType,String price,String availability){

        try{
            BufferedReader br = new BufferedReader(new FileReader("room.txt"));
            StringBuilder content = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;
                }
                String[] data = line.split("\\|");
                if(data.length >= 5){
                    String currentRoomNumber = data[0];
                    if(currentRoomNumber.equalsIgnoreCase(roomNumber)){
                        line = roomNumber + "|" + category + "|" + bedType + "|" + price+ "|" + availability;
                    }
                }
                content.append(line);
                content.append(System.lineSeparator());
            }

            br.close();

            FileWriter fw = new FileWriter("room.txt");
            fw.write(content.toString());
            fw.close();

            JOptionPane.showMessageDialog(this,"Room updated successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Manage_Room();

        } catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(this,"room.txt not found!","Error",JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            JOptionPane.showMessageDialog(this,"Error updating room!","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

}
