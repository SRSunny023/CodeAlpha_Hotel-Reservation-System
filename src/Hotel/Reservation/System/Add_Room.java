package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Add_Room extends JFrame {

    public static final int LABEL_WIDTH = 200;
    public static final int LABEL_HEIGHT = 30;
    public static final int LABEL_X_POSITION = 5;
    public static final int FIELD_X_POSITION = 210;
    public static final int DISTANCE = 70;
    public static final int DISTANCE_GAP = 55;

    Add_Room(){

        JLabel addRoom = new JLabel("Add Room");
        addRoom.setBounds(312,5,LABEL_WIDTH,LABEL_HEIGHT);
        addRoom.setFont(new Font("Arial",Font.BOLD,32));
        addRoom.setForeground(Color.WHITE);
        add(addRoom);

        JLabel roomNumber = new JLabel("Room Number");
        roomNumber.setBounds(LABEL_X_POSITION,DISTANCE,LABEL_WIDTH,LABEL_HEIGHT);
        roomNumber.setFont(new Font("Arial",Font.BOLD,26));
        roomNumber.setForeground(Color.WHITE);
        add(roomNumber);

        JTextField roomNumberTextField = new JTextField();
        roomNumberTextField.setBounds(FIELD_X_POSITION,DISTANCE,LABEL_WIDTH,LABEL_HEIGHT);
        roomNumberTextField.setFont(new Font("Arial",Font.PLAIN,26));
        add(roomNumberTextField);

        JLabel category = new JLabel("Category");
        category.setBounds(LABEL_X_POSITION,DISTANCE+(1*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        category.setFont(new Font("Arial",Font.BOLD,26));
        category.setForeground(Color.WHITE);
        add(category);

        JComboBox<String> categoryCombo = new JComboBox<>(new String[] {"Standard", "Deluxe", "Suite"});
        categoryCombo.setBounds(FIELD_X_POSITION,DISTANCE+(1*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        categoryCombo.setFont(new Font("Arial",Font.PLAIN,26));
        add(categoryCombo);

        JLabel bedType = new JLabel("Bed Type");
        bedType.setBounds(LABEL_X_POSITION,DISTANCE+(2*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        bedType.setFont(new Font("Arial",Font.BOLD,26));
        bedType.setForeground(Color.WHITE);
        add(bedType);

        JComboBox<String> bedTypeCombo = new JComboBox<>(new String[] {"Single Bed", "Double Bed", "King", "Twin"});
        bedTypeCombo.setBounds(FIELD_X_POSITION,DISTANCE+(2*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        bedTypeCombo.setFont(new Font("Arial",Font.PLAIN,26));
        add(bedTypeCombo);

        JLabel price = new JLabel("Price");
        price.setBounds(LABEL_X_POSITION,DISTANCE+(3*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        price.setFont(new Font("Arial",Font.BOLD,26));
        price.setForeground(Color.WHITE);
        add(price);

        JTextField priceTextField = new JTextField();
        priceTextField.setBounds(FIELD_X_POSITION,DISTANCE+(3*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        priceTextField.setFont(new Font("Arial",Font.PLAIN,26));
        add(priceTextField);

        JLabel availability = new JLabel("Availability");
        availability.setBounds(LABEL_X_POSITION,DISTANCE+(4*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        availability.setFont(new Font("Arial",Font.BOLD,26));
        availability.setForeground(Color.WHITE);
        add(availability);

        JComboBox<String> availabilityCombo = new JComboBox<>(new String[] {"Available", "Maintenance"});
        availabilityCombo.setBounds(FIELD_X_POSITION,DISTANCE+(4*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        availabilityCombo.setFont(new Font("Arial",Font.PLAIN,26));
        add(availabilityCombo);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Bed.png"));
        Image scaledImage = imageIcon.getImage().getScaledInstance(310, 310, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        label.setBounds(450,80,310,310);
        add(label);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(LABEL_X_POSITION,DISTANCE+(5*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        addBtn.setFont(new Font("Arial",Font.BOLD,26));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Color.BLACK);
        add(addBtn);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    String roomNumber = roomNumberTextField.getText().trim();
                    String category = (String) categoryCombo.getSelectedItem();
                    String bedType = (String) bedTypeCombo.getSelectedItem();
                    String price = (String) priceTextField.getText().trim();
                    String availability = (String) availabilityCombo.getSelectedItem();

                    if(roomNumber.isEmpty() || price.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try{
                        Integer.parseInt(roomNumber);
                    } catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Room number must be a number!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if(roomExists(roomNumber)){
                        JOptionPane.showMessageDialog(null,"Room number already exists!","Duplicate Room",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try{
                        Double.parseDouble(price);
                    } catch(Exception e) {
                        JOptionPane.showMessageDialog(null,"Price must be a number!","Error",JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String roomData = roomNumber + "|" + category + "|" + bedType + "|" + price + "|" + availability;

                    FileWriter fw = new FileWriter("room.txt",true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(roomData);
                    bw.newLine();
                    bw.close();
                    JOptionPane.showMessageDialog(null,"Room added successfully!","Success",JOptionPane.INFORMATION_MESSAGE);

                    roomNumberTextField.setText("");
                    priceTextField.setText("");

                } catch(Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Something went wrong!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(FIELD_X_POSITION,DISTANCE+(5*DISTANCE_GAP),LABEL_WIDTH,LABEL_HEIGHT);
        backBtn.setFont(new Font("Arial",Font.BOLD,26));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent aE){
                try{
                    setVisible(false);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        getContentPane().setBackground(Color.BLACK);

        setUndecorated(true);
        setLayout(null);
        setLocation(Welcome.X_POSITION + 280,Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);

    }

    private boolean roomExists(String roomNumber) {

        try{

            BufferedReader br = new BufferedReader(new FileReader("room.txt"));
            String line;
            while((line = br.readLine()) != null){

                String[] data = line.split("\\|");
                if(data.length > 0 && data[0].equals(roomNumber)){
                    br.close();
                    return true;
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static void main(String[] args){
        new Add_Room();
    }

}
