package dao.common;

public class SqlQueries {

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
}
