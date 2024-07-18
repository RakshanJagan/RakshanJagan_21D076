import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.*;

public class ItemSalesFrame {

    public ResultSet rs;
    public String[] arr;
    public Connection conn;
    ItemSalesFrame(Connection conn){
        try{
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery("select * from product_details");
            this.rs=rs;
            String arr[]=new String[6];   
            this.arr=arr;
            this.conn=conn;
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    ItemSalesFrame(){

    }
    public void run() {
        // Create the frame
        JFrame frame = new JFrame("Item Sales");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridBagLayout());

        // Create GridBagConstraints for layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title label
        JLabel titleLabel = new JLabel("ITEM SALES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);
        
        
        /* Connection conn;
        ItemSalesFrame isf =new ItemSalesFrame(conn); */
        ResultSet rs1=this.rs;
        // Create and add item sales detail sections
        try{

            JPanel  itemPanel[] = new JPanel[3];
            //JPanel itemPanel2 = new JPanel(new GridLayout(2, 5, 10, 10));
            //JPanel itemPanel3 = new JPanel(new GridLayout(2, 5, 10, 10));


            
            for (int i = 1; i <= 3; i++) {
                
                if(rs1.next()){
                    itemPanel[i-1]=new JPanel(new GridLayout(2, 5, 10, 10));
                    this.arr[0]=(rs.getString("product_name"));
                    this.arr[1]=String.valueOf(rs.getInt("pid"));
                    this.arr[2]=String.valueOf(rs.getFloat("quantity_sold"));
                    this.arr[3]=String.valueOf(rs.getFloat("stock_available"));
                    this.arr[4]=String.valueOf(rs.getFloat("price_per_unit"));
                    this.arr[5]=String.valueOf(rs.getFloat("quantity_sold") * rs.getFloat("price_per_unit"));
                

                
                itemPanel[i-1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                itemPanel[i-1].add(new JLabel("*Item name"));
                itemPanel[i-1].add(new JLabel("*PID"));
                itemPanel[i-1].add(new JLabel("*Quantity sold"));
                itemPanel[i-1].add(new JLabel("*Available stock"));
                itemPanel[i-1].add(new JLabel("*Price per unit"));
                itemPanel[i-1].add(new JLabel("*Total sales"));
                
                for (int j = 0; j < 6; j++) {
                    itemPanel[i-1].add(new JTextField(arr[j]));
                }

                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.gridwidth = 4;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                frame.add(itemPanel[i-1], gbc);
            }
            }

            // Create and add the next button
            JButton nextButton = new JButton("NEXT");
            gbc.gridx = 3;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.EAST;
            frame.add(nextButton, gbc);
            nextButton.addActionListener(new ActionListener() {
                

                public void actionPerformed(ActionEvent e) {
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.insets = new Insets(5, 5, 5, 5);

                    // Title label
                    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.gridwidth = 4;
                    gbc.anchor = GridBagConstraints.CENTER;
                    frame.add(titleLabel, gbc);
                    // Show a message dialog when the button is clicked
                    //JPanel  itemPanel[] = new JPanel[3];
                    for (int i = 1; i <= 3; i++){
                        frame.getContentPane().remove(itemPanel[i-1]);

            // Revalidate and repaint the frame
                       //frame.revalidate();
                       frame.repaint();
                    }

                    try {
                       
                        
                        for (int i = 1; i <= 3; i++) {
                            
                            if(rs1.next()){
                                itemPanel[i-1]=new JPanel(new GridLayout(2, 5, 10, 10));
                                arr[0]=(rs.getString("product_name"));
                                arr[1]=String.valueOf(rs.getInt("pid"));
                                arr[2]=String.valueOf(rs.getFloat("quantity_sold"));
                                arr[3]=String.valueOf(rs.getFloat("stock_available"));
                                arr[4]=String.valueOf(rs.getFloat("price_per_unit"));
                                arr[5]=String.valueOf(rs.getFloat("quantity_sold") * rs.getFloat("price_per_unit"));
                            
                                System.out.println(Arrays.toString(arr));
                            
                            itemPanel[i-1].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
                            itemPanel[i-1].add(new JLabel("*Item name"));
                            itemPanel[i-1].add(new JLabel("*PID"));
                            itemPanel[i-1].add(new JLabel("*Quantity sold"));
                            itemPanel[i-1].add(new JLabel("*Available stock"));
                            itemPanel[i-1].add(new JLabel("*Price per unit"));
                            itemPanel[i-1].add(new JLabel("*Total sales"));
                            
                            for (int j = 0; j < 6; j++) {
                                itemPanel[i-1].add(new JTextField(arr[j]));
                            }
            
                            gbc.gridx = 0;
                            gbc.gridy = i;
                            gbc.gridwidth = 4;
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            frame.add(itemPanel[i-1], gbc);
                        }
                        }
                    }
                    
                    catch (Exception e2) {
                            e2.printStackTrace();
                    }
                }
            });
            // Make the frame visible
            frame.setVisible(true);
        }
    catch(Exception e){
        e.printStackTrace();
    }
    }
}