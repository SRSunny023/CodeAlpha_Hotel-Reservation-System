package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class Manage_Room extends JFrame {

    Manage_Room(){

        JLabel title = new JLabel("Manage Room");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(40, 85, 150, 30);
        roomNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        roomNumberLabel.setForeground(Color.WHITE);
        add(roomNumberLabel);

        JTextField roomNumberField = new JTextField("All");
        roomNumberField.setBounds(180, 85, 120, 30);
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(roomNumberField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(320, 85, 120, 30);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setForeground(Color.WHITE);
        add(categoryLabel);

        JComboBox<String> categoryBox = new JComboBox<>(new String[]{"All", "Standard", "Deluxe", "Suite"});
        categoryBox.setBounds(430, 85, 150, 30);
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(categoryBox);

        JLabel bedLabel = new JLabel("Bed Type:");
        bedLabel.setBounds(40, 130, 120, 30);
        bedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bedLabel.setForeground(Color.WHITE);
        add(bedLabel);

        JComboBox<String> bedBox = new JComboBox<>(new String[]{"All", "Single Bed", "Double Bed", "King", "Twin"});
        bedBox.setBounds(180, 130, 120, 30);
        bedBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(bedBox);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(320, 130, 120, 30);
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availabilityLabel.setForeground(Color.WHITE);
        add(availabilityLabel);

        JComboBox<String> availabilityBox = new JComboBox<>(new String[]{"All", "Available", "Maintenance"});
        availabilityBox.setBounds(430, 130, 150, 30);
        availabilityBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(availabilityBox);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(600, 100, 100, 40);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 18));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBackground(Color.BLACK);
        add(searchBtn);

        String columns[] = {"Room Number", "Category", "Bed Type", "Price", "Availability"};
        DefaultTableModel model = new DefaultTableModel(columns,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 190, 665, 350);
        add(scrollPane);

        loadRooms(model,"All","All","All", "All");

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String roomNumber = roomNumberField.getText().trim();
                String category = categoryBox.getSelectedItem().toString();
                String bedType = bedBox.getSelectedItem().toString();
                String availability = availabilityBox.getSelectedItem().toString();
                if(roomNumber.isEmpty()) {
                    roomNumber = "All";
                }
                model.setRowCount(0);
                loadRooms(model,roomNumber,category,bedType,availability);
            }

        });

        JButton editRoomBtn = new JButton("Edit Selected Room");
        editRoomBtn.setBounds(40, 570, 250, 40);
        editRoomBtn.setFont(new Font("Arial", Font.BOLD, 20));
        editRoomBtn.setForeground(Color.WHITE);
        editRoomBtn.setBackground(Color.BLACK);
        add(editRoomBtn);
        editRoomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(Manage_Room.this, "Please select a room first!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String roomNumber = model.getValueAt(selectedRow,0).toString();
                String availability = model.getValueAt(selectedRow, 4).toString();
                if (availability.equalsIgnoreCase("Occupied")) {
                    JOptionPane.showMessageDialog(Manage_Room.this,"Occupied rooms cannot be edited!","Warning",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                setVisible(false);
                new Edit_Room(roomNumber);
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(505, 570, 200, 40);
        backBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        add(backBtn);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        getContentPane().setBackground(Color.BLACK);
        setUndecorated(true);
        setLayout(null);
        setLocation(Welcome.X_POSITION + 280,Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);

    }

    public void loadRooms(DefaultTableModel model, String selectedRoomNumber, String selectedCategory, String selectedBedType, String selectedAvailability){
        try{

            BufferedReader br = new BufferedReader(new FileReader("room.txt"));

            String line;
            while((line=br.readLine())!=null){
                if(line.trim().isEmpty()){
                    continue;
                }
                String[] data = line.split("\\|");
                if(data.length>=5){
                    String roomNumber = data[0];
                    String category = data[1];
                    String bedType = data[2];
                    String price = data[3];
                    String availability = data[4];
                    boolean roomNumberMatch = selectedRoomNumber.equalsIgnoreCase("All") || roomNumber.equalsIgnoreCase(selectedRoomNumber);
                    boolean categoryMatch = selectedCategory.equalsIgnoreCase("All") || category.equalsIgnoreCase(selectedCategory);
                    boolean bedMatch = selectedBedType.equalsIgnoreCase("All")|| bedType.equalsIgnoreCase(selectedBedType);
                    boolean availabilityMatch = selectedAvailability.equalsIgnoreCase("All")|| availability.equalsIgnoreCase(selectedAvailability);

                    if(roomNumberMatch && categoryMatch && bedMatch && availabilityMatch){
                        model.addRow(new Object[]{roomNumber,category,bedType,price,availability});
                    }
                }
            }

            br.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null,"room.txt not found!","Error",JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Manage_Room();
    }

}
