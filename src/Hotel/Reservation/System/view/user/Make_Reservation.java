package Hotel.Reservation.System.view.user;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import Hotel.Reservation.System.view.Welcome;

public class Make_Reservation extends JFrame {

    Make_Reservation() {

        JLabel title = new JLabel("Room Booking");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(40, 100, 150, 30);
        roomNumberLabel.setFont(new Font("Arial", Font.BOLD, 18));
        roomNumberLabel.setForeground(Color.WHITE);
        add(roomNumberLabel);

        JTextField roomNumberField = new JTextField();
        roomNumberField.setBounds(200, 100, 180, 30);
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(roomNumberField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(40, 155, 150, 30);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setForeground(Color.WHITE);
        add(categoryLabel);
        categoryLabel.setVisible(false);

        JTextField categoryBox = new JTextField();
        categoryBox.setBounds(200, 155, 180, 30);
        categoryBox.setFont(new Font("Arial", Font.PLAIN, 16));
        categoryBox.setEditable(false);
        add(categoryBox);
        categoryBox.setVisible(false);

        JLabel bedLabel = new JLabel("Bed Type:");
        bedLabel.setBounds(40, 210, 150, 30);
        bedLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bedLabel.setForeground(Color.WHITE);
        add(bedLabel);
        bedLabel.setVisible(false);

        JTextField bedBox = new JTextField();
        bedBox.setBounds(200, 210, 180, 30);
        bedBox.setFont(new Font("Arial", Font.PLAIN, 16));
        bedBox.setEditable(false);
        add(bedBox);
        bedBox.setVisible(false);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(40, 265, 150, 30);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(Color.WHITE);
        add(priceLabel);
        priceLabel.setVisible(false);

        JTextField priceField = new JTextField();
        priceField.setBounds(200, 265, 180, 30);
        priceField.setFont(new Font("Arial", Font.PLAIN, 18));
        priceField.setEditable(false);
        add(priceField);
        priceField.setVisible(false);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(40, 320, 150, 30);
        availabilityLabel.setFont(new Font("Arial", Font.BOLD, 18));
        availabilityLabel.setForeground(Color.WHITE);
        add(availabilityLabel);
        availabilityLabel.setVisible(false);

        JTextField availabilityBox = new JTextField();
        availabilityBox.setBounds(200, 320, 350, 30);
        availabilityBox.setFont(new Font("Arial", Font.PLAIN, 16));
        availabilityBox.setEditable(false);
        add(availabilityBox);
        availabilityBox.setVisible(false);

        JLabel checkInLabel = new JLabel("Check-In Date (yyyy/mm/dd):");
        checkInLabel.setBounds(40, 375, 270, 30);
        checkInLabel.setFont(new Font("Arial", Font.BOLD, 18));
        checkInLabel.setForeground(Color.WHITE);
        add(checkInLabel);
        checkInLabel.setVisible(false);

        JLabel checkOutLabel = new JLabel("Check-Out Date (yyyy/mm/dd):");
        checkOutLabel.setBounds(40, 430, 285, 30);
        checkOutLabel.setFont(new Font("Arial", Font.BOLD, 18));
        checkOutLabel.setForeground(Color.WHITE);
        add(checkOutLabel);
        checkOutLabel.setVisible(false);

        String[] years = new String[10];
        int startYear = java.time.Year.now().getValue();
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(startYear + i);
        }

        JComboBox<String> checkInYearCombo = new JComboBox<>(years);
        checkInYearCombo.setBounds(330, 375, 100, 30);
        checkInYearCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkInYearCombo);
        checkInYearCombo.setVisible(false);

