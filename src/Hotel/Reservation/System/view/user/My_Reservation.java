package Hotel.Reservation.System.view.user;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import Hotel.Reservation.System.view.Welcome;

public class My_Reservation extends JFrame {

    My_Reservation() {

        JLabel title = new JLabel("My Reservations");
        title.setBounds(250, 20, 300, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JTextArea reservationArea = new JTextArea();
        reservationArea.setBounds(40, 90, 665, 450);
        reservationArea.setFont(new Font("Arial", Font.PLAIN, 18));
        reservationArea.setEditable(false);
        reservationArea.setBackground(Color.WHITE);
        reservationArea.setForeground(Color.BLACK);
        add(reservationArea);

        String userName = loadUserName();

        loadReservations(userName, reservationArea);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(280, 570, 150, 35);
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
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

        setLocation(
                Welcome.X_POSITION + 280,
                Welcome.Y_POSITION + 2);

        setSize(745, 650);

        setVisible(true);
    }

    public String loadUserName() {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.CURRENT_SESSION_FILE));

            String line = br.readLine();

            br.close();

            if (line == null || line.trim().isEmpty()) {
                return "";
            }

            String[] parts = line.split("\\|");

            return parts[0];

        } catch (Exception e) {

            e.printStackTrace();

            return "";
        }
    }

    public void loadReservations(
            String userName,
            JTextArea reservationArea) {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.RESERVATION_FILE));

            String line;

            boolean found = false;

            reservationArea.setText("");

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 4 &&
                        data[0].equals(userName)) {

                    found = true;

                    reservationArea.append(
                            "Room Number : " + data[1] + "\n" +
                                    "Check-In    : " + data[2] + "\n" +
                                    "Check-Out   : " + data[3] + "\n" +
                                    "----------------------------------------\n\n");
                }
            }

            br.close();

            if (!found) {

                reservationArea.setText(
                        "You don't have any reservations.");
            }

        } catch (FileNotFoundException e) {

            reservationArea.setText(
                    "No reservation found.");

        } catch (IOException e) {

            e.printStackTrace();

            reservationArea.setText(
                    "Error reading reservations.");
        }
    }

    public static void main(String[] args) {

        new My_Reservation();
    }
}