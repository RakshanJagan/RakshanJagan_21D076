import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class ABCStoreGUI {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/zoho"; // Replace with your database URL
        String jdbcUsername = "root"; // Replace with your MySQL username
        String jdbcPassword = "manicka"; // Replace with your MySQL password

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish the connection
        final Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        JFrame mainFrame = new JFrame("ABC STORE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("ABC STORE", JLabel.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainFrame.add(titleLabel, gbc);

        JLabel dateLabel = new JLabel("", JLabel.RIGHT);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainFrame.add(dateLabel, gbc);

        JButton billingButton = new JButton("Billing");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        mainFrame.add(billingButton, gbc);

        JButton customerDetailsButton = new JButton("Customer Details");
        gbc.gridx = 2;
        gbc.gridy = 3;
        mainFrame.add(customerDetailsButton, gbc);

        JButton invoiceDetailsButton = new JButton("Invoice Details");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainFrame.add(invoiceDetailsButton, gbc);

        JButton productEntryButton = new JButton("Product entry");
        gbc.gridx = 3;
        gbc.gridy = 2;
        mainFrame.add(productEntryButton, gbc);

        

        JButton itemSalesButton = new JButton("Item sales");
        gbc.gridx = 3;
        gbc.gridy = 4;
        mainFrame.add(itemSalesButton, gbc);

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                JFrame newFrame = new JFrame(source.getText());
                newFrame.setSize(200, 150);
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newFrame.setVisible(true);
            }
        };

        billingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillingSystem billingSystem=new BillingSystem();
                billingSystem.run(conn);
            }});
        customerDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerDetailsForm csm=new CustomerDetailsForm();
                csm.run(conn);
            }});
        invoiceDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InvoiceSearchFrame inv=new InvoiceSearchFrame();
                inv.run(conn);
            }});
        productEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductEntryForm pef=new ProductEntryForm();
                pef.run(conn);
            }});
        
        itemSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemSalesFrame isf=new ItemSalesFrame(conn);
                isf.run();
            }});

        // Timer to update the date and time label every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateLabel.setText(sdf.format(new Date()));
            }
        });
        timer.start();

        mainFrame.setVisible(true);
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
}