        JComboBox<String> checkOutYearCombo = new JComboBox<>(years);
        checkOutYearCombo.setBounds(330, 430, 100, 30);
        checkOutYearCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkOutYearCombo);
        checkOutYearCombo.setVisible(false);

        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };

        JComboBox<String> checkInMonthCombo = new JComboBox<>(months);
        checkInMonthCombo.setBounds(435, 375, 160, 30);
        checkInMonthCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkInMonthCombo);
        checkInMonthCombo.setVisible(false);

        JComboBox<String> checkOutMonthCombo = new JComboBox<>(months);
        checkOutMonthCombo.setBounds(435, 430, 160, 30);
        checkOutMonthCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkOutMonthCombo);
        checkOutMonthCombo.setVisible(false);

        JComboBox<String> checkInDayCombo = new JComboBox<>();
        checkInDayCombo.setBounds(600, 375, 50, 30);
        checkInDayCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkInDayCombo);
        checkInDayCombo.setVisible(false);

        JComboBox<String> checkOutDayCombo = new JComboBox<>();
        checkOutDayCombo.setBounds(600, 430, 50, 30);
        checkOutDayCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkOutDayCombo);
        checkOutDayCombo.setVisible(false);

        JLabel totalPrice = new JLabel("Total Price:");
        totalPrice.setBounds(40, 485, 150, 30);
        totalPrice.setFont(new Font("Arial", Font.BOLD, 18));
        totalPrice.setForeground(Color.WHITE);
        add(totalPrice);
        totalPrice.setVisible(false);

        JTextField totalPriceField = new JTextField();
        totalPriceField.setBounds(195, 485, 180, 30);
        totalPriceField.setFont(new Font("Arial", Font.PLAIN, 18));
        totalPriceField.setEditable(false);
        add(totalPriceField);
        totalPriceField.setVisible(false);

        JButton payBtn = new JButton("Pay");
        payBtn.setBounds(40, 540, 150, 30);
        payBtn.setFont(new Font("Arial", Font.BOLD, 18));
        payBtn.setForeground(Color.WHITE);
        payBtn.setBackground(Color.BLACK);
        add(payBtn);
        payBtn.setVisible(false);
        payBtn.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                String roomNumber = roomNumberField.getText().trim();

                if (roomNumber.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "Please enter a room number first.");

                    return;
                }

                // Check the actual room status from room.txt
                String roomStatus = getRoomAvailability(roomNumber);

                if (roomStatus.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "Room not found!");

                    return;
                }

                // Maintenance rooms cannot be booked
                if (roomStatus.equalsIgnoreCase("Maintenance")) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "This room is under maintenance.\n" +
                                    "You cannot reserve this room.",
                            "Room Unavailable",
                            JOptionPane.WARNING_MESSAGE);

                    return;
                }

                // Occupied rooms cannot be booked
                if (roomStatus.equalsIgnoreCase("Occupied")) {

                    String availableDate = getAvailableDate(roomNumber);

                    if (!availableDate.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                Make_Reservation.this,
                                "This room is currently occupied.\n" +
                                        "Available on: " + availableDate,
                                "Room Unavailable",
                                JOptionPane.WARNING_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(
                                Make_Reservation.this,
                                "This room is currently occupied.",
                                "Room Unavailable",
                                JOptionPane.WARNING_MESSAGE);

                    }

                    return;
                }

                // Room must be Available
                if (!roomStatus.equalsIgnoreCase("Available")) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "This room is not available for booking.",
                            "Room Unavailable",
                            JOptionPane.WARNING_MESSAGE);

                    return;
                }

                // Check total price
                String totalText = totalPriceField.getText();

                if (totalText.isEmpty() ||
                        totalText.equals("Invalid Dates")) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "Please select valid dates first.");

                    return;
                }

                double totalPrice = Double.parseDouble(totalText);

                String checkInDate = checkInYearCombo.getSelectedItem()
                        + "-"
                        + checkInMonthCombo.getSelectedItem()
                        + "-"
                        + checkInDayCombo.getSelectedItem();

                String checkOutDate = checkOutYearCombo.getSelectedItem()
                        + "-"
                        + checkOutMonthCombo.getSelectedItem()
                        + "-"
                        + checkOutDayCombo.getSelectedItem();

                setVisible(false);

                new Payment(
                        totalPrice,
                        roomNumber,
                        checkInDate,
                        checkOutDate);

            }

        });

        ActionListener dateUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isCheckIn = (e.getSource() == checkInMonthCombo || e.getSource() == checkInYearCombo);

                JComboBox<String> yearCombo = isCheckIn ? checkInYearCombo : checkOutYearCombo;
                JComboBox<String> monthCombo = isCheckIn ? checkInMonthCombo : checkOutMonthCombo;
                JComboBox<String> dayCombo = isCheckIn ? checkInDayCombo : checkOutDayCombo;

                String selectedMonth = (String) monthCombo.getSelectedItem();
                String selectedYear = (String) yearCombo.getSelectedItem();

                if (selectedMonth == null || selectedYear == null)
                    return;

                int year = Integer.parseInt(selectedYear);
                int daysInMonth = 31;

                switch (selectedMonth) {
                    case "April":
                    case "June":
                    case "September":
                    case "November":
                        daysInMonth = 30;
                        break;
                    case "February":
                        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                            daysInMonth = 29;
                        } else {
                            daysInMonth = 28;
                        }
                        break;
                    default:
                        daysInMonth = 31;
                        break;
                }

                Object previouslySelected = dayCombo.getSelectedItem();
                dayCombo.removeAllItems();
                for (int i = 1; i <= daysInMonth; i++) {
                    dayCombo.addItem(String.valueOf(i));
                }
                if (previouslySelected != null) {
                    dayCombo.setSelectedItem(previouslySelected);
                }
            }
        };

        checkInMonthCombo.addActionListener(dateUpdater);
        checkInYearCombo.addActionListener(dateUpdater);
        checkOutMonthCombo.addActionListener(dateUpdater);
        checkOutYearCombo.addActionListener(dateUpdater);

        checkInYearCombo.setSelectedIndex(0);
        checkOutYearCombo.setSelectedIndex(0);
        int currentMonthIndex = java.time.LocalDate.now().getMonthValue() - 1;
        checkInMonthCombo.setSelectedIndex(currentMonthIndex);
        checkOutMonthCombo.setSelectedIndex(currentMonthIndex);
        String currentDay = String.valueOf(java.time.LocalDate.now().getDayOfMonth());
        checkInDayCombo.setSelectedItem(currentDay);
        checkOutDayCombo.setSelectedItem(currentDay);
        ActionListener totalRunner = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotalPrice(checkInYearCombo, checkInMonthCombo, checkInDayCombo,
                        checkOutYearCombo, checkOutMonthCombo, checkOutDayCombo,
                        priceField, totalPriceField);
            }
        };
        checkInDayCombo.addActionListener(totalRunner);
        checkOutDayCombo.addActionListener(totalRunner);

        calculateTotalPrice(checkInYearCombo, checkInMonthCombo, checkInDayCombo, checkOutYearCombo, checkOutMonthCombo,
                checkOutDayCombo, priceField, totalPriceField);

        JButton enterBtn = new JButton("Enter");
        enterBtn.setBounds(390, 100, 150, 30);
        enterBtn.setFont(new Font("Arial", Font.BOLD, 20));
        enterBtn.setForeground(Color.WHITE);
        enterBtn.setBackground(Color.BLACK);
        add(enterBtn);
        enterBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String roomNumber = roomNumberField.getText().trim();

                if (roomNumber.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "Please enter a room number.");

                    return;

                }

                // Load room information
                boolean found = loadRoom(
                        roomNumber,
                        categoryBox,
                        bedBox,
                        priceField,
                        availabilityBox);

                // Room does not exist
                if (!found) {

                    JOptionPane.showMessageDialog(
                            Make_Reservation.this,
                            "Room " + roomNumber + " not found!",
                            "Room Not Found",
                            JOptionPane.WARNING_MESSAGE);

                    categoryLabel.setVisible(false);

                    bedLabel.setVisible(false);

                    priceLabel.setVisible(false);

                    availabilityLabel.setVisible(false);

                    categoryBox.setVisible(false);

                    bedBox.setVisible(false);

                    priceField.setVisible(false);

                    availabilityBox.setVisible(false);

                    // Show date fields
                    checkInLabel.setVisible(false);

                    checkInYearCombo.setVisible(false);

                    checkInMonthCombo.setVisible(false);

                    checkInDayCombo.setVisible(false);

                    checkOutLabel.setVisible(false);

                    checkOutYearCombo.setVisible(false);

                    checkOutMonthCombo.setVisible(false);

                    checkOutDayCombo.setVisible(false);

                    totalPrice.setVisible(false);

                    totalPriceField.setVisible(false);

                    payBtn.setVisible(false);

                    return;

                }

                // Show room information
                categoryLabel.setVisible(true);

                bedLabel.setVisible(true);

                priceLabel.setVisible(true);

                availabilityLabel.setVisible(true);

                categoryBox.setVisible(true);

                bedBox.setVisible(true);

                priceField.setVisible(true);

                availabilityBox.setVisible(true);

                // Show date fields
                checkInLabel.setVisible(true);

                checkInYearCombo.setVisible(true);

                checkInMonthCombo.setVisible(true);

                checkInDayCombo.setVisible(true);

                checkOutLabel.setVisible(true);

                checkOutYearCombo.setVisible(true);

                checkOutMonthCombo.setVisible(true);

                checkOutDayCombo.setVisible(true);

                totalPrice.setVisible(true);

                totalPriceField.setVisible(true);

                payBtn.setVisible(true);

            }

        });

        getContentPane().setBackground(Color.BLACK);

        setUndecorated(true);
        setLayout(null);
        setLocation(Welcome.X_POSITION + 280, Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);
    }

    public boolean loadRoom(
            String selectedRoomNumber,
            JTextField categoryBox,
            JTextField bedBox,
            JTextField priceField,
            JTextField availabilityBox) {

        boolean found = false;

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.ROOM_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5) {

                    String roomNumber = data[0].trim();

                    String category = data[1].trim();

                    String bedType = data[2].trim();

                    String price = data[3].trim();

                    String availability = data[4].trim();

                    if (roomNumber.equalsIgnoreCase(
                            selectedRoomNumber)) {

                        found = true;

                        categoryBox.setText(category);

                        bedBox.setText(bedType);

                        priceField.setText(price);

                        // Show actual availability
                        if (availability.equalsIgnoreCase(
                                "Occupied")) {

                            String availableDate = getAvailableDate(roomNumber);

                            if (!availableDate.isEmpty()) {

                                availabilityBox.setText(
                                        "Occupied - Available on: "
                                                + availableDate);

                            } else {

                                availabilityBox.setText(
                                        "Occupied");

                            }

                        } else if (availability.equalsIgnoreCase(
                                "Maintenance")) {

                            availabilityBox.setText(
                                    "Maintenance");

                        } else if (availability.equalsIgnoreCase(
                                "Available")) {

                            availabilityBox.setText(
                                    "Available");

                        } else {

                            availabilityBox.setText(
                                    availability);

                        }

                        break;

                    }

                }

            }

            br.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "room.txt not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return found;

    }

    private void calculateTotalPrice(JComboBox<String> inYear, JComboBox<String> inMonth, JComboBox<String> inDay,
            JComboBox<String> outYear, JComboBox<String> outMonth, JComboBox<String> outDay, JTextField priceField,
            JTextField totalPriceField) {

        try {

            String priceText = priceField.getText().trim();
            if (priceText.isEmpty()) {
                totalPriceField.setText("");
                return;
            }
            double pricePerNight = Double.parseDouble(priceText);

            java.util.List<String> monthsList = java.util.Arrays.asList(
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December");

            int inM = monthsList.indexOf(inMonth.getSelectedItem()) + 1;
            int outM = monthsList.indexOf(outMonth.getSelectedItem()) + 1;

            int inY = Integer.parseInt((String) inYear.getSelectedItem());
            int inD = Integer.parseInt((String) inDay.getSelectedItem());
            int outY = Integer.parseInt((String) outYear.getSelectedItem());
            int outD = Integer.parseInt((String) outDay.getSelectedItem());

            java.time.LocalDate checkIn = java.time.LocalDate.of(inY, inM, inD);
            java.time.LocalDate checkOut = java.time.LocalDate.of(outY, outM, outD);

            long days = java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut);

            if (days > 0) {
                double total = days * pricePerNight;
                totalPriceField.setText(String.format("%.2f", total));
            } else {
                totalPriceField.setText("Invalid Dates");
            }

        } catch (Exception ex) {
            totalPriceField.setText("");
        }
    }

    public String getAvailableDate(String roomNumber) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(Welcome.RESERVATION_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                // username | roomNumber | checkIn | checkOut
                if (data.length >= 4 &&
                        data[1].equals(roomNumber)) {

                    br.close();

                    return data[3];
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getRoomAvailability(String roomNumber) {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.ROOM_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5) {

                    String savedRoomNumber = data[0].trim();

                    String availability = data[4].trim();

                    if (savedRoomNumber.equalsIgnoreCase(
                            roomNumber)) {

                        br.close();

                        return availability;

                    }

                }

            }

            br.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "room.txt not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return "";

    }

    public static void main(String[] args) {
        new Make_Reservation();
    }

}
