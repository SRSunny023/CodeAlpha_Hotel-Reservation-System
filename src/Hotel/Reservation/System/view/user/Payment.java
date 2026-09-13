package Hotel.Reservation.System.view.user;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import Hotel.Reservation.System.view.Welcome;

public class Payment extends JFrame {
    JComboBox<String> paymentMethodField;
    JButton addPaymentBtn, deleteCardBtn, depositBtn;
    double reservationAmount;
    String reservationRoomNumber, reservationCheckIn, reservationCheckOut;

    Payment(double amount, String roomNumber, String checkInDate, String checkOutDate) {

        reservationAmount = amount;
        reservationRoomNumber = roomNumber;
        reservationCheckIn = checkInDate;
        reservationCheckOut = checkOutDate;

        JLabel title = new JLabel("Payment");
        title.setBounds(280, 20, 250, 40);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        add(title);

        JLabel paymentMethod = new JLabel("Payment Method:");
        paymentMethod.setBounds(40, 85, 250, 30);
        paymentMethod.setFont(new Font("Arial", Font.BOLD, 18));
        paymentMethod.setForeground(Color.WHITE);
        add(paymentMethod);

        String[] cards = new String[5];
        String userName = loadUserName();
        loadCards(cards, userName);

        paymentMethodField = new JComboBox<>(cards);
        paymentMethodField.setBounds(300, 85, 250, 30);
        paymentMethodField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(paymentMethodField);

        JLabel paymentPin = new JLabel("Enter Pin:");
        paymentPin.setBounds(40, 140, 250, 30);
        paymentPin.setFont(new Font("Arial", Font.BOLD, 18));
        paymentPin.setForeground(Color.WHITE);
        add(paymentPin);

        JPasswordField paymentPinField = new JPasswordField();
        paymentPinField.setBounds(300, 140, 250, 30);
        paymentPinField.setFont(new Font("Arial", Font.BOLD, 18));
        paymentPinField.setForeground(Color.BLACK);
        paymentPinField.setBackground(Color.WHITE);
        add(paymentPinField);

        JLabel cardName = new JLabel("Enter Card Name (Ex: Card-1):");
        cardName.setBounds(40, 300, 300, 30);
        cardName.setFont(new Font("Arial", Font.BOLD, 18));
        cardName.setForeground(Color.WHITE);
        add(cardName);
        cardName.setVisible(false);

        JTextField cardNameField = new JTextField();
        cardNameField.setBounds(345, 300, 250, 30);
        cardNameField.setFont(new Font("Arial", Font.BOLD, 18));
        cardNameField.setForeground(Color.BLACK);
        cardNameField.setBackground(Color.WHITE);
        add(cardNameField);
        cardNameField.setVisible(false);

        JLabel cardNumber = new JLabel("Enter Card Number:");
        cardNumber.setBounds(40, 355, 300, 30);
        cardNumber.setFont(new Font("Arial", Font.BOLD, 18));
        cardNumber.setForeground(Color.WHITE);
        add(cardNumber);
        cardNumber.setVisible(false);

        JTextField cardNumberField = new JTextField();
        cardNumberField.setBounds(345, 355, 250, 30);
        cardNumberField.setFont(new Font("Arial", Font.BOLD, 18));
        cardNumberField.setForeground(Color.BLACK);
        cardNumberField.setBackground(Color.WHITE);
        add(cardNumberField);
        cardNumberField.setVisible(false);

        JLabel cardPin = new JLabel("Enter Card Pin:");
        cardPin.setBounds(40, 405, 300, 30);
        cardPin.setFont(new Font("Arial", Font.BOLD, 18));
        cardPin.setForeground(Color.WHITE);
        add(cardPin);
        cardPin.setVisible(false);

        JPasswordField cardPinField = new JPasswordField();
        cardPinField.setBounds(345, 405, 250, 30);
        cardPinField.setFont(new Font("Arial", Font.BOLD, 18));
        cardPinField.setForeground(Color.BLACK);
        cardPinField.setBackground(Color.WHITE);
        add(cardPinField);
        cardPinField.setVisible(false);

        JButton payBtn = new JButton("Pay");
        payBtn.setBounds(295, 195, 100, 30);
        payBtn.setFont(new Font("Arial", Font.BOLD, 18));
        payBtn.setForeground(Color.WHITE);
        payBtn.setBackground(Color.BLACK);
        add(payBtn);
        if (reservationAmount == -1)
            payBtn.setVisible(false);
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCard = (String) paymentMethodField.getSelectedItem();
                if (selectedCard == null || selectedCard.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please select a card first.");
                    return;
                }
                String pin = new String(paymentPinField.getPassword());
                if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please enter your PIN.");
                    return;
                }
                payForReservation(userName, selectedCard, pin, reservationAmount);
            }
        });

        JButton addPaymentSubmitBtn = new JButton("Add");
        addPaymentSubmitBtn.setBounds(40, 460, 150, 30);
        addPaymentSubmitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        addPaymentSubmitBtn.setForeground(Color.WHITE);
        addPaymentSubmitBtn.setBackground(Color.BLACK);
        add(addPaymentSubmitBtn);
        addPaymentSubmitBtn.setVisible(false);
        addPaymentSubmitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String cardNames = cardNameField.getText();
                    String cardNumbers = cardNumberField.getText();
                    String cardPins = new String(cardPinField.getPassword());

                    String lines = userName + "|" + cardNames + "|" + cardNumbers + "|" + cardPins + "|" + "0";

                    FileWriter fw = new FileWriter(Welcome.CARD_FILE, true);
                    fw.write(lines);
                    fw.write(System.lineSeparator());
                    fw.close();
                    setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        addPaymentBtn = new JButton("Add Payment Method");
        addPaymentBtn.setBounds(40, 195, 250, 30);
        addPaymentBtn.setFont(new Font("Arial", Font.BOLD, 18));
        addPaymentBtn.setForeground(Color.WHITE);
        addPaymentBtn.setBackground(Color.BLACK);
        add(addPaymentBtn);
        addPaymentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int cardCount = countCards(userName);

                    if (cardCount >= 5) {

                        JOptionPane.showMessageDialog(
                                Payment.this,
                                "You cannot add more than 5 cards.");

                        return;
                    }
                    cardName.setVisible(true);
                    cardNameField.setVisible(true);
                    cardNumber.setVisible(true);
                    cardNumberField.setVisible(true);
                    cardPin.setVisible(true);
                    cardPinField.setVisible(true);
                    paymentMethod.setVisible(false);
                    paymentMethodField.setVisible(false);
                    paymentPin.setVisible(false);
                    paymentPinField.setVisible(false);
                    addPaymentSubmitBtn.setVisible(true);
                    addPaymentBtn.setVisible(false);
                    deleteCardBtn.setVisible(false);
                    depositBtn.setVisible(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(400, 195, 100, 30);
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

        deleteCardBtn = new JButton("Delete Card");
        deleteCardBtn.setBounds(555, 85, 150, 30);
        deleteCardBtn.setFont(new Font("Arial", Font.BOLD, 18));
        deleteCardBtn.setForeground(Color.WHITE);
        deleteCardBtn.setBackground(Color.BLACK);
        add(deleteCardBtn);
        deleteCardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedCard = (String) paymentMethodField.getSelectedItem();
                if (selectedCard == null || selectedCard.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please select a card first.");
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(Payment.this,
                        "Are you sure you want to delete " + selectedCard + "?", "Delete Card",
                        JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                deleteCard(userName, selectedCard);
            }
        });

        depositBtn = new JButton("Deposit");
        depositBtn.setBounds(505, 195, 120, 30);
        depositBtn.setFont(new Font("Arial", Font.BOLD, 18));
        depositBtn.setForeground(Color.WHITE);
        depositBtn.setBackground(Color.BLACK);
        add(depositBtn);
        depositBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedCard = (String) paymentMethodField.getSelectedItem();
                if (selectedCard == null || selectedCard.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please select a card first.");
                    return;
                }

                String pin = new String(paymentPinField.getPassword());
                if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please enter your PIN.");
                    return;
                }

                String amountText = JOptionPane.showInputDialog(Payment.this, "Enter deposit amount:");
                if (amountText == null) {
                    return;
                }

                try {

                    double amount = Double.parseDouble(amountText);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(Payment.this, "Amount must be greater than 0.");
                        return;
                    }
                    depositMoney(userName, selectedCard, pin, amount);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Payment.this, "Please enter a valid amount.");
                }
            }
        });

        JButton showBalanceBtn = new JButton("Show Balance");
        showBalanceBtn.setBounds(40, 235, 200, 30);
        showBalanceBtn.setFont(new Font("Arial", Font.BOLD, 18));
        showBalanceBtn.setForeground(Color.WHITE);
        showBalanceBtn.setBackground(Color.BLACK);
        add(showBalanceBtn);
        showBalanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCard = (String) paymentMethodField.getSelectedItem();
                if (selectedCard == null || selectedCard.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please select a card first.");
                    return;
                }
                String pin = new String(paymentPinField.getPassword());
                if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(Payment.this, "Please enter your PIN.");
                    return;
                }
                showBalance(userName, selectedCard, pin);
            }
        });

        getContentPane().setBackground(Color.BLACK);

        setUndecorated(true);
        setLayout(null);
        setLocation(Welcome.X_POSITION + 280, Welcome.Y_POSITION + 2);
        setSize(745, 767);
        setVisible(true);
    }

    public String loadUserName() {
        try {

            BufferedReader br = new BufferedReader(new FileReader(Welcome.CURRENT_SESSION_FILE));
            String line = br.readLine();
            String[] parts = line.split("\\|");
            br.close();
            return parts[0];

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void loadCards(String[] cards, String userName) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(Welcome.CARD_FILE));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (data.length > 0 && data[0].equals(userName)) {
                    String cardName = data[1];
                    cards[i++] = cardName;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countCards(String userName) {

        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(Welcome.CARD_FILE));

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split("\\|");

                if (data.length >= 5 && data[0].equals(userName)) {
                    count++;
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public void deleteCard(String userName, String selectedCard) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(Welcome.CARD_FILE));
            StringBuilder content = new StringBuilder();
            String line;
            boolean deleted = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length >= 5 && data[0].equals(userName) && data[1].equals(selectedCard)) {
                    deleted = true;
                    continue;
                }
                content.append(line);
                content.append(System.lineSeparator());
            }
            br.close();
            if (deleted) {
                FileWriter fw = new FileWriter("cardInfo.txt");
                fw.write(content.toString());
                fw.close();
                JOptionPane.showMessageDialog(Payment.this, "Card deleted successfully!");
                paymentMethodField.removeItem(selectedCard);

            } else {
                JOptionPane.showMessageDialog(Payment.this, "Card not found!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Payment.this, "Error deleting card!");
        }
    }

    public void depositMoney(String userName, String selectedCard, String enteredPin, double amount) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(Welcome.CARD_FILE));
            StringBuilder content = new StringBuilder();
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length >= 5 && data[0].equals(userName) && data[1].equals(selectedCard)) {
                    String savedPin = data[3];
                    if (!savedPin.equals(enteredPin)) {
                        br.close();
                        JOptionPane.showMessageDialog(Payment.this, "Incorrect PIN!");
                        return;
                    }
                    double oldBalance = Double.parseDouble(data[4]);
                    double newBalance = oldBalance + amount;
                    line = data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3] + "|" + newBalance;
                    found = true;
                }
                content.append(line);
                content.append(System.lineSeparator());
            }

            br.close();

            if (found) {
                FileWriter fw = new FileWriter(Welcome.CARD_FILE);
                fw.write(content.toString());
                fw.close();
                JOptionPane.showMessageDialog(Payment.this, "Deposit successful!\n" + "Amount: " + amount);

            } else {

                JOptionPane.showMessageDialog(Payment.this, "Card not found!");
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(Payment.this, "Error processing deposit.");
        }
    }

    public void showBalance(String userName, String selectedCard, String enteredPin) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(Welcome.CARD_FILE));
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length >= 5 && data[0].equals(userName) && data[1].equals(selectedCard)) {
                    found = true;
                    String savedPin = data[3];
                    if (!savedPin.equals(enteredPin)) {
                        br.close();
                        JOptionPane.showMessageDialog(Payment.this, "Incorrect PIN!");
                        return;
                    }

                    double balance = Double.parseDouble(data[4]);
                    br.close();
                    JOptionPane.showMessageDialog(Payment.this, "Card: " + selectedCard + "\nBalance: " + balance);
                    return;
                }
            }

            br.close();

            if (!found) {

                JOptionPane.showMessageDialog(Payment.this, "Card not found!");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(Payment.this, "Error showing balance.");
        }
    }

    public void payForReservation(String userName, String selectedCard, String enteredPin, double amount) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(Welcome.CARD_FILE));
            StringBuilder content = new StringBuilder();
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length >= 5 && data[0].equals(userName) && data[1].equals(selectedCard)) {
                    found = true;
                    String savedPin = data[3];
                    if (!savedPin.equals(enteredPin)) {
                        br.close();
                        JOptionPane.showMessageDialog(Payment.this, "Incorrect PIN!");
                        return;
                    }

                    double balance = Double.parseDouble(data[4]);
                    if (balance < amount) {
                        br.close();
                        JOptionPane.showMessageDialog(Payment.this,
                                "Insufficient balance!\n" + "Required: " + amount + "\nAvailable: " + balance);

                        return;
                    }

                    double newBalance = balance - amount;

                    line = data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3] + "|" + newBalance;
                }
                content.append(line);
                content.append(System.lineSeparator());
            }

            br.close();
            if (!found) {
                JOptionPane.showMessageDialog(Payment.this, "Card not found!");
                return;
            }

            FileWriter fw = new FileWriter(Welcome.CARD_FILE);
            fw.write(content.toString());
            fw.close();

            saveReservation(userName, reservationRoomNumber, reservationCheckIn, reservationCheckOut);

            savePaymentRecord(
                    userName,
                    reservationRoomNumber,
                    amount,
                    selectedCard);

            updateRoomAvailability(reservationRoomNumber);

            JOptionPane.showMessageDialog(Payment.this, "Payment successful!\n" + "Amount: " + amount + "\nRoom "
                    + reservationRoomNumber + " is now occupied.");

            setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Payment.this, "Error processing payment.");
        }
    }

    public void updateRoomAvailability(String roomNumber) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(Welcome.ROOM_FILE));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] data = line.split("\\|");
                if (data.length >= 5 && data[0].equals(roomNumber)) {
                    data[4] = "Occupied";
                    line = data[0] + "|" + data[1] + "|" + data[2] + "|" + data[3] + "|" + data[4];
                }
                content.append(line);
                content.append(System.lineSeparator());
            }
            br.close();
            FileWriter fw = new FileWriter(Welcome.ROOM_FILE);
            fw.write(content.toString());
            fw.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Payment.this, "Error updating room availability.");
        }
    }

    public void saveReservation(String userName, String roomNumber, String checkInDate, String checkOutDate) {

        try {

            FileWriter fw = new FileWriter(Welcome.RESERVATION_FILE, true);

            fw.write(
                    userName + "|" +
                            roomNumber + "|" +
                            checkInDate + "|" +
                            checkOutDate);

            fw.write(System.lineSeparator());

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    Payment.this,
                    "Error saving reservation.");
        }
    }

    public void savePaymentRecord(
            String userName,
            String roomNumber,
            double amount,
            String paymentMethod) {

        try {

            FileWriter fw = new FileWriter(Welcome.PAYMENT_FILE, true);

            String date = java.time.LocalDate.now().format(
                    java.time.format.DateTimeFormatter.ofPattern(
                            "dd/MM/yy"));

            fw.write(
                    userName + "|" +
                            roomNumber + "|" +
                            amount + "|" +
                            paymentMethod + "|" +
                            date);

            fw.write(System.lineSeparator());

            fw.close();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    Payment.this,
                    "Error saving payment record.");
        }
    }

    public static void main(String[] args) {
        new Payment(-1, "-1", "-1", "-1");
    }

}
