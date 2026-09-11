package Hotel.Reservation.System;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Manage_Users extends JFrame {

    public static final int LABEL_WIDTH = 200;
    public static final int LABEL_HEIGHT = 30;

    Manage_Users(){

        JLabel title = new JLabel("Manage Users");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);


        String[] columns = { "Username", "Email", "Role" };
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable userTable = new JTable(model);
        userTable.setFont(new Font("Arial", Font.PLAIN, 18));
        userTable.setRowHeight(30);
        userTable.getTableHeader().setFont( new Font("Arial", Font.BOLD, 18));

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(40, 90, 665, 400);
        add(scrollPane);


        loadUsers(model);

        JButton changeRoleBtn = new JButton("Change Role");
        changeRoleBtn.setBounds(40, 520, 200, 40);
        changeRoleBtn.setFont(new Font("Arial", Font.BOLD, 20));
        changeRoleBtn.setForeground(Color.WHITE);
        changeRoleBtn.setBackground(Color.BLACK);
        add(changeRoleBtn);
        changeRoleBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = userTable.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(null,"Please select a user!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String username = model.getValueAt(selectedRow, 0).toString();
                String currentRole = model.getValueAt(selectedRow, 2).toString();

                JComboBox<String> roleCombo = new JComboBox<>(new String[]{"User", "Admin"});
                roleCombo.setSelectedItem(currentRole);
                roleCombo.setFont(new Font("Arial", Font.PLAIN, 18));

                int result = JOptionPane.showConfirmDialog(null,roleCombo,"Change Role for " + username,JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);


                if(result == JOptionPane.OK_OPTION){

                    String newRole = (String) roleCombo.getSelectedItem();

                    if (newRole.equals(currentRole)) {
                        return;
                    }

                    if (updateUserRole(username, newRole)) {

                        model.setValueAt(newRole,selectedRow,2);
                        JOptionPane.showMessageDialog(null,"User role updated successfully!","Success",JOptionPane.INFORMATION_MESSAGE);

                    } else {

                        JOptionPane.showMessageDialog(null,"Could not update user role!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(505, 520, 200, 40);
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
        setLocation(Welcome.X_POSITION + 280,Welcome.Y_POSITION + 38);
        setSize(745, 731);
        setVisible(true);
    }

    private void loadUsers(DefaultTableModel model) {

        try {

            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 4) {

                    String username = data[0];
                    String email = data[1];
                    String role = data[3];

                    if (username.equals("admin")) {
                        continue;
                    }

                    model.addRow(new Object[]{username,email,role});
                }
            }

            br.close();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null,"users.txt not found!","Error",JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private boolean updateUserRole(String username,String newRole) {

        File inputFile = new File("users.txt");
        File tempFile = new File("user_temp.txt");

        boolean updated = false;

        try {

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 4 && data[0].equals(username)) {

                    line = data[0] + "|" + data[1] + "|" + data[2] + "|" + newRole;
                    updated = true;
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if (updated) {

                if (!inputFile.delete()) {
                    return false;
                }

                if (!tempFile.renameTo(inputFile)) {
                    return false;
                }

                return true;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return false;
    }


    public static void main(String[] args) {

        new Manage_Users();
    }
}