//package Zoho_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class BillingSystem {
    public void run(Connection conn) {
        // Create the main JFrame
        try{
        JFrame frame = new JFrame("Billing System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);
        String sql = "SELECT count(*) from bill_details";
        
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        int invoice_num=resultSet.getInt("count(*)")+1;
        statement.executeUpdate("Delete from billing");
        System.out.println(invoice_num);
        // Create labels
        JLabel productIdLabel = new JLabel("Product ID");
        JLabel quantityLabel = new JLabel("Quantity");
        JLabel invoiceNumberLabel = new JLabel("Invoice number:- "+String.valueOf(invoice_num));

        // Create text fields
        JTextField productIdField = new JTextField();
        JTextField quantityField = new JTextField();

        // Create buttons
        JButton addButton = new JButton("ADD");
        JButton generateBillButton = new JButton("Generate bill");

        // Set bounds for components
        productIdLabel.setBounds(50, 50, 100, 30);
        productIdField.setBounds(150, 50, 150, 30);
        quantityLabel.setBounds(50, 100, 100, 30);
        quantityField.setBounds(150, 100, 150, 30);
        invoiceNumberLabel.setBounds(250, 10, 150, 30);
        addButton.setBounds(150, 150, 100, 30);
        generateBillButton.setBounds(150, 200, 150, 30);

        // Add components to frame
        frame.add(productIdLabel);
        frame.add(productIdField);
        frame.add(quantityLabel);
        frame.add(quantityField);
        frame.add(invoiceNumberLabel);
        frame.add(addButton);
        frame.add(generateBillButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
              int pid=Integer.parseInt(productIdField.getText());
              float qty=Float.parseFloat(quantityField.getText());
              try{
              PreparedStatement stmt = conn.prepareStatement("insert into billing values(?,?)");
              stmt.setInt(1,pid);
              stmt.setFloat(2,qty);
              stmt.executeUpdate();
              productIdField.setText("");
              quantityField.setText("");
              PreparedStatement stmt1=conn.prepareStatement("Update product_details set Quantity_sold=Quantity_sold+?,stock_available=stock_available-? where pid=?");
                    stmt1.setFloat(1,qty);
                    stmt1.setFloat(2,qty);
                    stmt1.setInt(3,pid);
                    stmt1.executeUpdate();
              }
              catch (Exception ef){
                ef.printStackTrace();
              }
            } 
        });
        // Add action listener to the generate bill button
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create the new JFrame
                JFrame billFrame = new JFrame("Billing Details");
                billFrame.setSize(400, 300);
                billFrame.setLayout(null);

                // Create labels and text fields
                JLabel nameLabel = new JLabel("Name");
                JTextField nameField = new JTextField();
                JLabel phoneNumberLabel = new JLabel("Phone Number");
                JTextField phoneNumberField = new JTextField();

                // Create radio buttons
                JRadioButton paidButton = new JRadioButton("Paid");
                JRadioButton unpaidButton = new JRadioButton("Unpaid");
                ButtonGroup paymentGroup = new ButtonGroup();
                JButton final_bill=new JButton("Final_bill");
                JLabel totalLabel=new JLabel("TOTAL");
                JTextField totalField=new JTextField();
                paymentGroup.add(paidButton);
                paymentGroup.add(unpaidButton);

                // Set bounds for components
                final_bill.setBounds(150,200,100,30);
                nameLabel.setBounds(50, 50, 100, 30);
                nameField.setBounds(150, 50, 150, 30);
                phoneNumberLabel.setBounds(50, 100, 100, 30);
                phoneNumberField.setBounds(150, 100, 150, 30);
                paidButton.setBounds(100, 150, 100, 30);
                unpaidButton.setBounds(200, 150, 100, 30);
                totalLabel.setBounds(25,200,40,30);
                totalField.setBounds(75,200,40,30);
                // Add components to the new frame
                billFrame.add(nameLabel);
                billFrame.add(nameField);
                billFrame.add(phoneNumberLabel);
                billFrame.add(phoneNumberField);
                billFrame.add(paidButton);
                billFrame.add(unpaidButton);
                billFrame.add(final_bill);
                billFrame.add(totalLabel);
                billFrame.add(totalField);
                // Make the new frame visible
                billFrame.setVisible(true);
                final_bill.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String paymentStatus;
                        if(paidButton.isSelected()){
                            paymentStatus="P";
                        }
                        else{
                            paymentStatus="U";
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDate = sdf.format(new Date());
                        try{
                        PreparedStatement stmt3 = conn.prepareStatement("insert into time values(?,?)");
                        stmt3.setInt(1,invoice_num);
                        stmt3.setString(2,formattedDate);
                        stmt3.executeUpdate();
                        }
                        catch (Exception ef){
                            ef.printStackTrace();
                        }


                        String ph_no=phoneNumberField.getText();
                        String name=nameField.getText();
                        try{
                            int cid;
                            PreparedStatement stmt = conn.prepareStatement("select cid from customer_details where phone_no=?");
                            stmt.setString(1,ph_no);
                            ResultSet resultSet = stmt.executeQuery();
                            if(resultSet.next()){
                                cid=resultSet.getInt("cid");
                            }
                            else{
                                resultSet=statement.executeQuery("select count(*) from customer_details");
                                resultSet.next();
                                cid=resultSet.getInt("count(*)")+1;
                                stmt=conn.prepareStatement("insert into customer_details values(?,?,?)");
                                stmt.setInt(1,cid);
                                stmt.setString(2,name);
                                stmt.setString(3,ph_no);
                                stmt.executeUpdate();
                            }
                            String item="",quantity="";
                            int pid_temp;
                            float quant_temp,total=0;
                            resultSet=statement.executeQuery("select *from billing");
                            ResultSet rs2=null;
                            Statement statement1=conn.createStatement();
                            while(resultSet.next()){
                                pid_temp=resultSet.getInt("pid");
                                item=item+String.valueOf(pid_temp)+",";
                                quant_temp=resultSet.getFloat("quantity_purchased");
                                quantity=quantity+String.valueOf(quant_temp)+",";
                                rs2=statement1.executeQuery("select price_per_unit from product_details where pid="+String.valueOf(pid_temp));
                                rs2.next();
                                total+=quant_temp*rs2.getFloat("price_per_unit");
                            }
                            totalField.setText(String.valueOf(total));
                            System.out.println(item);
                            System.out.println(quantity);
                            item=item.substring(0,item.length()-1);
                            quantity=quantity.substring(0,quantity.length()-1);
                            stmt=conn.prepareStatement("insert into bill_details values(?,?,?,?,?,?)");
                            stmt.setInt(1,invoice_num);
                            stmt.setString(2,item);
                            stmt.setString(3,quantity);
                            stmt.setInt(4,cid);
                            stmt.setFloat(5,total);
                            stmt.setString(6,paymentStatus);
                            stmt.executeUpdate();
                            //TimeUnit.SECONDS.sleep(5);
                            //billFrame.dispose();
                            frame.dispose();
                        }
                        catch (Exception ef){
                              ef.printStackTrace();
                        }
                    }
            });
        }
    });

        // Make the main frame visible
        frame.setVisible(true);
    }
    catch (Exception e){
        e.printStackTrace();
    }

    }
}