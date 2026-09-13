package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class All_Reservation extends JFrame {

    All_Reservation() {

        JLabel title = new JLabel("All Reservations");

        title.setBounds(250, 20, 350, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);


        // =========================
        // TABLE
        // =========================

        String columns[] = {
                "User Name",
                "Room Number",
                "Check-In",
                "Check-Out"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        JTable table = new JTable(model);

        table.setFont(
                new Font("Arial", Font.PLAIN, 18)
        );

        table.setRowHeight(35);

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 18)
        );


        // Column widths

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(150);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(120);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(180);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(180);


        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBounds(
                40,
                100,
                665,
                350
        );

        add(scrollPane);


        // Load all reservations

        loadAllReservations(model);


        // =========================
        // CANCEL BUTTON
        // =========================

        JButton cancelBtn =
                new JButton("Cancel Selected");

        cancelBtn.setBounds(
                40,
                500,
                250,
                40
        );

        cancelBtn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setBackground(Color.BLACK);

        add(cancelBtn);


        cancelBtn.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        int selectedRow =
                                table.getSelectedRow();


                        if (selectedRow == -1) {

                            JOptionPane.showMessageDialog(
                                    All_Reservation.this,
                                    "Please select a reservation first."
                            );

                            return;
                        }


                        String userName =
                                model.getValueAt(
                                        selectedRow,
                                        0
                                ).toString();

                        String roomNumber =
                                model.getValueAt(
                                        selectedRow,
                                        1
                                ).toString();

                        String checkIn =
                                model.getValueAt(
                                        selectedRow,
                                        2
                                ).toString();

                        String checkOut =
                                model.getValueAt(
                                        selectedRow,
                                        3
                                ).toString();


                        int confirm =
                                JOptionPane.showConfirmDialog(
                                        All_Reservation.this,

                                        "Cancel reservation?\n\n"
                                        + "User: "
                                        + userName
                                        + "\nRoom: "
                                        + roomNumber,

                                        "Confirm Cancellation",

                                        JOptionPane.YES_NO_OPTION
                                );


                        if (confirm !=
                                JOptionPane.YES_OPTION) {

                            return;
                        }


                        boolean cancelled =
                                cancelReservation(
                                        userName,
                                        roomNumber,
                                        checkIn,
                                        checkOut
                                );


                        if (cancelled) {

                            updateRoomStatus(
                                    roomNumber,
                                    "Available"
                            );


                            JOptionPane.showMessageDialog(
                                    All_Reservation.this,
                                    "Reservation cancelled successfully."
                            );


                            model.removeRow(
                                    selectedRow
                            );

                        } else {

                            JOptionPane.showMessageDialog(
                                    All_Reservation.this,
                                    "Could not cancel reservation."
                            );
                        }
                    }
                }
        );


        // =========================
        // REFRESH BUTTON
        // =========================

        JButton refreshBtn =
                new JButton("Refresh");

        refreshBtn.setBounds(
                310,
                500,
                150,
                40
        );

        refreshBtn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setBackground(Color.BLACK);

        add(refreshBtn);


        refreshBtn.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        model.setRowCount(0);

                        loadAllReservations(model);
                    }
                }
        );


        // =========================
        // BACK BUTTON
        // =========================

        JButton backBtn =
                new JButton("Back");

        backBtn.setBounds(
                505,
                500,
                200,
                40
        );

        backBtn.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

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
                }
        );


        // =========================
        // FRAME
        // =========================

        getContentPane()
                .setBackground(Color.BLACK);

        setUndecorated(true);

        setLayout(null);

        setLocation(
                Welcome.X_POSITION + 280,
                Welcome.Y_POSITION + 2
        );

        setSize(745, 600);

        setVisible(true);
    }


    // =====================================================
    // LOAD ALL RESERVATIONS
    // =====================================================

    public void loadAllReservations(
            DefaultTableModel model) {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "reservation.txt"
                            )
                    );

            String line;


            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {

                    continue;
                }


                /*
                 * Format:
                 *
                 * username | roomNumber | checkIn | checkOut
                 */

                String[] data =
                        line.split("\\|");


                if (data.length >= 4) {

                    model.addRow(
                            new Object[]{
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3]
                            }
                    );
                }
            }


            br.close();


        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "reservation.txt not found!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // CANCEL RESERVATION
    // =====================================================

    public boolean cancelReservation(
            String userName,
            String roomNumber,
            String checkIn,
            String checkOut) {


        File inputFile =
                new File("reservation.txt");

        File tempFile =
                new File("reservation_temp.txt");


        boolean found = false;


        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    inputFile
                            )
                    );


            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    tempFile
                            )
                    );


            String line;


            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {

                    continue;
                }


                String[] data =
                        line.split("\\|");


                /*
                 * Find the exact reservation
                 */

                if (data.length >= 4 &&
                        data[0].equals(userName) &&
                        data[1].equals(roomNumber) &&
                        data[2].equals(checkIn) &&
                        data[3].equals(checkOut)) {


                    found = true;

                    // Do not write this reservation

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


    // =====================================================
    // UPDATE ROOM STATUS
    // =====================================================

    public void updateRoomStatus(
            String roomNumber,
            String newStatus) {


        try {

            File inputFile =
                    new File("room.txt");

            File tempFile =
                    new File("room_temp.txt");


            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    inputFile
                            )
                    );


            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(
                                    tempFile
                            )
                    );


            String line;


            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {

                    continue;
                }


                String[] data =
                        line.split("\\|");


                if (data.length >= 5 &&
                        data[0].equals(roomNumber)) {


                    data[4] = newStatus;


                    line = String.join(
                            "|",
                            data
                    );
                }


                bw.write(line);

                bw.newLine();
            }


            br.close();

            bw.close();


            if (inputFile.delete()) {

                tempFile.renameTo(
                        inputFile
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Could not update room status."
                );
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        new All_Reservation();
    }
}