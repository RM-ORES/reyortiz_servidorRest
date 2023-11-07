package config;

public class Utilities {
    public static final String WRONG_CREDENTIALS = "Wrong credentials";

    private Utilities(){}
    public static final String PROPERTIESTXT = "configFiles/config.properties";
    public static final String PROPERTIESXML = "configFiles/properties.xml";
    //screens
    public static final String WELCOME = "Welcome, ";
    //filter combo
    public static final String CUSTOMER = "Customer";
    public static final String DATE = "Date";

    //messages
    public static final String ADDUSER = "Customer added";
    public static final String DELETEUSER = "Customer deleted";
    public static final String UPDATEUSER = "Customer updated";
    public static final String ADDORDER = "Order added";
    public static final String DELETEORDER = "Order deleted";
    public static final String UPDATEORDER = "Order updated";
    public static final String ADDORDERMISSING = "Date, customer, table and menu items must be filled out to add a new order";
    public static final String ADDITEMMISSING = "Menu item and quantity must be filled out to add a new order";
    public static final String ADDCUSTOMERMISSING = "All information must be filled out";
    public static final String REMOVEITEMMISSING = "Pick an item from the table to remove";
    public static final String SELECTCUST = "You must select a customer in order to delete";
    public static final String SELECTORDER = "You must select an order to delete";

    public static final String CONFDELETE = "This will delete the customer's orders, do you wish to continue?";
    public static final String CONFDELETEORDERS = "This will delete all the order's items, do you wish to continue?";

    public static final String CANCEL = "Operation cancelled";

    //ERROR
    public static final String LOGINERROR = "Wrong credentials";
    public static final String EMPTY = "Failure to read file";
    public static final String MENUFAILURE = "Failure to load menu";

    public static final String GETERROR = "Failure to retrieve object";
    public static final String ADDERROR = "Failure to add object";
    public static final String UPDATERROR = "Failure to update object";
    public static final String UPDATECUSTERROR = "Failure to update customer";
    public static final String UPDATECREDERROR = "Failure to update credentials";
    public static final String DELETERROR = "Failure to delete object";
    public static final String WRITERROR = "Failure to write file";
    public static final String CUSTERROR = "Customer not found";
    public static final String ORDERERROR = "Order not found";

    //FILES
    public static final String ORDERKEY = "ordersPathFile";
    public static final String CUSTOMERKEY = "customerPathFile";
    public static final String ORDERXMLKEY = "xmlOrdersPath";
    public static final String FXML_PRINCIPAL_FXML = "/fxml/principal.fxml";
}
