import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDetailsForm {
    public void run(Connection conn) {
        // Create the frame
        JFrame frame = new JFrame("Customer Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());

        // Create GridBagConstraints for layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Create and add the Phone number label and text field
        JLabel phoneLabel = new JLabel("Phone number");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(phoneLabel, gbc);

        JTextField phoneNumberField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(phoneNumberField, gbc);

        // Create and add the Search button
        JButton searchButton = new JButton("Search");
        gbc.gridx = 2;
        gbc.gridy = 0;
        frame.add(searchButton, gbc);

        // Create and add the Name label and text field
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        nameField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(nameField, gbc);

        // Create and add the Customer ID label and text field
        JLabel customerIdLabel = new JLabel("Customer ID:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frame.add(customerIdLabel, gbc);

        JTextField customerIdField = new JTextField(20);
        customerIdField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(customerIdField, gbc);

        // Create and add the Customer Balance label and text field
        JLabel customerBalanceLabel = new JLabel("Customer Balance:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        frame.add(customerBalanceLabel, gbc);

        JTextField customerBalanceField = new JTextField(20);
        customerBalanceField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        frame.add(customerBalanceField, gbc);

        // Create and add the Total Purchases label and text field
        JLabel totalPurchasesLabel = new JLabel("Total Purchases:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        frame.add(totalPurchasesLabel, gbc);

        JTextField totalPurchasesField = new JTextField(20);
        totalPurchasesField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        frame.add(totalPurchasesField, gbc);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberField.getText();
                try {
                    Statement st1 = conn.createStatement();
                    ResultSet r1 = st1
                            .executeQuery("Select cid,name from customer_details where phone_no=" + phoneNumber);
                    if (r1.next()) {
                        int cid = r1.getInt("cid");
                        String cname = r1.getString("name");
                        r1 = st1.executeQuery("select price,payment_Status from bill_details where cid=" + cid);
                        float price, balance = 0, total = 0;
                        String payment_status;
                        while (r1.next()) {
                            price = r1.getFloat("price");
                            payment_status = r1.getString("payment_status");
                            total += price;
                            if (payment_status.equalsIgnoreCase("U")) {
                                balance += price;
                            }
                        }
                        nameField.setText(cname);
                        customerIdField.setText(String.valueOf(cid));
                        customerBalanceField.setText(String.valueOf(balance));
                        totalPurchasesField.setText(String.valueOf(total));
                    } else {
                        nameField.setText("Unknown");
                        customerIdField.setText("");
                        customerBalanceField.setText("");
                        totalPurchasesField.setText("");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        });

        // Make the frame visible
        frame.setVisible(true);
    }
}