import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class InvoiceSearchFrame {
    public void run(Connection conn) {
        // Set up the JFrame
        //String jdbcURL = "jdbc:mysql://localhost:3306/zoho"; // Replace with your database URL
        //String jdbcUsername = "root"; // Replace with your MySQL username
        //String jdbcPassword = "manicka"; // Replace with your MySQL password
    
        try{
           // Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Establish the connection
            //final Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        JFrame frame = new JFrame("Invoice Details");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create the top panel with a text field and a search button
        JPanel topPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);

        // Create the text area with a scroll pane
        JTextArea textArea = new JTextArea(100, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create the bottom panel with ALL, PAID, and UNPAID buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton allButton = new JButton("ALL");
        JButton paidButton = new JButton("PAID");
        JButton unpaidButton = new JButton("UNPAID");
        bottomPanel.add(allButton);
        bottomPanel.add(paidButton);
        bottomPanel.add(unpaidButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add action listener for the search button to highlight text
        
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e22) {
                try {
                    textArea.setText("");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from bill_details");
                    textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    textArea.append("|  Inv No   |    Item    | Quantity  |    CID    |   Price   | Payment Status|\n");
                    textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    while(rs.next()){
                        String inv = String.valueOf(rs.getInt("Invoice_number"));
                        String item = rs.getString("Item");
                        String quantity = rs.getString("Quantity");
                        String cid = String.valueOf(rs.getInt("cid"));
                        String price = String.valueOf(rs.getFloat("Price"));
                        String payStat = rs.getString("Payment_status");

                        String formattedString = String.format("| %-13s | %-10s | %-11s | %-12s | %-9s | %-17s |\n", inv, item, quantity, cid, price, payStat);
                        textArea.append(formattedString);
                        textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    }


                } 
                catch (Exception ef) {
                    ef.printStackTrace();
                }
            }
        });

        paidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e33) {
                try {
                    textArea.setText("");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from bill_details where payment_status='P'");
                    textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    textArea.append("|  Inv No   |    Item    | Quantity  |    CID    |   Price   | Payment Status|\n");
                    textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    while(rs.next()){
                        String inv = String.valueOf(rs.getInt("Invoice_number"));
                        String item = rs.getString("Item");
                        String quantity = rs.getString("Quantity");
                        String cid = String.valueOf(rs.getInt("cid"));
                        String price = String.valueOf(rs.getFloat("Price"));
                        String payStat = rs.getString("Payment_status");

                        String formattedString = String.format("| %-13s | %-10s | %-11s | %-12s | %-9s | %-17s |\n", inv, item, quantity, cid, price, payStat);
                        textArea.append(formattedString);
                        textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    }
                } catch (Exception ef) {
                    ef.printStackTrace();
                }
            }
        });

        unpaidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e44) {
                try {
                    textArea.setText("");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from bill_details where payment_status='U'");
                    textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    textArea.append("|  Inv No   |    Item    | Quantity  |    CID    |   Price   | Payment Status|\n");
                    textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    while(rs.next()){
                        String inv = String.valueOf(rs.getInt("Invoice_number"));
                        String item = rs.getString("Item");
                        String quantity = rs.getString("Quantity");
                        String cid = String.valueOf(rs.getInt("cid"));
                        String price = String.valueOf(rs.getFloat("Price"));
                        String payStat = rs.getString("Payment_status");

                        String formattedString = String.format("| %-13s | %-10s | %-11s | %-12s | %-9s | %-17s |\n", inv, item, quantity, cid, price, payStat);
                        textArea.append(formattedString);
                        textArea.append("+-----------+------------+-----------+-----------+-----------+---------------+\n");
                    }
                } catch (Exception ef) {
                    ef.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from bill_details where Invoice_number=" + searchField.getText());
                    rs.next();
                    
                    Statement stmt1 = conn.createStatement();
                    ResultSet rs4 = stmt1.executeQuery("select * from time where inv="+searchField.getText());
                    rs4.next();

                    StringBuilder txt = new StringBuilder();
                    int cid = rs.getInt("cid");
                    txt.append("cid: ").append(cid).append("\n");
                    txt.append("Total: ").append(rs.getFloat("Price")).append("\n");
                    txt.append("Time : ").append(rs4.getString("time")).append('\n');
                    txt.append("Payment Status: ").append(rs.getString("Payment_status")).append("\n");
                    String[] items = rs.getString("Item").split(",");
                    String[] quantities = rs.getString("Quantity").split(",");
                    ResultSet rs1 = stmt.executeQuery("select name, phone_no from customer_details where cid=" + cid);
                    rs1.next();
                    txt.append("Name: ").append(rs1.getString("Name")).append("\n");
                    txt.append("Phone Number: ").append(rs1.getString("phone_no")).append("\n\n");
                    txt.append(String.format("%-15s %-10s %-15s %-10s\n", "Item Name", "Quantity", "Price Per Unit", "Cost"));
                    txt.append("---------------------------------------------------------------\n");
        
                    ResultSet rs2;
                    for (int i = 0; i < items.length; i++) {
                        rs2 = stmt.executeQuery("Select product_name, price_per_unit from product_details where pid=" + items[i]);
                        rs2.next();
                        String productName = rs2.getString("product_name");
                        String quantity = quantities[i];
                        float ppu = rs2.getFloat("price_per_unit");
                        float cost = ppu * Float.parseFloat(quantity);
                        txt.append(String.format("%-15s %-10s %-15s %-10s\n", productName, quantity, ppu, cost));
                    }
        
                    textArea.setText(txt.toString());
        
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        // Display the frame
        frame.setVisible(true);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
}