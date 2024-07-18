import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductEntryForm {
    public void run(Connection conn) {
        // Create the frame
        JFrame frame = new JFrame("Product Entry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());

        // Create GridBagConstraints for layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Create and add the Product name label and text field
        JLabel nameLabel = new JLabel("Product name*");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(nameLabel, gbc);

        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(nameField, gbc);

        // Create and add the New stock quantity label and text field
        JLabel quantityLabel = new JLabel("New stock quantity*");
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(quantityLabel, gbc);

        JTextField quantityField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(quantityField, gbc);

        // Create and add the Price per unit label and text field
        JLabel priceLabel = new JLabel("Price per unit");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.add(priceLabel, gbc);

        JTextField priceField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(priceField, gbc);

        // Create and add the ADD button
        JButton addButton = new JButton("ADD");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(addButton, gbc);

        // Make the frame visible
        frame.setVisible(true);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                float price_per_unit = Float.parseFloat(priceField.getText());
                float stock = Float.parseFloat(quantityField.getText());
                try {
                    Statement s1 = conn.createStatement();
                    ResultSet r1 = s1.executeQuery("select count(*) from product_details;");
                    int pid;
                    if (r1.next()) {
                        pid = r1.getInt("count(*)") + 1;
                    } else {
                        pid = 1;
                    }
                    PreparedStatement p1 = conn.prepareStatement("Insert into product_details values(?,?,0,?,?)");
                    p1.setInt(1, pid);
                    p1.setString(2, name);
                    p1.setFloat(3, price_per_unit);
                    p1.setFloat(4, stock);
                    p1.executeUpdate();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
    }
}