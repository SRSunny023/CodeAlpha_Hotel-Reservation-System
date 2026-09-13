package Hotel.Reservation.System.view.admin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Hotel.Reservation.System.view.Welcome;

public class Payment_Record extends JFrame {

    Payment_Record() {

        JLabel title = new JLabel("Payment Records");

        title.setBounds(250, 20, 300, 40);

        title.setFont(new Font("Arial", Font.BOLD, 32));

        title.setForeground(Color.WHITE);

        add(title);

        // Table columns
        String[] columns = {
                "User Name",
                "Room Number",
                "Amount",
                "Payment Method",
                "Date"
        };

        // Table model
        DefaultTableModel model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;

            }

        };

        // JTable
        JTable paymentTable = new JTable(model);

        paymentTable.setFont(
                new Font("Arial", Font.PLAIN, 16));

        paymentTable.setRowHeight(30);

        paymentTable.setBackground(Color.WHITE);

        paymentTable.setForeground(Color.BLACK);

        // Table header
        paymentTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 15));

        paymentTable.getTableHeader().setBackground(
                Color.BLACK);

        paymentTable.getTableHeader().setForeground(
                Color.WHITE);

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(paymentTable);

        scrollPane.setBounds(
                30,
                90,
                685,
                450);

        add(scrollPane);

        // Load payment records
        loadPaymentRecords(model);

        // Back button
        JButton backBtn = new JButton("Back");

        backBtn.setBounds(
                280,
                570,
                150,
                35);

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

    public void loadPaymentRecords(
            DefaultTableModel model) {

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(Welcome.PAYMENT_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5) {

                    model.addRow(
                            new Object[] {
                                    data[0],
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4]
                            });

                }

            }

            br.close();

            if (model.getRowCount() == 0) {

                JOptionPane.showMessageDialog(
                        Payment_Record.this,
                        "No payment records found.");

            }

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(
                    Payment_Record.this,
                    "No payment records found.");

        } catch (IOException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    Payment_Record.this,
                    "Error reading payment records.");

        }

    }

    public static void main(String[] args) {

        new Payment_Record();

    }

}