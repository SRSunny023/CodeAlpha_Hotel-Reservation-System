package Hotel.Reservation.System.view.user;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import Hotel.Reservation.System.view.Welcome;

public class Search_Room extends JFrame {

    Search_Room() {

        JLabel title = new JLabel("Search Room");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(40, 85, 120, 30);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setForeground(Color.WHITE);
        add(categoryLabel);

        JComboBox<String> categoryBox = new JComboBox<>(new String[] { "All", "Standard", "Deluxe", "Suite" });
        categoryBox.setBounds(150, 85, 150, 30);
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(categoryBox);

        JLabel bedLabel = new JLabel("Bed Type:");
        bedLabel.setBounds(320, 85, 120, 30);
        bedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bedLabel.setForeground(Color.WHITE);
        add(bedLabel);

        JComboBox<String> bedBox = new JComboBox<>(new String[] { "All", "Single Bed", "Double Bed", "King", "Twin" });
        bedBox.setBounds(420, 85, 150, 30);
        bedBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(bedBox);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(40, 130, 120, 30);
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availabilityLabel.setForeground(Color.WHITE);
        add(availabilityLabel);

        JComboBox<String> availabilityBox = new JComboBox<>(new String[] { "All", "Available", "Maintenance" });
        availabilityBox.setBounds(150, 130, 150, 30);
        availabilityBox.setFont(new Font("Arial", Font.PLAIN, 16));
        add(availabilityBox);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(420, 125, 150, 40);
        searchBtn.setFont(new Font("Arial", Font.BOLD, 18));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBackground(Color.BLACK);
        add(searchBtn);

        String columns[] = { "Room Number", "Category", "Bed Type", "Price", "Availability" };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(80); // Room Number
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Category
        table.getColumnModel().getColumn(2).setPreferredWidth(110); // Bed Type
        table.getColumnModel().getColumn(3).setPreferredWidth(70); // Price
        table.getColumnModel().getColumn(4).setPreferredWidth(250); // Availability
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(40, 190, 665, 350);
        add(scrollPane);

        loadRooms(model, "All", "All", "All");

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String category = categoryBox.getSelectedItem().toString();
                String bedType = bedBox.getSelectedItem().toString();
                String availability = availabilityBox.getSelectedItem().toString();
                model.setRowCount(0);
                loadRooms(model, category, bedType, availability);
            }

        });

        JButton bookBtn = new JButton("Book Selected Room");
        bookBtn.setBounds(40, 570, 250, 40);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 20));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setBackground(Color.BLACK);
        add(bookBtn);
        bookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
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
        setLocation(Welcome.X_POSITION + 280, Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);

    }

    public void loadRooms(DefaultTableModel model, String selectedCategory, String selectedBedType,
            String selectedAvailability) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(Welcome.ROOM_FILE));

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length >= 5) {
                    String roomNumber = data[0];
                    String category = data[1];
                    String bedType = data[2];
                    String price = data[3];
                    String availability = data[4];
                    if (availability.equalsIgnoreCase("Occupied")) {

                        String availableDate = getAvailableDate(roomNumber);

                        if (!availableDate.isEmpty()) {

                            try {

                                /*
                                 * Your reservation date format is:
                                 * 2026-August-15
                                 */
                                java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                                        .ofPattern(
                                                "yyyy-MMMM-d",
                                                java.util.Locale.ENGLISH);

                                java.time.LocalDate checkoutDate = java.time.LocalDate.parse(
                                        availableDate,
                                        formatter);

                                java.time.LocalDate today = java.time.LocalDate.now();

                                /*
                                 * If checkout date has arrived,
                                 * make room available again.
                                 */
                                if (!today.isBefore(checkoutDate)) {

                                    availability = "Available";

                                    updateRoomStatus(
                                            roomNumber,
                                            "Available");

                                } else {

                                    availability = "Occupied|Available: "
                                            + formatAvailableDate(availableDate);
                                }

                            } catch (Exception ex) {

                                ex.printStackTrace();
                            }
                        }
                    }
                    boolean categoryMatch = selectedCategory.equals("All") || category.equals(selectedCategory);
                    boolean bedMatch = selectedBedType.equals("All") || bedType.equals(selectedBedType);
                    boolean availabilityMatch;

                    if (selectedAvailability.equals("All")) {

                        availabilityMatch = true;

                    } else if (selectedAvailability.equals("Available")) {

                        availabilityMatch = availability.equalsIgnoreCase("Available");

                    } else {

                        availabilityMatch = availability.equalsIgnoreCase(
                                selectedAvailability);
                    }

                    if (categoryMatch && bedMatch && availabilityMatch) {
                        model.addRow(new Object[] { roomNumber, category, bedType, price, availability });
                    }
                }
            }

            br.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, "room.txt not found!", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAvailableDate(String roomNumber) {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.RESERVATION_FILE));

            String line;

            String latestCheckOut = "";

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                // username | roomNumber | checkIn | checkOut
                String[] data = line.split("\\|");

                if (data.length >= 4 &&
                        data[1].equals(roomNumber)) {

                    latestCheckOut = data[3];
                }
            }

            br.close();

            return latestCheckOut;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }

    public void updateRoomStatus(
            String roomNumber,
            String newStatus) {

        try {

            File inputFile = new File(Welcome.ROOM_FILE);
            File tempFile = new File(Welcome.DATA_FOLDER + "room_temp.txt");

            BufferedReader br = new BufferedReader(
                    new FileReader(inputFile));

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(tempFile));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5 &&
                        data[0].equals(roomNumber)) {

                    data[4] = newStatus;

                    line = String.join("|", data);
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if (inputFile.delete()) {

                tempFile.renameTo(inputFile);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Could not update room status.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String formatAvailableDate(String date) {

        try {

            java.time.format.DateTimeFormatter inputFormatter = java.time.format.DateTimeFormatter.ofPattern(
                    "yyyy-MMMM-d",
                    java.util.Locale.ENGLISH);

            java.time.format.DateTimeFormatter outputFormatter = java.time.format.DateTimeFormatter.ofPattern(
                    "dd/MM/yy");

            java.time.LocalDate localDate = java.time.LocalDate.parse(
                    date,
                    inputFormatter);

            return localDate.format(outputFormatter);

        } catch (Exception e) {

            return date;
        }
    }

    public static void main(String[] args) {
        new Search_Room();
    }

}
