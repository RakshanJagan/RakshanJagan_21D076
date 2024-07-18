# Billing System for Stores

## Introduction
This project is a comprehensive billing system for stores, developed as part of an incubation project initiated by Zoho Corporation. It is designed to streamline the billing process, ensuring efficiency and accuracy in transactions.

## Features
- User-friendly interface for store employees
- Efficient billing and invoicing system
- Product inventory management
- Sales reporting 

## Technologies Used
- Programming Language: Java
- Development Environment: Visual Studio Code
- Database: MySQL

## Acknowledgements
- Zoho Corporation for the oppurtunity.
- Thiagarajar College of Engineering for their support.

## Contact
For any queries, please contact:
- Name: Rakshan Jagan
- Email: msrakshanjagan007@gmail.com
_________________________________________________________________________________________________________________________

class ABCStoreGUI

## Modules and Attributes

### 1. Main Frame
The main frame serves as the entry point of the application, providing a graphical user interface for navigation to different functionalities of the billing system.

**Attributes:**
- `JFrame mainFrame`: The main window frame of the application.
- `GridBagConstraints gbc`: Used for layout management.
- `JLabel titleLabel`: Displays the title of the application.
- `JLabel dateLabel`: Displays the current date and time.
- `JButton billingButton`: Button to navigate to the billing system.
- `JButton customerDetailsButton`: Button to navigate to customer details.
- `JButton invoiceDetailsButton`: Button to navigate to invoice details.
- `JButton productEntryButton`: Button to navigate to product entry.
- `JButton itemSalesButton`: Button to navigate to item sales.
- `Timer timer`: Updates the date and time label every second.

### 2. Billing Button
Triggers the billing system, which handles the billing process for customers.

**Attributes:**
- `BillingSystem billingSystem`: Instance of the billing system.

### 3. Customer Details Button
Navigates to the customer details module, which manages customer information.

**Attributes:**
- `CustomerDetailsForm csm`: Instance of the customer details form.

### 4. Invoice Details Button
Navigates to the invoice details module, providing a view of past invoices.

**Attributes:**
- `InvoiceSearchFrame inv`: Instance of the invoice search frame.

### 5. Product Entry Button
Navigates to the product entry module, allowing for the addition and management of products.

**Attributes:**
- `ProductEntryForm pef`: Instance of the product entry form.

### 6. Item Sales Button
Navigates to the item sales module, tracking the sales of individual items.

**Attributes:**
- `ItemSalesFrame isf`: Instance of the item sales frame.

### 7. Database Connection
Establishes a connection to the MySQL database.

**Attributes:**
- `String jdbcURL`: URL of the MySQL database.
- `String jdbcUsername`: Username for the MySQL database.
- `String jdbcPassword`: Password for the MySQL database.
- `Connection conn`: Database connection object.

## Program Flow
1. The main frame is initialized with a title, date label, and buttons for navigation.
2. Buttons are added to the main frame and associated with action listeners to handle navigation.
3. Each button click opens a new frame or module to perform specific tasks (billing, customer details, etc.).
4. The date label is updated every second using a timer.
5. Database connection is established at the start of the application.
___________________________________________________________________________________________________________________________________


class BillingSystem

## Modules and Attributes

### 1. Main Billing Frame
The main billing frame handles the process of adding products to a bill and generating the final bill.

**Attributes:**
- `JFrame frame`: The main window frame for the billing system.
- `JLabel productIdLabel`: Label for the product ID input.
- `JLabel quantityLabel`: Label for the quantity input.
- `JLabel invoiceNumberLabel`: Label displaying the invoice number.
- `JTextField productIdField`: Text field for entering the product ID.
- `JTextField quantityField`: Text field for entering the quantity.
- `JButton addButton`: Button to add a product to the bill.
- `JButton generateBillButton`: Button to generate the final bill.
- `Statement statement`: Statement for executing SQL queries.
- `ResultSet resultSet`: Result set for retrieving data from SQL queries.
- `int invoice_num`: The invoice number for the current bill.

