package dao.common;

public class SqlQueries {

    public static final String IDCREDENTIALS = "idcredentials";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CUSTOMER = "customer";
    public static final String CREDENTIALS = "credentials";
    public static final String INTEGRITY_CONSTRAINT_VIOLATION = "IntegrityConstraintViolation";

    private SqlQueries(){}



    //CUSTOMER
    public static final String TABLEGETALL = "SELECT * FROM table_restaurant";
    public static final String TABLEGET = "SELECT * FROM table_restaurant WHERE table_number = ?";
    public static final String TABLEUPDATE = "update table_restaurant set number_seats = ? where table_number = ?";
    public static final String TABLEADD = "INSERT INTO table_restaurant (number_seats) VALUES (?)";
    public static final String TABLEDELETE = "delete from table_restaurant where table_number = ?";




    //ORDER

    public static final String ORDERGETALL = "SELECT * FROM restaurant_order";
    public static final String ORDERGET = "SELECT * FROM restaurant_order WHERE idorder = ?";
    public static final String ORDERGETBYTABLE = "SELECT * FROM restaurant_order WHERE table_number = ?";
    public static final String ORDERUPDATE = "update restaurant_order set table_number = ?, idcustomer = ? where idorder = ?";
    public static final String ORDERADD = "INSERT INTO restaurant_order (table_number, idcustomer, time_stamp) VALUES (?,?,?)";
    public static final String ORDERDELETE = "delete from restaurant_order where idorder = ?";
    public static final String ORDERDELETEBYTABLE = "delete from restaurant_order where table_number = ?";

    //ORDERITEM
    public static final String ORDERITEMDELETEBYORDER = "delete from order_item where idorder = ?";

    //Connection pool
    public static final String URL = "sqlUrl";
    public static final String USER = "sqlUser";
    public static final String SQL_PASSWORD = "sqlPassword";

    public static final String URLCASA = "sqlUrlCasa";
    public static final String USERCASA = "sqlUserCasa";
    public static final String SQL_PASSWORD_CASA = "sqlPasswordCasa";
    public static final String DRIVER = "driver";
    public static final String HIKARICACHEPREP = "cachePrepStmts";
    public static final String HIKARICACHESIZE= "prepStmtCacheSize";
    public static final String HIKARICACHELIMIT = "prepStmtCacheSqlLimit";

    //TABLE NAMES
    public static final String IDORDER = "idorder";
    public static final String TABLE_NUMBER = "table_number";
    public static final String IDCUSTOMER = "idcustomer";
    public static final String TIME_STAMP = "time_stamp";
    public static final String NUMBER_SEATS = "number_seats";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String IDORDER_ITEM = "idorder_item";
    public static final String IDMENU_ITEM = "idmenu_item";
    public static final String QUANTITY = "quantity";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
}
