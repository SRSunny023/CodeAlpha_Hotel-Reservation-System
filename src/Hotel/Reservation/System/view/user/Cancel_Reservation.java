package Hotel.Reservation.System.view.user;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import Hotel.Reservation.System.view.Welcome;

public class Cancel_Reservation extends JFrame {

    Cancel_Reservation() {

        JLabel title = new JLabel("Cancel Reservation");

        title.setBounds(230, 20, 350, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        String columns[] = {
                "Room Number",
                "Check-In",
                "Check-Out"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        JTable table = new JTable(model);

        table.setFont(new Font("Arial", Font.PLAIN, 18));
        table.setRowHeight(35);
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(40, 100, 665, 350);
        add(scrollPane);

        String userName = loadUserName();

        loadReservations(userName, model);

        JButton cancelBtn = new JButton("Cancel Selected Reservation");

        cancelBtn.setBounds(40, 500, 300, 40);
        cancelBtn.setFont(
                new Font("Arial", Font.BOLD, 18));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setBackground(Color.BLACK);
        add(cancelBtn);

        cancelBtn.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        int selectedRow = table.getSelectedRow();

                        if (selectedRow == -1) {

                            JOptionPane.showMessageDialog(
                                    Cancel_Reservation.this,
                                    "Please select a reservation first.");

                            return;
                        }

                        String roomNumber = model.getValueAt(
                                selectedRow, 0).toString();

                        String checkIn = model.getValueAt(
                                selectedRow, 1).toString();

                        String checkOut = model.getValueAt(
                                selectedRow, 2).toString();

                        int confirm = JOptionPane.showConfirmDialog(
                                Cancel_Reservation.this,
                                "Are you sure you want to cancel\n"
                                        + "the reservation for Room "
                                        + roomNumber + "?",
                                "Confirm Cancellation",
                                JOptionPane.YES_NO_OPTION);

                        if (confirm != JOptionPane.YES_OPTION) {
                            return;
                        }

                        boolean cancelled = cancelReservation(
                                userName,
                                roomNumber,
                                checkIn,
                                checkOut);

                        if (cancelled) {

                            updateRoomStatus(
                                    roomNumber,
                                    "Available");

                            JOptionPane.showMessageDialog(
                                    Cancel_Reservation.this,
                                    "Reservation cancelled successfully.");

                            model.removeRow(selectedRow);

                        } else {

                            JOptionPane.showMessageDialog(
                                    Cancel_Reservation.this,
                                    "Could not cancel reservation.");
                        }
                    }
                });

        JButton backBtn = new JButton("Back");

        backBtn.setBounds(505, 500, 200, 40);
        backBtn.setFont(
                new Font("Arial", Font.BOLD, 18));
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        add(backBtn);

        backBtn.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        setVisible(false);
                    }
                });

        getContentPane().setBackground(Color.BLACK);

        setUndecorated(true);
        setLayout(null);

        setLocation(
                Welcome.X_POSITION + 280,
                Welcome.Y_POSITION + 2);

        setSize(745, 600);

        setVisible(true);
    }

    public String loadUserName() {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.CURRENT_SESSION_FILE));

            String line = br.readLine();

            br.close();

            if (line == null ||
                    line.trim().isEmpty()) {

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
            DefaultTableModel model) {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.RESERVATION_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                // username | roomNumber | checkIn | checkOut
                String[] data = line.split("\\|");

                if (data.length >= 4 &&
                        data[0].equals(userName)) {

                    model.addRow(
                            new Object[] {
                                    data[1],
                                    data[2],
                                    data[3]
                            });
                }
            }

            br.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "reservation.txt not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public boolean cancelReservation(
            String userName,
            String roomNumber,
            String checkIn,
            String checkOut) {

        File inputFile = new File(Welcome.RESERVATION_FILE);

        File tempFile = new File(Welcome.DATA_FOLDER + "reservation_temp.txt");

        boolean found = false;

        try {

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

                if (data.length >= 4 &&
                        data[0].equals(userName) &&
                        data[1].equals(roomNumber) &&
                        data[2].equals(checkIn) &&
                        data[3].equals(checkOut)) {

                    // Do NOT write this reservation
                    found = true;

                    continue;
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if (!found) {

                tempFile.delete();

                return false;
            }

            if (!inputFile.delete()) {

                tempFile.delete();

                return false;
            }

            if (!tempFile.renameTo(inputFile)) {

                return false;
            }

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
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

                    line = String.join(
                            "|",
                            data);
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

    public static void main(String[] args) {

        new Cancel_Reservation();
    }
}