### 2. Add Button
Handles the addition of a product to the bill by inserting the product ID and quantity into the `billing` table and updating the product quantity in `product_details`.

**Attributes:**
- `int pid`: The product ID entered by the user.
- `float qty`: The quantity of the product entered by the user.
- `PreparedStatement stmt`: Prepared statement for inserting data into the `billing` table.
- `PreparedStatement stmt1`: Prepared statement for updating the `product_details` table.

### 3. Generate Bill Button
Generates the final bill by opening a new frame to enter customer details and payment status, then inserts the bill details into the `bill_details` table.

**Attributes:**
- `JFrame billFrame`: The window frame for entering billing details.
- `JLabel nameLabel`: Label for the customer name input.
- `JTextField nameField`: Text field for entering the customer name.
- `JLabel phoneNumberLabel`: Label for the phone number input.
- `JTextField phoneNumberField`: Text field for entering the phone number.
- `JRadioButton paidButton`: Radio button for selecting "Paid" status.
- `JRadioButton unpaidButton`: Radio button for selecting "Unpaid" status.
- `ButtonGroup paymentGroup`: Button group for the payment status radio buttons.
- `JButton final_bill`: Button to finalize the bill.
- `JLabel totalLabel`: Label for the total amount.
- `JTextField totalField`: Text field for displaying the total amount.
- `String paymentStatus`: The selected payment status.
- `String formattedDate`: The current date and time formatted as a string.
- `PreparedStatement stmt3`: Prepared statement for inserting data into the `time` table.
- `String ph_no`: The phone number entered by the user.
- `String name`: The name entered by the user.
- `int cid`: Customer ID.
- `Statement statement1`: Statement for executing SQL queries.
- `ResultSet rs2`: Result set for retrieving data from SQL queries.
- `float total`: The total amount of the bill.
- `String item`: A comma-separated string of product IDs.
- `String quantity`: A comma-separated string of quantities.

## Program Flow
1. The main billing frame is initialized with labels, text fields, and buttons for entering product details and generating the bill.
2. The `addButton` adds the product ID and quantity to the `billing` table and updates the `product_details` table.
3. The `generateBillButton` opens a new frame for entering customer details and payment status.
4. The `final_bill` button finalizes the bill by inserting the bill details into the `bill_details` table, and the customer details into the `customer_details` table if necessary.
5. The `totalField` displays the total amount of the bill.
___________________________________________________________________________________________________________________________________

class CustomerDetailsForm

### Attributes of the Code

1. **GUI Components**:
   - `JFrame`: The main window containing all components.
   - `JLabel`: Labels for phone number, name, customer ID, balance, and total purchases.
   - `JTextField`: Text fields for input and display of customer details.
   - `JButton`: Button to trigger search action.

2. **Layout**:
   - `GridBagLayout`: Used for organizing components in a grid with specific constraints.
   - `GridBagConstraints`: Controls the positioning and spacing of components.

3. **Database Interaction**:
   - `Connection`: Used to connect to the database.
   - `Statement` and `ResultSet`: Execute SQL queries and process the results.

4. **Event Handling**:
   - `ActionListener`: Responds to button click events to trigger database queries.

### Code Flow Explanation

The code initializes a GUI for displaying customer details based on a phone number search. Here's a brief summary of the flow:

1. **Initialize GUI**: A JFrame is created with GridBagLayout to organize components such as labels, text fields, and a search button.
2. **User Input**: The user enters a phone number and clicks the "Search" button.
3. **Database Query**: On button click, an SQL query is executed to fetch the customer ID and name from the `customer_details` table based on the phone number.
4. **Fetch Details**: If a customer is found, another query fetches billing details (price and payment status) from the `bill_details` table.
5. **Display Results**: The customer's name, ID, balance, and total purchases are calculated and displayed in the respective text fields. If the customer is not found, "Unknown" is displayed in the name field, and other fields are cleared.
6. **Error Handling**: Any exceptions during database operations are caught and printed to the console.

