package Hotel.Reservation.System.view.admin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import Hotel.Reservation.System.view.Welcome;

public class Report extends JFrame {

    JTextArea reportArea;

    Report() {

        JLabel title = new JLabel("HOTEL REPORT");

        title.setBounds(250, 20, 300, 40);

        title.setFont(new Font("Arial", Font.BOLD, 32));

        title.setForeground(Color.WHITE);

        add(title);

        // Report area
        reportArea = new JTextArea();

        reportArea.setBounds(40, 90, 665, 450);

        reportArea.setFont(
                new Font("Monospaced", Font.PLAIN, 18));

        reportArea.setEditable(false);

        reportArea.setBackground(Color.WHITE);

        reportArea.setForeground(Color.BLACK);

        reportArea.setLineWrap(false);

        add(reportArea);

        // Load report
        loadReport();

        // Refresh button
        JButton refresh = new JButton("Refresh");

        refresh.setBounds(200, 570, 150, 35);

        refresh.setFont(
                new Font("Arial", Font.BOLD, 18));

        refresh.setForeground(Color.WHITE);

        refresh.setBackground(Color.BLACK);

        refresh.setFocusPainted(false);

        add(refresh);

        refresh.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        loadReport();

                    }

                });

        // Back button
        JButton back = new JButton("Back");

        back.setBounds(390, 570, 150, 35);

        back.setFont(
                new Font("Arial", Font.BOLD, 18));

        back.setForeground(Color.WHITE);

        back.setBackground(Color.BLACK);

        back.setFocusPainted(false);

        add(back);

        back.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        setVisible(false);

                    }

                });

        // Frame settings
        getContentPane().setBackground(Color.BLACK);

        setUndecorated(true);

        setLayout(null);

        setLocation(
                Welcome.X_POSITION + 280,
                Welcome.Y_POSITION + 2);

        setSize(745, 650);

        setVisible(true);

    }

    public void loadReport() {

        int totalRooms = 0;

        int availableRooms = 0;

        int occupiedRooms = 0;

        int maintenanceRooms = 0;

        int totalReservations = 0;

        // Read room information
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

                    totalRooms++;

                    String availability = data[4].trim();

                    if (availability.equalsIgnoreCase(
                            "Available")) {

                        availableRooms++;

                    } else if (availability.equalsIgnoreCase(
                            "Occupied")) {

                        occupiedRooms++;

                    } else if (availability.equalsIgnoreCase(
                            "Maintenance")) {

                        maintenanceRooms++;

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

            return;

        } catch (IOException e) {

            e.printStackTrace();

        }

        // Read reservation information
        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.RESERVATION_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 4) {

                    totalReservations++;

                }

            }

            br.close();

        } catch (FileNotFoundException e) {

            totalReservations = 0;

        } catch (IOException e) {

            e.printStackTrace();

        }

        // Display report
        StringBuilder report = new StringBuilder();

        report.append(
                "========================================\n");

        report.append(
                "              HOTEL REPORT\n");

        report.append(
                "========================================\n\n");

        report.append(
                "Total Rooms          : ");

        report.append(totalRooms);

        report.append("\n\n");

        report.append(
                "Available Rooms      : ");

        report.append(availableRooms);

        report.append("\n");

        report.append(
                "Occupied Rooms       : ");

        report.append(occupiedRooms);

        report.append("\n");

        report.append(
                "Maintenance Rooms    : ");

        report.append(maintenanceRooms);

        report.append("\n\n");

        report.append(
                "----------------------------------------\n");

        report.append(
                "Total Reservations   : ");

        report.append(totalReservations);

        report.append("\n\n");

        report.append(
                "========================================\n");

        reportArea.setText(
                report.toString());

    }

    public static void main(String[] args) {

        new Report();

    }

}