___________________________________________________________________________________________________________________________________

class InvoiceSearchFrame

### Attributes of the Code

1. **GUI Components**:
   - `JFrame`: The main window containing all components.
   - `JTextField`, `JButton`, `JTextArea`, `JScrollPane`, `JPanel`: Components for user input, displaying results, and navigating between different invoice statuses.

2. **Layout**:
   - `BorderLayout`, `FlowLayout`: Used for organizing components in specific areas of the frame.

3. **Database Interaction**:
   - `Connection`, `Statement`, `ResultSet`: Establishes a connection to a database and executes SQL queries to fetch and display invoice details.

4. **Event Handling**:
   - `ActionListener`: Responds to button clicks to fetch and display all invoices, paid invoices, unpaid invoices, and specific invoice details based on user input.

### Code Flow Explanation

The `InvoiceSearchFrame` class sets up a graphical user interface for searching and displaying invoice details. Here's an overview of its functionality:

1. **Initialize GUI**: Creates a JFrame with BorderLayout to organize components into top, center (scrollable text area), and bottom panels (buttons).
2. **Search Button**: Executes a SQL query based on the invoice number entered in the search field. Displays detailed invoice information including customer details, itemized purchases, and payment status.
3. **Filter Buttons (ALL, PAID, UNPAID)**: Executes SQL queries to fetch and display all invoices, paid invoices, and unpaid invoices respectively, formatted neatly in the text area.
4. **Exception Handling**: Catches and prints any exceptions that occur during database operations to aid in debugging.

___________________________________________________________________________________________________________________________________

class ItemSalesFrame

### Attributes of the Code

1. **GUI Components**:
   - `JFrame`: The main window containing all components.
   - `JPanel`: Panels used to organize sections within the frame.
   - `JLabel`, `JTextField`: Components for displaying labels and text fields.
   - `JButton`: Button components to interact with the application.

2. **Layout**:
   - `GridBagLayout`, `GridLayout`: Used for organizing components in a grid-based layout with flexible constraints.

3. **Database Interaction**:
   - `Connection`, `Statement`, `ResultSet`: Establishes a connection to a database and retrieves data (product details) for display.

4. **Event Handling**:
   - `ActionListener`: Responds to button clicks to navigate through and update displayed data.

### Code Flow Explanation

The `ItemSalesFrame` class sets up a graphical user interface for displaying item sales details fetched from a database. Here's an overview of its functionality:

1. **Initialize Constructor**: Retrieves product details from the database (`product_details` table) using a `Statement` and `ResultSet`.

2. **Setup and Display GUI**:
   - Creates a `JFrame` titled "Item Sales" with a `GridBagLayout`.
   - Sets up `JLabel` for the title and multiple `JPanel` instances (`itemPanel`) for displaying item sales details.

3. **Populate Data**:
   - Fetches data from the `ResultSet` (`rs`) into an array (`arr`) to populate `JTextField` components within each `itemPanel`.

4. **Navigation Button (NEXT)**:
   - Adds a "NEXT" button (`nextButton`) to the frame.
   - Implements an `ActionListener` to handle button clicks.
   - Removes existing `itemPanel` components from the frame when the button is clicked.
   - Revalidates and repaints the frame to update the display.
   - Retrieves the next set of product details from the `ResultSet`, updates the `itemPanel` with new data, and re-adds it to the frame.

5. **Exception Handling**: Catches and prints any exceptions that occur during database operations or GUI setup to aid in debugging.

___________________________________________________________________________________________________________________________________

class ProductEntryForm

### Attributes of the Code

1. **GUI Components**:
   - `JFrame`: Main window for entering product details.
   - `JPanel`, `JLabel`, `JTextField`: Components for labeling and inputting product details.
   - `JButton`: Button for adding product details to the database.

2. **Layout**:
   - `GridBagLayout`: Used for arranging components in a grid with flexible constraints.

3. **Database Interaction**:
   - `Connection`, `PreparedStatement`, `Statement`, `ResultSet`: Establishes a connection to a database and performs SQL operations (inserting product details).

4. **Event Handling**:
   - `ActionListener`: Listens for button clicks to retrieve input data, generate product IDs, and insert data into the database table (`product_details`).

### Code Flow Explanation

The `ProductEntryForm` class sets up a graphical user interface for entering new product details into a database. Here's an overview of its functionality:

1. **Initialize GUI Components**:
   - Creates a `JFrame` titled "Product Entry" with a `GridBagLayout`.
   - Sets up labels (`JLabel`) and text fields (`JTextField`) for entering product name, quantity, and price per unit.
   - Adds an "ADD" button (`JButton`) to submit the entered details.

2. **Event Handling (ADD Button)**:
   - Implements an `ActionListener` for the "ADD" button to handle button clicks.
   - Retrieves input data from the text fields (`nameField`, `quantityField`, `priceField`).
   - Generates a unique product ID (`pid`) by querying the current count of products in the database.
   - Inserts the entered product details into the `product_details` table using a `PreparedStatement`.
   - Disposes of the frame (`frame.dispose()`) after successfully adding the product to the database.

3. **Exception Handling**:
   - Catches and prints any exceptions that occur during database operations or GUI setup to aid in debugging.
___________________________________________________________________________________________________________________________________

MySQL



#### Concept and Modules Overview

This database named `zoho` manages various aspects of a business operation, including billing, customer details, product management, and time tracking. Below is a breakdown of each table and its attributes:

---

#### Table: `bill_details`

**Attributes:**
- `Invoice_number` (int): Primary key, uniquely identifies each invoice.
- `Item` (varchar(150)) : Name of the item(s) in the invoice.
- `Quantity` (varchar(50)): Quantity of each item in the invoice.
- `Cid` (int): Foreign key referencing `cid` in `customer_details`.
- `Price` (float): Price of each item.
- `Payment_Status` (char(1)): Payment status ('P' for paid, 'U' for unpaid).

---

#### Table: `billing`

**Attributes:**
- `pid` (int): Primary key, uniquely identifies each entry.
- `quantity_purchased` (float): Quantity of products purchased.

---

#### Table: `customer_details`

**Attributes:**
- `cid` (int): Primary key, uniquely identifies each customer.
- `name` (char(20)): Name of the customer.
- `phone_no` (char(20)): Phone number of the customer.

---

#### Table: `product_details`

**Attributes:**
- `pid` (int): Primary key, uniquely identifies each product.
- `product_name` (varchar(20)): Name of the product.
- `quantity_sold` (float): Total quantity sold of the product.
- `price_per_unit` (float): Price per unit of the product.
- `stock_available` (float): Quantity of the product available in stock.

---

#### Table: `time`

**Attributes:**
- `inv` (int): Foreign key referencing `Invoice_number` in `bill_details`.
- `time` (varchar(30)): Time associated with the invoice.

---

### Explanation

Each table in the `zoho` database serves a specific purpose:

- **`bill_details`**: Stores detailed information about invoices, including items purchased, quantities, prices, customer ID, and payment status.
  
- **`billing`**: Tracks the quantity of products purchased, linked to product IDs (`pid`).
  
- **`customer_details`**: Holds customer information such as name and phone number, uniquely identified by `cid`.
  
- **`product_details`**: Manages product data including names, quantities sold, prices, and available stock levels, uniquely identified by `pid`.
  
- **`time`**: Records time-related data associated with invoices, linked to `Invoice_number` in `bill_details`.

### Conclusion

This database structure supports efficient management of billing, customer relations, and inventory control. Each table's attributes are designed to facilitate accurate data storage and retrieval, ensuring operational transparency and reliability.
___________________________________________________________________________________________________________________________________
